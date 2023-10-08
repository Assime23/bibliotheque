package model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.Document;
import model.entities.Domaine;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-09-28T10:20:48")
@StaticMetamodel(Dicipline.class)
public class Dicipline_ { 

    public static volatile SingularAttribute<Dicipline, String> nomdicipline;
    public static volatile CollectionAttribute<Dicipline, Document> documentCollection;
    public static volatile SingularAttribute<Dicipline, String> numdicipline;
    public static volatile SingularAttribute<Dicipline, Domaine> fkDomaine;

}