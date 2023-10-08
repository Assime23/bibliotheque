/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Assime
 */
@Entity
@Table(name = "emprunt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emprunt.findAll", query = "SELECT e FROM Emprunt e"),
    @NamedQuery(name = "Emprunt.findByNumemprunt", query = "SELECT e FROM Emprunt e WHERE e.numemprunt = :numemprunt"),
    @NamedQuery(name = "Emprunt.findByDateemprunt", query = "SELECT e FROM Emprunt e WHERE e.dateemprunt = :dateemprunt"),
    @NamedQuery(name = "Emprunt.findByDateretour", query = "SELECT e FROM Emprunt e WHERE e.dateretour = :dateretour")})
public class Emprunt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numemprunt")
    private String numemprunt;
    @Column(name = "dateemprunt")
    private String dateemprunt;
    @Column(name = "dateretour")
    private String dateretour;
    @JoinColumn(name = "fk_document", referencedColumnName = "numdocument")
    @ManyToOne
    private Document fkDocument;
    @JoinColumn(name = "fk_utilisateur", referencedColumnName = "numutilisateur")
    @ManyToOne
    private Utilisateur fkUtilisateur;

    public Emprunt() {
    }

    public Emprunt(String numemprunt) {
        this.numemprunt = numemprunt;
    }

    public String getNumemprunt() {
        return numemprunt;
    }

    public void setNumemprunt(String numemprunt) {
        this.numemprunt = numemprunt;
    }

    public String getDateemprunt() {
        return dateemprunt;
    }

    public void setDateemprunt(String dateemprunt) {
        this.dateemprunt = dateemprunt;
    }

    public String getDateretour() {
        return dateretour;
    }

    public void setDateretour(String dateretour) {
        this.dateretour = dateretour;
    }

    public Document getFkDocument() {
        return fkDocument;
    }

    public void setFkDocument(Document fkDocument) {
        this.fkDocument = fkDocument;
    }

    public Utilisateur getFkUtilisateur() {
        return fkUtilisateur;
    }

    public void setFkUtilisateur(Utilisateur fkUtilisateur) {
        this.fkUtilisateur = fkUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numemprunt != null ? numemprunt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emprunt)) {
            return false;
        }
        Emprunt other = (Emprunt) object;
        if ((this.numemprunt == null && other.numemprunt != null) || (this.numemprunt != null && !this.numemprunt.equals(other.numemprunt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Emprunt[ numemprunt=" + numemprunt + " ]";
    }
    
}
