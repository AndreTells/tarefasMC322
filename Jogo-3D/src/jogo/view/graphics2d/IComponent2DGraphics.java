package jogo.view.graphics2d;

import com.jogamp.opengl.GL2;

public interface IComponent2DGraphics {
	public void draw(GL2 gl);
	
	public void setPosition(float pos_x,float pos_y);
	
	public void setDims(float height,float width);

	public String getID();
	
	public void dispose();
	
	public float[] getPos();
	
	public float[] getDims();
	
	public IComposite2DGraphics getParent();
}
