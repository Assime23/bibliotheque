package controlleur.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.entities.Emprunt;
import model.entities.Utilisateur;
import services.EmpruntManager;



@WebServlet("/emprunts")
public class EmpruntsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer l'utilisateur courant depuis la session (ou vous pouvez le passer en paramètre)
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");

        // Vérifier si l'utilisateur est connecté ou non
        if (utilisateur == null) {
            response.sendRedirect("login.jsp"); // Rediriger vers la page de connexion si l'utilisateur n'est pas connecté
            return;
        }

        // Récupérer la liste des emprunts associés à l'utilisateur depuis EmpruntManager
        EmpruntManager empruntManager = new EmpruntManager();
        List<Emprunt> emprunts = empruntManager.getEmpruntsByUtilisateur(utilisateur);

        // Ajouter la liste des emprunts à l'objet request pour l'afficher dans le JSP
        request.setAttribute("emprunts", emprunts);

        // Rediriger vers la page JSP pour afficher les emprunts
        request.getRequestDispatcher("emprunts.jsp").forward(request, response);
    }
}
