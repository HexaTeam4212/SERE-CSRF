/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package metier.service;

import dao.JpaUtil;
import java.text.ParseException;
        
/**
 *
 * @author DELL
 */
public class main {
    public static void main(String[] args) throws ParseException {
        JpaUtil.init();
        Service serv = new Service();
        serv.initialisation();
        
        
    }
}
