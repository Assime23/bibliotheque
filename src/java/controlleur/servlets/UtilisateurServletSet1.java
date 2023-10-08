package controlleur.servlets;

import dao.exceptions.PreexistingEntityException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.entities.Filliere;
import model.entities.Utilisateur;
import services.FilliereManager;
import services.UtManag;
import services.UtilisateurManager;

public class UtilisateurServletSet1 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numUtilisateur = request.getParameter("numUtilisateur");
        String nomUtilisateur = request.getParameter("nomUtilisateur");
        String prenomUtilisateur = request.getParameter("prenomUtilisateur");
        String dateNaissanceUtilisateur = request.getParameter("dateNaissanceUtilisateur");
        String adresseUtilisateur = request.getParameter("adresseUtilisateur");
        String telephoneUtilisateur = request.getParameter("telephoneUtilisateur");
        String motdepasseUtilisateur = request.getParameter("motdepasseUtilisateur");
        String emailUtilisateur = request.getParameter("emailUtilisateur");

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNumutilisateur(numUtilisateur);
        utilisateur.setNom(nomUtilisateur);
        utilisateur.setPrenom(prenomUtilisateur);
        utilisateur.setDatenaissance(dateNaissanceUtilisateur);
        utilisateur.setAdresse(adresseUtilisateur);
        utilisateur.setTelephone(telephoneUtilisateur);
        utilisateur.setMotdepasse(motdepasseUtilisateur);
        utilisateur.setEmail(emailUtilisateur);
        
             
    String filliereUtilisateurId = request.getParameter("filliereUtilisateur");
    FilliereManager filliereManager = new FilliereManager();
Filliere filliereUtilisateur = filliereManager.getFilliereById(filliereUtilisateurId);

    utilisateur.setFkNumfilliere(filliereUtilisateur);

        UtManag utilisateurManager = new UtManag();
        try {
            utilisateurManager.createUtilisateur(utilisateur);
            request.setAttribute("message", "Utilisateur enregistré avec succès!");
        } catch (Exception e) {
            request.setAttribute("message", "Une erreur est survenue lors de l'enregistrement de l'utilisateur.");
        }

        // Récupérer la liste complète des utilisateurs
        List<Utilisateur> listeUtilisateurs = utilisateurManager.getAllUtilisateurs();
        request.setAttribute("utilisateurs", listeUtilisateurs);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
