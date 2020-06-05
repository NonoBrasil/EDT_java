/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.*;
import java.sql.Time;

/**
 *
 * @author Noémie
 */
public class Seance {
    //attribut
    private int id;
    private int week;
    private Date day;
    private Time timeD;//heure de debut
    private Time timeF;//heure de fin
    private int etat;
    private int id_cours;
    private int id_type;
    public Seance(){
        //consctructeur
    }
    
    public void afficher_seance(){
        //affichage des données de la seance en console
        System.out.println(" ");
        System.out.println("SEANCE");
        System.out.println("ID: "+id+" SEMAINE: "+week+" JOUR: "+day+" HEURE DEBUT: "+timeD+" HEURE FIN: "+timeF+" ETAT: "+etat+" ID COURS: "+id_cours+" ID TYPE:"+id_type);
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
  
}
