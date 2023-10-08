package controlleur.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.entities.Utilisateur;
import services.UtilisateurManager;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UtilisateurManager ut;
    

    @Override
    public void init() throws ServletException {
        super.init();
        ut = new UtilisateurManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Afficher la page d'inscription
        request.getRequestDispatcher("index.html").forward(request, response);
    }

    @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Traitement des informations de connexion
    String username = request.getParameter("matricule");
    String password = request.getParameter("password");

    // Vérification des informations et redirection appropriée
    if (ut.utilisateurExists(username, password)) {
        // Récupérer l'utilisateur connecté depuis le backend
        Utilisateur utilisateur = ut.getUtilisateurById(username);
        utilisateur.setDocumentCollection(ut.getUtilisateurDocument(username));

    
         // Mettre l'utilisateur dans la session
         HttpSession session = request.getSession();
         session.setAttribute("utilisateur", utilisateur);
         //session.setMaxInactiveInterval(600);
           
                // Ajouter l'utilisateur comme attribut de requête
           request.setAttribute("utilisateur", utilisateur);

        // Redirection vers le tableau de bord de l'étudiant
        request.getRequestDispatcher("deshEtudiant.jsp").forward(request, response);
    } else {
        // Ajouter un message d'erreur
        request.setAttribute("errorMessage", "Matricule, adresse e-mail ou mot de passe incorrect");

        // Redirection vers la page de connexion avec le message d'erreur
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }


    }
}
/*HttpSession session = request.getSession();
Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
if (utilisateur != null) {
    // L'utilisateur est connecté, vous pouvez accéder à ses informations
    // et effectuer des actions en fonction de son statut connecté.
} else {
    // L'utilisateur n'est pas connecté, redirigez-le vers la page de connexion.
}
*/
