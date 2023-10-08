package model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.Dicipline;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-09-28T10:20:47")
@StaticMetamodel(Domaine.class)
public class Domaine_ { 

    public static volatile SingularAttribute<Domaine, String> nomdicipline;
    public static volatile SingularAttribute<Domaine, String> numdomaine;
    public static volatile CollectionAttribute<Domaine, Dicipline> diciplineCollection;

}