package mc322.jogo.model.components;

import java.awt.Color;

import mc322.jogo.model.BoardModel;
import mc322.jogo.model.CellModel;

public abstract class Component {
	protected CellModel cell;
	protected Color color;
	protected int rank;
	protected int modifier[];
	protected int cost;
	/*
	 * modifier index
	 0-> food
	 1->production
	 2->population_limit 
	*/
	
	
	public Color getColor() {
		return this.color;
	}
	
	public int[] getModifier() {
		return modifier;
	}
	
	public boolean greaterThan(Component comp) {
		if(this.rank > comp.rank) {
			return true;
		}
		return false;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public boolean setCell(CellModel cell) {
		this.cell = cell;
		cell.addModifiers(modifier);
		return true;
	}	
	
	
	public static boolean isConstructalbe(CellModel cell) {
		return false;
	}
	
	protected void setModifier(int food,int production,int population) {
		modifier = new int[3];
		modifier[0] = food;
		modifier[1] = production;
		modifier[2] = population;
	}

}
