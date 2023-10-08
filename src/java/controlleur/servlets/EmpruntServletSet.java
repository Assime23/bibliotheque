package controlleur.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.entities.Document;
import model.entities.Emprunt;
import model.entities.Utilisateur;
import services.DocumentManager;
import services.EmpruntManager;
import services.UtilisateurManager;



public class EmpruntServletSet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire pour créer un nouvel emprunt
        long timestamp = System.nanoTime();
        String numEmprunt = Long.toString(timestamp);
        String dateEmprunt = request.getParameter("dateEmprunt");
        String dateRetour = request.getParameter("dateRetour");
        String utilisateurId = request.getParameter("utilisateur");
        String documentId = request.getParameter("document");

        // Créer une instance de EmpruntManager
        EmpruntManager empruntManager = new EmpruntManager();
        UtilisateurManager utilisateurManager = new UtilisateurManager();
        DocumentManager documentManager = new DocumentManager();

        // Récupérer l'utilisateur correspondant à l'ID
        Utilisateur utilisateur = utilisateurManager.getUtilisateurById(utilisateurId);

        // Récupérer le document correspondant à l'ID
        Document document = documentManager.getDocumentById(documentId);

        // Créer un nouvel objet Emprunt avec les paramètres
        Emprunt emprunt = new Emprunt();
        emprunt.setNumemprunt(numEmprunt);
        emprunt.setDateemprunt(dateEmprunt);
        emprunt.setDateretour(dateRetour);
        emprunt.setFkUtilisateur(utilisateur);
        emprunt.setFkDocument(document);

        // Appeler la méthode createEmprunt pour créer l'emprunt
        empruntManager.createEmprunt(emprunt);

        // Récupérer la liste complète des emprunts
        List<Emprunt> listeEmprunts = empruntManager.getAllEmprunts();
        request.setAttribute("emprunts", listeEmprunts);

        request.getRequestDispatcher("emprunts.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID de l'emprunt à partir des paramètres de requête
        String empruntId = request.getParameter("id");

        // Créer une instance de EmpruntManager
        EmpruntManager empruntManager = new EmpruntManager();
        UtilisateurManager utilisateurManager = new UtilisateurManager();
        DocumentManager documentManager = new DocumentManager();

        // Vérifier si l'ID de l'emprunt est fourni
        if (empruntId != null) {
            // Récupérer l'emprunt correspondant à l'ID
            Emprunt emprunt = empruntManager.getEmpruntById(empruntId);
            request.setAttribute("emprunt", emprunt);
        }

        // Récupérer la liste complète des emprunts
        List<Emprunt> listeEmprunts = empruntManager.getAllEmprunts();
        request.setAttribute("emprunts", listeEmprunts);

        // Récupérer la liste complète des utilisateurs
        List<Utilisateur> listeUtilisateurs = utilisateurManager.getAllUtilisateurs();
        request.setAttribute("utilisateurs", listeUtilisateurs);

        // Récupérer la liste complète des documents
        List<Document> listeDocuments = documentManager.getAllDocuments();
        request.setAttribute("documents", listeDocuments);

        request.getRequestDispatcher("EmpruntAfficheRep.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire pour mettre à jour un emprunt existant
        String empruntId = request.getParameter("id");
        String dateEmprunt = request.getParameter("dateEmprunt");
        String dateRetour = request.getParameter("dateRetour");
        String utilisateurId = request.getParameter("utilisateurId");
        String documentId = request.getParameter("documentId");

        // Créer une instance de EmpruntManager
        EmpruntManager empruntManager = new EmpruntManager();
        UtilisateurManager utilisateurManager = new UtilisateurManager();
        DocumentManager documentManager = new DocumentManager();

        // Récupérer l'utilisateur correspondant à l'ID
        Utilisateur utilisateur = utilisateurManager.getUtilisateurById(utilisateurId);

        // Récupérer le document correspondant à l'ID
        Document document = documentManager.getDocumentById(documentId);

        // Récupérer l'emprunt à mettre à jour en utilisant son ID
        Emprunt emprunt = empruntManager.getEmpruntById(empruntId);

        // Mettre à jour les propriétés de l'emprunt
        emprunt.setDateemprunt(dateEmprunt);
        emprunt.setDateretour(dateRetour);
        emprunt.setFkUtilisateur(utilisateur);
        emprunt.setFkDocument(document);

        // Appeler la méthode editEmprunt pour mettre à jour l'emprunt
        try {
            empruntManager.editEmprunt(emprunt);
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'erreur de mise à jour de l'emprunt
        }

        // Récupérer la liste complète des emprunts
        List<Emprunt> listeEmprunts = empruntManager.getAllEmprunts();
        request.setAttribute("emprunts", listeEmprunts);

        request.getRequestDispatcher("EmpruntAfficheRep.jsp").forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID de l'emprunt à supprimer à partir des paramètres de requête
        String empruntId = request.getParameter("id");

        // Créer une instance de EmpruntManager
        EmpruntManager empruntManager = new EmpruntManager();

        // Supprimer l'emprunt correspondant à l'ID
        try {
            empruntManager.deleteEmprunt(empruntId);
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'erreur de suppression de l'emprunt
        }

        // Récupérer la liste complète des emprunts
        List<Emprunt> listeEmprunts = empruntManager.getAllEmprunts();
        request.setAttribute("emprunts", listeEmprunts);

        request.getRequestDispatcher("EmpruntAfficheRep.jsp").forward(request, response);
    }
}
