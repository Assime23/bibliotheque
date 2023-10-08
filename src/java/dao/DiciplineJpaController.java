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
import model.entities.Domaine;
import model.entities.Document;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.entities.Dicipline;

/**
 *
 * @author Assime
 */
public class DiciplineJpaController implements Serializable {

    public DiciplineJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dicipline dicipline) throws PreexistingEntityException, Exception {
        if (dicipline.getDocumentCollection() == null) {
            dicipline.setDocumentCollection(new ArrayList<Document>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Domaine fkDomaine = dicipline.getFkDomaine();
            if (fkDomaine != null) {
                fkDomaine = em.getReference(fkDomaine.getClass(), fkDomaine.getNumdomaine());
                dicipline.setFkDomaine(fkDomaine);
            }
            Collection<Document> attachedDocumentCollection = new ArrayList<Document>();
            for (Document documentCollectionDocumentToAttach : dicipline.getDocumentCollection()) {
                documentCollectionDocumentToAttach = em.getReference(documentCollectionDocumentToAttach.getClass(), documentCollectionDocumentToAttach.getNumdocument());
                attachedDocumentCollection.add(documentCollectionDocumentToAttach);
            }
            dicipline.setDocumentCollection(attachedDocumentCollection);
            em.persist(dicipline);
            if (fkDomaine != null) {
                fkDomaine.getDiciplineCollection().add(dicipline);
                fkDomaine = em.merge(fkDomaine);
            }
            for (Document documentCollectionDocument : dicipline.getDocumentCollection()) {
                Dicipline oldFkNumdiciplineOfDocumentCollectionDocument = documentCollectionDocument.getFkNumdicipline();
                documentCollectionDocument.setFkNumdicipline(dicipline);
                documentCollectionDocument = em.merge(documentCollectionDocument);
                if (oldFkNumdiciplineOfDocumentCollectionDocument != null) {
                    oldFkNumdiciplineOfDocumentCollectionDocument.getDocumentCollection().remove(documentCollectionDocument);
                    oldFkNumdiciplineOfDocumentCollectionDocument = em.merge(oldFkNumdiciplineOfDocumentCollectionDocument);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDicipline(dicipline.getNumdicipline()) != null) {
                throw new PreexistingEntityException("Dicipline " + dicipline + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dicipline dicipline) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dicipline persistentDicipline = em.find(Dicipline.class, dicipline.getNumdicipline());
            Domaine fkDomaineOld = persistentDicipline.getFkDomaine();
            Domaine fkDomaineNew = dicipline.getFkDomaine();
            Collection<Document> documentCollectionOld = persistentDicipline.getDocumentCollection();
            Collection<Document> documentCollectionNew = dicipline.getDocumentCollection();
            if (fkDomaineNew != null) {
                fkDomaineNew = em.getReference(fkDomaineNew.getClass(), fkDomaineNew.getNumdomaine());
                dicipline.setFkDomaine(fkDomaineNew);
            }
            Collection<Document> attachedDocumentCollectionNew = new ArrayList<Document>();
            for (Document documentCollectionNewDocumentToAttach : documentCollectionNew) {
                documentCollectionNewDocumentToAttach = em.getReference(documentCollectionNewDocumentToAttach.getClass(), documentCollectionNewDocumentToAttach.getNumdocument());
                attachedDocumentCollectionNew.add(documentCollectionNewDocumentToAttach);
            }
            documentCollectionNew = attachedDocumentCollectionNew;
            dicipline.setDocumentCollection(documentCollectionNew);
            dicipline = em.merge(dicipline);
            if (fkDomaineOld != null && !fkDomaineOld.equals(fkDomaineNew)) {
                fkDomaineOld.getDiciplineCollection().remove(dicipline);
                fkDomaineOld = em.merge(fkDomaineOld);
            }
            if (fkDomaineNew != null && !fkDomaineNew.equals(fkDomaineOld)) {
                fkDomaineNew.getDiciplineCollection().add(dicipline);
                fkDomaineNew = em.merge(fkDomaineNew);
            }
            for (Document documentCollectionOldDocument : documentCollectionOld) {
                if (!documentCollectionNew.contains(documentCollectionOldDocument)) {
                    documentCollectionOldDocument.setFkNumdicipline(null);
                    documentCollectionOldDocument = em.merge(documentCollectionOldDocument);
                }
            }
            for (Document documentCollectionNewDocument : documentCollectionNew) {
                if (!documentCollectionOld.contains(documentCollectionNewDocument)) {
                    Dicipline oldFkNumdiciplineOfDocumentCollectionNewDocument = documentCollectionNewDocument.getFkNumdicipline();
                    documentCollectionNewDocument.setFkNumdicipline(dicipline);
                    documentCollectionNewDocument = em.merge(documentCollectionNewDocument);
                    if (oldFkNumdiciplineOfDocumentCollectionNewDocument != null && !oldFkNumdiciplineOfDocumentCollectionNewDocument.equals(dicipline)) {
                        oldFkNumdiciplineOfDocumentCollectionNewDocument.getDocumentCollection().remove(documentCollectionNewDocument);
                        oldFkNumdiciplineOfDocumentCollectionNewDocument = em.merge(oldFkNumdiciplineOfDocumentCollectionNewDocument);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = dicipline.getNumdicipline();
                if (findDicipline(id) == null) {
                    throw new NonexistentEntityException("The dicipline with id " + id + " no longer exists.");
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
            Dicipline dicipline;
            try {
                dicipline = em.getReference(Dicipline.class, id);
                dicipline.getNumdicipline();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dicipline with id " + id + " no longer exists.", enfe);
            }
            Domaine fkDomaine = dicipline.getFkDomaine();
            if (fkDomaine != null) {
                fkDomaine.getDiciplineCollection().remove(dicipline);
                fkDomaine = em.merge(fkDomaine);
            }
            Collection<Document> documentCollection = dicipline.getDocumentCollection();
            for (Document documentCollectionDocument : documentCollection) {
                documentCollectionDocument.setFkNumdicipline(null);
                documentCollectionDocument = em.merge(documentCollectionDocument);
            }
            em.remove(dicipline);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dicipline> findDiciplineEntities() {
        return findDiciplineEntities(true, -1, -1);
    }

    public List<Dicipline> findDiciplineEntities(int maxResults, int firstResult) {
        return findDiciplineEntities(false, maxResults, firstResult);
    }

    private List<Dicipline> findDiciplineEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dicipline.class));
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

    public Dicipline findDicipline(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dicipline.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiciplineCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dicipline> rt = cq.from(Dicipline.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
