package controlleur.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.entities.Domaine;
import services.DomaineManager;

public class DomaineServletSet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numDomaine = request.getParameter("numDomaine");
        String nomDomaine = request.getParameter("nomDomaine");
        DomaineManager domaineManager = new DomaineManager();
         domaineManager.createDomaine(numDomaine,nomDomaine);
      
        request.setAttribute("message", "Domaine enregistré avec succès!");

        // Récupérer la liste complète des domaines
        List<Domaine> listeDomaines = domaineManager.getAllDomaines();
        request.setAttribute("domaines", listeDomaines);

        request.getRequestDispatcher("DomaineAfficheRep.jsp").forward(request, response);
    }

}
