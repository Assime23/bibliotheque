package controlleur.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.entities.Dicipline;
import services.DiciplineManager;

public class DiciplineServletSet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numDicipline = request.getParameter("numDicipline");
        String nomDicipline = request.getParameter("nomDicipline");
        DiciplineManager diciplineManager = new DiciplineManager();
        diciplineManager.createDicipline(numDicipline, nomDicipline);
      
        request.setAttribute("message", "Discipline enregistrée avec succès!");

        // Récupérer la liste complète des disciplines
        List<Dicipline> listeDisciplines = diciplineManager.getAllDiciplines();
        request.setAttribute("disciplines", listeDisciplines);

        request.getRequestDispatcher("DiciplineAfficheRep.jsp").forward(request, response);
    }

}
