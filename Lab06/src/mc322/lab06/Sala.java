package mc322.lab06;
import java.util.LinkedList;
import java.util.List;

import mc322.lab06.componentes.Brisa;
import mc322.lab06.componentes.Buraco;
import mc322.lab06.componentes.Componente;
import mc322.lab06.componentes.Fedor;
import mc322.lab06.componentes.Wumpus;

public class Sala {
	private List<Componente> componentes;
	private boolean visited;
	private int x;
	private int y;
	private Sala left;
	private Sala right;
	private Sala up;
	private Sala down;
	
	Sala(int x,int y,Sala left ,Sala up){
		this.componentes = new LinkedList<Componente>();
		this.visited = false;
		this.x = x;
		this.y = y;
		
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

	private boolean greaterThan(char icon_1, char icon_2) {
		String comparison_order = "-bfPWBO";
		int value_1 = comparison_order.indexOf(icon_1);
		int value_2 = comparison_order.indexOf(icon_2);
		if(value_1 > value_2){
			return true;
		}
		else {
			return false;
		}
	}
	
	public char getIcon() {
		if(!visited) {
			return '-';
		}
		else if(componentes.size() == 0) {
			return '#';
		}
		
		char result = '-';
		for(int i =0;i<componentes.size();i++) {
			if(!greaterThan(result,componentes.get(i).getIcon())) {
				result = componentes.get(i).getIcon();
			}
		}
		
		return result;
	}

	public void visit() {
		this.visited = true;
	}

	public void addComponente(Componente comp) {
		componentes.add(comp);
		
		if(comp instanceof Wumpus) {
			if(left!=null) {
				left.addComponente(new Fedor(left.x,left.y));
			}
			if(right!=null) {
				right.addComponente(new Fedor(right.x,right.y));
			}
			if(up!=null) {
				up.addComponente(new Fedor(up.x,up.y));
			}
			if(down!=null) {
				down.addComponente(new Fedor(down.x,down.y));
			}
		}
		
		else if(comp instanceof Buraco) {
			if(left!=null) {
				left.addComponente(new Brisa(left.x,left.y));
			}
			if(right!=null) {
				right.addComponente(new Brisa(right.x,right.y));
			}
			if(up!=null) {
				up.addComponente(new Brisa(up.x,up.y));
			}
			if(down!=null) {
				down.addComponente(new Brisa(down.x,down.y));
			}
		}
	}
	
	public void removeComponente(char icon) {
		for(int i =0;i<componentes.size();i++) {
			if(icon == componentes.get(i).getIcon()) {
				componentes.remove(i);
				break;
			}
		}
		
		if(icon == 'W') {
			if(left!=null) {
				left.removeComponente('f');
			}
			if(right!=null) {
				right.removeComponente('f');
			}
			if(up!=null) {
				up.removeComponente('f');
			}
			if(down!=null) {
				down.removeComponente('f');
			}
		}
	}

	
}
