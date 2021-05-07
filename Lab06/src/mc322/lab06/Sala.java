package mc322.lab06;
import java.util.LinkedList;
import java.util.List;


import mc322.lab06.componentes.Componente;
import mc322.lab06.componentes.Heroi;
import mc322.lab06.componentes.Ouro;
import mc322.lab06.componentes.Wumpus;
import mc322.lab06.componentes.Buraco;

public class Sala {
	private List<Componente> componentes;
	private boolean visited;
	private Sala left;
	private Sala right;
	private Sala up;
	private Sala down;
	
	Sala(int x,int y,Sala left ,Sala up){
		this.componentes = new LinkedList<Componente>();
		this.visited = false;
		
		this.up = null;
		this.down = null;
		this.right = null;
		this.left = null;
		this.setRelative(left, null, up, null);
		
		if(left!=null) {
			left.setRelative(null, this, null, null);
		}
		if(up!=null) {
			up.setRelative(null, null, null, this);
		}
	}

	private void setRelative(Sala left,Sala right,Sala up,Sala down) {
		this.left = left==null ?this.left:left;
		this.right = right==null ?this.right:right;
		this.up = up==null ?this.up:up;
		this.down = down==null ?this.down:down;
	}
	
	public char getIcon() {
		if(!visited) {
			return '-';
		}
		else if(componentes.size() == 0) {
			return '#';
		}
		
		Componente comp_result= null;
		for(Componente comp:componentes) {
			if(comp.greaterThan(comp_result)) {
				comp_result = comp;
			}
		}
		return comp_result.getIcon();
	}
	
	public Sala getLeft() {return left;}
	
	public Sala getRight() {return right;}
	
	public Sala getUp() {return up;}
	
	public Sala getDown() {return down;}
	
	public void visit() {
		this.visited = true;
	}
	
	public boolean addComponente(Componente comp) {
		componentes.add(comp);
		if(comp  instanceof Wumpus && (this.hasComponente(Buraco.class) || this.hasComponente(Ouro.class)) ) {
			return false;
		}
		if(comp  instanceof Buraco && (this.hasComponente(Wumpus.class) || this.hasComponente(Ouro.class))) {
			return false;
		}
		if(comp  instanceof Ouro && (this.hasComponente(Buraco.class) || this.hasComponente(Wumpus.class))) {
			return false;
		}
		
		if(comp instanceof Heroi) {
			this.visit();
		}
		
		return true;
	}
	
	public void removeComponente(Componente comp) {
		for(int i=0;i<componentes.size();i++) {
			if(componentes.get(i).equals(comp)) {
				componentes.remove(i);
			}
		}
	}

	public boolean hasComponente(Class<?> cls) {
		for(Componente comp: componentes) {
			if(comp.getClass().equals(cls)) {
				return true;
			}	
		}
		return false;
	}

	public Componente  getComponente(Class<?> cls) {
		for(Componente comp: componentes) {
			if(comp.getClass().equals(cls)) {
				return comp;
			}	
		}
		return null;
	}

}
