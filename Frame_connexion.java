/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Noémie
 */
public class Frame_connexion extends JFrame{
    public Frame_connexion(){
        this.setSize(900, 1050);
        //pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Panel_connexion());
        this.setLocationRelativeTo(null);
    }
    public void connexion_fond(){
        Panel_connexion pan = new Panel_connexion();
        pan.panel_connexion();
        this.setContentPane(pan);
        //Panel_connexion pan2 = new Panel_connexion();
        pan.formulaire();
        this.setContentPane(pan);
        //this.add(pan);
        //this.setVisible(true);
        //return this;
    }
}
