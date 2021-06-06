package jogo.view.glelements;

import com.jogamp.opengl.GL2;

public class Button extends GLElement{
	private RoundedRectangle button_backdrop;
	private Label button_text;
	private int container_width;
	private int container_height;
	
	public Button(String text,int font_size,float pos_x,float pos_y,float height,float width,float radius,float[] color) {
		super(pos_x,pos_y);
		this.button_backdrop = new RoundedRectangle(height,width,radius,pos_x,pos_y,color);
		this.button_text = new Label(text,font_size,pos_x,pos_y,new float[] {1f,1f,1f,1f},true);
	}
	
	public void setScreenDims(int container_width,int container_height) {
		this.container_width = container_width;
		this.container_height = container_height;
	}
	
	public int[] getDims(int container_width,int container_height) {
		return button_backdrop.getDims(container_width, container_height);
	}
	
	public void draw(GL2 gl) {
		// TODO Auto-generated method stub
		button_backdrop.draw(gl);
		button_text.setScreenDims(container_width, container_height);
		button_text.draw(gl);
	}

}
