package jogo.view.glelements.graphics2d.composite;

import jogo.view.IActor;
import jogo.view.glelements.graphics2d.GLElementComposite;
import jogo.view.glelements.graphics2d.IComposite2DGraphics;
import jogo.view.glelements.graphics2d.leaf.GLLabel;
import jogo.view.glelements.graphics2d.leaf.GLRectangle;

public class GLButton extends GLElementComposite {
	
	//private GLLabel text;
	private GLRectangle backdrop;
	private String text;
	
	public GLButton(
			String id,IComposite2DGraphics parent,
			float pos_x,float pos_y,
			String text,float[] text_color,
			float width,float height,float border_radius,
			float[] rectangle_color,float z_index) {
		
		super(id,parent,pos_x,pos_y,width,height);
		
		this.text = text;
		
		GLLabel btn_text = new GLLabel(id+"_text",this,
				0,0,text,text_color
				);
		
		backdrop = new GLRectangle(id+"_rectangle",this
				,0,0,width,height,
				border_radius,rectangle_color
			);

	float[] text_dims = btn_text.getDims();
	btn_text.setPosition(width/2 - text_dims[0],( height-text_dims[1])/2);
		
	}
	
	
	public void setOnClickObserver(IActor actor,int rank) {
		backdrop.setActionObserver(actor,rank);
	}
	
}
