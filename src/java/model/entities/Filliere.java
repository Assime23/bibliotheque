/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Assime
 */
@Entity
@Table(name = "filliere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Filliere.findAll", query = "SELECT f FROM Filliere f"),
    @NamedQuery(name = "Filliere.findByNumfilliere", query = "SELECT f FROM Filliere f WHERE f.numfilliere = :numfilliere"),
    @NamedQuery(name = "Filliere.findByNomfilliere", query = "SELECT f FROM Filliere f WHERE f.nomfilliere = :nomfilliere")})
public class Filliere implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numfilliere")
    private String numfilliere;
    @Column(name = "nomfilliere")
    private String nomfilliere;
    @OneToMany(mappedBy = "fkNumfilliere")
    private Collection<Utilisateur> utilisateurCollection;

    public Filliere() {
    }

    public Filliere(String numfilliere) {
        this.numfilliere = numfilliere;
    }

    public String getNumfilliere() {
        return numfilliere;
    }

    public void setNumfilliere(String numfilliere) {
        this.numfilliere = numfilliere;
    }

    public String getNomfilliere() {
        return nomfilliere;
    }

    public void setNomfilliere(String nomfilliere) {
        this.nomfilliere = nomfilliere;
    }

    @XmlTransient
    public Collection<Utilisateur> getUtilisateurCollection() {
        return utilisateurCollection;
    }

    public void setUtilisateurCollection(Collection<Utilisateur> utilisateurCollection) {
        this.utilisateurCollection = utilisateurCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numfilliere != null ? numfilliere.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filliere)) {
            return false;
        }
        Filliere other = (Filliere) object;
        if ((this.numfilliere == null && other.numfilliere != null) || (this.numfilliere != null && !this.numfilliere.equals(other.numfilliere))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Filliere[ numfilliere=" + numfilliere + " ]";
    }
    
}
