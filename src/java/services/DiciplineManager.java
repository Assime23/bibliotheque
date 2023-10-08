package services;

import dao.DiciplineJpaController;
import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.entities.Dicipline;

public class DiciplineManager {
    private final DiciplineJpaController jpaController;

    public DiciplineManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Web_App_BiblioPU");
        jpaController = new DiciplineJpaController(emf);
    }
    
    public void createDicipline(String numDicipline, String nomDicipline) {
        Dicipline dicipline = new Dicipline(numDicipline);
        dicipline.setNomdicipline(nomDicipline);

        try {
            jpaController.create(dicipline);
            System.out.println("Dicipline créée avec succès.");
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(DiciplineManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DiciplineManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editDicipline(Dicipline dicipline) throws NonexistentEntityException, Exception {
        jpaController.edit(dicipline);
    }

    public void deleteDicipline(String id) throws NonexistentEntityException {
        jpaController.destroy(id);
    }

    public List<Dicipline> getAllDiciplines() {
        return jpaController.findDiciplineEntities();
    }

    public Dicipline getDiciplineById(String id) {
        return jpaController.findDicipline(id);
    }

    public int getDiciplineCount() {
        return jpaController.getDiciplineCount();
    }
    
    // Méthodes supplémentaires
    
    public List<Dicipline> findDiciplineEntities(int maxResults, int firstResult) {
        return jpaController.findDiciplineEntities(maxResults, firstResult);
    }
    
    // Vous pouvez ajouter d'autres méthodes ici en fonction de vos besoins
}
