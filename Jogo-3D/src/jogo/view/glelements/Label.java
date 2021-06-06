package jogo.view.glelements;

import java.awt.Font;
import java.awt.geom.Rectangle2D;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;

public class Label extends GLElement{
	private String[] string;
	private int font_size;
	private int width;
	private int height;
	private boolean center;
	
	public Label(String string,int font_size,float pos_x,float pos_y,float[] color,boolean center) {
		super(pos_x,pos_y);
		this.string = string.split("\n");
		this.center = center;
		this.font_size = font_size;
		this.color = color;
	}
	
	public void setText(String string) {
		this.string = string.split("\n");
	}
	
	public void setScreenDims(int width,int height) {
		this.width = width;
		this.height = height;
	}
	
	public void draw(GL2 gl) {
		
		TextRenderer renderer = new TextRenderer(new Font("Calibri", Font.BOLD, font_size));
		renderer.setColor(color[0],color[1],color[2],color[3]);
		renderer.setSmoothing(true);
		
		//translating [-1,1] position to [0,width] and [0,height]
		int x = (int)((width/2.0f)*(pos_x+1.0f));
		int y = (int)((height/2.0f)*(pos_y+1.0f));
		
			
		if(center && string.length == 1) {
			Rectangle2D bounds = renderer.getBounds(string[0]);
			x -=bounds.getWidth()/2;
			y -=bounds.getHeight()/2;
		}
		
		int font_size = renderer.getFont().getSize();
		
		for(String sentence: string) {
			renderer.beginRendering(width,height);
			renderer.setColor(1f, 1f, 1f,1f);
			renderer.draw(sentence, x,y);
			renderer.endRendering();
			y-=font_size;
		}
	}

}
