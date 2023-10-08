/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlleur.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.entities.Utilisateur;
import model.entities.Document;
import services.UtilisateurManager;
import services.DocumentManager;

@WebServlet(name = "addEmprunt", urlPatterns = {"/addEmprunt"})
public class addEmprunt extends HttpServlet {

    private UtilisateurManager utilisateurManager = new UtilisateurManager();
    private DocumentManager documentManager = new DocumentManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer la liste complète des utilisateurs
        List<Utilisateur> utilisateurs = utilisateurManager.getAllUtilisateurs();

        // Récupérer la liste complète des documents
        List<Document> documents = documentManager.getAllDocuments();

        request.setAttribute("utilisateurs", utilisateurs);
        request.setAttribute("documents", documents);
        request.getRequestDispatcher("addEmprunt.jsp").forward(request, response);
    }

}
