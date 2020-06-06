/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Noémie
 */
public class Salle {
    private int id_salle;
    Connect_base con;
    ResultSet resultSet = null;
    ArrayList<Seance> liste_seances=new ArrayList<>();  //declaration d'une liste de seances
    int nb_seances;
    
    public Salle(int num_salle){
        //constructeur
        id_salle = num_salle;
    }
    
    public void liste_cours(){
        con = new Connect_base();   //creation de la connexion a la base
        try{ 
            //requete pour recuperer les sceances de l'enseignant
            String req="SELECT id_seance FROM seance_salles WHERE id_salle="+id_salle+";";
            resultSet=con.connexion(req);
            if(resultSet!=null){
                //recuperation de toutes les seances avec l'id de la salle
                int id_seance = 0;
                ResultSet resultSetSeance=null;
                while(resultSet.next()){
                    //on recupere l'id de la seance
                    id_seance=resultSet.getInt("id_seance");
                    System.out.println("ID SEANCE : "+id_seance);
                    //requette pour recuperer toute la data de la seance
                    req="SELECT * FROM seance WHERE id='"+id_seance+"';";
                    //recuperation de la reponse de la requette
                    resultSetSeance=con.connexion(req);
                    //on passe à la ligne suivante car titre des variables
                    resultSetSeance.next();
                    //creation d'une seance
                    Seance sessao = new Seance();
                    //initialiser l'id de la seance "SESSAO"
                    sessao.setId(id_seance);
                    //initialiser la semaine de la seance "SESSAO"
                    sessao.setWeek(resultSetSeance.getInt("semaine"));
                    //initialiser le jour de la seance "SESSAO"
                    sessao.setDay(resultSetSeance.getDate("jour"));
                    //initialiser l'heure du debut de la seance "SESSAO"
                    sessao.setTimeD(resultSetSeance.getTime("heure_debut"));
                    //initialiser l'heure de la fin de la seance "SESSAO"
                    sessao.setTimeF(resultSetSeance.getTime("heure_fin"));
                    //initialiser l'etat de la seance "SESSAO"
                    sessao.setEtat(resultSetSeance.getInt("etat"));
                    //initialiser l'id du cours de la seance "SESSAO"
                    sessao.setCours(resultSetSeance.getInt("id_cours"));
                    //initialiser l'id du type de la seance "SESSAO"
                    sessao.setType(resultSetSeance.getInt("id_type"));
                    sessao.dataPlus();
                    //on rajoute la seance dans le tableau dynamique "liste_seances" 
                    liste_seances.add(sessao);
                }
            
                //TRI DE LA LISTE DE SEANCE 
                tri();//appel de la fonction tri
            }
            else{
                //pas de seance dans la salle
            }
                        
        }catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
        }
       //fin liste_cours() 
    }
    
    public static Comparator<Seance> ComparatorWeek = new Comparator<Seance>(){
        //comparaison de la semaine entre 2 seances
        @Override
        public int compare(Seance s1, Seance s2){
            Integer week1=s1.getWeek(); //semaine de la seance 1
            Integer week2=s2.getWeek(); //semaine de la seance 2
            return week1.compareTo(week2);  //on retourne la comparaison
        }
    };
    
    public static Comparator<Seance> ComparatorDay = new Comparator<Seance>(){
        //comparaison du jour entre 2 seances
        @Override
        public int compare(Seance s1, Seance s2){
            if(s1.getWeek()==s2.getWeek()){
                //si les seances sont de la même semaine
                Date day1=(Date) s1.getDay();   //jour de la seance 1
                Date day2=(Date) s2.getDay();   //jour de la seance 2
                return day1.compareTo(day2);    //on retourne la comparaison
            }
            else{
                //les seances sont dans une semaine differente
                //donc on ne change pas l'orde
                return 0;
            }
        }
    };
    
    public static Comparator<Seance> ComparatorHour = new Comparator<Seance>(){
        //comparaison de l'horaire entre 2 seances
        @Override
        public int compare(Seance s1, Seance s2){
            if(s1.getWeek()==s2.getWeek()){
                //si les seances sont de la même semaine
                Time time1=(Time) s1.getTimeD();    //heure du debut de la seance 1
                Time time2=(Time) s2.getTimeD();    //heure du debut de la seance 2
                return time1.compareTo(time2);  //on retourne la comparaison
            }
            else{
                //les seances sont dans une semaine differente
                //donc on ne change pas l'orde
                return 0;
            }
        }
    };
    
    public void tri(){
        afficherListe();    //affichage de la liste avant le tri
        //TRI CROISSANT EN FONCTION DE LA SEMAINE
        //appel de la fonction pour trier en fonction de la semaine
        Collections.sort(liste_seances, ComparatorWeek);  
        //appel de la fonction pour trier en fonction de l'heure
        Collections.sort(liste_seances, ComparatorHour);
        //appel de la fonction pour trier en fonction du jour
        Collections.sort(liste_seances, ComparatorDay);
        afficherListe();    //affichage de la liste apres le tri
        //fin tri()
    }
    
    public void afficherListe(){
        //fonction pour afficher le tableau dynamique "liste_seances" en console
        //pour chaque seance:
        for(int i=0;i<liste_seances.size();i++){
            //on appelle la fonction afficher_seance() pour afficher les données de la seance
            liste_seances.get(i).afficher_seance();
        }
    }
}
