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
import model.entities.Dicipline;
import model.entities.Typedocument;
import model.entities.Utilisateur;
import model.entities.Document;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.entities.Emprunt;

/**
 *
 * @author Assime
 */
public class DocumentJpaController implements Serializable {

    public DocumentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Document document) throws PreexistingEntityException, Exception {
        if (document.getDocumentCollection() == null) {
            document.setDocumentCollection(new ArrayList<Document>());
        }
        if (document.getDocumentCollection1() == null) {
            document.setDocumentCollection1(new ArrayList<Document>());
        }
        if (document.getEmpruntCollection() == null) {
            document.setEmpruntCollection(new ArrayList<Emprunt>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dicipline fkNumdicipline = document.getFkNumdicipline();
            if (fkNumdicipline != null) {
                fkNumdicipline = em.getReference(fkNumdicipline.getClass(), fkNumdicipline.getNumdicipline());
                document.setFkNumdicipline(fkNumdicipline);
            }
            Typedocument fkTypedocument = document.getFkTypedocument();
            if (fkTypedocument != null) {
                fkTypedocument = em.getReference(fkTypedocument.getClass(), fkTypedocument.getNumtypedocument());
                document.setFkTypedocument(fkTypedocument);
            }
            Utilisateur fkUtilisateur = document.getFkUtilisateur();
            if (fkUtilisateur != null) {
                fkUtilisateur = em.getReference(fkUtilisateur.getClass(), fkUtilisateur.getNumutilisateur());
                document.setFkUtilisateur(fkUtilisateur);
            }
            Collection<Document> attachedDocumentCollection = new ArrayList<Document>();
            for (Document documentCollectionDocumentToAttach : document.getDocumentCollection()) {
                documentCollectionDocumentToAttach = em.getReference(documentCollectionDocumentToAttach.getClass(), documentCollectionDocumentToAttach.getNumdocument());
                attachedDocumentCollection.add(documentCollectionDocumentToAttach);
            }
            document.setDocumentCollection(attachedDocumentCollection);
            Collection<Document> attachedDocumentCollection1 = new ArrayList<Document>();
            for (Document documentCollection1DocumentToAttach : document.getDocumentCollection1()) {
                documentCollection1DocumentToAttach = em.getReference(documentCollection1DocumentToAttach.getClass(), documentCollection1DocumentToAttach.getNumdocument());
                attachedDocumentCollection1.add(documentCollection1DocumentToAttach);
            }
            document.setDocumentCollection1(attachedDocumentCollection1);
            Collection<Emprunt> attachedEmpruntCollection = new ArrayList<Emprunt>();
            for (Emprunt empruntCollectionEmpruntToAttach : document.getEmpruntCollection()) {
                empruntCollectionEmpruntToAttach = em.getReference(empruntCollectionEmpruntToAttach.getClass(), empruntCollectionEmpruntToAttach.getNumemprunt());
                attachedEmpruntCollection.add(empruntCollectionEmpruntToAttach);
            }
            document.setEmpruntCollection(attachedEmpruntCollection);
            em.persist(document);
            if (fkNumdicipline != null) {
                fkNumdicipline.getDocumentCollection().add(document);
                fkNumdicipline = em.merge(fkNumdicipline);
            }
            if (fkTypedocument != null) {
                fkTypedocument.getDocumentCollection().add(document);
                fkTypedocument = em.merge(fkTypedocument);
            }
            if (fkUtilisateur != null) {
                fkUtilisateur.getDocumentCollection().add(document);
                fkUtilisateur = em.merge(fkUtilisateur);
            }
            for (Document documentCollectionDocument : document.getDocumentCollection()) {
                documentCollectionDocument.getDocumentCollection().add(document);
                documentCollectionDocument = em.merge(documentCollectionDocument);
            }
            for (Document documentCollection1Document : document.getDocumentCollection1()) {
                documentCollection1Document.getDocumentCollection().add(document);
                documentCollection1Document = em.merge(documentCollection1Document);
            }
            for (Emprunt empruntCollectionEmprunt : document.getEmpruntCollection()) {
                Document oldFkDocumentOfEmpruntCollectionEmprunt = empruntCollectionEmprunt.getFkDocument();
                empruntCollectionEmprunt.setFkDocument(document);
                empruntCollectionEmprunt = em.merge(empruntCollectionEmprunt);
                if (oldFkDocumentOfEmpruntCollectionEmprunt != null) {
                    oldFkDocumentOfEmpruntCollectionEmprunt.getEmpruntCollection().remove(empruntCollectionEmprunt);
                    oldFkDocumentOfEmpruntCollectionEmprunt = em.merge(oldFkDocumentOfEmpruntCollectionEmprunt);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDocument(document.getNumdocument()) != null) {
                throw new PreexistingEntityException("Document " + document + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Document document) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Document persistentDocument = em.find(Document.class, document.getNumdocument());
            Dicipline fkNumdiciplineOld = persistentDocument.getFkNumdicipline();
            Dicipline fkNumdiciplineNew = document.getFkNumdicipline();
            Typedocument fkTypedocumentOld = persistentDocument.getFkTypedocument();
            Typedocument fkTypedocumentNew = document.getFkTypedocument();
            Utilisateur fkUtilisateurOld = persistentDocument.getFkUtilisateur();
            Utilisateur fkUtilisateurNew = document.getFkUtilisateur();
            Collection<Document> documentCollectionOld = persistentDocument.getDocumentCollection();
            Collection<Document> documentCollectionNew = document.getDocumentCollection();
            Collection<Document> documentCollection1Old = persistentDocument.getDocumentCollection1();
            Collection<Document> documentCollection1New = document.getDocumentCollection1();
            Collection<Emprunt> empruntCollectionOld = persistentDocument.getEmpruntCollection();
            Collection<Emprunt> empruntCollectionNew = document.getEmpruntCollection();
            if (fkNumdiciplineNew != null) {
                fkNumdiciplineNew = em.getReference(fkNumdiciplineNew.getClass(), fkNumdiciplineNew.getNumdicipline());
                document.setFkNumdicipline(fkNumdiciplineNew);
            }
            if (fkTypedocumentNew != null) {
                fkTypedocumentNew = em.getReference(fkTypedocumentNew.getClass(), fkTypedocumentNew.getNumtypedocument());
                document.setFkTypedocument(fkTypedocumentNew);
            }
            if (fkUtilisateurNew != null) {
                fkUtilisateurNew = em.getReference(fkUtilisateurNew.getClass(), fkUtilisateurNew.getNumutilisateur());
                document.setFkUtilisateur(fkUtilisateurNew);
            }
            Collection<Document> attachedDocumentCollectionNew = new ArrayList<Document>();
            for (Document documentCollectionNewDocumentToAttach : documentCollectionNew) {
                documentCollectionNewDocumentToAttach = em.getReference(documentCollectionNewDocumentToAttach.getClass(), documentCollectionNewDocumentToAttach.getNumdocument());
                attachedDocumentCollectionNew.add(documentCollectionNewDocumentToAttach);
            }
            documentCollectionNew = attachedDocumentCollectionNew;
            document.setDocumentCollection(documentCollectionNew);
            Collection<Document> attachedDocumentCollection1New = new ArrayList<Document>();
            for (Document documentCollection1NewDocumentToAttach : documentCollection1New) {
                documentCollection1NewDocumentToAttach = em.getReference(documentCollection1NewDocumentToAttach.getClass(), documentCollection1NewDocumentToAttach.getNumdocument());
                attachedDocumentCollection1New.add(documentCollection1NewDocumentToAttach);
            }
            documentCollection1New = attachedDocumentCollection1New;
            document.setDocumentCollection1(documentCollection1New);
            Collection<Emprunt> attachedEmpruntCollectionNew = new ArrayList<Emprunt>();
            for (Emprunt empruntCollectionNewEmpruntToAttach : empruntCollectionNew) {
                empruntCollectionNewEmpruntToAttach = em.getReference(empruntCollectionNewEmpruntToAttach.getClass(), empruntCollectionNewEmpruntToAttach.getNumemprunt());
                attachedEmpruntCollectionNew.add(empruntCollectionNewEmpruntToAttach);
            }
            empruntCollectionNew = attachedEmpruntCollectionNew;
            document.setEmpruntCollection(empruntCollectionNew);
            document = em.merge(document);
            if (fkNumdiciplineOld != null && !fkNumdiciplineOld.equals(fkNumdiciplineNew)) {
                fkNumdiciplineOld.getDocumentCollection().remove(document);
                fkNumdiciplineOld = em.merge(fkNumdiciplineOld);
            }
            if (fkNumdiciplineNew != null && !fkNumdiciplineNew.equals(fkNumdiciplineOld)) {
                fkNumdiciplineNew.getDocumentCollection().add(document);
                fkNumdiciplineNew = em.merge(fkNumdiciplineNew);
            }
            if (fkTypedocumentOld != null && !fkTypedocumentOld.equals(fkTypedocumentNew)) {
                fkTypedocumentOld.getDocumentCollection().remove(document);
                fkTypedocumentOld = em.merge(fkTypedocumentOld);
            }
            if (fkTypedocumentNew != null && !fkTypedocumentNew.equals(fkTypedocumentOld)) {
                fkTypedocumentNew.getDocumentCollection().add(document);
                fkTypedocumentNew = em.merge(fkTypedocumentNew);
            }
            if (fkUtilisateurOld != null && !fkUtilisateurOld.equals(fkUtilisateurNew)) {
                fkUtilisateurOld.getDocumentCollection().remove(document);
                fkUtilisateurOld = em.merge(fkUtilisateurOld);
            }
            if (fkUtilisateurNew != null && !fkUtilisateurNew.equals(fkUtilisateurOld)) {
                fkUtilisateurNew.getDocumentCollection().add(document);
                fkUtilisateurNew = em.merge(fkUtilisateurNew);
            }
            for (Document documentCollectionOldDocument : documentCollectionOld) {
                if (!documentCollectionNew.contains(documentCollectionOldDocument)) {
                    documentCollectionOldDocument.getDocumentCollection().remove(document);
                    documentCollectionOldDocument = em.merge(documentCollectionOldDocument);
                }
            }
            for (Document documentCollectionNewDocument : documentCollectionNew) {
                if (!documentCollectionOld.contains(documentCollectionNewDocument)) {
                    documentCollectionNewDocument.getDocumentCollection().add(document);
                    documentCollectionNewDocument = em.merge(documentCollectionNewDocument);
                }
            }
            for (Document documentCollection1OldDocument : documentCollection1Old) {
                if (!documentCollection1New.contains(documentCollection1OldDocument)) {
                    documentCollection1OldDocument.getDocumentCollection().remove(document);
                    documentCollection1OldDocument = em.merge(documentCollection1OldDocument);
                }
            }
            for (Document documentCollection1NewDocument : documentCollection1New) {
                if (!documentCollection1Old.contains(documentCollection1NewDocument)) {
                    documentCollection1NewDocument.getDocumentCollection().add(document);
                    documentCollection1NewDocument = em.merge(documentCollection1NewDocument);
                }
            }
            for (Emprunt empruntCollectionOldEmprunt : empruntCollectionOld) {
                if (!empruntCollectionNew.contains(empruntCollectionOldEmprunt)) {
                    empruntCollectionOldEmprunt.setFkDocument(null);
                    empruntCollectionOldEmprunt = em.merge(empruntCollectionOldEmprunt);
                }
            }
            for (Emprunt empruntCollectionNewEmprunt : empruntCollectionNew) {
                if (!empruntCollectionOld.contains(empruntCollectionNewEmprunt)) {
                    Document oldFkDocumentOfEmpruntCollectionNewEmprunt = empruntCollectionNewEmprunt.getFkDocument();
                    empruntCollectionNewEmprunt.setFkDocument(document);
                    empruntCollectionNewEmprunt = em.merge(empruntCollectionNewEmprunt);
                    if (oldFkDocumentOfEmpruntCollectionNewEmprunt != null && !oldFkDocumentOfEmpruntCollectionNewEmprunt.equals(document)) {
                        oldFkDocumentOfEmpruntCollectionNewEmprunt.getEmpruntCollection().remove(empruntCollectionNewEmprunt);
                        oldFkDocumentOfEmpruntCollectionNewEmprunt = em.merge(oldFkDocumentOfEmpruntCollectionNewEmprunt);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = document.getNumdocument();
                if (findDocument(id) == null) {
                    throw new NonexistentEntityException("The document with id " + id + " no longer exists.");
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
            Document document;
            try {
                document = em.getReference(Document.class, id);
                document.getNumdocument();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The document with id " + id + " no longer exists.", enfe);
            }
            Dicipline fkNumdicipline = document.getFkNumdicipline();
            if (fkNumdicipline != null) {
                fkNumdicipline.getDocumentCollection().remove(document);
                fkNumdicipline = em.merge(fkNumdicipline);
            }
            Typedocument fkTypedocument = document.getFkTypedocument();
            if (fkTypedocument != null) {
                fkTypedocument.getDocumentCollection().remove(document);
                fkTypedocument = em.merge(fkTypedocument);
            }
            Utilisateur fkUtilisateur = document.getFkUtilisateur();
            if (fkUtilisateur != null) {
                fkUtilisateur.getDocumentCollection().remove(document);
                fkUtilisateur = em.merge(fkUtilisateur);
            }
            Collection<Document> documentCollection = document.getDocumentCollection();
            for (Document documentCollectionDocument : documentCollection) {
                documentCollectionDocument.getDocumentCollection().remove(document);
                documentCollectionDocument = em.merge(documentCollectionDocument);
            }
            Collection<Document> documentCollection1 = document.getDocumentCollection1();
            for (Document documentCollection1Document : documentCollection1) {
                documentCollection1Document.getDocumentCollection().remove(document);
                documentCollection1Document = em.merge(documentCollection1Document);
            }
            Collection<Emprunt> empruntCollection = document.getEmpruntCollection();
            for (Emprunt empruntCollectionEmprunt : empruntCollection) {
                empruntCollectionEmprunt.setFkDocument(null);
                empruntCollectionEmprunt = em.merge(empruntCollectionEmprunt);
            }
            em.remove(document);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Document> findDocumentEntities() {
        return findDocumentEntities(true, -1, -1);
    }

    public List<Document> findDocumentEntities(int maxResults, int firstResult) {
        return findDocumentEntities(false, maxResults, firstResult);
    }

    private List<Document> findDocumentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Document.class));
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

    public Document findDocument(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Document.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocumentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Document> rt = cq.from(Document.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
