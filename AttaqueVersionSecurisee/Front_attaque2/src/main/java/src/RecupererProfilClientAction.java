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
public class RecupererProfilClientAction extends Action{

    public RecupererProfilClientAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Client client = (Client)session.getAttribute("client");
        
        if(client==null){
            request.setAttribute("succes",false);
        }else{
            request.setAttribute("succes",true);
            request.setAttribute("profil",client);
        }
        
    }
    
}
