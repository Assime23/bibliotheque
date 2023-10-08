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
@Table(name = "typedocument")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typedocument.findAll", query = "SELECT t FROM Typedocument t"),
    @NamedQuery(name = "Typedocument.findByNumtypedocument", query = "SELECT t FROM Typedocument t WHERE t.numtypedocument = :numtypedocument"),
    @NamedQuery(name = "Typedocument.findByNomtypedocument", query = "SELECT t FROM Typedocument t WHERE t.nomtypedocument = :nomtypedocument")})
public class Typedocument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numtypedocument")
    private String numtypedocument;
    @Column(name = "nomtypedocument")
    private String nomtypedocument;
    @OneToMany(mappedBy = "fkTypedocument")
    private Collection<Document> documentCollection;

    public Typedocument() {
    }

    public Typedocument(String numtypedocument) {
        this.numtypedocument = numtypedocument;
    }

    public String getNumtypedocument() {
        return numtypedocument;
    }

    public void setNumtypedocument(String numtypedocument) {
        this.numtypedocument = numtypedocument;
    }

    public String getNomtypedocument() {
        return nomtypedocument;
    }

    public void setNomtypedocument(String nomtypedocument) {
        this.nomtypedocument = nomtypedocument;
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
        hash += (numtypedocument != null ? numtypedocument.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typedocument)) {
            return false;
        }
        Typedocument other = (Typedocument) object;
        if ((this.numtypedocument == null && other.numtypedocument != null) || (this.numtypedocument != null && !this.numtypedocument.equals(other.numtypedocument))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Typedocument[ numtypedocument=" + numtypedocument + " ]";
    }
    
}
