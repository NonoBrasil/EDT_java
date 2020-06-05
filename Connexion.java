package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * @author paulm
 */

public class Connexion extends JFrame{
    private final JTextField user = new JTextField();
    private final JPasswordField mdp = new JPasswordField();
    private final JButton login = new JButton("Login");
    private final JButton annuler = new JButton("Annuler");
    float fontsize = 30.0f;
    
    public Connexion() {

        super("Connexion emploi du temps ECE");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //On arrête les processus de la fenêtre en ne fermant le programme que quand la dernière fenêtre est encore ouverte
        
        this.setMinimumSize(new Dimension(800, 600));   //On Donne une dimention minimum de 800 par 600
        this.setLocationRelativeTo(null);      //On décale la position originelle de la fenêtre en centrant sur le bureau

        JPanel mainpage = (JPanel) this.getContentPane();    //On stock un JPanel qui sera la page principale
        mainpage.setLayout(new BorderLayout());
        
        JLabel title = new JLabel("LOGIN PAGE", JLabel.HORIZONTAL);
        title.setFont (title.getFont ().deriveFont (fontsize+20));
        mainpage.add(title ,BorderLayout.NORTH);
        
        JPanel mid = new JPanel(new GridLayout(1,3));       //Pour atteindre une forme adéquate
        mid.add(new JLabel(""));
        mid.add(createbody());
        mid.add(new JLabel(""));
        mainpage.add(mid,BorderLayout.CENTER);
    }
    
    private JPanel createbody(){
        String[] utilisateurStrings = { "Elève", "Enseignant", "Admin"};   //Utilisateur
        JPanel body = new JPanel(new GridLayout(8,1));
        
        JLabel label = new JLabel("Nom d'utilisateur");
        label.setFont (label.getFont ().deriveFont (fontsize));
        body.add(label);
        user.setFont (user.getFont ().deriveFont (fontsize-10));
        body.add(user);
        
        label = new JLabel("Mot de passe");
        label.setFont (label.getFont ().deriveFont (fontsize));
        body.add(label);
        mdp.setFont (mdp.getFont ().deriveFont (fontsize-10));
        body.add(mdp);
        
        label = new JLabel("Type d'utilisateur");
        label.setFont (label.getFont ().deriveFont (fontsize));
        body.add(label);
        JComboBox selection = new JComboBox(utilisateurStrings);
        selection.setFont (selection.getFont ().deriveFont (fontsize-10));
        body.add(selection);
        
        body.add(new JLabel(""));       //Blank space
        
        JPanel choix = new JPanel(new GridLayout(1,2));
        login.setFont (login.getFont ().deriveFont (fontsize-10));
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Graphique g = new Graphique();
                g.setVisible(true);
                dispose();
            }
        });
        choix.add(login);
        
        annuler.setFont (annuler.getFont ().deriveFont (fontsize-10));
        choix.add(annuler);
        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        body.add(choix);
        
        return body;
    }
    
    public static void main(String[] args) throws Exception
    {
        NimbusLookAndFeel nimbus = new NimbusLookAndFeel();
        //SynthLookAndFeel synth = new SynthLookAndFeel();
        UIManager.setLookAndFeel( nimbus );
        
        Connexion window = new Connexion();
        window.setBackground(Color.white);
        window.setVisible(true);
                
    }

    
}
