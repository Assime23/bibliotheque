package services;

import dao.DocumentJpaController;
import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.entities.Document;
import model.entities.Emprunt;
import model.entities.Utilisateur;

public class DocumentManager {
    private final DocumentJpaController jpaController;

    public DocumentManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Web_App_BiblioPU");
        jpaController = new DocumentJpaController(emf);
    }
    
    public void createDocument(Document document) {
        try {
            jpaController.create(document);
            System.out.println("Document créé avec succès.");
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(DocumentManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DocumentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editDocument(Document document) throws NonexistentEntityException, Exception {
        jpaController.edit(document);
    }

    public void deleteDocument(String id) throws NonexistentEntityException {
        jpaController.destroy(id);
    }

    public List<Document> getAllDocuments() {
        return jpaController.findDocumentEntities();
    }

    public Document getDocumentById(String id) {
        return jpaController.findDocument(id);
    }

    public int getDocumentCount() {
        return jpaController.getDocumentCount();
    }
    
    // Méthodes supplémentaires
    
    public List<Document> findDocumentEntities(int maxResults, int firstResult) {
        return jpaController.findDocumentEntities(maxResults, firstResult);
    }
     public List<Document> findDocumentsByEtatDisponible() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Web_App_BiblioPU");
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Document> query = em.createQuery("SELECT d FROM Document d WHERE d.etat = :etat", Document.class);
            query.setParameter("etat", "disponible");

            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }
     public List<Document> getDocumentsEmpruntesByUtilisateur(Utilisateur utilisateur) {
        // Récupérez tous les emprunts de l'utilisateur donné
        EmpruntManager empruntManager = new EmpruntManager();
        List<Emprunt> empruntsByUser = empruntManager.getEmpruntsByUtilisateur(utilisateur);

        // Créez une liste pour stocker les documents empruntés par l'utilisateur
        List<Document> documentsEmpruntes = new ArrayList<>();

        // Parcourez tous les emprunts de l'utilisateur
        for (Emprunt emprunt : empruntsByUser) {
            Document document = emprunt.getFkDocument();
            if (document != null) {
                documentsEmpruntes.add(document);
            }
        }

        return documentsEmpruntes;
    }
    
    // Vous pouvez ajouter d'autres méthodes ici en fonction de vos besoins
}
