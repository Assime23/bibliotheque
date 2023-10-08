package controlleur.servlets;

import dao.exceptions.NonexistentEntityException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entities.Filliere;
import services.FilliereManager;

public class FilliereServletSet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numFilliere = request.getParameter("numfilliere");
        String nomFilliere = request.getParameter("nomfilliere");

        // Create a new Filliere instance
        Filliere filliere = new Filliere(numFilliere);
        filliere.setNomfilliere(nomFilliere);

        // Create an instance of FilliereManager
        FilliereManager filliereManager = new FilliereManager();

        try {
            // Create the Filliere entity in the database
            filliereManager.createFilliere(filliere);
            request.setAttribute("message", "Fillière enregistrée avec succès!");
        } catch (Exception ex) {
            Logger.getLogger(FilliereServletSet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "Une erreur s'est produite lors de l'enregistrement de la Fillière.");
        }

        // Retrieve the updated list of Fillieres
        List<Filliere> fillieres = filliereManager.getAllFillieres();
        request.setAttribute("fillieres", fillieres);

        // Forward the request to a JSP for displaying the results
        request.getRequestDispatcher("FilliereAfficheRep.jsp").forward(request, response);
    }
}
