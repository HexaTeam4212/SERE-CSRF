/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author acer
 */
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idClient;

    private String civilite;
    private String nom;
    private String prenom;
    
    @Temporal(TemporalType.DATE)
    private Date datedeNaissance;
    
    private String adressePostale;
    private String numTel;
    private String mail;
    private String motDePasse;
    
    @OneToOne 
    private Compte monCompte;

    public Client() {
        
    }
    public Client(String civilite, String nom, String prenom, Date datedeNaissance, String adressePostale, String numTel, String mail, String motDePasse) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.datedeNaissance = datedeNaissance;
        this.adressePostale = adressePostale;
        this.numTel = numTel;
        this.mail = mail;
        this.motDePasse = motDePasse;
    }
    
    
    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDatedeNaissance() {
        return datedeNaissance;
    }

    public void setDatedeNaissance(Date datedeNaissance) {
        this.datedeNaissance = datedeNaissance;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Compte getMonCompte() {
        return monCompte;
    }

    public void setMonCompte(Compte monCompte) {
        this.monCompte = monCompte;
    }

    @Override
    public String toString() {
        return "Client{" + "idClient=" + idClient + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", datedeNaissance=" + datedeNaissance + ", adressePostale=" + adressePostale + ", numTel=" + numTel + ", mail=" + mail + ", motDePasse=" + motDePasse + '}';
    }
    
}
