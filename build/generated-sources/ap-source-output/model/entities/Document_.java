package model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.Dicipline;
import model.entities.Document;
import model.entities.Emprunt;
import model.entities.Typedocument;
import model.entities.Utilisateur;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-09-28T10:20:47")
@StaticMetamodel(Document.class)
public class Document_ { 

    public static volatile SingularAttribute<Document, String> resume;
    public static volatile SingularAttribute<Document, String> numdocument;
    public static volatile CollectionAttribute<Document, Document> documentCollection1;
    public static volatile SingularAttribute<Document, String> motcle;
    public static volatile CollectionAttribute<Document, Document> documentCollection;
    public static volatile SingularAttribute<Document, Typedocument> fkTypedocument;
    public static volatile SingularAttribute<Document, Utilisateur> fkUtilisateur;
    public static volatile SingularAttribute<Document, String> nomdocument;
    public static volatile SingularAttribute<Document, String> etat;
    public static volatile CollectionAttribute<Document, Emprunt> empruntCollection;
    public static volatile SingularAttribute<Document, Dicipline> fkNumdicipline;

}