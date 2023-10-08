package controlleur.servlets;

import dao.exceptions.PreexistingEntityException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.entities.Typedocument;
import services.DocumentTypeManager;

public class TypedocumentServletSet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numTypedocument = request.getParameter("numTypedocument");
        String libelleTypedocument = request.getParameter("libelleTypedocument");

        Typedocument typedocument = new Typedocument(numTypedocument);
        typedocument.setNomtypedocument(libelleTypedocument);

        DocumentTypeManager documentTypeManager = new DocumentTypeManager();
        try {
            documentTypeManager.createDocumentType(typedocument);
            request.setAttribute("message", "Type de document enregistré avec succès!");
        } catch (PreexistingEntityException e) {
            request.setAttribute("message", "Erreur: Le type de document existe déjà!");
        } catch (Exception e) {
            request.setAttribute("message", "Une erreur est survenue lors de l'enregistrement du type de document.");
        }

        // Récupérer la liste complète des types de document
        List<Typedocument> listeTypedocuments = documentTypeManager.getAllDocumentTypes();
        request.setAttribute("typedocuments", listeTypedocuments);

        request.getRequestDispatcher("TypedocumentAfficheRep.jsp").forward(request, response);
    }
}
