/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.*;

/**
 *
 * @author Noémie
 */
public class Connect_base {
    Connection maconnexion = null;
    Statement stmt = null;
    ResultSet result = null;
    //URL pour acceder à la base de donnee
    String url = "jdbc:mysql://localhost:3306/bdd?useSSL=false";
    //identifiant pour acceder a la base de donnee
    String user = "root";
    //Mot de passe pour acceder a la base de donnee
    String pass = "";
    public Connect_base(){
        
    }
    
    public ResultSet connexionBase(String requete){
        try{
            //création d'une connexion JDBC à la base 
            maconnexion = DriverManager.getConnection(url, user, pass);
            // création d'un ordre SQL (statement)
            stmt = maconnexion.createStatement();
            //on envoie la requete et on recupere le resultat
            result = stmt.executeQuery(requete);
            System.out.print(result);
            return result;
            }catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
                return  null;
            }
    }
}
