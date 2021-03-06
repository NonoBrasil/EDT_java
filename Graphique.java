package Vue;

/**
 * @author paulm
*/

import Model.Groupe;
import Model.Seance;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

public class Graphique extends JFrame {

    //Initialise la page vue par l'utilisateur
    private boolean grille = true;
    private boolean edt = true;
    private int groupe;
    JPanel mainpage;
    ArrayList<ArrayList<Seance>> liste_seances=new ArrayList<>();  //declaration d'une liste de seances
    int userRight;
            
    public Graphique(int droit) throws ParseException {

        super("Emploi du temps ECE");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //On arrête les processus de la fenêtre en ne fermant le programme que quand la dernière fenêtre est encore ouverte
        
        this.setMinimumSize(new Dimension(800, 600));   //On Donne une dimention minimum de 800 par 600
        this.setLocationRelativeTo(null);      //On décale la position originelle de la fenêtre en centrant sur le bureau
        this.getContentPane().setBackground( Color.white );
        
        userRight=droit;
        groupe=1;
        
        remplirtest();
        this.setJMenuBar(createMenuBar());
        mainpage = (JPanel) this.getContentPane();    //On stock un JPanel qui sera la page principale
        mainpage.setLayout(new BorderLayout());
        grid();
            
    }
    
    private void grid () throws ParseException{
        mainpage.removeAll();
        mainpage.add(createtoolbar(),BorderLayout.NORTH);
        mainpage.add(createhour(),BorderLayout.WEST);
        mainpage.add(createweek(),BorderLayout.CENTER);
    }
    
    private void liste (){
        mainpage.removeAll();
        mainpage.add(createtoolbar(),BorderLayout.NORTH);
        mainpage.add(createlist(),BorderLayout.CENTER);
    }
    
    private void report(){
        mainpage.removeAll();
        mainpage.add(createtoolbar(),BorderLayout.NORTH);
        mainpage.add(createReport(),BorderLayout.CENTER);
    }
    
    //Initialise la barre de menu
    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        
        return menuBar;
    }
    
    //Initialise la barre d'outil avec le choix du type d'affichage
    private JToolBar createtoolbar(){
        String[] typeAffStrings = { "En grille", "En liste"};   //Type d'affichage
        String[] utilisateurStrings = { "Paul Moquin", "Groupe"};   //Utilisateur
        JToolBar tb = new JToolBar();
        JComboBox typeAction = new JComboBox(typeAffStrings);
        JComboBox userAction = new JComboBox(utilisateurStrings);
        if(!grille)
            typeAction.setSelectedIndex(1);
        typeAction.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                if (ae.getStateChange() == ItemEvent.SELECTED) {
                    if(ae.getItem().equals("En liste")){
                        grille=false;
                        edt=true;
                        liste();
                    }
                    else{
                        grille=true;
                        edt=true;
                        try {
                            grid();
                        } catch (ParseException ex) {
                            Logger.getLogger(Graphique.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                }
            }
        });
        if(!edt)
            userAction.setSelectedIndex(1);
        userAction.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                if (ae.getStateChange() == ItemEvent.SELECTED) {
                    if(ae.getItem().equals("Groupe")){
                        edt=false;
                        report();
                    }
                    else{
                        edt=true;
                        grille=true;
                        try {
                            grid();
                        } catch (ParseException ex) {
                            Logger.getLogger(Graphique.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                }
            }
        });
        tb.add(typeAction);
        if(userRight!=4)
            tb.add(userAction);
        
        return tb;
    }
    
    //Initialise la grille d'une semaine
    private JPanel createweek() throws ParseException{      
        JPanel semaine = new JPanel(new GridLayout(1,6)); //On créé un Panel avec 24 case en grid
        
        for (int i=0;i<6;i++){
            semaine.add(createday(i));
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
    private JPanel createday(int j) throws ParseException{       
        JPanel jour = new JPanel(new GridLayout(15,1)); //On créé un Panel avec 14 case en grid
        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.black, 1);      //On définit la bordure
        String hour;
        String text;
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        java.sql.Time t;
        JLabel cours;
        String day = getDay(j);
        day+= " " + String.valueOf(j+1);
        JLabel title = new JLabel(day+"" ,JLabel.HORIZONTAL);
        //Icon test =  new Icon();
        title.setBorder(border);
        jour.add(title);     //On indique le jour de la semaine
        
        for (int i=0;i<13;i++){                         //On créé les 13 cases par jour (0==8h30 et 12==20h30)
            hour = "";
            text="";
            
            hour=i+8+":30:00";
                
            t = new java.sql.Time(formatter.parse(hour).getTime());
            Color col=Color.lightGray;
            
            for(int u = 0 ; u < liste_seances.get(j).size(); u++){
                if (t.equals(liste_seances.get(j).get(u).getTimeD())){
                    text=liste_seances.get(j).get(u).getNomCours()+", "+liste_seances.get(j).get(u).getNomEnseignant()+", "+liste_seances.get(j).get(u).getNomGroupe()+", "+liste_seances.get(j).get(u).getNomSalle()+", "+liste_seances.get(j).get(u).getSite()+", "+liste_seances.get(j).get(u).getCapacite();
                    col = getCol(liste_seances.get(j).get(u).getCours());
                }
            }
            
            cours = new JLabel(text ,JLabel.HORIZONTAL);
            cours.setBackground(col);
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
        //heure.setBackground(Color.white);
        //heureAligned.setBackground(Color.white);
        String text;
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(60,40));
        for (int i=0;i<14;i++){
            text = String.valueOf(i+8)+"h30";
            label = new JLabel(text ,JLabel.RIGHT);
            heure.add(label);    //On créé les 14 cases par jour
        }
        heureAligned.add(new JLabel(" " ,JLabel.RIGHT),BorderLayout.NORTH);
        heureAligned.add(new JLabel(" " ,JLabel.RIGHT),BorderLayout.SOUTH);
        heureAligned.add(heure,BorderLayout.CENTER);
        return heureAligned;
    }
    
    //Renvoie la couleur assignée au cours
    private Color getCol(int id_cours){        
        Color col;
        switch(id_cours){
            case 0:
                col=Color.white;
                break;
            case 1:
                col=Color.red;
                break;
            case 2:
                col=Color.pink;
                break;
            case 3:
                col=Color.cyan;
                break;
            default:
                col=Color.lightGray;
                break;
        }
        return col;
    }
    
    //Créé l'affichage en liste
    private JScrollPane createlist(){
        JPanel list = new JPanel(new GridLayout(liste_seances.size()*6,0));       //On créé une grille avec le nombre de cours
        String text;
        for(int j = 0 ; j < liste_seances.size(); j++){
            for(int u = 0 ; u < liste_seances.get(j).size(); u++){
                text=liste_seances.get(j).get(u).getNomCours()+", "+liste_seances.get(j).get(u).getNomEnseignant()+", "+liste_seances.get(j).get(u).getNomGroupe()+", "+liste_seances.get(j).get(u).getNomSalle()+", "+liste_seances.get(j).get(u).getSite()+", "+liste_seances.get(j).get(u).getCapacite();
                list.add(new JLabel(text));
            }
        }
       JScrollPane scroll=new JScrollPane(list);
        return scroll;
    }
    
    //Permet de fermer la fenêtre
    public void disposeScreen(){
        dispose();
    }
    
    private void remplirtest() throws ParseException{
        Seance s = new Seance();
        Seance s2 = new Seance();
        Seance s3 = new Seance();
        
        for(int j = 0 ; j < 6; j++){
            liste_seances.add(new ArrayList<>());
        }
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        java.sql.Time t;
        java.sql.Time t2;
        java.sql.Time t3;
        
        t = new java.sql.Time(formatter.parse("17:30:00").getTime());
        t2 = new java.sql.Time(formatter.parse("08:30:00").getTime());
        t3 = new java.sql.Time(formatter.parse("11:30:00").getTime());
        
        s.setId(0);
        s.setCours(0);
        s.setTimeD(t);
        s.setEtat(1);
        s.setNomCours("Java");
        s.setNomGroupe("TD11");
        s.setNomEnseignant("JP Segado");
        s.setNomSalle("P445");
        s.setCapacite(70);
        s.setSite("E2");
        liste_seances.get(1).add(s);
        
        s2.setId(1);
        s2.setCours(1);
        s2.setTimeD(t2);
        s2.setEtat(1);
        s2.setNomCours("Maths");
        s2.setNomGroupe("TD11");
        s2.setNomEnseignant("M Chaari");
        s2.setNomSalle("SC01");
        s2.setCapacite(70);
        s2.setSite("E1");
        liste_seances.get(1).add(s2);
        
        s3.setId(2);
        s3.setCours(2);
        s3.setTimeD(t3);
        s3.setEtat(1);
        s3.setNomCours("Sports");
        s3.setNomGroupe("TD11");
        s3.setNomEnseignant("M Propre");
        s3.setNomSalle("SC02");
        s3.setCapacite(30);
        s3.setSite("E1");
        liste_seances.get(3).add(s3);
    }
    
    //Créé l'écran de reporting
    private JPanel createReport(){
        JPanel rp = new JPanel(new BorderLayout());
        ArrayList<JButton> b =new ArrayList<>();
        String[] tdString = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};   //TD
        
        JComboBox tdAction = new JComboBox(tdString);
        tdAction.setSelectedIndex(groupe-1);
        tdAction.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                if (ae.getStateChange() == ItemEvent.SELECTED) {
                    groupe=Integer.parseInt((String) ae.getItem());
                    report();
                }
            }
        });
        rp.add(tdAction, BorderLayout.NORTH);
        Groupe td = new Groupe(groupe);
        td.dataGroupe();
        JLabel info = new JLabel("NOMBRE D'HEURES: "+td.getHeures()+"      "+"NOMBRE DE SEANCES: "+td.getSeances(),SwingConstants.CENTER);
        
        //info.setHorizontalAlignment(WIDTH);
        info.setFont (info.getFont ().deriveFont (30.0f));
        rp.add(info, BorderLayout.CENTER);
        
        return rp;
    }
    
    /*public static void main(String[] args) throws Exception
    {
        NimbusLookAndFeel nimbus = new NimbusLookAndFeel();
        //SynthLookAndFeel synth = new SynthLookAndFeel();
        UIManager.setLookAndFeel( nimbus );
        
        Graphique window = new Graphique();
        window.setVisible(true);
                
    }*/
}

