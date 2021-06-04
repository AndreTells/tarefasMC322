package jogo.view.glelements;

import com.jogamp.opengl.GL2;

public abstract class GLElement {
	protected float pos_x;
	protected float pos_y;
	
	protected GLElement(float pos_x,float pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}
	
	public abstract void draw(GL2 gl);
	
	public int[] getPos(int container_width, int container_height) {
		return new int[] {(int)((pos_x+1)*container_width/2.0f),(int)((pos_y+1)*container_height/2.0f)};
	}
}
