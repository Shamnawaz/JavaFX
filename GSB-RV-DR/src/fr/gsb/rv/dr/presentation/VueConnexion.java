/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.presentation;

import fr.gsb.rv.dr.technique.ConnexionException;
import javafx.scene.control.Dialog;
import javafx.util.Pair;
import java.sql.Connection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 *
 * @author developpeur
 */
public class VueConnexion extends Dialog<Pair<String, String>>{
    
    
   public VueConnexion() {
       
       super();
        //Dialog<Pair<String, String>> dialog = new Dialog<>();
        super.setTitle("Authentification");
        super.setHeaderText("Saisir vos données de connexion");
        
        ButtonType OK_DONE = new ButtonType("Se connecter", ButtonData.OK_DONE);
        /*dialog.getDialogPane().getButtonTypes().clear();*/
        super.getDialogPane().getButtonTypes().addAll(OK_DONE,ButtonType.CANCEL);
        
        GridPane gridpane = new GridPane();
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.setPadding(new Insets(20, 150, 10, 10));
        
        TextField matricule = new TextField();
        matricule.setPromptText("");
        PasswordField password = new PasswordField();
        password.setPromptText("");
        
        gridpane.add(new Label("Matricule :"), 0, 0);
        gridpane.add(matricule, 1, 0);
        gridpane.add(new Label("Mot de passe :"), 0, 1);
        gridpane.add(password, 1, 1);
        
        
        super.getDialogPane().setContent(gridpane);
        //Optional<Pair<String, String>> reponse = super.showAndWait();
        
        
        setResultConverter(
            new Callback<ButtonType, Pair<String, String>>() {
            @Override
            public Pair<String, String> call(ButtonType typeBouton) {
                
                if(typeBouton == OK_DONE){
                    
                    return new Pair<String, String>(matricule.getText(), password.getText());
                    
                
                }
                
                return null;
                
          
            }
            
            
            });
        
        
    }
}