package controlleur.servlets;

import dao.exceptions.PreexistingEntityException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.entities.Auteur;
import services.AuteurManager;

public class AuteurServletSet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numauteur = request.getParameter("numauteur");
        String nomauteur = request.getParameter("nomauteur");
        String prenomauteur = request.getParameter("prenomauteur");

        Auteur auteur = new Auteur(numauteur);
        auteur.setNomauteur(nomauteur);
        auteur.setPrenomauteur(prenomauteur);

        AuteurManager auteurManager = new AuteurManager();
        try {
            auteurManager.createAuteur(auteur);
            request.setAttribute("message", "Auteur enregistré avec succès!");
        } catch (PreexistingEntityException e) {
            request.setAttribute("message", "Erreur: L'auteur existe déjà!");
        } catch (Exception e) {
            request.setAttribute("message", "Une erreur est survenue lors de l'enregistrement de l'auteur.");
        }

        // Récupérer la liste complète des auteurs
        List<Auteur> listeAuteurs = auteurManager.getAllAuteurs();
        request.setAttribute("auteurs", listeAuteurs);

        request.getRequestDispatcher("/vue/AuteurAfficheRep.jsp").forward(request, response);
    }
}
