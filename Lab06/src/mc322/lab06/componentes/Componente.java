package mc322.lab06.componentes;

public class Componente {
	protected char icon;
	protected int x;
	protected int y;
	
	Componente(int x,int y){
		this.x = x; 
		this.y =y;
	}
	
	public char getIcon() {
		return icon;
	}

}
