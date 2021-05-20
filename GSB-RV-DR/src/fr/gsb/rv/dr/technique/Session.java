/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Visiteur;

/**
 *
 * @author developpeur
 */
public class Session {
    
    private static Session session = null;
    private static Visiteur leVisiteur = new Visiteur();
    
    
    private Session(Visiteur visiteur){
        
        this.leVisiteur = leVisiteur;
        
    }
    
    public static void ouvrir(Visiteur visiteur){
        
        session = new Session(visiteur);
       
        
    }
    
    public static void fermer(){
       
        session = null;
    }
    
    public static Session getSession(){
   
        return session;
    }
    
    
    public Visiteur getLeVisiteur(){
        
        return leVisiteur;
    }
    
    public static boolean estOuverte(){
        
        if(session == null){
            
            return false;
            
        }
        else{
            
            return true;
        }
        
    }

}
    
    

