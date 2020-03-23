/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import javax.persistence.Query;
import metier.modele.Client;
import metier.modele.Compte;



/**
 *
 * @author acroc
 */
public class CompteDao {

    public static void comptePersist(Compte c) {
        JpaUtil.obtenirEntityManager().persist(c);
    }

    public static Compte findCompte(long id) {
        return JpaUtil.obtenirEntityManager().find(Compte.class, id);
    }

    public static Compte updateCompte(Compte c) {
        return JpaUtil.obtenirEntityManager().merge(c);
    }

    //RÃ©cupÃ©rer tous les comtpes de la base
    public static List<Compte> listerComptes() {
        String jpql = "select c from Compte c";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        List<Compte> resultat = (List<Compte>) query.getResultList();
        return resultat;
    }
    

}
