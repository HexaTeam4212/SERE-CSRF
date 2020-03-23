package src;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acroc
 */
public class TransactionSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            JsonObject jsonContainer = new JsonObject();
            boolean transaction = (boolean) request.getAttribute("transaction");
            jsonContainer.addProperty("transaction", transaction);
            
            if(transaction){
                System.out.println("if transaction");
                Client client = (Client) request.getAttribute("client");
                JsonObject jsonClient = new JsonObject();

                jsonClient.addProperty("solde", client.getMonCompte().getSolde());

                jsonContainer.add("client", jsonClient);
            }
                
            
            //formattage et ecriture sur la sortie
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jsonContainer, out);

        } catch (IOException ex) {
            Logger.getLogger(RecupererProfilClientSerialisation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
