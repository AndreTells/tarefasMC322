package jogo.view.glelements.graphics2d.composite;

import jogo.view.IActor;
import jogo.view.glelements.graphics2d.GLElementComposite;
import jogo.view.glelements.graphics2d.IComposite2DGraphics;
import jogo.view.glelements.graphics2d.leaf.GLRectangle;

public class GLPopMenu extends GLElementComposite{
	private static float item_height = 0.1f;
	private GLButton[] items;
	
	public GLPopMenu(String id, IComposite2DGraphics parent,
			float pos_x, float pos_y, float width,
			String[] button_text
			) {
		super(id, parent, pos_x, pos_y-(item_height*(button_text.length)+(button_text.length+1)*0.01f), width, item_height*(button_text.length)+(button_text.length+1)*0.01f);
		
		System.out.println(pos_x+" "+pos_y);
		
		GLRectangle backdrop = new GLRectangle(id+"_backdrop",this,0,0,width, item_height*button_text.length+(button_text.length+1)*0.01f,0.02f,new float[] {0.09f, 0.1f, 0.1f,0.95f});
		
		float y = item_height*(button_text.length-1)+0.01f;
		items = new GLButton[button_text.length];
		for(int i=0;i<button_text.length;i++) {
			items[i] = new GLButton(id+"_btn"+i,this,
					0.01f,y,
					button_text[i],new float[] {1,1,1,1},
					width-0.02f,item_height,0.02f,
					new float[] {0.26f, 0.52f, 0.96f,0.95f},3);
			y -= item_height;
			y -=0.01f;
		}
	}
	
	public void setActionObservers(IActor actors[]) {
		
		for(int i=0;i<actors.length;i++) {
			this.items[i].setOnClickObserver(actors[i]);
		}
	}
}
