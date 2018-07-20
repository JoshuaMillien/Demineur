import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.*;

public class Demineur {
    private JFrame jeu = new JFrame("Démineur");
    private JPanel container = new JPanel();

    private int nbCases;
    private int nbBombes;
    private int nbLignes;
    private int nbColonnes;
    
    private LinkedList<Cell> allButtons = new LinkedList<Cell>();
    
    public Demineur(int nbCases) {
        this.nbCases = nbCases;
        if (nbCases == 100) {
        	this.nbBombes = 30;
            GridLayout gl = new GridLayout(10, 10);
            this.nbLignes = gl.getRows();
            this.nbColonnes = gl.getColumns();
            this.container.setLayout(gl);
        }
        if (nbCases == 75) {
        	this.nbBombes = 20;
            GridLayout gl = new GridLayout(15, 5);
            this.nbLignes = gl.getRows();
            this.nbColonnes = gl.getColumns();
            this.container.setLayout(gl);
        }
        if (nbCases == 50) {
        	this.nbBombes = 10;
            GridLayout gl = new GridLayout(10, 5);
            this.nbLignes = gl.getRows();
            this.nbColonnes = gl.getColumns();
            this.container.setLayout(gl);
        }

    }

    public void display() {
        jeu.setLocationRelativeTo(null);
        jeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // the window can closes
        jeu.setExtendedState(JFrame.MAXIMIZED_BOTH); // the window opens in fullscreen mode
        int cpt = 0;
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                Cell button = new Cell(""+cpt);
            	this.allButtons.add(button);
                this.container.add(button);
                cpt +=1;
            }
        }

        this.jeu.getContentPane().add(container);
        this.jeu.setVisible(true);
        this.addBombs();
        this.setNeighbours();
        //this.displayNeighbours();
        //this.displayList();
    }
    
    public void addBombs() {
    	int x= 0;
    	for (int i = 0;i<this.nbBombes;i++) {
    		x = (int)(Math.random()* nbCases);
    		JLabel bomb = new JLabel("Bomb");
    		this.allButtons.get(x).add(bomb);
    		this.allButtons.get(x).setIsBomb(true);
    		//System.out.println(x);
    	}
    }
    
    public void setNeighbours() {
    	//remplir le tableau de voisins de chaque case
    	int j = nbColonnes;
    	int k = 40;
    	
    	for (int i = 0 ; i<this.allButtons.size(); i++) {
    		
    		if (i == 0) {
    			//first cell
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(1));
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(nbColonnes));
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(nbColonnes + 1));
    		}
    		
    		if (i == nbColonnes -1) {
    			//last cell of the first line
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(nbColonnes - 2)); // previous
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(nbColonnes + nbColonnes - 2)); // cell on the bottom left
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(nbColonnes + nbColonnes - 1)); // cell on the bottom
    		}
    		
    		if (i!=0 && i< this.nbColonnes -1) {
    			//first line of the game
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i-1)); // previous cell
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(j)); // bottom left
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(j+1)); // bottom
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(j+2)); //bottom right
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i+1)); // next cell
    			j+=1;

    		}
    		
    		if (i > nbColonnes -1 && i%nbColonnes == 0 && i <nbCases -1 - nbColonnes) {
    			//first column of the game
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i - nbColonnes)); //cell on the top
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i - nbColonnes + 1)); // cell on the top right
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i+1)); // cell on the right
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i + nbColonnes + 1)); // cell on the bottom right
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i + nbColonnes)); // cell on the bottom
    			
    		}
    		
    		if (i> nbColonnes -1 && i%nbColonnes == 0 && i != nbColonnes) {
    			//last column of the game
    			this.allButtons.get(i-1).addNeighbour(this.allButtons.get(i-1 - nbColonnes)); //cell on the top
    			this.allButtons.get(i-1).addNeighbour(this.allButtons.get(i-1 - nbColonnes - 1)); // cell on the top left
    			this.allButtons.get(i-1).addNeighbour(this.allButtons.get(i-2)); // cell on the left
    			this.allButtons.get(i-1).addNeighbour(this.allButtons.get(i-1 + nbColonnes - 1)); // cell on the bottom left
    			this.allButtons.get(i-1).addNeighbour(this.allButtons.get(i-1+ nbColonnes)); // cell on the bottom
    			
    		}
    		
    		if (i >= nbCases - nbColonnes +1 && i != nbCases -1) {
    			//last line of the game
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i-1)); // previous cell
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(k)); // top left
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(k+1)); // top
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(k+2)); //top right
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i+1)); // next cell
    			k+=1;
    			
    		}
    		
    		if (i == nbCases - nbColonnes) {
    			//first cell last line
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i + 1)); // next cell
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i - nbColonnes + 1)); // top right
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i - nbColonnes)); // top
    		}
    		
    		if (i == nbCases - 1) {
    			//last cell
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i - 1)); // previous cell
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i - nbColonnes - 1)); // top left
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i - nbColonnes)); // top
    		
    		}
    		
    		
    		if (i > nbColonnes -1 && i<nbCases-nbColonnes && i%nbColonnes != 0 && (!this.endLine(i))) {
    			//all other cells
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i - nbColonnes)); //cell on the top
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i - nbColonnes + 1)); // cell on the top right
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i + 1)); // cell on the right
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i + nbColonnes + 1)); // cell on the bottom right
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i+ nbColonnes)); // cell on the bottom
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i + nbColonnes - 1)); //cell on the bottom left
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i - 1)); // cell on the left
    			this.allButtons.get(i).addNeighbour(this.allButtons.get(i - nbColonnes - 1)); // cell on the top left
    			
    		}
    		
    	}
    }
    
    public void displayBombs() {
    	for (Cell i : this.allButtons) {
    		System.out.println(i.getIsBomb());
    	}
    }
    
    public void displayList() {
    	for (Cell i : this.allButtons) {
    		System.out.println(i.getName());
    	}
    	
    	System.out.println("--------------------------");
    }
    
    public void displayNeighbours() {
    	for (Cell i : this.allButtons) {
    		for (Cell j : i.getNeighbours()) {
    			System.out.print(" " + j.getName());    			
    		}
    		System.out.println("\n");
    	}
    	
    	System.out.println("-------------------------");
    }
    
    public boolean endLine(int i) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	if (nbCases < 100) {
	    	list.add(4);list.add(9);list.add(14);list.add(19);
	    	list.add(24);list.add(29);list.add(34);list.add(39);
	    	list.add(44);list.add(49);list.add(54);list.add(59);
	    	list.add(64);list.add(69);list.add(74);list.add(79);
	    	list.add(89);list.add(99);
	    }
    	
    	else {
    		list.add(9);list.add(19);
	    	list.add(29);list.add(39);
	    	list.add(49);list.add(59);
	    	list.add(69);list.add(79);
	    	list.add(89);list.add(99);
    	}
    	
    	if (!list.contains(i)) {
    		return false;
    	}
    	else
    		return true;
    }

}