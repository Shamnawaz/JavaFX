/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.presentation;

import fr.gsb.rv.dr.metier.Praticiens;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 *
 * @author developpeur
 */
public class PanneauPraticiens extends Pane{
    
    private GridPane gridpane = new GridPane();
    private TableView table = new TableView();
    private ToggleGroup groupe = new ToggleGroup();
    private ObservableList<Praticiens> olPraticien = FXCollections.observableArrayList();
    private List<Praticiens> lPrat;
    
    public PanneauPraticiens() {
    
        super();
        
        try{
            List<Praticiens> lPrat = ModeleGsbRv.getPraticiensHesitants();
            for( Praticiens unPrat : lPrat){
            
                olPraticien.add(unPrat);
            }
            System.out.println(olPraticien);
       }
        
        catch(ConnexionException e){
            
            Logger.getLogger(PanneauPraticiens.class.getName()).log(Level.SEVERE, null, e);
            
        }
        
        
        VBox root = new VBox();
        root.setStyle("-fx-background-color: white");
        root.setStyle("");
        root.getChildren().add(new Label("Selectionnez le critère de tri :"));
        this.getChildren().add(root);
        RadioButton rb1 = new RadioButton("Confiance");
        rb1.setToggleGroup(groupe);
        RadioButton rb2 = new RadioButton("Notoriété");
        rb2.setToggleGroup(groupe);
        RadioButton rb3 = new RadioButton("Date Visite");
        rb3.setToggleGroup(groupe);
        GridPane gridpane = new GridPane();
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.setPadding(new Insets(20, 150, 10, 10));
        gridpane.add(rb1, 0, 0);
        gridpane.add(rb2, 1, 0);
        gridpane.add(rb3, 2, 0);
        
        table.setEditable(false);
        TableColumn numero = new TableColumn("Numéro");
        numero.setMinWidth(130);
        numero.setCellValueFactory( new PropertyValueFactory<Praticiens, String>("numero"));
        
        TableColumn nom = new TableColumn("Nom");
        nom.setMinWidth(130);
        nom.setCellValueFactory( new PropertyValueFactory<Praticiens, String>("nom"));
        
        TableColumn ville = new TableColumn("Ville");
        ville.setMinWidth(130);
        ville.setCellValueFactory( new PropertyValueFactory<Praticiens, String>("ville"));
        
        table.getColumns().addAll(numero, nom, ville);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(olPraticien);
        
        root.getChildren().add(gridpane);
        root.getChildren().add(table);
        


    }
        
}
    

