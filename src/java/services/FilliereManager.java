package services;

import dao.FilliereJpaController;
import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.entities.Filliere;
import java.util.List;

public class FilliereManager {
    private final FilliereJpaController filliereController;

    public FilliereManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Web_App_BiblioPU");
        filliereController = new FilliereJpaController(emf);
    }

    public void createFilliere(Filliere filliere) throws PreexistingEntityException, Exception {
        filliereController.create(filliere);
    }

    public void updateFilliere(Filliere filliere) throws NonexistentEntityException, Exception {
        filliereController.edit(filliere);
    }

    public void deleteFilliere(String filliereId) throws NonexistentEntityException {
        filliereController.destroy(filliereId);
    }

    public List<Filliere> getAllFillieres() {
        return filliereController.findFilliereEntities();
    }

    public Filliere getFilliereById(String filliereId) {
        return filliereController.findFilliere(filliereId);
    }
}
