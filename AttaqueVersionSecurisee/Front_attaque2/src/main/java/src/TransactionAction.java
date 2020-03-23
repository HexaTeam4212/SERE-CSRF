package src;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
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
public class TransactionAction extends Action {

    public TransactionAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        String montant=request.getParameter("montant");
        System.out.println("montant : "+montant);
        String dest = request.getParameter("destinataire");
        String mdp = request.getParameter("mdp");
        
        HttpSession session = request.getSession();
        Client client = (Client)session.getAttribute("client");
        
        Client clientDest = null;

        if ("".equals(dest) || "".equals(montant)||"".equals(mdp)) {
            clientDest = null;
        } else {
            try{
                double somme = 0;                
                try{
                    somme = Double.parseDouble(montant);
                } catch (NumberFormatException nfe) {
                    somme = 0;
		}
                clientDest = (Client) service.transaction(client,somme,dest,mdp);   
            } catch (NoResultException ex) {
                Logger.getLogger(TransactionAction.class.getName()).log(Level.SEVERE, null, ex);
                clientDest = null;
            }    
        }
        System.out.println("clientDEst : "+clientDest);
        //Ajouter attributs au request pour faire un objet json
        if (clientDest == null) {
            System.out.println("if clientdest");
            request.setAttribute("transaction", false);
        } else {
            System.out.println("else clientdest");
            request.setAttribute("transaction", true);
            request.setAttribute("client", client);
            
            System.out.println("client : "+ client);
        }

    }
}
