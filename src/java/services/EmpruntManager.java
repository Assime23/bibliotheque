package services;

import dao.EmpruntJpaController;
import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.entities.Emprunt;
import model.entities.Utilisateur;

public class EmpruntManager {
    private final EmpruntJpaController jpaController;

    public EmpruntManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Web_App_BiblioPU");
        jpaController = new EmpruntJpaController(emf);
    }
    
    public void createEmprunt(Emprunt emprunt) {
        try {
            jpaController.create(emprunt);
            System.out.println("Emprunt créé avec succès.");
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(EmpruntManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EmpruntManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editEmprunt(Emprunt emprunt) throws NonexistentEntityException, Exception {
        jpaController.edit(emprunt);
    }

    public void deleteEmprunt(String id) throws NonexistentEntityException {
        jpaController.destroy(id);
    }

    public List<Emprunt> getAllEmprunts() {
        return jpaController.findEmpruntEntities();
    }

    public Emprunt getEmpruntById(String id) {
        return jpaController.findEmprunt(id);
    }

    public int getEmpruntCount() {
        return jpaController.getEmpruntCount();
    }
    
    // Méthodes supplémentaires
    
    public List<Emprunt> findEmpruntEntities(int maxResults, int firstResult) {
        return jpaController.findEmpruntEntities(maxResults, firstResult);
    }
    public List<Emprunt> getEmpruntsByUtilisateur(Utilisateur utilisateur) {
        List<Emprunt> allEmprunts = getAllEmprunts();
        List<Emprunt> empruntsByUser = new ArrayList<>();

        for (Emprunt emprunt : allEmprunts) {
            if (emprunt.getFkUtilisateur().equals(utilisateur)) {
                empruntsByUser.add(emprunt);
            }
        }

        return empruntsByUser;
    }
    
    // Vous pouvez ajouter d'autres méthodes ici en fonction de vos besoins
}
