/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entities.Document;
import model.entities.Emprunt;
import model.entities.Utilisateur;

/**
 *
 * @author Assime
 */
public class EmpruntJpaController implements Serializable {

    public EmpruntJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Emprunt emprunt) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Document fkDocument = emprunt.getFkDocument();
            if (fkDocument != null) {
                fkDocument = em.getReference(fkDocument.getClass(), fkDocument.getNumdocument());
                emprunt.setFkDocument(fkDocument);
            }
            Utilisateur fkUtilisateur = emprunt.getFkUtilisateur();
            if (fkUtilisateur != null) {
                fkUtilisateur = em.getReference(fkUtilisateur.getClass(), fkUtilisateur.getNumutilisateur());
                emprunt.setFkUtilisateur(fkUtilisateur);
            }
            em.persist(emprunt);
            if (fkDocument != null) {
                fkDocument.getEmpruntCollection().add(emprunt);
                fkDocument = em.merge(fkDocument);
            }
            if (fkUtilisateur != null) {
                fkUtilisateur.getEmpruntCollection().add(emprunt);
                fkUtilisateur = em.merge(fkUtilisateur);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmprunt(emprunt.getNumemprunt()) != null) {
                throw new PreexistingEntityException("Emprunt " + emprunt + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Emprunt emprunt) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emprunt persistentEmprunt = em.find(Emprunt.class, emprunt.getNumemprunt());
            Document fkDocumentOld = persistentEmprunt.getFkDocument();
            Document fkDocumentNew = emprunt.getFkDocument();
            Utilisateur fkUtilisateurOld = persistentEmprunt.getFkUtilisateur();
            Utilisateur fkUtilisateurNew = emprunt.getFkUtilisateur();
            if (fkDocumentNew != null) {
                fkDocumentNew = em.getReference(fkDocumentNew.getClass(), fkDocumentNew.getNumdocument());
                emprunt.setFkDocument(fkDocumentNew);
            }
            if (fkUtilisateurNew != null) {
                fkUtilisateurNew = em.getReference(fkUtilisateurNew.getClass(), fkUtilisateurNew.getNumutilisateur());
                emprunt.setFkUtilisateur(fkUtilisateurNew);
            }
            emprunt = em.merge(emprunt);
            if (fkDocumentOld != null && !fkDocumentOld.equals(fkDocumentNew)) {
                fkDocumentOld.getEmpruntCollection().remove(emprunt);
                fkDocumentOld = em.merge(fkDocumentOld);
            }
            if (fkDocumentNew != null && !fkDocumentNew.equals(fkDocumentOld)) {
                fkDocumentNew.getEmpruntCollection().add(emprunt);
                fkDocumentNew = em.merge(fkDocumentNew);
            }
            if (fkUtilisateurOld != null && !fkUtilisateurOld.equals(fkUtilisateurNew)) {
                fkUtilisateurOld.getEmpruntCollection().remove(emprunt);
                fkUtilisateurOld = em.merge(fkUtilisateurOld);
            }
            if (fkUtilisateurNew != null && !fkUtilisateurNew.equals(fkUtilisateurOld)) {
                fkUtilisateurNew.getEmpruntCollection().add(emprunt);
                fkUtilisateurNew = em.merge(fkUtilisateurNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = emprunt.getNumemprunt();
                if (findEmprunt(id) == null) {
                    throw new NonexistentEntityException("The emprunt with id " + id + " no longer exists.");
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
            Emprunt emprunt;
            try {
                emprunt = em.getReference(Emprunt.class, id);
                emprunt.getNumemprunt();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The emprunt with id " + id + " no longer exists.", enfe);
            }
            Document fkDocument = emprunt.getFkDocument();
            if (fkDocument != null) {
                fkDocument.getEmpruntCollection().remove(emprunt);
                fkDocument = em.merge(fkDocument);
            }
            Utilisateur fkUtilisateur = emprunt.getFkUtilisateur();
            if (fkUtilisateur != null) {
                fkUtilisateur.getEmpruntCollection().remove(emprunt);
                fkUtilisateur = em.merge(fkUtilisateur);
            }
            em.remove(emprunt);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Emprunt> findEmpruntEntities() {
        return findEmpruntEntities(true, -1, -1);
    }

    public List<Emprunt> findEmpruntEntities(int maxResults, int firstResult) {
        return findEmpruntEntities(false, maxResults, firstResult);
    }

    private List<Emprunt> findEmpruntEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Emprunt.class));
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

    public Emprunt findEmprunt(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Emprunt.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpruntCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Emprunt> rt = cq.from(Emprunt.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
