import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Cell extends JButton {
	
	private boolean isBomb;
	private int nearBombs;
	private String name;
	
	private ArrayList<Cell> neighbours;
	
	public Cell(String name) {
		super(name);
		this.name = name;
		this.nearBombs = 0;
		this.isBomb = false;
		this.neighbours = new ArrayList<Cell>();
	}
	
	public void setIsBomb(boolean value) {
		this.isBomb = value;
	}
	
	public boolean getIsBomb() {
		return this.isBomb;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Cell> getNeighbours(){
		return this.neighbours;
	}
	
	public void addNeighbour(Cell cell) {
		this.neighbours.add(cell);
	}
}
