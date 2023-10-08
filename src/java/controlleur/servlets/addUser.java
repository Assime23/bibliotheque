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
import model.entities.Filliere;
import services.FilliereManager;
import java.util.List;
/**
 *
 * @author Assime
 */
@WebServlet(name = "FilliereServlet", urlPatterns = {"/fillieres"})
public class addUser extends HttpServlet {
        FilliereManager utilisateurManager = new FilliereManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                List<Filliere> fillieres = utilisateurManager.getAllFillieres();

        request.setAttribute("fillieres", fillieres);
        request.getRequestDispatcher("addUtilisateur1.jsp").forward(request, response);
    }

}
