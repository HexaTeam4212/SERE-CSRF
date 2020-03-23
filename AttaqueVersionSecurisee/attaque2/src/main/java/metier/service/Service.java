/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import dao.ClientDao;
import dao.CompteDao;
import dao.JpaUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import metier.modele.Client;
import metier.modele.Compte;

/**
 *
 * @author acroc
 */
public class Service {

    public Service() {
    }

    public void initialisation() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse("1994-05-14");
        Date d2 = sdf.parse("1985-11-13");
        Date d3 = sdf.parse("1995-09-14");
        Date d4 = sdf.parse("1972-07-08");
        Date d5 = sdf.parse("1985-11-13");

        Client c1 = new Client("Madame", "Thomas", "Charline", d1, "Villeurbanne", "0610236578", "charline.thomas@gmail.com", "charte");
        Client c2 = new Client("Madame", "Abbeur", "Rym", d2, "7 Rue de la Soie, Villeurbanne", "0577459411", "rym.abbeur@yahoo.com", "labeur");
        Client c3 = new Client("Monsieur", "Neuchall", "Philippe", d3, "17 Rue du 4 Septembre 1797, Villeurbanne", "0421688682", "philippe.nechall@yahoo.com", "narval");
        Client c4 = new Client("Monsieur", "Tuleup", "Li", d4, "3 Place Jean Chorel, Villeurbanne", "0263015577", "li.tuleup@gmail.com", "tulipe");
        Client c5 = new Client("Madame", "Dirgham", "Milena", d5, "5 Impasse du Reve, Villeurbanne", "0530504140", "milena.dirgham@hotmail.com", "mitaine");

        Compte cpt1 = new Compte("Compte 1", 1000);
        Compte cpt2 = new Compte("Compte 2", 1000);
        Compte cpt3 = new Compte("Compte 3", 1000);
        Compte cpt4 = new Compte("Compte 4", 1000);
        Compte cpt5 = new Compte("Compte 5", 1000);

        creationClient(c1);
        creationClient(c2);
        creationClient(c3);
        creationClient(c4);
        creationClient(c5);

        creationCompte(cpt1);
        creationCompte(cpt2);
        creationCompte(cpt3);
        creationCompte(cpt4);
        creationCompte(cpt5);

        c1.setMonCompte(cpt1);
        c2.setMonCompte(cpt2);
        c3.setMonCompte(cpt3);
        c4.setMonCompte(cpt4);
        c5.setMonCompte(cpt5);

        c1 = modifierClient(c1);
        c2 = modifierClient(c2);
        c3 = modifierClient(c3);
        c4 = modifierClient(c4);
        c5 = modifierClient(c5);

        cpt1.setMonClient(c1);
        cpt2.setMonClient(c2);
        cpt3.setMonClient(c3);
        cpt4.setMonClient(c4);
        cpt5.setMonClient(c5);

        cpt1 = modifierCompte(cpt1);
        cpt2 = modifierCompte(cpt2);
        cpt3 = modifierCompte(cpt3);
        cpt4 = modifierCompte(cpt4);
        cpt5 = modifierCompte(cpt5);
    }

    //Création d'un client et ajout dans la base 
    public void creationClient(Client c) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            ClientDao.clientPersist(c);
            JpaUtil.validerTransaction();

            //Gestion des exceptions : echec de la persistance employé
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();

        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    public void creationCompte(Compte c) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            CompteDao.comptePersist(c);
            JpaUtil.validerTransaction();

            //Gestion des exceptions : echec de la persistance employé
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();

        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    //Authentification du client
    public Client connexionClient(String mail, String mdp) {
        Client trouve = null;
        try {
            JpaUtil.creerEntityManager();

            //Récupération du client qui souhaite se connecter si trouvé
            trouve = ClientDao.connexionClient(mail, mdp);
            System.out.println("client trouve connexion : " + trouve);
            System.out.println("Vous êtes connecté.");

            //Gestion de l'exception : client non trouvé
        } catch (Exception ex) {
            // System.out.println("Erreur lors de la connexion. Votre identifiant ou votre mot de passe est incorrect.");

        } finally {
            JpaUtil.fermerEntityManager();
            return trouve;
        }
    }

    //Création d'un client et ajout dans la base 
    public Client modifierClient(Client c) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            c = ClientDao.updateClient(c);
            JpaUtil.validerTransaction();

            //Gestion des exceptions : echec de la persistance employé
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();

        } finally {
            JpaUtil.fermerEntityManager();
            return c;
        }
    }

    public Compte modifierCompte(Compte c) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            c = CompteDao.updateCompte(c);
            JpaUtil.validerTransaction();

            //Gestion des exceptions : echec de la persistance employé
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();

        } finally {
            JpaUtil.fermerEntityManager();
            return c;
        }
    }

    //Récupérer le client correspondant à l'id donné en paramètre si il existe
    public Client rechercherClientParID(long id) {
        Client c = null;

        try {
            JpaUtil.creerEntityManager();
            c = ClientDao.findClient(id);

            //Gestion de l'exception : client non trouvé
        } catch (Exception ex) {

        } finally {
            JpaUtil.fermerEntityManager();
            return c;
        }

    }

    //Récupérer tous les clients de la base
    public List<Client> listerClients() {
        List<Client> listCl = null;

        try {
            JpaUtil.creerEntityManager();
            listCl = ClientDao.listerClients();

            //Gestion de l'exception : aucun client trouvé
        } catch (Exception ex) {

        } finally {

            JpaUtil.fermerEntityManager();
            return listCl;
        }
    }

    public Client transaction(Client client, double somme, String email, String mdp) {
        Client clientDest = null;
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            if (client != null) {
                Client c = ClientDao.connexionClient(client.getMail(), mdp);
                if (c.getIdClient().equals(client.getIdClient())) {
                    Compte cpt = client.getMonCompte();
                    if (cpt.getSolde() >= somme) {
                        //Client qui recoit l'argent
                        clientDest = ClientDao.rechercheClientParMail(email);

                        if (clientDest != null && !clientDest.getIdClient().equals(client.getIdClient())) {
                            Compte cptD = clientDest.getMonCompte();
                            cptD.setSolde(cptD.getSolde() + somme);
                            cptD = CompteDao.updateCompte(cptD);
                            clientDest.setMonCompte(cptD);
                            clientDest = ClientDao.updateClient(clientDest);

                            cpt.setSolde(cpt.getSolde() - somme);
                            cpt = CompteDao.updateCompte(cpt);
                            client.setMonCompte(cpt);
                            client = ClientDao.updateClient(client);
                        } else {
                            clientDest = null;
                        }

                    } else {
                        clientDest = null;
                    }
                } else {
                    clientDest = null;
                }

            } else {
                clientDest = null;
            }

            JpaUtil.validerTransaction();

            //Gestion de l'exception : aucun client trouvé
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();

        } finally {
            System.out.println("clientdest : " + clientDest);
            JpaUtil.fermerEntityManager();
            return clientDest;
        }

    }

}
