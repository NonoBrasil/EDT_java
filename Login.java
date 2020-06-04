/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Noémie
 */
public class Login {
    Connection maconnexion = null;
    Statement stmt = null;
    ResultSet resultSet = null;
    //URL pour acceder à la base de donnee
    String url = "jdbc:mysql://localhost:3306/bdd";
    //identifiant pour acceder a la base de donnee
    String user = "root";
    //Mot de passe pour acceder a la base de donnee
    String pass = "";
    //numero d'identifiant de l'utilisateur
    private int identifiant;
    
    public Login(){
        //constructeur
    }
    public void connecter(){
        //IDENTIFIANT ENTRE DANS LE FORMULAIRE DE CONNEXION
        String ident="segado@edu.ece.fr";
        //MOT DE PASSE ENTRE DANS LE FORMULAIRE DE CONNEXION
        String mdp="jp";
        
        try{
            //Connexion con = new Connexion();
            //ident= con.getEmail();
            //mdp= con.getMdp();
            //création d'une connexion JDBC à la base 
            maconnexion = DriverManager.getConnection(url, user, pass);
            // création d'un ordre SQL (statement)
            stmt = maconnexion.createStatement();
            //on envoie la requete et on recupere le resultat
            resultSet = stmt.executeQuery("SELECT id FROM utilisateur WHERE (email='"+ident+"') AND (passwd='"+mdp+"');");
            //on passe à la ligne suivante car titre des variables
            resultSet.next();
            //on recupère l'id de l'utilisateur
            int num_id= resultSet.getInt("id");
            System.out.println("ID utilisateur connecté: "+num_id);
                
        }catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
        }
    }
    
    // Getter pour l'id
    public int getId() {
        return identifiant; //retourne l'identifiant de l'utilisateur connecté
    }
}
