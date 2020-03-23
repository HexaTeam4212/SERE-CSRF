/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author acer
 */
@Entity
public class Compte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCompte;
    
    private String nom;
    private double solde;

    @OneToOne (mappedBy="monCompte")
    private Client monClient;

    public Compte () {
        
    }
    public Compte(String nom, double solde) {
        this.nom = nom;
        this.solde = solde;
    }

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Client getMonClient() {
        return monClient;
    }

    public void setMonClient(Client monClient) {
        this.monClient = monClient;
    }
    
    
    
}
