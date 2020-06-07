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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Noémie
 */
public class Etudiant {
    //attributs
    Login log;
    Connect_base con;
    ResultSet resultSet = null;
    private int id_etudiant;
    private int id_groupe;
    ArrayList<Seance> liste_seances=new ArrayList<>();  //declaration d'une liste de seances
    ArrayList<ArrayList<Seance>>liste_jours=new ArrayList<>();
    int nb_seances;
    
    ArrayList<Seance> lundi=new ArrayList<>();
    ArrayList<Seance> mardi=new ArrayList<>();
    ArrayList<Seance> mercredi=new ArrayList<>();
    ArrayList<Seance> jeudi=new ArrayList<>();
    ArrayList<Seance> vendredi=new ArrayList<>();
    ArrayList<Seance> samedi=new ArrayList<>();
        
    public Etudiant(Login entrar){
        //constructeur
        this.log=entrar;//on recupère le login de l'etudiant
        id_etudiant=log.getIdentifiant();   //via le login on recupère l'ID de l'étudiant
    }
    
    public void liste_cours(){
        con = new Connect_base();   //creation de la connexion a la base
        try{
            
            //requete pour le groupe de l'étudiant
            String req = "SELECT id_groupe FROM etudiant WHERE (id_utilisateur="+id_etudiant+");";
            resultSet=con.connexionBase(req);
            //on passe à la ligne suivante car titre des variables
            resultSet.next();
            //on recupère le groupe de l'etudiant
            id_groupe=resultSet.getInt("id_groupe");
            
            //requete pour recuperer les sceances du groupe
            req="SELECT id_seance FROM seance_groupes WHERE id_groupe="+id_groupe+";";
            resultSet=con.connexionBase(req);
            //recuperation de toutes les seances avec l'id de la seance
            int id_seance = 0;
            ResultSet resultSetSeance=null;
            while(resultSet.next()){
                //on recupere l'id de la seance
                id_seance=resultSet.getInt("id_seance");
                System.out.println("ID SEANCE : "+id_seance);
                //requette pour recuperer toute la data de la seance
                req="SELECT * FROM seance WHERE id='"+id_seance+"';";
                //recuperation de la reponse de la requette
                resultSetSeance=con.connexionBase(req);
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
            
            //TRI DE LA LISTE EN FONCTION DES JOURS
            organizacao();
            afficherSemaine();
            
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
    
    public void afficherSemaine(){
        //fonction pour afficher le tableau dynamique "liste_seances" en console
        //pour chaque seance:
        for(int i=0;i<lundi.size();i++){
            //on appelle la fonction afficher_seance() pour afficher les données de la seance
            System.out.println(" ");
            System.out.println("LUNDI : ");
            lundi.get(i).afficher_seance();
        }
        
        for(int i=0;i<mardi.size();i++){
            //on appelle la fonction afficher_seance() pour afficher les données de la seance
            System.out.println(" ");
            System.out.println("MARDI : ");
            mardi.get(i).afficher_seance();
        }
        
        for(int i=0;i<mercredi.size();i++){
            //on appelle la fonction afficher_seance() pour afficher les données de la seance
            System.out.println(" ");
            System.out.println("MERCREDI : ");
            mercredi.get(i).afficher_seance();
        }
        
        for(int i=0;i<jeudi.size();i++){
            //on appelle la fonction afficher_seance() pour afficher les données de la seance
            System.out.println(" ");
            System.out.println("JEUDI : ");
            jeudi.get(i).afficher_seance();
        }
        
        for(int i=0;i<vendredi.size();i++){
            //on appelle la fonction afficher_seance() pour afficher les données de la seance
            System.out.println(" ");
            System.out.println("VENDREDI : ");
            vendredi.get(i).afficher_seance();
        }
        
        for(int i=0;i<samedi.size();i++){
            //on appelle la fonction afficher_seance() pour afficher les données de la seance
            System.out.println(" ");
            System.out.println("SAMEDI : ");
            samedi.get(i).afficher_seance();
        }
    }
    
    public void organizacao(){
        Seance porra = new Seance();
        int day=8;
        Date jour=new Date(2020, 6, day);
        Date now;
        //Date before;
        int dia=1;
        for(int i=0; i<liste_seances.size(); i++){
            porra=liste_seances.get(i);
            now=(Date) porra.getDay();
            if(now==jour){
                System.out.println("AZERTYUIOPAZERTYUIO");
                if(dia==1){
                    //lundi
                    lundi.add(porra);
                }
                if(dia==2){
                    //mardi
                    mardi.add(porra);
                }
                if(dia==3){
                    //mercredi
                    mercredi.add(porra);
                }
                if(dia==4){
                    //jeudi
                    jeudi.add(porra);
                }
                if(dia==5){
                    //vendredi
                    vendredi.add(porra);
                }
                if(dia==6){
                    //samedi
                    samedi.add(porra);
                }
                
                
            }
            else{
                day=day+1;
                jour.setDate(day);
                System.out.println(jour);
                dia=dia+1;
                if(dia==1){
                    //lundi
                    lundi.add(porra);
                }
                if(dia==2){
                    //mardi
                    mardi.add(porra);
                }
                if(dia==3){
                    //mercredi
                    mercredi.add(porra);
                }
                if(dia==4){
                    //jeudi
                    jeudi.add(porra);
                }
                if(dia==5){
                    //vendredi
                    vendredi.add(porra);
                }
                if(dia==6){
                    //samedi
                    samedi.add(porra);
                }
            }
            if(dia==7){
                dia=1;
            }
            
            //fin du for
        }
        liste_jours.add(lundi);
        liste_jours.add(mardi);
        liste_jours.add(mercredi);
        liste_jours.add(jeudi);
        liste_jours.add(vendredi);
        liste_jours.add(samedi);
    }
}
