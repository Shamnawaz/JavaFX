/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.metier.Praticiens;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.presentation.PanneauAccueil;
import fr.gsb.rv.dr.presentation.PanneauPraticiens;
import fr.gsb.rv.dr.presentation.PanneauRapports;
import fr.gsb.rv.dr.presentation.VueConnexion;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Session;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefNotoriete;
import fr.gsb.rv.dr.utilitaires.ComparateurDateVisite;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author developpeur
 */
public class Appli extends Application {
    
    PanneauAccueil vueAccueil = new PanneauAccueil();
    PanneauRapports vueRapports = new PanneauRapports();
    PanneauPraticiens vuePraticiens = new PanneauPraticiens();
    
    @Override
    public void start(Stage primaryStage) throws ConnexionException {
        
        BorderPane root = new BorderPane();
        StackPane pile = new StackPane();
        Scene scene = new Scene(root, 500, 300);
        
        pile.getChildren().addAll(vueAccueil, vueRapports, vuePraticiens);
        

        
        Session session = null;
        //Visiteur vis = new Visiteur();
        //vis.setMatricule("OB001");
        //vis.setNom("BOUAICHI");
        //vis.setPrenom("Oumayma");
        
        MenuBar barreMenus = new MenuBar();
        Menu menuFichier = new Menu("Fichier");
        Menu menuRapports = new Menu("Rapports");
        Menu menuPraticiens = new Menu("Praticiens");
        root.setTop(barreMenus);
        
        MenuItem itemSeConnecter = new MenuItem("se connecter");
        MenuItem itemConsulter = new MenuItem("Consulter");
        MenuItem itemHesitants = new MenuItem("Hésitants");
        MenuItem itemSeDeconnecter = new MenuItem("Se déconnecter");
        MenuItem itemQuitter = new MenuItem("Quitter");
        
        menuFichier.getItems().add(itemSeConnecter);
        menuRapports.getItems().add(itemConsulter);
        menuPraticiens.getItems().add(itemHesitants);
        menuFichier.getItems().add(itemSeDeconnecter);
        menuFichier.getItems().add(itemQuitter);
        
        barreMenus.getMenus().add(menuFichier);
        barreMenus.getMenus().add(menuRapports);
        barreMenus.getMenus().add(menuPraticiens);
        
        itemSeDeconnecter.setDisable(true);
        menuRapports.setDisable(true);
        menuPraticiens.setDisable(true);
        
        vueRapports.setVisible(false);
        vuePraticiens.setVisible(false);
        vueAccueil.setVisible(true);
        vueAccueil.toFront();
        root.setCenter(pile);
        
        /*Connection con = ConnexionBD.getConnexion();*/
       
        
        itemSeConnecter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                /*session.ouvrir(vis);
                Session session = Session.getSession();*/
                
                /*primaryStage.setTitle(vis.getNom() + " "+ vis.getPrenom());*/
               
                
                /*try {
                    vis = ModeleGsbRv.seConnecter("a17", "azerty");
                    System.out.println(vis);
                } catch (ConnexionException ex) {
                    Logger.getLogger(Appli.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                
                VueConnexion vue = new VueConnexion();
                Optional<Pair<String, String>> reponse = vue.showAndWait();
               
                if(reponse.isPresent()){
                    Visiteur vis = new Visiteur();
                    try {
                        
                    vis = ModeleGsbRv.seConnecter(reponse.get().getKey(), reponse.get().getValue());
                    
                    }
                    
                    catch (ConnexionException ex) {
                    Logger.getLogger(Appli.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    if(vis != null ){
                    
                    session.ouvrir(vis);
                    Session session = Session.getSession();
                    itemSeDeconnecter.setDisable(false);
                    menuRapports.setDisable(false);
                    menuPraticiens.setDisable(false);
                    itemSeConnecter.setDisable(true);
                    System.out.println(vis);
                    }    
                else{
                    
                    System.out.println("Echec de  l'authentification");
                    
                }    
                   
                
                    
                
                }
                

            }
        });
        
        itemSeDeconnecter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                
                itemSeDeconnecter.setDisable(true);
                menuRapports.setDisable(true);
                menuPraticiens.setDisable(true);
                itemSeConnecter.setDisable(false);
//                session.fermer();
//                primaryStage.setTitle("Appli");

            }
        });
        
        itemQuitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                 Alert alertQuitter = new Alert(Alert.AlertType.CONFIRMATION);
                 alertQuitter.setTitle("Quitter");
                 alertQuitter.setHeaderText("Demande de confirmation");
                 alertQuitter.setContentText("Voulez-vous quitter l'application ?");
                 ButtonType btnoui = new ButtonType("oui");
                 ButtonType btnnon = new ButtonType("non");
                 alertQuitter.getButtonTypes().setAll(btnoui, btnnon);
                 Optional<ButtonType> reponse = alertQuitter.showAndWait();
                 if(reponse.get() == btnoui){
                     
                     Platform.exit();
                 
                 }
            }
        });
        
        
        itemHesitants.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("[Praticiens]" + " " +  vis.getNom() + " " + vis.getPrenom());
                vueRapports.setVisible(false);
                vueAccueil.setVisible(false);
                vuePraticiens.setVisible(true);
                vueAccueil.toFront();
                
            }
        }) ;      
        
        itemConsulter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                 //System.out.println("[Rapports]" + " " +  vis.getNom() + " " + vis.getPrenom());
                vueRapports.setVisible(true);
                vueAccueil.setVisible(false);
                vuePraticiens.setVisible(false);
                vueAccueil.toFront();
                 
            }
        });
   
        
        primaryStage.setTitle("Appli");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ConnexionException {
        launch(args);
        System.out.println("\nListe des praticiens hésitants------------------------------------------------------------------");
        List<Praticiens> praticiens = ModeleGsbRv.getPraticiensHesitants();             
        for( Praticiens unPrat : praticiens){
            System.out.println(unPrat);
        }
        System.out.print("\nComparer par ordre croissant coefConfiance-----------------------------------------------------------\n");
        Collections.sort(praticiens , new ComparateurCoefConfiance() );
        for( Praticiens unPrat : praticiens){
            System.out.println(unPrat);
        }
        System.out.println("\nComparer par ordre décroissant coefNotoriete--------------------------------------------------------\n");
        Collections.sort(praticiens , new ComparateurCoefNotoriete()  );
        Collections.reverse(praticiens);
        for( Praticiens unPrat : praticiens){
            System.out.println(unPrat);
        }
        System.out.println("\nComparer par ordre chronologique inverse de dateVisite------------------------------------------------\n");
        Collections.sort(praticiens , new ComparateurDateVisite()  );
        
        for( Praticiens unPrat : praticiens){
            System.out.println(unPrat);
        }
        
        
    }
    
}
