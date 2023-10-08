package controlleur.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.entities.Document;
import model.entities.Utilisateur;
import services.DocumentManager;



@WebServlet(name = "DocumentsDisponiblesServlet", urlPatterns = {"/documents-disponibles"})
public class DocumentsDisponiblesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DocumentManager documentManager = new DocumentManager();
        List<Document> documentsDisponibles = documentManager.findDocumentsByEtatDisponible();
        
        request.setAttribute("documents", documentsDisponibles);
        request.getRequestDispatcher("documents_disponibles.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        // Traitement éventuel des requêtes POST, si nécessaire
    }
}
