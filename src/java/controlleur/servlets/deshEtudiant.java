/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlleur.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.entities.Utilisateur;


/**
 *
 * @author Assime
 */
public class deshEtudiant extends HttpServlet {

    

 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Ajouter l'utilisateur comme attribut de requête s'il est non nul
    HttpSession session = request.getSession();
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur != null) {
        request.setAttribute("utilisateur", utilisateur);
    }
    request.getRequestDispatcher("deshEtudiant.jsp").forward(request, response);
}


   

}
