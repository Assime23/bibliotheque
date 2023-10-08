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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "document")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d"),
    @NamedQuery(name = "Document.findByNumdocument", query = "SELECT d FROM Document d WHERE d.numdocument = :numdocument"),
    @NamedQuery(name = "Document.findByNomdocument", query = "SELECT d FROM Document d WHERE d.nomdocument = :nomdocument"),
    @NamedQuery(name = "Document.findByEtat", query = "SELECT d FROM Document d WHERE d.etat = :etat"),
    @NamedQuery(name = "Document.findByResume", query = "SELECT d FROM Document d WHERE d.resume = :resume"),
    @NamedQuery(name = "Document.findByMotcle", query = "SELECT d FROM Document d WHERE d.motcle = :motcle")})
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numdocument")
    private String numdocument;
    @Column(name = "nomdocument")
    private String nomdocument;
    @Column(name = "etat")
    private String etat;
    @Column(name = "resume")
    private String resume;
    @Column(name = "motcle")
    private String motcle;
    @JoinTable(name = "auteurdocument", joinColumns = {
        @JoinColumn(name = "fk_auteur", referencedColumnName = "numdocument")}, inverseJoinColumns = {
        @JoinColumn(name = "fk_document", referencedColumnName = "numdocument")})
    @ManyToMany
    private Collection<Document> documentCollection;
    @ManyToMany(mappedBy = "documentCollection")
    private Collection<Document> documentCollection1;
    @OneToMany(mappedBy = "fkDocument")
    private Collection<Emprunt> empruntCollection;
    @JoinColumn(name = "fk_numdicipline", referencedColumnName = "numdicipline")
    @ManyToOne
    private Dicipline fkNumdicipline;
    @JoinColumn(name = "fk_typedocument", referencedColumnName = "numtypedocument")
    @ManyToOne
    private Typedocument fkTypedocument;
    @JoinColumn(name = "fk_utilisateur", referencedColumnName = "numutilisateur")
    @ManyToOne
    private Utilisateur fkUtilisateur;

    public Document() {
    }

    public Document(String numdocument) {
        this.numdocument = numdocument;
    }

    public String getNumdocument() {
        return numdocument;
    }

    public void setNumdocument(String numdocument) {
        this.numdocument = numdocument;
    }

    public String getNomdocument() {
        return nomdocument;
    }

    public void setNomdocument(String nomdocument) {
        this.nomdocument = nomdocument;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getMotcle() {
        return motcle;
    }

    public void setMotcle(String motcle) {
        this.motcle = motcle;
    }

    @XmlTransient
    public Collection<Document> getDocumentCollection() {
        return documentCollection;
    }

    public void setDocumentCollection(Collection<Document> documentCollection) {
        this.documentCollection = documentCollection;
    }

    @XmlTransient
    public Collection<Document> getDocumentCollection1() {
        return documentCollection1;
    }

    public void setDocumentCollection1(Collection<Document> documentCollection1) {
        this.documentCollection1 = documentCollection1;
    }

    @XmlTransient
    public Collection<Emprunt> getEmpruntCollection() {
        return empruntCollection;
    }

    public void setEmpruntCollection(Collection<Emprunt> empruntCollection) {
        this.empruntCollection = empruntCollection;
    }

    public Dicipline getFkNumdicipline() {
        return fkNumdicipline;
    }

    public void setFkNumdicipline(Dicipline fkNumdicipline) {
        this.fkNumdicipline = fkNumdicipline;
    }

    public Typedocument getFkTypedocument() {
        return fkTypedocument;
    }

    public void setFkTypedocument(Typedocument fkTypedocument) {
        this.fkTypedocument = fkTypedocument;
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
        hash += (numdocument != null ? numdocument.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.numdocument == null && other.numdocument != null) || (this.numdocument != null && !this.numdocument.equals(other.numdocument))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Document[ numdocument=" + numdocument + " ]";
    }
    
}
