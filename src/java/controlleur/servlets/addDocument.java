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
import model.entities.Dicipline;
import model.entities.Typedocument;
import services.DiciplineManager;
import services.DocumentTypeManager;

@WebServlet(name = "FilliereServlet", urlPatterns = {"/fillieres"})
public class addDocument extends HttpServlet {
        DiciplineManager utilisateurManager = new DiciplineManager();
        DocumentTypeManager doc=new DocumentTypeManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                List<Dicipline> disciplines = utilisateurManager.getAllDiciplines();

        request.setAttribute("disciplines", disciplines);
        
        List<Typedocument> documentTypes = doc.getAllDocumentTypes();

        request.setAttribute("documentTypes", documentTypes);
        request.getRequestDispatcher("addDocument.jsp").forward(request, response);
    }

}
