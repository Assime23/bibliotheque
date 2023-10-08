package controlleur.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalider la session de l'utilisateur
        request.getSession().invalidate();

        // Rediriger vers la page de connexion (ou une autre page de votre choix après la déconnexion)
        response.sendRedirect("index.jsp");
    }
}
