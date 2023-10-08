package services;

import dao.UtilisateurJpaController;
import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.entities.Document;
import model.entities.Utilisateur;

public class UtilisateurManager{
    private final UtilisateurJpaController utilisateurController;

    public UtilisateurManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Web_App_BiblioPU");
        utilisateurController = new UtilisateurJpaController(emf);
    }

    public void createUtilisateur(Utilisateur utilisateur) throws PreexistingEntityException, Exception {
        utilisateurController.create(utilisateur);
    }

    public void updateUtilisateur(Utilisateur utilisateur) throws NonexistentEntityException, Exception {
        utilisateurController.edit(utilisateur);
    }

    public void deleteUtilisateur(String id) throws NonexistentEntityException {
        utilisateurController.destroy(id);
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurController.findUtilisateurEntities();
    }

    public Utilisateur getUtilisateurById(String id) {
        return utilisateurController.findUtilisateur(id);
    }
    public boolean utilisateurExists(String username, String password) {
    List<Utilisateur> utilisateurs = getAllUtilisateurs();

    for (Utilisateur utilisateur : utilisateurs) {
        if ((utilisateur.getNumutilisateur().equals(username) || 
            (utilisateur.getEmail() != null && utilisateur.getEmail().equals(username))) &&
            utilisateur.getMotdepasse().equals(password)) {
            return true; // L'utilisateur existe dans la base de données
        }
    }

    return false; // L'utilisateur n'a pas été trouvé dans la base de données
}

      
    public Collection<Document> getUtilisateurDocument(String userId) {
        Utilisateur utilisateur = utilisateurController.findUtilisateur(userId);
        if (utilisateur != null) {
            return utilisateurController.getUtilisateurDocuments(userId);
        } else {
            return null;
        }
    }
     public void ajouterDocumentAUtilisateur(String userId, Document document) throws NonexistentEntityException, Exception {
        utilisateurController.ajouterDocumentAUtilisateur(userId, document);
    }
    
  

}
