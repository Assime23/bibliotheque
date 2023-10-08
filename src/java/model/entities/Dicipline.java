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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "dicipline")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dicipline.findAll", query = "SELECT d FROM Dicipline d"),
    @NamedQuery(name = "Dicipline.findByNumdicipline", query = "SELECT d FROM Dicipline d WHERE d.numdicipline = :numdicipline"),
    @NamedQuery(name = "Dicipline.findByNomdicipline", query = "SELECT d FROM Dicipline d WHERE d.nomdicipline = :nomdicipline")})
public class Dicipline implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numdicipline")
    private String numdicipline;
    @Column(name = "nomdicipline")
    private String nomdicipline;
    @JoinColumn(name = "fk_domaine", referencedColumnName = "numdomaine")
    @ManyToOne
    private Domaine fkDomaine;
    @OneToMany(mappedBy = "fkNumdicipline")
    private Collection<Document> documentCollection;

    public Dicipline() {
    }

    public Dicipline(String numdicipline) {
        this.numdicipline = numdicipline;
    }

    public String getNumdicipline() {
        return numdicipline;
    }

    public void setNumdicipline(String numdicipline) {
        this.numdicipline = numdicipline;
    }

    public String getNomdicipline() {
        return nomdicipline;
    }

    public void setNomdicipline(String nomdicipline) {
        this.nomdicipline = nomdicipline;
    }

    public Domaine getFkDomaine() {
        return fkDomaine;
    }

    public void setFkDomaine(Domaine fkDomaine) {
        this.fkDomaine = fkDomaine;
    }

    @XmlTransient
    public Collection<Document> getDocumentCollection() {
        return documentCollection;
    }

    public void setDocumentCollection(Collection<Document> documentCollection) {
        this.documentCollection = documentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numdicipline != null ? numdicipline.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dicipline)) {
            return false;
        }
        Dicipline other = (Dicipline) object;
        if ((this.numdicipline == null && other.numdicipline != null) || (this.numdicipline != null && !this.numdicipline.equals(other.numdicipline))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Dicipline[ numdicipline=" + numdicipline + " ]";
    }
    
}
