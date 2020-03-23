package src;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.service.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author acroc
 */
public class ConnexionClientAction extends Action {

    public ConnexionClientAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        String user = request.getParameter("login");
        String mdp = request.getParameter("password");
        System.out.println("Connexion Action");
        System.out.println("user : "+user);
        System.out.println("password : "+mdp);
        Client client = null;
        HttpSession session = request.getSession();

        if ("".equals(user) || "".equals(mdp)) {
            System.out.println("if connexion");
            client = null;
        } else {
                client = service.connexionClient(user, mdp);   
                System.out.println("else connexion");
        }
        System.out.println("client connexion : "+client);
        //Ajouter attributs au request pour faire un objet json
        if (client == null) {
            request.setAttribute("connexion", false);
        } else {
            request.setAttribute("connexion", true);
            request.setAttribute("client", client);

            session.setAttribute("client", client);
        }

    }
}
