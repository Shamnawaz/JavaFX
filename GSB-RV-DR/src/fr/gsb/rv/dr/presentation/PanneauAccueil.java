/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.presentation;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author developpeur
 */
public class PanneauAccueil extends Pane {
    
    public PanneauAccueil(){
    
        super();
        
        VBox root = new VBox(new Label("Accueil"));
        root.setStyle("-fx-background-color: white");
        this.getChildren().add(root);
        
    }
    
    
}
