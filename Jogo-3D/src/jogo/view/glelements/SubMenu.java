package jogo.view.glelements;

import com.jogamp.opengl.GL2;

import jogo.view.UI;

public class SubMenu extends GLElement{
	//jannkyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy change this
	private UI ui;
	private RoundedRectangle backdrop;
	private Button[] menu_items;
	private int width;
	private int height;
	
	
	public SubMenu(float pos_x, float pos_y,float[] color,UI ui,String[] button_texts,float transparency) {
		super(pos_x, pos_y);
		this.ui = ui;
		
		
		menu_items = new Button[button_texts.length];
		
		float btn_height = 0.04f;
		float menu_height = ((btn_height + 0.01f)*button_texts.length+ 0.035f);
		float radius = 0.02f;
		float menu_width = 0.12f;
		
		for(int i=0;i<button_texts.length;i++) {
			menu_items[i] = new Button(
					button_texts[i]
					,14
					,pos_x
					,pos_y+(menu_height/2.0f  +0.01f)-(btn_height*2 + 0.01f)*i - 0.035f
					,btn_height
					,menu_width-0.01f
					,radius
					,new float[] {0.26f, 0.52f, 0.96f,transparency});
			
		}
		
		this.backdrop = new RoundedRectangle(menu_height,menu_width,radius,pos_x,pos_y,color);
		// TODO Auto-generated constructor stub
		
	}
	
	public void setScreenDims(int width,int height) {
		this.width = width;
		this.height = height;
	}
	
	public Button[] getMenuItems() {
		return this.menu_items;
	}

	public void draw(GL2 gl) {
		backdrop.draw(gl);
		for(Button btn:menu_items) {
			btn.setScreenDims(width, height);
			btn.draw(gl);
		}
		
	}
	
	public void close() {
	}
	
	public int[] getDims(int container_width,int container_height) {
		return backdrop.getDims(container_width, container_height);
	}

}
