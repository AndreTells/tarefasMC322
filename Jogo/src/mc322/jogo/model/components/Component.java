package mc322.jogo.model.components;

import java.awt.Color;

import mc322.jogo.model.CellModel;

public abstract class Component {
	protected CellModel cell;
	protected Color color;
	protected int rank;
	protected int modifier[];
	/*
	 * modifier index
	0 ->population
	1 ->food 
	2 ->production
	3 ->happiness
	4 ->wet
	*/
	
	public Color getColor() {
		return this.color;
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

	public void setCell(CellModel cell) {
		this.cell = cell;
		cell.addModifiers(modifier);
	}
	
	public static boolean isConstructalbe(CellModel cell) {
		return false;
	}
	
	protected void setModifier(int population, int food,int production, int happiness, int wet) {
		modifier[0] = population;
		modifier[1] = food;
		modifier[2] = production;
		modifier[3] = happiness;
		modifier[4] = wet;
	}

}
