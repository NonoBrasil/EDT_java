/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import Vue.Connexion;

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
    private int num_droit;
    Connect_base con;   //creation de la connexion de la base
    Connexion connexao = new Connexion();
    //String ident;
    //String mdp;
    
    public Login(){
        //constructeur
        //connexao=abacaxi;
    }
    
    public int connecter(String ident, String mdp){
        //IDENTIFIANT ENTRE DANS LE FORMULAIRE DE CONNEXION
        //String ident="noemie.pasquier@edu.ece.fr";
        //ident =connexao.getEmail();
        //MOT DE PASSE ENTRE DANS LE FORMULAIRE DE CONNEXION
        //String mdp="azerty";
        //mdp=connexao.getMdp();
        System.out.println("IDENTIFIANT: "+ ident+ "MOT DE PASSE: "+mdp);
        
        try{
            //requete pour le login
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            String req="SELECT id FROM utilisateur WHERE (email='"+ident+"') AND (passwd='"+mdp+"');";
            //on créée une connexion
            con = new Connect_base();
            //recuperation du resultat de la requete
            resultSet=con.connexionBase(req);
            if(resultSet!=null){
                //il a correspondance donc il y a connexion dans le compte de l'utilisateur
                connexao.setlog(true);
                //fermer la fenetre connexion
                //connexao.dispose();
                
                System.out.println(resultSet);
                //on passe à la ligne suivante car titre des variables
                resultSet.next();
                //on recupère l'id de l'utilisateur
                int num_id= resultSet.getInt("id");
                System.out.println("ID utilisateur connecté: "+num_id);
                identifiant=num_id;
                //DEFINIR LE TYPE D'UTILISATEUR
                req="SELECT droit FROM utilisateur WHERE (id='"+num_id+"');";
                resultSet = con.connexionBase(req);
                //on passe à la ligne suivante car titre des variables
                resultSet.next();
                //on recupère le droit de l'utilisateur
                num_droit= resultSet.getInt("droit");
                System.out.println("Droit de l'utilisateur: "+num_droit);
                //redirection vers la classe de l'utilisateur en fonction de ses droits
                return 1;
            }
            else{
                //pas de match, mauvais mail ou mauvais mot de passe
                //faire un set pour modifier une variable chez paul
                return 0;
            }      
        }catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
                return 0;
        }
    }
    
    // Getter pour l'id
    public int getIdentifiant() {
        return identifiant; //retourne l'identifiant de l'utilisateur connecté
    }
    
    // Getter pour du droit
    public int getDroit() {
        return num_droit; //retourne l'identifiant de l'utilisateur connecté
    }
    
}
