package model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.Document;
import model.entities.Emprunt;
import model.entities.Filliere;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-09-28T10:20:47")
@StaticMetamodel(Utilisateur.class)
public class Utilisateur_ { 

    public static volatile SingularAttribute<Utilisateur, String> numutilisateur;
    public static volatile SingularAttribute<Utilisateur, String> datenaissance;
    public static volatile SingularAttribute<Utilisateur, String> adresse;
    public static volatile SingularAttribute<Utilisateur, Filliere> fkNumfilliere;
    public static volatile CollectionAttribute<Utilisateur, Document> documentCollection;
    public static volatile SingularAttribute<Utilisateur, String> telephone;
    public static volatile SingularAttribute<Utilisateur, String> motdepasse;
    public static volatile SingularAttribute<Utilisateur, String> nom;
    public static volatile SingularAttribute<Utilisateur, String> prenom;
    public static volatile SingularAttribute<Utilisateur, String> email;
    public static volatile CollectionAttribute<Utilisateur, Emprunt> empruntCollection;

}