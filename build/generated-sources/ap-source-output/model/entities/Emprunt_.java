package model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.Document;
import model.entities.Utilisateur;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-09-28T10:20:47")
@StaticMetamodel(Emprunt.class)
public class Emprunt_ { 

    public static volatile SingularAttribute<Emprunt, String> numemprunt;
    public static volatile SingularAttribute<Emprunt, String> dateretour;
    public static volatile SingularAttribute<Emprunt, Document> fkDocument;
    public static volatile SingularAttribute<Emprunt, Utilisateur> fkUtilisateur;
    public static volatile SingularAttribute<Emprunt, String> dateemprunt;

}