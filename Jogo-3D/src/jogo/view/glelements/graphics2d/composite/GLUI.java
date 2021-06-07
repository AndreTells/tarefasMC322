package jogo.view.glelements.graphics2d.composite;

import java.util.LinkedList;
import java.util.List;

import jogo.view.glelements.graphics2d.GLElement;
import jogo.view.glelements.graphics2d.IComponent2DGraphics;
import jogo.view.glelements.graphics2d.IComposite2DGraphics;
import jogo.view.glelements.graphics2d.ILeaf2DGraphics;

public class GLUI extends GLElement implements IComposite2DGraphics{
	private float pos_x;
	private float pos_y;
		
	private List<ILeaf2DGraphics> children_leaf;
	private List<IComposite2DGraphics> children_composite;

	private String id;
	private IComposite2DGraphics parent;
	
	private float width;
	private float height;
	
	public GLUI(String id,float height,float width) {
		this.setPosition(0,0);
		this.setDims(height, width);
		this.id = id;
		this.parent = null;
		this.children_leaf = new LinkedList<ILeaf2DGraphics>();
		this.children_composite = new LinkedList<IComposite2DGraphics>();
		
		
	}

	public void setPosition(float pos_x, float pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

	public void setDims(float width,float height) {
		this.height = height;
		this.width = width;
	}

	public String getID() {
		return id;
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

	public float[] getPos() {
		return new float[] {pos_x,pos_y};
	}
	
	public float[] getDims() {
		return new float[] {width,height};
	}

	public IComposite2DGraphics getParent() {
		return this.parent;
	}


}
