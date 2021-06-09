package jogo.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import jogo.controller.NextTurnController;
import jogo.view.glelements.graphics2d.IComponent2DGraphics;
import jogo.view.glelements.graphics2d.IComposite2DGraphics;
import jogo.view.glelements.graphics2d.ILeaf2DGraphics;
import jogo.view.glelements.graphics2d.composite.GLButton;
import jogo.view.glelements.graphics2d.leaf.GLLabel;
import jogo.view.glelements.graphics2d.leaf.GLRectangle;

public class UI implements IComposite2DGraphics{
	
//	private int width;
//	private int height;
	private boolean active;
	
	private String id;
	private IComposite2DGraphics parent;
	private float pos_x;
	private float pos_y;
	
	private GLRectangle backdrop;
	private GLLabel population;
	private GLLabel production;
	private GLLabel food;
	private GLLabel info;
	private GLButton next_turn;
	
	private List<ILeaf2DGraphics> children_leaf;
	private List<IComposite2DGraphics> children_composite;
	
	private final float x_size = 0.4f;
	private final float y_size = 1.96f;
	private final float transparency =0.95f;
	
	public UI(IComposite2DGraphics parent) {
		this.id ="ui";
		
		this.setPosition(1-x_size, 0);
		this.parent = parent;
		parent.addChild(this);
		
		this.children_leaf = new LinkedList<ILeaf2DGraphics>();
		this.children_composite = new LinkedList<IComposite2DGraphics>();
		
		this.population = new GLLabel(
				id+"_population_text", this,
				0, 0.9f,
				"population: ", new float[] {1,1,1,1},0.5f);
		
		this.production = new GLLabel(
				id+"_production_text", this,
				0, 0.7f,
				"production: ", new float[] {1,1,1,1},0.5f);
		
		this.food = new GLLabel(
				id+"_food_text", this,
				0, 0.5f,
				"food: ", new float[] {1,1,1,1},0.5f);
		
		this.info = new GLLabel(
				id+"_info_text", this,
				0, 0.3f,
				"info: ", new float[] {1,1,1,1},0.5f);
		
		this.backdrop = new GLRectangle(
				id+"_backdrop",this,
				0,-y_size/2,
				x_size,y_size,
				0.02f,new float[] {0.09f, 0.1f, 0.1f,transparency},
				1
				);
		
		this.next_turn = new GLButton(
				id+"_btn",this,
				0.01f,(-y_size/2)+0.02f,
				"Next Turn", new float[] {1,1,1,1},
				x_size-0.02f, 0.2f,
				0.02f,new float[] {0.26f, 0.52f, 0.96f,transparency},1
				);
		
	}
	
	public void setNextTurnListener(NextTurnController controller) {
		next_turn.setOnClickObserver(controller);
	}
	
	public boolean isActive() {
		return active;
	}

	public float[] getDims() {
		return backdrop.getDims();
	}
	/*
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
			active = active? false:true;
			//System.out.println("yay");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	 */
	
	//----------- setter methods 
	
	public void setPopulation(String population_text) {
		population.setText(population_text);
	}
	
	public void setProduction(String production_text) {
		production.setText(production_text);
	}
	
	public void setFood(String food_text) {
		food.setText(food_text);
	}
	
	public void setInfo(String info_text) {
		this.info.setText(info_text);
		float height = this.info.getDims()[1];
		int num_lines = info_text.split("\n").length;
		this.info.setPosition(0, 0.3f-height*((float)num_lines-1)/num_lines);
	}

	@Override
	public void setPosition(float pos_x, float pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

	@Override
	public void setDims(float width, float height) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public float[] getPos() {
		return new float[] {pos_x,pos_y};
	}

	@Override
	public IComposite2DGraphics getParent() {
		return parent;
	}

	public IComponent2DGraphics getChild(String child_id) {
		for(ILeaf2DGraphics child:children_leaf) {
			if(child.getID().equals(child_id)) {
				return child;
			}
		}
		
		for(IComposite2DGraphics child:children_composite) {
			if(child.getID().equals(child_id)) {
				return child;
			}
			
			IComponent2DGraphics desired_child = child.getChild(child_id);
			if(desired_child != null) {
				return desired_child;
			}
		}
		
		return null;
	}

	@Override
	public void addChild(IComposite2DGraphics child) {
		children_composite.add(child);
	}
	
	public void addChild(ILeaf2DGraphics child) {
		children_leaf.add(child);
	}

	@Override
	public boolean removeChild(String child_id) {
		for(ILeaf2DGraphics child:children_leaf) {
			if(child.getID().equals(child_id)) {
				children_leaf.remove(child);
				return true;
			}
		}
		
		for(IComposite2DGraphics child:children_composite) {
			if(child.getID().equals(child_id)) {
				children_composite.remove(child);
				return true;
			}
			
			if(child.removeChild(child_id)) {
				return true;
			}
		}
		
		return false;
	}

	public List<IComponent2DGraphics> getAllChildren() {
		List<IComponent2DGraphics> all_children = new LinkedList<IComponent2DGraphics>();
		if(children_leaf !=null) {
			all_children.addAll(children_leaf);
		}
		
		if(children_composite !=null) {
			all_children.addAll(children_composite);
		}
		return all_children;
	}
	
}