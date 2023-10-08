package services;

import dao.DomaineJpaController;
import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.entities.Domaine;

public class DomaineManager {
    private final DomaineJpaController jpaController;

    public DomaineManager() {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("Web_App_BiblioPU");

        jpaController = new DomaineJpaController(emf);
    }
 public void createDomaine(String numFilliere, String nomFilliere) {
        Domaine filliere = new Domaine(numFilliere);
        filliere.setNomdicipline(nomFilliere);

        try {
            jpaController.create(filliere);
            System.out.println("Domaine créée avec succès.");
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(DomaineManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DomaineManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   /* public void createDomaine(Domaine domaine) throws PreexistingEntityException, Exception {
        jpaController.create(domaine);
    }
*/
    public void editDomaine(Domaine domaine) throws NonexistentEntityException, Exception {
        jpaController.edit(domaine);
    }

    public void deleteDomaine(String id) throws NonexistentEntityException {
        jpaController.destroy(id);
    }

    public List<Domaine> getAllDomaines() {
        return jpaController.findDomaineEntities();
    }

    public Domaine getDomaineById(String id) {
        return jpaController.findDomaine(id);
    }

    public int getDomaineCount() {
        return jpaController.getDomaineCount();
    }
    
    // Additional methods
    
    public List<Domaine> findDomaineEntities(int maxResults, int firstResult) {
        return jpaController.findDomaineEntities(maxResults, firstResult);
    }
    
    // You can add more methods here based on your requirements
}
