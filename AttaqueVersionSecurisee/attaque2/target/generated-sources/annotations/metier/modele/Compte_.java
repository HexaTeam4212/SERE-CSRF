package metier.modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Client;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-23T21:36:13")
@StaticMetamodel(Compte.class)
public class Compte_ { 

    public static volatile SingularAttribute<Compte, Long> idCompte;
    public static volatile SingularAttribute<Compte, Double> solde;
    public static volatile SingularAttribute<Compte, Client> monClient;
    public static volatile SingularAttribute<Compte, String> nom;

}