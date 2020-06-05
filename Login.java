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
    ResultSet resultSet = null;
    //numero d'identifiant de l'utilisateur
    private int identifiant;
    Connect_base con;   //creation de la connexion de la base
    
    public Login(){
        //constructeur
    }
    
    public void connecter(){
        //IDENTIFIANT ENTRE DANS LE FORMULAIRE DE CONNEXION
        String ident="noemie.pasquier@edu.ece.fr";
        //MOT DE PASSE ENTRE DANS LE FORMULAIRE DE CONNEXION
        String mdp="azerty";
        
        try{
            //requete pour le login
            String req="SELECT id FROM utilisateur WHERE (email='"+ident+"') AND (passwd='"+mdp+"');";
            //on créée une connexion
            con = new Connect_base();
            //recuperation du resultat de la requete
            resultSet=con.connexion(req);
            System.out.println(resultSet);
            //on passe à la ligne suivante car titre des variables
            resultSet.next();
            //on recupère l'id de l'utilisateur
            int num_id= resultSet.getInt("id");
            System.out.println("ID utilisateur connecté: "+num_id);
            identifiant=num_id;
            //DEFINIR LE TYPE D'UTILISATEUR
            req="SELECT droit FROM utilisateur WHERE (id='"+num_id+"');";
            resultSet = con.connexion(req);
            //on passe à la ligne suivante car titre des variables
            resultSet.next();
            //on recupère le droit de l'utilisateur
            int num_droit= resultSet.getInt("droit");
            System.out.println("Droit de l'utilisateur: "+num_droit);
            //redirection vers la classe de l'utilisateur en fonction de ses droits
            switch(num_droit) {
                case 1:
                    // cas droit administrateur
                    break;
                case 2:
                    // cas droit referent pedagogique
                break;
                case 3:
                    // cas droit enseignant
                    break;
                case 4:
                    // cas droit etudiant
                    
                break;
                
            }
                
        }catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
        }
    }
    
    // Getter pour l'id
    public int getIdentifiant() {
        return identifiant; //retourne l'identifiant de l'utilisateur connecté
    }
}
