/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Noémie
 */
public class Utilisateur {
    //attributs
    Login log;
    Connect_base con;
    ResultSet resultSet = null;
    int id;
    String nom;
    String prenom;
    String droit;
    
    public Utilisateur(Login entrar){
        //constructeur
        this.log=entrar;//on recupère le login de l'enseignant
        id=log.getIdentifiant();   //via le login on recupère l'ID de l'enseignant
    }
    
    public void initialisation(){
        con = new Connect_base();   //creation de la connexion a la base
        try{
            //requete pour le groupe de l'étudiant
            String req = "SELECT nom, prenom FROM utilisateur WHERE (id_utilisateur="+id+");";
            resultSet=con.connexionBase(req);
            //on passe à la ligne suivante car titre des variables
            resultSet.next();
            if(resultSet!=null){
                nom=resultSet.getString("nom");
                prenom=resultSet.getString("prenom");
            }
        }catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
        }
    }
    
    public String getNom() {
        return nom;
    }
  public String getPrenom() {
    return prenom;
  }
}
