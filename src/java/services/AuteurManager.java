package services;

import dao.AuteurJpaController;
import dao.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.entities.Auteur;

public class AuteurManager {
    private final AuteurJpaController auteurController;

    public AuteurManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Web_App_BiblioPU");
        auteurController = new AuteurJpaController(emf);
    }

    public void createAuteur(Auteur auteur) throws PreexistingEntityException, Exception {
        auteurController.create(auteur);
    }

    public void updateAuteur(Auteur auteur) throws Exception {
        auteurController.edit(auteur);
    }

    public void deleteAuteur(String id) throws Exception {
        auteurController.destroy(id);
    }

    public List<Auteur> getAllAuteurs() {
        return auteurController.findAuteurEntities();
    }

    public Auteur getAuteurById(String id) {
        return auteurController.findAuteur(id);
    }
}
