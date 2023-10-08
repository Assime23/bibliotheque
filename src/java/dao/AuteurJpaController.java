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
import model.entities.Auteur;

/**
 *
 * @author Assime
 */
public class AuteurJpaController implements Serializable {

    public AuteurJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Auteur auteur) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(auteur);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAuteur(auteur.getNumauteur()) != null) {
                throw new PreexistingEntityException("Auteur " + auteur + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Auteur auteur) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            auteur = em.merge(auteur);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = auteur.getNumauteur();
                if (findAuteur(id) == null) {
                    throw new NonexistentEntityException("The auteur with id " + id + " no longer exists.");
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
            Auteur auteur;
            try {
                auteur = em.getReference(Auteur.class, id);
                auteur.getNumauteur();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The auteur with id " + id + " no longer exists.", enfe);
            }
            em.remove(auteur);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Auteur> findAuteurEntities() {
        return findAuteurEntities(true, -1, -1);
    }

    public List<Auteur> findAuteurEntities(int maxResults, int firstResult) {
        return findAuteurEntities(false, maxResults, firstResult);
    }

    private List<Auteur> findAuteurEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Auteur.class));
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

    public Auteur findAuteur(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Auteur.class, id);
        } finally {
            em.close();
        }
    }

    public int getAuteurCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Auteur> rt = cq.from(Auteur.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
