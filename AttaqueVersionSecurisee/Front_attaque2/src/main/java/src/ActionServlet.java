package src;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.JpaUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.service.Service;

/**
 *
 * @author acroc
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(true); //Contexte de la session
        Service service = new Service();
        String todo = request.getParameter("action");

        if (null != todo) switch (todo) {
            case "connecter":{
                Action action = new ConnexionClientAction(service);
                action.execute(request);
                Serialisation serialisation = new ConnexionClientSerialisation();
                serialisation.serialiser(request, response);
                    break;
                }
           
            case "realiser_transaction":{
                Action action = new TransactionAction(service);
                action.execute(request);
                Serialisation serialisation = new TransactionSerialisation();
                serialisation.serialiser(request, response);
                    break;
                }
            
            case "recuperer_profil_client":{
                Action action = new RecupererProfilClientAction(service);
                action.execute(request);
                Serialisation serialisation = new RecupererProfilClientSerialisation();
                serialisation.serialiser(request, response);
                    break;
                }
            
            case "deconnecter":{
                session.invalidate();
                    break;
                }

            default:
                break;
        }

    }

    @Override
    public void init() {
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
