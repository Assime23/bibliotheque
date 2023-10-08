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
@Table(name = "domaine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Domaine.findAll", query = "SELECT d FROM Domaine d"),
    @NamedQuery(name = "Domaine.findByNumdomaine", query = "SELECT d FROM Domaine d WHERE d.numdomaine = :numdomaine"),
    @NamedQuery(name = "Domaine.findByNomdicipline", query = "SELECT d FROM Domaine d WHERE d.nomdicipline = :nomdicipline")})
public class Domaine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numdomaine")
    private String numdomaine;
    @Column(name = "nomdicipline")
    private String nomdicipline;
    @OneToMany(mappedBy = "fkDomaine")
    private Collection<Dicipline> diciplineCollection;

    public Domaine() {
    }

    public Domaine(String numdomaine) {
        this.numdomaine = numdomaine;
    }

    public String getNumdomaine() {
        return numdomaine;
    }

    public void setNumdomaine(String numdomaine) {
        this.numdomaine = numdomaine;
    }

    public String getNomdicipline() {
        return nomdicipline;
    }

    public void setNomdicipline(String nomdicipline) {
        this.nomdicipline = nomdicipline;
    }

    @XmlTransient
    public Collection<Dicipline> getDiciplineCollection() {
        return diciplineCollection;
    }

    public void setDiciplineCollection(Collection<Dicipline> diciplineCollection) {
        this.diciplineCollection = diciplineCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numdomaine != null ? numdomaine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Domaine)) {
            return false;
        }
        Domaine other = (Domaine) object;
        if ((this.numdomaine == null && other.numdomaine != null) || (this.numdomaine != null && !this.numdomaine.equals(other.numdomaine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Domaine[ numdomaine=" + numdomaine + " ]";
    }
    
}
