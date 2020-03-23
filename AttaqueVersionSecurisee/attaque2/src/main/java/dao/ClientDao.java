/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import metier.modele.Client;

/**
 *
 * @author acroc
 */
public class ClientDao {

    public static void clientPersist(Client c) {
        JpaUtil.obtenirEntityManager().persist(c);
    }

    public static Client findClient(long id) {
        return JpaUtil.obtenirEntityManager().find(Client.class, id);
    }

    public static Client updateClient(Client c) {
        return JpaUtil.obtenirEntityManager().merge(c);
    }

    //Trouver le client correspondant aux parametres dans la base
    public static Client connexionClient(String email, String mdp) {
        Client resultat = null;
        String jpql = "select c from Client c where c.mail = :email and c.motDePasse = :mdp";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("email", email);
        query.setParameter("mdp", mdp);
         try {
            resultat = (Client) query.getSingleResult();
        } catch (NoResultException ex)
        {
            resultat = null;
        }
        return resultat;    
    }

    //RÃ©cupÃ©rer tous les clients de la base
    public static List<Client> listerClients() {
        String jpql = "select c from Client c";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        List<Client> resultat = (List<Client>) query.getResultList();
        return resultat;
    }

    public static Client rechercheClientParMail(String mail) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        String jpql = "select c from Client c where c.mail = :mail";
        Query query = em.createQuery(jpql);
        query.setParameter("mail", mail);
        return (Client) query.getSingleResult();
    }

    public static Client rechercheClientParId(Integer idClient) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Client.class, idClient);
    }

}
