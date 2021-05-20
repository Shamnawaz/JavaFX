package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.metier.Praticiens;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeleGsbRv {
    
    public static Visiteur seConnecter( String matricule , String mdp ) throws ConnexionException{
        
        // Code de test à compléter
        
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "select v.vis_nom, v.vis_prenom, v.vis_matricule, v.vis_mdp from Visiteur as v inner join Travailler as t on v.vis_matricule = t.vis_matricule where jjmmaa = (select max(jjmmaa) from Travailler as t where t.vis_matricule = v.vis_matricule) and v.vis_matricule = ? and v.vis_mdp = ?";

        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setString( 1 , matricule );
            requetePreparee.setString( 2 , mdp );
            ResultSet resultat = requetePreparee.executeQuery() ;
            if( resultat.next() ){
                Visiteur visiteur = new Visiteur() ;
                visiteur.setMatricule( resultat.getString("vis_matricule") );
                visiteur.setNom( resultat.getString( "vis_nom" ) );
                visiteur.setPrenom(resultat.getString("vis_prenom"));
                
                requetePreparee.close() ;
                return visiteur ;
            }
            else {
                return null ;
            }
        }
        catch( Exception e ){
            return null ;
        } 
    }
    
    
    public static List<Praticiens> getPraticiensHesitants() throws ConnexionException{
        
         Connection connexion = ConnexionBD.getConnexion() ;
         List<Praticiens> list = new ArrayList<Praticiens>();
         
         String requete = "select p.pra_num, p.pra_nom, p.pra_ville, p.pra_coefnotoriete, r.rap_date_visite, r.rap_coeff_confiance from Praticien as p inner join RapportVisite as r on p.pra_num = r.pra_num where r.rap_coeff_confiance < 5";
         
         
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            ResultSet resultat = requetePreparee.executeQuery() ;
           
            while( resultat.next() ){
                
                Praticiens pra = new Praticiens();
                pra.setNumero(resultat.getInt("p.pra_num"));
                pra.setNom(resultat.getString("p.pra_nom"));
                pra.setVille(resultat.getString("p.pra_ville"));
                pra.setCoefNotoriete(resultat.getDouble("p.pra_coefnotoriete")); 
                Date date = resultat.getDate("r.rap_date_visite");
                pra.setDateDerniereVisite(date.toLocalDate());
                pra.setDernierCoefConfiance(resultat.getInt("r.rap_coeff_confiance"));
                list.add(pra);
                
            }
            requetePreparee.close() ;
            return list;
            
            
            
        }
        
        catch( Exception e ){
            return null ;
        } 
       
    }
}
