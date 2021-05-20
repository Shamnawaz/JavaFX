/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.utilitaires;

import fr.gsb.rv.dr.metier.Praticiens;
import java.util.Comparator;

/**
 *
 * @author developpeur
 */
public class ComparateurDateVisite implements Comparator<Praticiens>{
    
        public int compare(Praticiens o1, Praticiens o2){
        
        if(o1.getDateDerniereVisite() == o2.getDateDerniereVisite()){
            return 0;
            
        }
        else if(o1.getDateDerniereVisite().isBefore(o2.getDateDerniereVisite())){
        
            return 1;
        }
        else{
            return -1;
        }
    
    }
        
}
