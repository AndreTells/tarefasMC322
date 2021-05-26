package mc322.jogo.model.board;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import mc322.jogo.model.board.components.City;
import mc322.jogo.model.board.components.Component;
import mc322.jogo.model.board.components.ConstructableComponent;
import mc322.jogo.model.board.components.Farm;
import mc322.jogo.model.board.components.Grass;
import mc322.jogo.model.board.components.LumberMill;
import mc322.jogo.model.board.components.Water;

public class CellModel {
	private List<Component> components;
	private int x;
	private int y;
	private int modifier[];
	private boolean claimed;
	/*
	 * modifier index
	 0-> food
	 1->production
	 2->population_limit
	*/
	
	private CellModel up;
	private CellModel down;
	private CellModel left;
	private CellModel right;
	
	public CellModel(int x,int y,CellModel left, CellModel up){
		this.x = x;
		this.y = y;
		this.components = new LinkedList<Component>();
		this.claimed = false;
		
		this.modifier = new int[3];
		for(int i=0;i<modifier.length;i++) {
			modifier[i] = 0;
		}
		
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
		for(Component comp:components) 	{
			info+="\n-"+comp.getClass().getSimpleName();
		}
		info+="\nHousing:"+modifier[2];
		info+="\nProduction:"+modifier[1];
		info+="\nFood:"+modifier[0];
		return info;
	}
	
	public void addComponent	(Component comp) {
		if(!this.hasComponent(comp.getClass())) {
			components.add(comp);
			if(this.claimed) {
				BoardModel.removeModifiers(modifier);
				comp.setCell(this);
				BoardModel.addModifier(modifier);
			}
			else {
				this.addModifiers(comp.getModifier());
			}
		}		
	}
	
	public void removeComponent(Class cls) {
		for(int i =0; i < components.size();i++) {
			Component comp = components.get(i);
			if(cls.isInstance(comp)) {
				this.removeModifiers(comp.getModifier());
				if(this.claimed) {
					BoardModel.removeModifiers(comp.getModifier());
				}
				components.remove(i);
				break;
			}
		}
	}
	
	
	public void addModifiers(int external_modifier[]) {
		for(int i=0;i<modifier.length;i++) {
			modifier[i] += external_modifier[i];
		}
	}
	
	public void removeModifiers(int external_modifier[]) {
		for(int i=0;i<modifier.length;i++) {
			modifier[i] -= external_modifier[i];
		}
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

	public List<ConstructableComponent> getPossibleActions(){
		List<ConstructableComponent> result = new LinkedList<ConstructableComponent>();
		
		if(City.isConstructalbe(this)) {
			result.add(new City());
		}
		if(LumberMill.isConstructalbe(this)) {
			result.add(new LumberMill());
		}
		if(Farm.isConstructalbe(this)) {
			result.add(new Farm());
		}
		
		return result;
	}

	public boolean isClaimed() {
		return claimed;
	}

	public void claim() {
		this.claimed = true;
		BoardModel.addModifier(modifier);
	}
	
	public boolean isIrigated() {
		if(up!=null && up.hasComponent(Water.class)) {
			return true;
		}
		if(down!=null && down.hasComponent(Water.class)) {
			return true;
		}
		if(left!=null && left.hasComponent(Water.class)) {
			return true;
		}
		if(right!=null && right.hasComponent(Water.class)) {
			return true;
		}
		return false;
	}
	
	public CellModel getUp() {return this.up;}
	public CellModel getDown() {return this.down;}
	public CellModel getLeft() {return this.left;}
	public CellModel getRight() {return this.right;}
	

}