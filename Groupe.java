/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

/**
 *
 * @author Noémie
 */
public class Groupe {
    //attributs
    private int id_groupe;
    Connect_base con;
    ResultSet resultSet = null;
    int nb_seances=0;
    long totalH;
    Time nb_heures;
    public Groupe(int id){
        //contructeur
        id_groupe=id;
    }
    public void dataGroupe(){
        con = new Connect_base();   //creation de la connexion a la base
        try{            
            //requete pour recuperer les sceances de l'enseignant
            String req="SELECT id_seance FROM seance_groupes WHERE id_groupe="+id_groupe+";";
            resultSet=con.connexion(req);
            if(resultSet!=null){
                //recuperation de toutes les seances avec l'id du groupe
                int id_seance=0;  //identifiant de la seance
                Time horaD=null;    //heure du debut de la seance
                Time horaF=null;    //heure de fin de la seance
                ResultSet resultSetSeance=null;
                while(resultSet.next()){
                    //on recupere l'id de la seance
                    id_seance=resultSet.getInt("id_seance");
                    System.out.println("ID SEANCE : "+id_seance);
                    //requette pour recuperer toute la data de la seance
                    req="SELECT heure_debut, heure_fin FROM seance WHERE id='"+id_seance+"';";
                    //recuperation de la reponse de la requette
                    resultSetSeance=con.connexion(req);
                    if(resultSetSeance!=null){
                        //on passe à la ligne suivante car titre des variables
                        resultSetSeance.next();
                        horaD=resultSetSeance.getTime("heure_debut");
                        horaF=resultSetSeance.getTime("heure_fin");
                        long diff= (horaF.getTime())-(horaD.getTime());
                        totalH=totalH+diff;
                    } 
                    nb_seances=nb_seances+1;
                }
                int h,m;
                h=(int) totalH/3600000;
                m=(int) (totalH%3600000) / 60000;
                nb_heures=new Time(h,m,0);
            }
            else{
                //pas de seance dans la salle
            }
            System.out.println("NOMBRE D'HEURES: "+nb_heures);
            System.out.println("NOMBRE DE SEANCES: "+nb_seances);
                        
        }catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
        }
       //fin liste_cours() 
    }

}
