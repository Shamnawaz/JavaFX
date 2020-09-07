/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awa.awajaba.metier;

import java.time.LocalDate;

/**
 *
 * @author developpeur
 */
public class Repas {
    
    private int numero;
    private LocalDate date;
    
    
    public Repas(int numero, LocalDate date){

    this.numero = numero;
    this.date = date;
}

}

