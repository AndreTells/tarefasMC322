package jogo.view.glelements.graphics2d.composite;

import java.util.LinkedList;
import java.util.List;

import jogo.view.glelements.graphics2d.GLElement;
import jogo.view.glelements.graphics2d.IComponent2DGraphics;
import jogo.view.glelements.graphics2d.IComposite2DGraphics;
import jogo.view.glelements.graphics2d.ILeaf2DGraphics;
import jogo.view.glelements.graphics2d.leaf.GLLabel;
import jogo.view.glelements.graphics2d.leaf.GLRectangle;

public class GLButton extends GLElement implements IComposite2DGraphics{
	private float pos_x;
	private float pos_y;

	private String id;
	private IComposite2DGraphics parent;
	
	float z_index;
	
	//private GLLabel text;
	//private GLRectangle backdrop;
	
	private List<ILeaf2DGraphics> children_leaf;
	
	public GLButton(String id,IComposite2DGraphics parent,float pos_x,float pos_y,
			String text,float[] text_color,float width,float height,float border_radius,
			float[] rectangle_color,float z_index) {
		this.id = id;
		this.parent = parent;
		parent.addChild(this);
		
		this.setPosition(pos_x, pos_y);
		this.children_leaf = new LinkedList<ILeaf2DGraphics>();
		
		
		GLLabel btn_text = new GLLabel(id+"_btn_text",this,0,0,text,text_color,z_index-0.1f);
		
		GLRectangle btn_rectangle = new GLRectangle(id+"btn_rectangle",this,0,0,width,height,border_radius,rectangle_color,z_index);

		//center text in the button
		
	}
	
	@Override
	public void setPosition(float pos_x, float pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

	@Override
	public void setDims(float width, float height) {
		this.getChild(id+"btn_rectangle").setDims(width, height);
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public float[] getPos() {
		return new float[] {pos_x,pos_y};
	}

	@Override
	public float[] getDims() {
		return this.getChild(id+"btn_rectangle").getDims();
	}

	@Override
	public IComposite2DGraphics getParent() {
		return this.parent;
	}

	@Override
	public List<IComponent2DGraphics> getAllChildren() {
		List<IComponent2DGraphics> all_children = new LinkedList<IComponent2DGraphics>();
		if(children_leaf !=null) {
			all_children.addAll(children_leaf);
		}

		return all_children;
	}

	@Override
	public IComponent2DGraphics getChild(String child_id) {
		for(ILeaf2DGraphics child:children_leaf) {
			if(child.getID().equals(child_id)) {
				return child;
			}
		}
		return null;
	}

	//feels off
	@Override
	public void addChild(IComposite2DGraphics child) {
		return ;
	}

	
	@Override
	public void addChild(ILeaf2DGraphics child) {
		this.children_leaf.add(child);
	}

	@Override
	public boolean removeChild(String child_id) {
		for(ILeaf2DGraphics child:children_leaf) {
			if(child.getID().equals(child_id)) {
				children_leaf.remove(child);
				return true;
			}
		}
		return false;
	}

}
