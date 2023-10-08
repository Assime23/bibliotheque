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
import model.entities.Utilisateur;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.entities.Filliere;

/**
 *
 * @author Assime
 */
public class FilliereJpaController implements Serializable {

    public FilliereJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Filliere filliere) throws PreexistingEntityException, Exception {
        if (filliere.getUtilisateurCollection() == null) {
            filliere.setUtilisateurCollection(new ArrayList<Utilisateur>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Utilisateur> attachedUtilisateurCollection = new ArrayList<Utilisateur>();
            for (Utilisateur utilisateurCollectionUtilisateurToAttach : filliere.getUtilisateurCollection()) {
                utilisateurCollectionUtilisateurToAttach = em.getReference(utilisateurCollectionUtilisateurToAttach.getClass(), utilisateurCollectionUtilisateurToAttach.getNumutilisateur());
                attachedUtilisateurCollection.add(utilisateurCollectionUtilisateurToAttach);
            }
            filliere.setUtilisateurCollection(attachedUtilisateurCollection);
            em.persist(filliere);
            for (Utilisateur utilisateurCollectionUtilisateur : filliere.getUtilisateurCollection()) {
                Filliere oldFkNumfilliereOfUtilisateurCollectionUtilisateur = utilisateurCollectionUtilisateur.getFkNumfilliere();
                utilisateurCollectionUtilisateur.setFkNumfilliere(filliere);
                utilisateurCollectionUtilisateur = em.merge(utilisateurCollectionUtilisateur);
                if (oldFkNumfilliereOfUtilisateurCollectionUtilisateur != null) {
                    oldFkNumfilliereOfUtilisateurCollectionUtilisateur.getUtilisateurCollection().remove(utilisateurCollectionUtilisateur);
                    oldFkNumfilliereOfUtilisateurCollectionUtilisateur = em.merge(oldFkNumfilliereOfUtilisateurCollectionUtilisateur);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFilliere(filliere.getNumfilliere()) != null) {
                throw new PreexistingEntityException("Filliere " + filliere + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Filliere filliere) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Filliere persistentFilliere = em.find(Filliere.class, filliere.getNumfilliere());
            Collection<Utilisateur> utilisateurCollectionOld = persistentFilliere.getUtilisateurCollection();
            Collection<Utilisateur> utilisateurCollectionNew = filliere.getUtilisateurCollection();
            Collection<Utilisateur> attachedUtilisateurCollectionNew = new ArrayList<Utilisateur>();
            for (Utilisateur utilisateurCollectionNewUtilisateurToAttach : utilisateurCollectionNew) {
                utilisateurCollectionNewUtilisateurToAttach = em.getReference(utilisateurCollectionNewUtilisateurToAttach.getClass(), utilisateurCollectionNewUtilisateurToAttach.getNumutilisateur());
                attachedUtilisateurCollectionNew.add(utilisateurCollectionNewUtilisateurToAttach);
            }
            utilisateurCollectionNew = attachedUtilisateurCollectionNew;
            filliere.setUtilisateurCollection(utilisateurCollectionNew);
            filliere = em.merge(filliere);
            for (Utilisateur utilisateurCollectionOldUtilisateur : utilisateurCollectionOld) {
                if (!utilisateurCollectionNew.contains(utilisateurCollectionOldUtilisateur)) {
                    utilisateurCollectionOldUtilisateur.setFkNumfilliere(null);
                    utilisateurCollectionOldUtilisateur = em.merge(utilisateurCollectionOldUtilisateur);
                }
            }
            for (Utilisateur utilisateurCollectionNewUtilisateur : utilisateurCollectionNew) {
                if (!utilisateurCollectionOld.contains(utilisateurCollectionNewUtilisateur)) {
                    Filliere oldFkNumfilliereOfUtilisateurCollectionNewUtilisateur = utilisateurCollectionNewUtilisateur.getFkNumfilliere();
                    utilisateurCollectionNewUtilisateur.setFkNumfilliere(filliere);
                    utilisateurCollectionNewUtilisateur = em.merge(utilisateurCollectionNewUtilisateur);
                    if (oldFkNumfilliereOfUtilisateurCollectionNewUtilisateur != null && !oldFkNumfilliereOfUtilisateurCollectionNewUtilisateur.equals(filliere)) {
                        oldFkNumfilliereOfUtilisateurCollectionNewUtilisateur.getUtilisateurCollection().remove(utilisateurCollectionNewUtilisateur);
                        oldFkNumfilliereOfUtilisateurCollectionNewUtilisateur = em.merge(oldFkNumfilliereOfUtilisateurCollectionNewUtilisateur);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = filliere.getNumfilliere();
                if (findFilliere(id) == null) {
                    throw new NonexistentEntityException("The filliere with id " + id + " no longer exists.");
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
            Filliere filliere;
            try {
                filliere = em.getReference(Filliere.class, id);
                filliere.getNumfilliere();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The filliere with id " + id + " no longer exists.", enfe);
            }
            Collection<Utilisateur> utilisateurCollection = filliere.getUtilisateurCollection();
            for (Utilisateur utilisateurCollectionUtilisateur : utilisateurCollection) {
                utilisateurCollectionUtilisateur.setFkNumfilliere(null);
                utilisateurCollectionUtilisateur = em.merge(utilisateurCollectionUtilisateur);
            }
            em.remove(filliere);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Filliere> findFilliereEntities() {
        return findFilliereEntities(true, -1, -1);
    }

    public List<Filliere> findFilliereEntities(int maxResults, int firstResult) {
        return findFilliereEntities(false, maxResults, firstResult);
    }

    private List<Filliere> findFilliereEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Filliere.class));
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

    public Filliere findFilliere(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Filliere.class, id);
        } finally {
            em.close();
        }
    }

    public int getFilliereCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Filliere> rt = cq.from(Filliere.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
