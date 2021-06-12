package jogo.view.glelements.graphics2d;

import com.jogamp.opengl.GL2;

import jogo.view.glelements.GLMouse;

public abstract class GLElementComponent implements IComponent2DGraphics{
	protected static GLMouse mouse;
	protected float pos_x;
	protected float pos_y;

	protected String id;
	protected IComposite2DGraphics parent;

	protected float width;
	protected float height;
	
	GLElementComponent(String id,IComposite2DGraphics parent,float pos_x,float pos_y,float width,float height){
		this.setPosition(pos_x, pos_y);
		this.setDims(width, height);
		
		this.parent = parent;
		this.id = id;
	}
	

	public static void setMouse(GLMouse mouse_i) {
		mouse = mouse_i;
	}

	abstract public void draw(GL2 gl);

	public void setPosition(float pos_x,float pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

	public void setDims(float width,float height) {
		this.width = width;
		this.height = height;
	}

	public String getID() {
		return this.id;
	}

	abstract public void dispose();

	//returns a vector of length 2 {pos_x,pos_y}
	public float[] getPos() {
		return new float[] {pos_x,pos_y};
	}

	//returns a vector of length 2 {width,height}
	public float[] getDims() {
		return new float [] {width,height};
	}

	public IComposite2DGraphics getParent() {
		return parent;
	}

	public IComposite2DGraphics getTop() {
		IComposite2DGraphics top = this.parent;
		while(top.getParent() != null) {
			top=top.getParent();
		}
		
		return top;
	}

	public float[] getAbsolutePos() {
		float[] element_pos = this.getPos();
		IComposite2DGraphics parent = this.getParent();
		if(parent!=null) {
			float[] parent_pos = parent.getAbsolutePos();
			element_pos[0] += parent_pos[0];
			element_pos[1] += parent_pos[1];
			return element_pos;
		}
		else {
			return element_pos;
		}
	}
}