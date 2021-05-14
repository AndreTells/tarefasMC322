package mc322.lab06.componentes;

import mc322.lab06.Caverna;
import mc322.lab06.Controle;
import mc322.lab06.Sala;

public class Heroi extends ComponenteSemEfeito{
	private String name;
	private int score;
	private int num_arrows;
	private boolean alive;
	private boolean arrow_ready;
	private boolean has_gold;
	
	public Heroi(int x,int y){
		super(x,y);
		this.icon = 'P';
		this.score = 0;
		this.num_arrows = 1;
		this.has_gold = false;
		this.arrow_ready = false;
		this.alive = true;
		Controle.initControle(this);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean prepareArrow() {
		arrow_ready = !arrow_ready;
		this.score -=100;
		return arrow_ready;
	}

	public void unprepareArrow() {
		arrow_ready = false;
	}
	
	public boolean getGold() {
		Componente ouro = this.sala.getComponente(Ouro.class);
		if(ouro!=null){
			ouro.unsetSala();
			this.has_gold = true;
			return true;
		}
		return false;
	}
	
	public boolean hasGold() {
		return this.has_gold;
	}
	
	public boolean hasPreparedArrow() {
		this.num_arrows -=1;
		return this.arrow_ready;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	private boolean move(int delta_x,int delta_y) {
		Caverna.move(this,this.x+delta_x, this.y+delta_y);
		this.score -=15;
		return true;
	}
	
	public boolean moveUp() {
		return move(-1,0);
	}
	
	public boolean moveDown() {
		return move(1,0);
		
	}
	
	public boolean moveLeft() {
		return move(0,-1);
	}
	
	public boolean moveRight() {
		return move(0,1);
	}
	
	public boolean isAtStart() {
		if(x ==  0 && y ==0) {
			return true;
		}
		return false;
	}

	public boolean isInHole() {
		if(this.sala.getComponente(Buraco.class)!=null) {
			return true;
		}
		
		return false;
	}

	public Componente isWithWumpus() {
		Componente wumpus = this.sala.getComponente(Wumpus.class);
		if(wumpus!=null) {
			return wumpus;
		}
		return null;
	}
	
	public void killWumpus(Componente wumpus) {
		wumpus.unsetSala();
		this.score +=500;
	}
	
	public void killed() {
		this.alive = false;
	}
	
	public void exitCave() {
		if(isAlive()) {
			this.score +=1000;
		}
		else {
			this.score -=1000;
		}
	}

}
