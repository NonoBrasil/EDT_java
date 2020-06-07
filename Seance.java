/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.*;
import java.sql.Time;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Noémie
 */
public class Seance {
    //attribut
    private int id; //ID de la seance
    private int week;
    private Date day;
    private Time timeD;//heure de debut
    private Time timeF;//heure de fin
    private int etat;
    private int id_cours;
    private String cours;   //nom du cours
    private String groupe;  //nom du groupe
    private String enseignant;  //nom de l'enseignant
    private String salle;   //nom de la salle
    private int capacite;
    private String site;
    private int id_type;
    Connect_base con;
    ResultSet resultSet = null;
    public Seance(){
        //consctructeur
        id=0; //ID de la seance
        week=0;
        day=null;
        timeD=null;//heure de debut
        timeF=null;//heure de fin
        etat=0;
        id_cours=0;
        cours=null;   //nom du cours
        groupe=null;  //nom du groupe
        enseignant=null;  //nom de l'enseignant
        salle=null;   //nom de la salle
        capacite=0;
        site=null;
        id_type=0;
    }
    
    public void afficher_seance(){
        //affichage des données de la seance en console
        System.out.println(" ");
        System.out.println("SEANCE");
        System.out.println("ID: "+id+" SEMAINE: "+week+" JOUR: "+day+" HEURE DEBUT: "+timeD+" HEURE FIN: "+timeF+" ETAT: "+etat+" ID COURS: "+id_cours+" ID TYPE:"+id_type+" COURS: "+cours+" ENSIGNANT: "+enseignant+" SALLE: "+salle+" CAPACITE: "+capacite+" SITE: "+site);
    }
    
    public void dataPlus(){
        con = new Connect_base();   //creation de la connexion a la base
        try{
            //************** NOM DU COURS ****************
            //requete pour le nom du cours
            String req = "SELECT nom FROM cours WHERE id="+id_cours+";";
            resultSet=con.connexion(req);
            if(resultSet != null){
                //on passe à la ligne suivante car titre des variables
                resultSet.next();
                //on recupère le nom du cours
                cours=resultSet.getString("nom");
                System.out.println(" ");
                System.out.println("NOM COURS: "+cours);
            }
            
            //************** NOM DU GROUPE ****************
            
            //requete pour chercher le nom du groupe
            req="SELECT id_groupe FROM seance_groupes WHERE id_seance="+id+";";
            resultSet=con.connexion(req);
            if(resultSet != null){
                //on passe à la ligne suivante car titre des variables
                resultSet.next();
                //on recupère l'id du groupe
                int id_groupe;
                id_groupe=resultSet.getInt("id_groupe");
                System.out.println("ID GROUPE: "+id_groupe);
                //requette pour chercher le nom du groupe
                req="SELECT nom FROM groupe WHERE id="+id_groupe+";";
                ResultSet resultSet1=con.connexion(req);
                if(resultSet1 != null){
                    //on passe à la ligne suivante car titre des variables
                    resultSet1.next();
                    //on recupère le nom du groupe
                    groupe=resultSet1.getString("nom");
                    System.out.println("NOM GROUPE: "+groupe);
                }
            }
            
            //************** NOM DE L'ENSEIGNANT ****************
            //requete pour chercher l'iD de l'enseignant
            req="SELECT id_enseignant FROM seance_enseignants WHERE id_seance="+id+";";
            ResultSet resultSet2=con.connexion(req);
            if(resultSet2 != null){
                //on passe à la ligne suivante car titre des variables
                resultSet2.next();
                //on recupère l'id de l'enseignant
                int id_prof;
                id_prof=resultSet2.getInt("id_enseignant");
                //requette pour chercher le nom de l'enseignant
                req="SELECT nom FROM utilisateur WHERE id="+id_prof+";";
                ResultSet resultSet3=con.connexion(req);
                if(resultSet3 != null){
                    //on passe à la ligne suivante car titre des variables
                    resultSet3.next();
                    //on recupère le nom de l'enseignant
                    enseignant=resultSet3.getString("nom");
                    System.out.println("NOM PROF: "+enseignant);
                }
            }
            
            //************** NOM DE LA SALLE + CAPACITE + SITE ****************
                //requete pour chercher l'iD de la salle
            req="SELECT id_salle FROM seance_salles WHERE id_seance="+id+";";
            ResultSet resultSet4=con.connexion(req);
            if(resultSet4 != null){
                //on passe à la ligne suivante car titre des variables
                resultSet4.next();
                //on recupère l'id de la salle
                int id_salle;
                id_salle=resultSet4.getInt("id_salle");
                //requette pour chercher le nom de la salle
                req="SELECT nom, capacite, id_site FROM salle WHERE id="+id_salle+";";
                ResultSet resultSet5=con.connexion(req);
                if(resultSet5 != null){
                    
                    //on passe à la ligne suivante car titre des variables
                    resultSet5.next();
                    //on recupère le nom de la salle
                    salle=resultSet5.getString("nom");
                    System.out.println("NOM SALLE: "+salle);
                    //on recupère la capacité de la salle
                    capacite=resultSet5.getInt("capacite");
                    System.out.println("CAPACITE SALLE: "+capacite);
                    //on recupère l'id du site
                    int id_site=resultSet5.getInt("id_site");
                    //requette pour chercher le nom du site
                    req="SELECT nom FROM site WHERE id="+id_site+";";
                    ResultSet resultSet6=con.connexion(req);
                    if(resultSet6 != null){
                        //on passe à la ligne suivante car titre des variables
                        resultSet6.next();
                        //on recupère le nom du site
                        site=resultSet6.getString("nom"); 
                        System.out.println("NOM SITE: "+site);
                    }
                }
            }
            
        }catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
        }
    }
    
    // Getter
  public int getId() {
    return id;
  }
  public int getWeek() {
    return week;
  }
  public Date getDay() {
    return day;
  }
  public Time getTimeD() {
    return timeD;
  }
  public Time getTimeF() {
    return timeF;
  }
  public int getEtat() {
    return etat;
  }
  public int getCours() {
    return id_cours;
  }
  public int getType() {
    return id_type;
  }
  
  public String getNomCours() {
    return cours;
  }
  
  public String getNomGroupe() {
    return groupe;
  }
  
  public String getNomEnseignant() {
    return enseignant;
  }
  
  public String getNomSalle() {
    return salle;
  }
  
  public int getCapacite() {
    return capacite;
  }
  
  public String getSite() {
    return site;
  }

  // Setter
  public void setId(int newId) {
    this.id=newId;
  }
  public void setWeek(int newWeek) {
    this.week=newWeek;
  }
  public void setDay(Date newDay) {
    this.day=newDay;
  }
  public void setTimeD(Time newTimeD) {
    this.timeD=newTimeD;
  }
  public void setTimeF(Time newTimeF) {
    this.timeF=newTimeF;
  }
  public void setEtat(int newEtat) {
    this.etat=newEtat;
  }
  public void setCours(int newCours) {
    this.id_cours=newCours;
  }
  public void setType(int newType) {
    this.id_type=newType;
  }
  public void setNomCours(String newCours) {
    this.cours=newCours;
  }
  
  public void setNomGroupe(String newGroupe) {
    this.groupe=newGroupe;
  }
  
  public void setNomEnseignant(String newProf) {
    this.enseignant=newProf;
  }
  
  public void setNomSalle(String newSalle) {
    this.salle=newSalle;
  }
  
  public void setCapacite(int newC) {
    this.capacite=newC;
  }
  
  public void setSite(String newSite) {
    this.site=newSite;
  }
  
}
