import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

public class GameUI implements ActionListener {
    private JFrame window = new JFrame ("Démineur");
    private JFrame game = new JFrame ("Jeu du démineur");
    private JFrame difficulty = new JFrame ("Réglage de la difficulté");

    private JButton launcher = new JButton ("Lancer le jeu");
    private JButton difficulties = new JButton("Choix de la difficulté");
    private JButton easy = new JButton ("Facile");
    private JButton medium = new JButton ("Normal");
    private JButton hard = new JButton ("difficile");
    
    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    private int height = (int)dimension.getHeight();
    private int width  = (int)dimension.getWidth();
    
    private int x = 400;
    private int y = 400;
    private int nbCases = 50;

    public void display(){
        JPanel container = new JPanel();
        GridLayout gl = new GridLayout(2,1);
        gl.setVgap(50);
        container.setLayout(gl);
        container.add(launcher);
        container.add(difficulties);
        
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // the window can closes
        window.setExtendedState(JFrame.MAXIMIZED_BOTH); // the window opens in fullscreen mode
        window.getContentPane().add(container);
        window.getContentPane().setLayout(null);
        container.setBounds(this.width/2 - x/2, this.height/2 - y/2, x, y);
        window.setVisible(true);

        launcher.addActionListener(this);
        difficulties.addActionListener(this);
    }

    public void displayDifficulty(){
        JPanel diff = new JPanel();
        GridLayout gl = new GridLayout(3,1);
        gl.setVgap(50);
        diff.setLayout(gl);
        diff.add(easy);
        diff.add(medium);
        diff.add(hard);

        this.easy.addActionListener(this);
        this.medium.addActionListener(this);
        this.hard.addActionListener(this);

        this.difficulty.setLocationRelativeTo(null);
        this.difficulty.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // the window can closes
        this.difficulty.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.difficulty.getContentPane().add(diff);
        this.difficulty.getContentPane().setLayout(null);
        diff.setBounds(this.width/2 - x/2, this.height/2 - y/2, x, y);
        this.difficulty.setVisible(true);
    }


    public void actionPerformed(ActionEvent arg0){
        if (arg0.getSource() == launcher)  {
            Demineur demineur = new Demineur(nbCases);
            demineur.display();
        }

        if (arg0.getSource() == difficulties){
            this.displayDifficulty();
        }

        if (arg0.getSource() == easy){
            this.nbCases = 50;
            this.display();
        }

        if (arg0.getSource() == medium){
            this.nbCases = 75;
            this.display();
        }

        if (arg0.getSource() == hard){
            this.nbCases = 100;
            this.display();
        }
    }
}