package mc322.jogo.model.components;

import java.awt.Color;

import mc322.jogo.model.CellModel;

public abstract class Component {
	protected CellModel cell;
	protected Color color;
	protected int rank;
	
	public Color getColor() {
		return this.color;
	}
	
	public boolean greaterThan(Component comp) {
		if(this.rank > comp.rank) {
			return true;
		}
		return false;
	}
	
	protected void setColor(Color color) {
		this.color = color;
	}

	public void setCell(CellModel cell) {
		this.cell = cell;
	}

}
