package jogo.view.graphics2d.leaf;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;

import jogo.view.IActor;
import jogo.view.graphics2d.GLElement;
import jogo.view.graphics2d.IComposite2DGraphics;
import jogo.view.graphics2d.ILeaf2DGraphics;

public class GLLabel extends GLElement implements ILeaf2DGraphics{
	private float pos_x;
	private float pos_y;

	private String id;
	private IComposite2DGraphics parent;
	
	private float width;
	private float height;
	
	private String text;
	private float[] color;
	
	public GLLabel(String id,IComposite2DGraphics parent,float pos_x,float pos_y,float height,float width,float font_size,String text,float[] color) {
		this.setPosition(pos_x, pos_y);
		
		this.id = id;
		this.parent = parent;
		
		this.color = color;
		this.text =text;
		
		this.setDims(height, width);
	}
	
	
	public void draw(GL2 gl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPosition(float pos_x, float pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

	@Override
	public void setDims(float height, float width) {
		this.height = height;
		this.width = width;
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public void dispose() {
		this.
		if(parent!=null) {
			parent.removeChild(this.getID());
		}
	}

	@Override
	public float[] getPos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float[] getDims() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IComposite2DGraphics getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setActionObserver(IActor actor) {
	}

	@Override
	public void removeActionObserver() {

	}
	
	@Override
	public void setColor(float[] color) {
		// TODO Auto-generated method stub
		
	}
	
}
