package Vue;

/**
 * @author paulm
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ItemListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.synth.SynthLookAndFeel;

public class Graphique extends JFrame {

    //Initialise la page vue par l'utilisateur
    public Graphique() {

        super("Emploi du temps ECE");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //On arrête les processus de la fenêtre en ne fermant le programme que quand la dernière fenêtre est encore ouverte
        
        this.setMinimumSize(new Dimension(800, 600));   //On Donne une dimention minimum de 800 par 600
        this.setLocationRelativeTo(null);      //On décale la position originelle de la fenêtre en centrant sur le bureau

        this.setJMenuBar(createMenuBar());
        JPanel mainpage = (JPanel) this.getContentPane();    //On stock un JPanel qui sera la page principale
        //SpringLayout springLayout = new SpringLayout();
        //FlowLayout flowLayout = new FlowLayout(FlowLayout.LEADING);
        //GridLayout gridLayout = new GridLayout(2,2);   //Affichage en grille dans ce cas on a 24 ligne et 1 colonne
        mainpage.setLayout(new BorderLayout());
        mainpage.add(createtoolbar(),BorderLayout.NORTH);
        mainpage.add(createhour(),BorderLayout.WEST);
        mainpage.add(createweek(7),BorderLayout.CENTER);
    }
    
    //Initialise la barre de menu
    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        
        
        return menuBar;
    }
    
    //Initialise la barre d'outil avec le choix du type d'affichage
    private JToolBar createtoolbar(){
        String[] typeAffStrings = { "En grille", "En liste"};   //Type d'affichage
        String[] utilisateurStrings = { "Paul Moquin", "TD 11", "Salle 415"};   //Utilisateur
        JToolBar tb = new JToolBar();
        JComboBox typeAction = new JComboBox(typeAffStrings);
        JComboBox userAction = new JComboBox(utilisateurStrings);
        typeAction.setSelectedIndex(0);
        userAction.setSelectedIndex(0);
        //listeAction.addActionListener(this);
        tb.add(typeAction);
        tb.add(userAction);
        return tb;
    }
    
    //Initialise la grille d'une semaine
    private JPanel createweek(int date){      
        JPanel semaine = new JPanel(new GridLayout(1,7)); //On créé un Panel avec 24 case en grid
        String j;
        for (int i=0;i<7;i++){
            j=getDay(i);
            j+= " " + String.valueOf(i+date);
            semaine.add(createday(j));
        }
        return semaine;
    }
    
    //Trouve le jour en fonction de sa position dans la semaine
    private String getDay(int i){       
        String j;
        switch(i){
                    case 0:
                        j="Lundi";
                        break;
                    case 1:
                        j="Mardi";
                        break;
                    case 2:
                        j="Mercredi";
                        break;
                    case 3:
                        j="Jeudi";
                        break;
                    case 4:
                        j="Vendredi";
                        break;
                    case 5:
                        j="Samedi";
                        break;
                    case 6:
                        j="Dimanche";
                        break;
                    default:
                        j=" ";
            }
        return j;
    }
    
    //Initialise la grille d'une journée
    private JPanel createday(String j){       
        JPanel jour = new JPanel(new GridLayout(15,1)); //On créé un Panel avec 14 case en grid
        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.black, 1);      //On définit la bordure
        String text;
        JLabel cours;
        JLabel title = new JLabel(j ,JLabel.HORIZONTAL);
        //Icon test =  new Icon();
        title.setBorder(border);
        jour.add(title);     //On indique le jour de la semaine
        
        
        for (int i=0;i<13;i++){                         //On créé les 13 cases par jour
            text = String.valueOf(i+1);
            if (i==3)
                text="Elec";
            if (i==6)
                text="Infos";
            if (i==10)
                text="Maths";
            cours = new JLabel(text ,JLabel.HORIZONTAL);
            cours.setBackground(getCol(text));
            cours.setBorder(border);
            cours.setOpaque(true);
            jour.add(cours);    
        }
        jour.add(new JLabel(" " ,JLabel.HORIZONTAL));
        return jour;
    }
    
    //Initialise la barre à gauche de l'emploi du temps avec les heures indiquées
    private JPanel createhour(){      
        JPanel heureAligned = new JPanel(new BorderLayout());
        JPanel heure = new JPanel(new GridLayout(14,1)); //On créé un Panel avec 14 case en grid
        String text;
        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.black, 1);      //On définit la bordure
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(60,40));
        /*JLabel blank = new JLabel(" " ,JLabel.RIGHT);
        blank.setPreferredSize(new Dimension(60,40));
        heure.add(blank);*/
        for (int i=0;i<14;i++){
            text = String.valueOf(i+8)+"h30";
            label = new JLabel(text ,JLabel.RIGHT);
            //label.setBorder(border);
            heure.add(label);    //On créé les 14 cases par jour
        }
        heureAligned.add(new JLabel(" " ,JLabel.RIGHT),BorderLayout.NORTH);
        heureAligned.add(new JLabel(" " ,JLabel.RIGHT),BorderLayout.SOUTH);
        heureAligned.add(heure,BorderLayout.CENTER);
        return heureAligned;
    }
    
    //Renvoie la couleur assignée au cours
    private Color getCol(String classe){        
        Color col;
        switch(classe){
            case "Elec":
                col=Color.white;
                break;
            case "Maths":
                col=Color.red;
                break;
            case "Infos":
                col=Color.pink;
                break;
            default:
                col=Color.lightGray;
                break;
        }
        return col;
    }
    
    public static void main(String[] args) throws Exception
    {
        NimbusLookAndFeel nimbus = new NimbusLookAndFeel();
        //SynthLookAndFeel synth = new SynthLookAndFeel();
        UIManager.setLookAndFeel( nimbus );
        
        Graphique window = new Graphique();
        window.setVisible(true);
                
    }
}

