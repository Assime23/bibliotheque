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
import model.entities.Filliere;
import model.entities.Emprunt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.entities.Document;
import model.entities.Utilisateur;

/**
 *
 * @author Assime
 */
public class UtilisateurJpaController implements Serializable {

    public UtilisateurJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Utilisateur utilisateur) throws PreexistingEntityException, Exception {
        if (utilisateur.getEmpruntCollection() == null) {
            utilisateur.setEmpruntCollection(new ArrayList<Emprunt>());
        }
        if (utilisateur.getDocumentCollection() == null) {
            utilisateur.setDocumentCollection(new ArrayList<Document>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Filliere fkNumfilliere = utilisateur.getFkNumfilliere();
            if (fkNumfilliere != null) {
                fkNumfilliere = em.getReference(fkNumfilliere.getClass(), fkNumfilliere.getNumfilliere());
                utilisateur.setFkNumfilliere(fkNumfilliere);
            }
            Collection<Emprunt> attachedEmpruntCollection = new ArrayList<Emprunt>();
            for (Emprunt empruntCollectionEmpruntToAttach : utilisateur.getEmpruntCollection()) {
                empruntCollectionEmpruntToAttach = em.getReference(empruntCollectionEmpruntToAttach.getClass(), empruntCollectionEmpruntToAttach.getNumemprunt());
                attachedEmpruntCollection.add(empruntCollectionEmpruntToAttach);
            }
            utilisateur.setEmpruntCollection(attachedEmpruntCollection);
            Collection<Document> attachedDocumentCollection = new ArrayList<Document>();
            for (Document documentCollectionDocumentToAttach : utilisateur.getDocumentCollection()) {
                documentCollectionDocumentToAttach = em.getReference(documentCollectionDocumentToAttach.getClass(), documentCollectionDocumentToAttach.getNumdocument());
                attachedDocumentCollection.add(documentCollectionDocumentToAttach);
            }
            utilisateur.setDocumentCollection(attachedDocumentCollection);
            em.persist(utilisateur);
            if (fkNumfilliere != null) {
                fkNumfilliere.getUtilisateurCollection().add(utilisateur);
                fkNumfilliere = em.merge(fkNumfilliere);
            }
            for (Emprunt empruntCollectionEmprunt : utilisateur.getEmpruntCollection()) {
                Utilisateur oldFkUtilisateurOfEmpruntCollectionEmprunt = empruntCollectionEmprunt.getFkUtilisateur();
                empruntCollectionEmprunt.setFkUtilisateur(utilisateur);
                empruntCollectionEmprunt = em.merge(empruntCollectionEmprunt);
                if (oldFkUtilisateurOfEmpruntCollectionEmprunt != null) {
                    oldFkUtilisateurOfEmpruntCollectionEmprunt.getEmpruntCollection().remove(empruntCollectionEmprunt);
                    oldFkUtilisateurOfEmpruntCollectionEmprunt = em.merge(oldFkUtilisateurOfEmpruntCollectionEmprunt);
                }
            }
            for (Document documentCollectionDocument : utilisateur.getDocumentCollection()) {
                Utilisateur oldFkUtilisateurOfDocumentCollectionDocument = documentCollectionDocument.getFkUtilisateur();
                documentCollectionDocument.setFkUtilisateur(utilisateur);
                documentCollectionDocument = em.merge(documentCollectionDocument);
                if (oldFkUtilisateurOfDocumentCollectionDocument != null) {
                    oldFkUtilisateurOfDocumentCollectionDocument.getDocumentCollection().remove(documentCollectionDocument);
                    oldFkUtilisateurOfDocumentCollectionDocument = em.merge(oldFkUtilisateurOfDocumentCollectionDocument);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUtilisateur(utilisateur.getNumutilisateur()) != null) {
                throw new PreexistingEntityException("Utilisateur " + utilisateur + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Utilisateur utilisateur) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Utilisateur persistentUtilisateur = em.find(Utilisateur.class, utilisateur.getNumutilisateur());
            Filliere fkNumfilliereOld = persistentUtilisateur.getFkNumfilliere();
            Filliere fkNumfilliereNew = utilisateur.getFkNumfilliere();
            Collection<Emprunt> empruntCollectionOld = persistentUtilisateur.getEmpruntCollection();
            Collection<Emprunt> empruntCollectionNew = utilisateur.getEmpruntCollection();
            Collection<Document> documentCollectionOld = persistentUtilisateur.getDocumentCollection();
            Collection<Document> documentCollectionNew = utilisateur.getDocumentCollection();
            if (fkNumfilliereNew != null) {
                fkNumfilliereNew = em.getReference(fkNumfilliereNew.getClass(), fkNumfilliereNew.getNumfilliere());
                utilisateur.setFkNumfilliere(fkNumfilliereNew);
            }
            Collection<Emprunt> attachedEmpruntCollectionNew = new ArrayList<Emprunt>();
            for (Emprunt empruntCollectionNewEmpruntToAttach : empruntCollectionNew) {
                empruntCollectionNewEmpruntToAttach = em.getReference(empruntCollectionNewEmpruntToAttach.getClass(), empruntCollectionNewEmpruntToAttach.getNumemprunt());
                attachedEmpruntCollectionNew.add(empruntCollectionNewEmpruntToAttach);
            }
            empruntCollectionNew = attachedEmpruntCollectionNew;
            utilisateur.setEmpruntCollection(empruntCollectionNew);
            Collection<Document> attachedDocumentCollectionNew = new ArrayList<Document>();
            for (Document documentCollectionNewDocumentToAttach : documentCollectionNew) {
                documentCollectionNewDocumentToAttach = em.getReference(documentCollectionNewDocumentToAttach.getClass(), documentCollectionNewDocumentToAttach.getNumdocument());
                attachedDocumentCollectionNew.add(documentCollectionNewDocumentToAttach);
            }
            documentCollectionNew = attachedDocumentCollectionNew;
            utilisateur.setDocumentCollection(documentCollectionNew);
            utilisateur = em.merge(utilisateur);
            if (fkNumfilliereOld != null && !fkNumfilliereOld.equals(fkNumfilliereNew)) {
                fkNumfilliereOld.getUtilisateurCollection().remove(utilisateur);
                fkNumfilliereOld = em.merge(fkNumfilliereOld);
            }
            if (fkNumfilliereNew != null && !fkNumfilliereNew.equals(fkNumfilliereOld)) {
                fkNumfilliereNew.getUtilisateurCollection().add(utilisateur);
                fkNumfilliereNew = em.merge(fkNumfilliereNew);
            }
            for (Emprunt empruntCollectionOldEmprunt : empruntCollectionOld) {
                if (!empruntCollectionNew.contains(empruntCollectionOldEmprunt)) {
                    empruntCollectionOldEmprunt.setFkUtilisateur(null);
                    empruntCollectionOldEmprunt = em.merge(empruntCollectionOldEmprunt);
                }
            }
            for (Emprunt empruntCollectionNewEmprunt : empruntCollectionNew) {
                if (!empruntCollectionOld.contains(empruntCollectionNewEmprunt)) {
                    Utilisateur oldFkUtilisateurOfEmpruntCollectionNewEmprunt = empruntCollectionNewEmprunt.getFkUtilisateur();
                    empruntCollectionNewEmprunt.setFkUtilisateur(utilisateur);
                    empruntCollectionNewEmprunt = em.merge(empruntCollectionNewEmprunt);
                    if (oldFkUtilisateurOfEmpruntCollectionNewEmprunt != null && !oldFkUtilisateurOfEmpruntCollectionNewEmprunt.equals(utilisateur)) {
                        oldFkUtilisateurOfEmpruntCollectionNewEmprunt.getEmpruntCollection().remove(empruntCollectionNewEmprunt);
                        oldFkUtilisateurOfEmpruntCollectionNewEmprunt = em.merge(oldFkUtilisateurOfEmpruntCollectionNewEmprunt);
                    }
                }
            }
            for (Document documentCollectionOldDocument : documentCollectionOld) {
                if (!documentCollectionNew.contains(documentCollectionOldDocument)) {
                    documentCollectionOldDocument.setFkUtilisateur(null);
                    documentCollectionOldDocument = em.merge(documentCollectionOldDocument);
                }
            }
            for (Document documentCollectionNewDocument : documentCollectionNew) {
                if (!documentCollectionOld.contains(documentCollectionNewDocument)) {
                    Utilisateur oldFkUtilisateurOfDocumentCollectionNewDocument = documentCollectionNewDocument.getFkUtilisateur();
                    documentCollectionNewDocument.setFkUtilisateur(utilisateur);
                    documentCollectionNewDocument = em.merge(documentCollectionNewDocument);
                    if (oldFkUtilisateurOfDocumentCollectionNewDocument != null && !oldFkUtilisateurOfDocumentCollectionNewDocument.equals(utilisateur)) {
                        oldFkUtilisateurOfDocumentCollectionNewDocument.getDocumentCollection().remove(documentCollectionNewDocument);
                        oldFkUtilisateurOfDocumentCollectionNewDocument = em.merge(oldFkUtilisateurOfDocumentCollectionNewDocument);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = utilisateur.getNumutilisateur();
                if (findUtilisateur(id) == null) {
                    throw new NonexistentEntityException("The utilisateur with id " + id + " no longer exists.");
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
            Utilisateur utilisateur;
            try {
                utilisateur = em.getReference(Utilisateur.class, id);
                utilisateur.getNumutilisateur();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The utilisateur with id " + id + " no longer exists.", enfe);
            }
            Filliere fkNumfilliere = utilisateur.getFkNumfilliere();
            if (fkNumfilliere != null) {
                fkNumfilliere.getUtilisateurCollection().remove(utilisateur);
                fkNumfilliere = em.merge(fkNumfilliere);
            }
            Collection<Emprunt> empruntCollection = utilisateur.getEmpruntCollection();
            for (Emprunt empruntCollectionEmprunt : empruntCollection) {
                empruntCollectionEmprunt.setFkUtilisateur(null);
                empruntCollectionEmprunt = em.merge(empruntCollectionEmprunt);
            }
            Collection<Document> documentCollection = utilisateur.getDocumentCollection();
            for (Document documentCollectionDocument : documentCollection) {
                documentCollectionDocument.setFkUtilisateur(null);
                documentCollectionDocument = em.merge(documentCollectionDocument);
            }
            em.remove(utilisateur);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Utilisateur> findUtilisateurEntities() {
        return findUtilisateurEntities(true, -1, -1);
    }

    public List<Utilisateur> findUtilisateurEntities(int maxResults, int firstResult) {
        return findUtilisateurEntities(false, maxResults, firstResult);
    }

    private List<Utilisateur> findUtilisateurEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Utilisateur.class));
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

    public Utilisateur findUtilisateur(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Utilisateur.class, id);
        } finally {
            em.close();
        }
    }

    public int getUtilisateurCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Utilisateur> rt = cq.from(Utilisateur.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
   public Collection<Document> getUtilisateurDocuments(String userId) {
    EntityManager em = getEntityManager();
    try {
        Utilisateur utilisateur = findUtilisateur(userId);
        if (utilisateur != null) {
            Query query = em.createQuery("SELECT d FROM Document d WHERE d.fkUtilisateur = :user");
            query.setParameter("user", utilisateur);
            return query.getResultList();
        }
    } finally {
        em.close();
    }
    return new ArrayList<>(); // Retourner une liste vide si l'utilisateur n'est pas trouvé ou s'il n'a aucun document lié
}
 

    public void ajouterDocumentAUtilisateur(String userId, Document document) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Récupérer l'entité Utilisateur correspondante à partir de JPA
            Utilisateur utilisateur = em.find(Utilisateur.class, userId);

            if (utilisateur != null) {
                // Ajouter le document à la collection de documents de l'utilisateur
                utilisateur.getDocumentCollection().add(document);
                document.setFkUtilisateur(utilisateur); // Définir la relation côté Document

                // Mettre à jour l'entité Utilisateur pour prendre en compte les modifications
                em.merge(utilisateur);
                em.merge(document); // Sauvegarder l'objet Document avec la relation mise à jour
            } else {
                throw new NonexistentEntityException("Utilisateur with ID " + userId + " not found.");
            }

            em.getTransaction().commit();
        } catch (NonexistentEntityException ex) {
            throw ex; // Rethrow l'exception pour la traiter au niveau supérieur si nécessaire
        } catch (Exception ex) {
            // Traiter les autres exceptions ici ou les rethrow si besoin
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }



   

    
}
