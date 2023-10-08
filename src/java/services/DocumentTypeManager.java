package services;

import dao.TypedocumentJpaController;
import dao.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.entities.Typedocument;

public class DocumentTypeManager {
    private final TypedocumentJpaController documentTypeController;

    public DocumentTypeManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Web_App_BiblioPU");
        documentTypeController = new TypedocumentJpaController(emf);
    }

    public void createDocumentType(Typedocument documentType) throws PreexistingEntityException, Exception {
        documentTypeController.create(documentType);
    }

    public List<Typedocument> getAllDocumentTypes() {
        return documentTypeController.findTypedocumentEntities();
    }

    public Typedocument getDocumentTypeById(String id) {
        return documentTypeController.findTypedocument(id);
    }
}
