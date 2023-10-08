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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Assime
 */
@Entity
@Table(name = "auteur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auteur.findAll", query = "SELECT a FROM Auteur a"),
    @NamedQuery(name = "Auteur.findByNumauteur", query = "SELECT a FROM Auteur a WHERE a.numauteur = :numauteur"),
    @NamedQuery(name = "Auteur.findByNomauteur", query = "SELECT a FROM Auteur a WHERE a.nomauteur = :nomauteur"),
    @NamedQuery(name = "Auteur.findByPrenomauteur", query = "SELECT a FROM Auteur a WHERE a.prenomauteur = :prenomauteur")})
public class Auteur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numauteur")
    private String numauteur;
    @Column(name = "nomauteur")
    private String nomauteur;
    @Column(name = "prenomauteur")
    private String prenomauteur;

    public Auteur() {
    }

    public Auteur(String numauteur) {
        this.numauteur = numauteur;
    }

    public String getNumauteur() {
        return numauteur;
    }

    public void setNumauteur(String numauteur) {
        this.numauteur = numauteur;
    }

    public String getNomauteur() {
        return nomauteur;
    }

    public void setNomauteur(String nomauteur) {
        this.nomauteur = nomauteur;
    }

    public String getPrenomauteur() {
        return prenomauteur;
    }

    public void setPrenomauteur(String prenomauteur) {
        this.prenomauteur = prenomauteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numauteur != null ? numauteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auteur)) {
            return false;
        }
        Auteur other = (Auteur) object;
        if ((this.numauteur == null && other.numauteur != null) || (this.numauteur != null && !this.numauteur.equals(other.numauteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Auteur[ numauteur=" + numauteur + " ]";
    }
    
}
