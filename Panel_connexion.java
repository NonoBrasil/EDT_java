/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Noémie
 */
public class Panel_connexion extends JPanel{
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension taille_fenetre =Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = taille_fenetre.width;
        //Rectangle bleu du formulaire
        g.setColor(new Color(0, 0, 77));
        g.fillRect(250, 150, 400, 300);
        
        g.setColor(new Color(255,255,255));//blanc
        g.fillRect(0, 0, longueur, 70); //bande blanche en haut de la page
        g.fillRect(250, 150, 400, 60);  //bande blanche du formulaire
        
        g.setColor(new Color(37, 102, 156));//bleu titre formulaire
        Font police = new Font("Marker Felt",Font.PLAIN,30); //choix de la police
        g.setFont(police);
        g.drawString("Mon Planning",370,190);        
    }
    
    public void panel_connexion(){
        //JPanel panneau1 = new JPanel();
        //couleur de fond
        this.setBackground(new Color(193, 179, 215));
        //AJOUT D'UN GRIDBAGLAYOUT AU PANEL
        //this.setLayout(new GridBagLayout());
        //permet de définir la position et la taille des elements sur le grid
        //GridBagConstraints gc = new GridBagConstraints();
        //gc.weightx = 3;//nombre de cases en abcisse
        //gc.weighty = 4;//nombre de cases en ordonnee
        //CREATION DU TITRE CONNEXION
        JLabel titre = new JLabel();
        titre.setText("CONNEXION");
        Font font = new Font("TimesRoman",Font.BOLD,40);
        titre.setFont(font);
        //this.add(titre);
        //gc.gridx=2;
        //gc.gridy=0;
        this.add(titre);
        /*
        
        */
    }
    
    public void formulaire(){
        //this.setLayout(new BorderLayout());
        //CREATION BOUTON POUR SE CONNECTER
        JButton valider = new JButton();
        valider.setText("SE CONNECTER");
        //gc.gridx=2;
        //gc.gridy=4;
        //this.add(valider,gc);
        valider.setBounds(100, 100, 100, 100);
        this.add(valider);
       
        //ZONE TEXTE POUR IDENTIFIANT
        JTextField identifiant = new JTextField(20);
        this.add(identifiant);
        //ZONE TEXTE POUR MOT DE PASSE
        JPasswordField mdp = new JPasswordField(20);
        this.add(mdp);
        
    }
}
