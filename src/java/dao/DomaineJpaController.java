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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.entities.Domaine;

/**
 *
 * @author Assime
 */
public class DomaineJpaController implements Serializable {

    public DomaineJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Domaine domaine) throws PreexistingEntityException, Exception {
        if (domaine.getDiciplineCollection() == null) {
            domaine.setDiciplineCollection(new ArrayList<Dicipline>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Dicipline> attachedDiciplineCollection = new ArrayList<Dicipline>();
            for (Dicipline diciplineCollectionDiciplineToAttach : domaine.getDiciplineCollection()) {
                diciplineCollectionDiciplineToAttach = em.getReference(diciplineCollectionDiciplineToAttach.getClass(), diciplineCollectionDiciplineToAttach.getNumdicipline());
                attachedDiciplineCollection.add(diciplineCollectionDiciplineToAttach);
            }
            domaine.setDiciplineCollection(attachedDiciplineCollection);
            em.persist(domaine);
            for (Dicipline diciplineCollectionDicipline : domaine.getDiciplineCollection()) {
                Domaine oldFkDomaineOfDiciplineCollectionDicipline = diciplineCollectionDicipline.getFkDomaine();
                diciplineCollectionDicipline.setFkDomaine(domaine);
                diciplineCollectionDicipline = em.merge(diciplineCollectionDicipline);
                if (oldFkDomaineOfDiciplineCollectionDicipline != null) {
                    oldFkDomaineOfDiciplineCollectionDicipline.getDiciplineCollection().remove(diciplineCollectionDicipline);
                    oldFkDomaineOfDiciplineCollectionDicipline = em.merge(oldFkDomaineOfDiciplineCollectionDicipline);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDomaine(domaine.getNumdomaine()) != null) {
                throw new PreexistingEntityException("Domaine " + domaine + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Domaine domaine) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Domaine persistentDomaine = em.find(Domaine.class, domaine.getNumdomaine());
            Collection<Dicipline> diciplineCollectionOld = persistentDomaine.getDiciplineCollection();
            Collection<Dicipline> diciplineCollectionNew = domaine.getDiciplineCollection();
            Collection<Dicipline> attachedDiciplineCollectionNew = new ArrayList<Dicipline>();
            for (Dicipline diciplineCollectionNewDiciplineToAttach : diciplineCollectionNew) {
                diciplineCollectionNewDiciplineToAttach = em.getReference(diciplineCollectionNewDiciplineToAttach.getClass(), diciplineCollectionNewDiciplineToAttach.getNumdicipline());
                attachedDiciplineCollectionNew.add(diciplineCollectionNewDiciplineToAttach);
            }
            diciplineCollectionNew = attachedDiciplineCollectionNew;
            domaine.setDiciplineCollection(diciplineCollectionNew);
            domaine = em.merge(domaine);
            for (Dicipline diciplineCollectionOldDicipline : diciplineCollectionOld) {
                if (!diciplineCollectionNew.contains(diciplineCollectionOldDicipline)) {
                    diciplineCollectionOldDicipline.setFkDomaine(null);
                    diciplineCollectionOldDicipline = em.merge(diciplineCollectionOldDicipline);
                }
            }
            for (Dicipline diciplineCollectionNewDicipline : diciplineCollectionNew) {
                if (!diciplineCollectionOld.contains(diciplineCollectionNewDicipline)) {
                    Domaine oldFkDomaineOfDiciplineCollectionNewDicipline = diciplineCollectionNewDicipline.getFkDomaine();
                    diciplineCollectionNewDicipline.setFkDomaine(domaine);
                    diciplineCollectionNewDicipline = em.merge(diciplineCollectionNewDicipline);
                    if (oldFkDomaineOfDiciplineCollectionNewDicipline != null && !oldFkDomaineOfDiciplineCollectionNewDicipline.equals(domaine)) {
                        oldFkDomaineOfDiciplineCollectionNewDicipline.getDiciplineCollection().remove(diciplineCollectionNewDicipline);
                        oldFkDomaineOfDiciplineCollectionNewDicipline = em.merge(oldFkDomaineOfDiciplineCollectionNewDicipline);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = domaine.getNumdomaine();
                if (findDomaine(id) == null) {
                    throw new NonexistentEntityException("The domaine with id " + id + " no longer exists.");
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
            Domaine domaine;
            try {
                domaine = em.getReference(Domaine.class, id);
                domaine.getNumdomaine();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The domaine with id " + id + " no longer exists.", enfe);
            }
            Collection<Dicipline> diciplineCollection = domaine.getDiciplineCollection();
            for (Dicipline diciplineCollectionDicipline : diciplineCollection) {
                diciplineCollectionDicipline.setFkDomaine(null);
                diciplineCollectionDicipline = em.merge(diciplineCollectionDicipline);
            }
            em.remove(domaine);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Domaine> findDomaineEntities() {
        return findDomaineEntities(true, -1, -1);
    }

    public List<Domaine> findDomaineEntities(int maxResults, int firstResult) {
        return findDomaineEntities(false, maxResults, firstResult);
    }

    private List<Domaine> findDomaineEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Domaine.class));
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

    public Domaine findDomaine(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Domaine.class, id);
        } finally {
            em.close();
        }
    }

    public int getDomaineCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Domaine> rt = cq.from(Domaine.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
