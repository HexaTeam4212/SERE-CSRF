package src;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
public class RecupererProfilClientSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            JsonObject jsonContainer = new JsonObject();
            boolean succes = (boolean) request.getAttribute("succes");
            jsonContainer.addProperty("succes", succes);
            
            if(succes){
              Client client = (Client) request.getAttribute("profil");
                JsonObject jsonClient = new JsonObject();

                jsonClient.addProperty("nom", client.getNom());
                jsonClient.addProperty("prenom", client.getPrenom());
                jsonClient.addProperty("civilite", client.getCivilite());
                
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                
                jsonClient.addProperty("dateNais", format.format(client.getDatedeNaissance()));
                jsonClient.addProperty("adresse", client.getAdressePostale());
                jsonClient.addProperty("mail", client.getMail());
                jsonClient.addProperty("numTel", client.getNumTel());
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
