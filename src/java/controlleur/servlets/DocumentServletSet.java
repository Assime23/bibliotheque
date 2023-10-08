package controlleur.servlets;

import dao.exceptions.NonexistentEntityException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import model.entities.Dicipline;
import model.entities.Document;
import model.entities.Typedocument;
import model.entities.Utilisateur;
import services.DiciplineManager;
import services.DocumentManager;
import services.DocumentTypeManager;
import services.UtilisateurManager;

public class DocumentServletSet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire pour créer un nouveau document
          long timestamp = System.nanoTime();
          String numDocument = Long.toString(timestamp);

        String titre = request.getParameter("titre");
        String etat = request.getParameter("etat");
        String resume = request.getParameter("resume");
        String motcle = request.getParameter("motcle");
        String diciplineId = request.getParameter("dicipline");
        String typeId = request.getParameter("typedocument");

        // Créer une instance de DocumentManager
        DocumentManager documentManager = new DocumentManager();

        // Créer un nouvel objet Document avec les paramètres
        Document document = new Document();
        document.setNumdocument(numDocument);
        document.setNomdocument(titre);
        document.setEtat(etat);
        document.setResume(resume);
        document.setMotcle(motcle);

        // Récupérer la discipline correspondante à l'ID
        DiciplineManager diciplineManager = new DiciplineManager();
        Dicipline discipline = diciplineManager.getDiciplineById(diciplineId);
        document.setFkNumdicipline(discipline);

        // Récupérer le type de document correspondant à l'ID
        DocumentTypeManager documentTypeManager = new DocumentTypeManager();
        Typedocument typedocument = documentTypeManager.getDocumentTypeById(typeId);
        document.setFkTypedocument(typedocument);
        
        
    // Récupérer l'ID de l'utilisateur courant (supposons que vous l'avez déjà)
  // Récupérer l'ID de l'utilisateur courant à partir de la session
HttpSession session = request.getSession();
Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    // Créer une instance de UtilisateurManager (si nécessaire)
    UtilisateurManager utilisateurManager = new UtilisateurManager();

    // Récupérer l'utilisateur courant à partir de l'ID
String utilisateurId= utilisateur.getNumutilisateur();

try {
    // Appeler la méthode pour ajouter le document à l'utilisateur
    utilisateurManager.ajouterDocumentAUtilisateur(utilisateurId, document);
    // Succès : le document a été ajouté à l'utilisateur
} catch (NonexistentEntityException ex) {
    // Traiter le cas où l'utilisateur n'a pas été trouvé
    ex.printStackTrace();
} catch (Exception ex) {
    // Traiter d'autres exceptions ici
    ex.printStackTrace();
}

    // Lier le document à l'utilisateur courant
    document.setFkUtilisateur(utilisateur);
    

        // Appeler la méthode createDocument pour créer le document
        documentManager.createDocument(document);

        // Récupérer la liste complète des documents
        List<Document> listeDocuments = documentManager.getAllDocuments();
        request.setAttribute("utilisateur", utilisateur);

        request.getRequestDispatcher("deshEtudiant").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID du document à partir des paramètres de requête
        String documentId = request.getParameter("id");

        // Créer une instance de DocumentManager
        DocumentManager documentManager = new DocumentManager();

        // Vérifier si l'ID du document est fourni
        if (documentId != null) {
            // Récupérer le document correspondant à l'ID
            Document document = documentManager.getDocumentById(documentId);
            request.setAttribute("document", document);
        }

        // Récupérer la liste complète des documents
        List<Document> listeDocuments = documentManager.getAllDocuments();
        request.setAttribute("documents", listeDocuments);

        request.getRequestDispatcher("DocumentAfficheRep.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire pour mettre à jour un document existant
        String documentId = request.getParameter("id");
        String titre = request.getParameter("titre");
        // Autres paramètres du document à mettre à jour

        // Créer une instance de DocumentManager
        DocumentManager documentManager = new DocumentManager();

        // Récupérer le document à mettre à jour en utilisant son ID
        Document document = documentManager.getDocumentById(documentId);

        // Mettre à jour les propriétés du document
        document.setNomdocument(titre);
        // Mettre à jour d'autres propriétés du document

        // Appeler la méthode editDocument pour mettre à jour le document
        try {
            documentManager.editDocument(document);
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'erreur de mise à jour du document
        }

        // Récupérer la liste complète des documents
        List<Document> listeDocuments = documentManager.getAllDocuments();
        request.setAttribute("documents", listeDocuments);

        request.getRequestDispatcher("DocumentAfficheRep.jsp").forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID du document à supprimer à partir des paramètres de requête
        String documentId = request.getParameter("id");

        // Créer une instance de DocumentManager
        DocumentManager documentManager = new DocumentManager();

        // Supprimer le document correspondant à l'ID
        try {
            documentManager.deleteDocument(documentId);
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'erreur de suppression du document
        }

        // Récupérer la liste complète des documents
        List<Document> listeDocuments = documentManager.getAllDocuments();
        request.setAttribute("documents", listeDocuments);

        request.getRequestDispatcher("DocumentAfficheRep.jsp").forward(request, response);
    }
}
