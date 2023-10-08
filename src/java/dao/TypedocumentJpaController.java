/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entities.Document;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.entities.Typedocument;

/**
 *
 * @author Assime
 */
public class TypedocumentJpaController implements Serializable {

    public TypedocumentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Typedocument typedocument) throws PreexistingEntityException, Exception {
        if (typedocument.getDocumentCollection() == null) {
            typedocument.setDocumentCollection(new ArrayList<Document>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Document> attachedDocumentCollection = new ArrayList<Document>();
            for (Document documentCollectionDocumentToAttach : typedocument.getDocumentCollection()) {
                documentCollectionDocumentToAttach = em.getReference(documentCollectionDocumentToAttach.getClass(), documentCollectionDocumentToAttach.getNumdocument());
                attachedDocumentCollection.add(documentCollectionDocumentToAttach);
            }
            typedocument.setDocumentCollection(attachedDocumentCollection);
            em.persist(typedocument);
            for (Document documentCollectionDocument : typedocument.getDocumentCollection()) {
                Typedocument oldFkTypedocumentOfDocumentCollectionDocument = documentCollectionDocument.getFkTypedocument();
                documentCollectionDocument.setFkTypedocument(typedocument);
                documentCollectionDocument = em.merge(documentCollectionDocument);
                if (oldFkTypedocumentOfDocumentCollectionDocument != null) {
                    oldFkTypedocumentOfDocumentCollectionDocument.getDocumentCollection().remove(documentCollectionDocument);
                    oldFkTypedocumentOfDocumentCollectionDocument = em.merge(oldFkTypedocumentOfDocumentCollectionDocument);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTypedocument(typedocument.getNumtypedocument()) != null) {
                throw new PreexistingEntityException("Typedocument " + typedocument + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Typedocument typedocument) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Typedocument persistentTypedocument = em.find(Typedocument.class, typedocument.getNumtypedocument());
            Collection<Document> documentCollectionOld = persistentTypedocument.getDocumentCollection();
            Collection<Document> documentCollectionNew = typedocument.getDocumentCollection();
            Collection<Document> attachedDocumentCollectionNew = new ArrayList<Document>();
            for (Document documentCollectionNewDocumentToAttach : documentCollectionNew) {
                documentCollectionNewDocumentToAttach = em.getReference(documentCollectionNewDocumentToAttach.getClass(), documentCollectionNewDocumentToAttach.getNumdocument());
                attachedDocumentCollectionNew.add(documentCollectionNewDocumentToAttach);
            }
            documentCollectionNew = attachedDocumentCollectionNew;
            typedocument.setDocumentCollection(documentCollectionNew);
            typedocument = em.merge(typedocument);
            for (Document documentCollectionOldDocument : documentCollectionOld) {
                if (!documentCollectionNew.contains(documentCollectionOldDocument)) {
                    documentCollectionOldDocument.setFkTypedocument(null);
                    documentCollectionOldDocument = em.merge(documentCollectionOldDocument);
                }
            }
            for (Document documentCollectionNewDocument : documentCollectionNew) {
                if (!documentCollectionOld.contains(documentCollectionNewDocument)) {
                    Typedocument oldFkTypedocumentOfDocumentCollectionNewDocument = documentCollectionNewDocument.getFkTypedocument();
                    documentCollectionNewDocument.setFkTypedocument(typedocument);
                    documentCollectionNewDocument = em.merge(documentCollectionNewDocument);
                    if (oldFkTypedocumentOfDocumentCollectionNewDocument != null && !oldFkTypedocumentOfDocumentCollectionNewDocument.equals(typedocument)) {
                        oldFkTypedocumentOfDocumentCollectionNewDocument.getDocumentCollection().remove(documentCollectionNewDocument);
                        oldFkTypedocumentOfDocumentCollectionNewDocument = em.merge(oldFkTypedocumentOfDocumentCollectionNewDocument);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = typedocument.getNumtypedocument();
                if (findTypedocument(id) == null) {
                    throw new NonexistentEntityException("The typedocument with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Typedocument typedocument;
            try {
                typedocument = em.getReference(Typedocument.class, id);
                typedocument.getNumtypedocument();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The typedocument with id " + id + " no longer exists.", enfe);
            }
            Collection<Document> documentCollection = typedocument.getDocumentCollection();
            for (Document documentCollectionDocument : documentCollection) {
                documentCollectionDocument.setFkTypedocument(null);
                documentCollectionDocument = em.merge(documentCollectionDocument);
            }
            em.remove(typedocument);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Typedocument> findTypedocumentEntities() {
        return findTypedocumentEntities(true, -1, -1);
    }

    public List<Typedocument> findTypedocumentEntities(int maxResults, int firstResult) {
        return findTypedocumentEntities(false, maxResults, firstResult);
    }

    private List<Typedocument> findTypedocumentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Typedocument.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Typedocument findTypedocument(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Typedocument.class, id);
        } finally {
            em.close();
        }
    }

    public int getTypedocumentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Typedocument> rt = cq.from(Typedocument.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
