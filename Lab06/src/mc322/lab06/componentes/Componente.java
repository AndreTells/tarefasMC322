package mc322.lab06.componentes;

import mc322.lab06.Sala;

public class Componente {
	protected char icon;
	protected Sala sala;
	protected int x;
	protected int y;
	
	Componente(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public char getIcon() {
		return icon;
	}
	
	public int []getPos() {
		return new int[] {x,y};
	}
	
	public boolean setSala(Sala sala) {
		this.sala = sala;
		if(!sala.addComponente(this)) {
			return false;
		}
		this.addEffects();
		return true;
	}
	
	public void unsetSala() {
		this.sala.removeComponente(this);
		this.removeEffects();
		this.sala = null;
	}
	
	public void setPosition(int new_pos[]) {
		this.x = new_pos[0];
		this.y = new_pos[1];
	}
	
	public boolean equals(Componente comp) {
		if(this.getClass().equals(comp.getClass()) && this.x == comp.x && this.y == comp.y) {
			return true;
		}
		
		return false;
	}
	
	public boolean greaterThan(Componente comp) {
		if(comp == null) {
			return true;
		}
		
		String comparison_order = "-bfPWBO";
		int value_1 = comparison_order.indexOf(this.icon);
		int value_2 = comparison_order.indexOf(comp.icon);
		if(value_1 > value_2){
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addEffects() {}
	public void removeEffects() {}
	
	
}
 