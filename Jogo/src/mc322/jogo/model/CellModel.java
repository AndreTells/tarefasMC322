package mc322.jogo.model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import mc322.jogo.model.components.City;
import mc322.jogo.model.components.Component;
import mc322.jogo.model.components.Grass;
import mc322.jogo.model.components.Water;

public class CellModel {
	private List<Component> components;
	private int x;
	private int y;
	
	private CellModel up;
	private CellModel down;
	private CellModel left;
	private CellModel right;
	
	public CellModel(int x,int y,CellModel left, CellModel up){
		this.x = x;
		this.y = y;
		this.components = new LinkedList<Component>();
		
		this.up = null;
		this.down = null;
		this.right = null;
		this.left = null;
		
		this.setRelative(left, right, up, down);
		
		if(left!=null) {
			left.setRelative(null, this, null, null);
		}
		if(up!=null) {
			up.setRelative(null, null, null, this);
		}
	}
	
	private void setRelative(CellModel left,CellModel right,CellModel up,CellModel down) {
		this.left = left==null ?this.left:left;
		this.right = right==null ?this.right:right;
		this.up = up==null ?this.up:up;
		this.down = down==null ?this.down:down;
	}

	public String getInfo() {
		String info = new String();
		info+="Position: ("+x+","+y+")";
		info+="\nComponentes:";
		for(Component comp:components) {
			info+="\n-"+comp.getClass().getSimpleName();
		}
		return info;
	}

	public void addComponent(Component comp) {
		components.add(comp);
	}
	
	private Component getHighestComponent() {
		if(components.size() == 0) {
			return null;
		}
		
		Component top= components.get(0);
		for(Component comp: components) {
			if(comp.greaterThan(top)) {
				top = comp;
			}
		}
		return top;
	}
	
	public boolean hasComponent(Class cls) {
		for(Component comp: components) {
			if(comp.getClass().equals(cls)) {
				return true;
			}
		}
		return false;
	}
	
	public Color getColor() {
		Component top_comp = getHighestComponent();
		if(top_comp ==null) {
			return new Color(0,0,0);
		}
		return top_comp.getColor();
	}

	public List<Component> getPossibleActions(){
		List<Component> result = new LinkedList<Component>();
		
		if(City.isConstructalbe(this)) {
			result.add(new City());
		}
		
		return result;
	}
}