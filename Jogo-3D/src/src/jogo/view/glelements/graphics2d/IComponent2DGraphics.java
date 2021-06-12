package jogo.view.glelements.graphics2d;

import com.jogamp.opengl.GL2;

public interface IComponent2DGraphics {
	
	public void draw(GL2 gl);
	
	public void setPosition(float pos_x,float pos_y);
	
	public void setDims(float width,float height);

	public String getID();
	
	public void dispose();
	
	//returns a vector of length 2 {pos_x,pos_y}
	public float[] getPos();
	
	//returns a vector of length 2 {width,height}
	public float[] getDims();
	
	public IComposite2DGraphics getParent();
	
	public default IComponent2DGraphics getTop() {
		IComponent2DGraphics top = this;
		while(top.getParent() != null) {
			top=top.getParent();
		}
		
		return top;
	}

	public default float[] getAbsolutePos() {
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