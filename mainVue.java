/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author paulm
 */
public class mainVue {
    
    public static void main(String[] args) throws Exception
    {
        NimbusLookAndFeel nimbus = new NimbusLookAndFeel();
        UIManager.setLookAndFeel( nimbus );
        
        Connexion window = new Connexion();
        window.setBackground(Color.white);
        window.setVisible(true);
                
    }
}
