package jogo.view.glelements.graphics2d;

import jogo.view.IActor;

public abstract class GLElementLeaf extends GLElementComponent implements ILeaf2DGraphics{

	protected float[] color;
	protected float z_index;
	
	protected GLElementLeaf(String id,IComposite2DGraphics parent, float pos_x, float pos_y, float width, float height,float[] color,float z_index) {
		super(id,parent, pos_x, pos_y, width, height);
		if(parent!=null) {
			parent.addChild(this);
		}
		
		this.setColor(color);
		this.z_index = z_index;
	}
	
	public void setActionObserver(IActor actor) {
		mouse.addActionObservers(this.id, new GL2DObserver(this,actor,2));
	}

	public void removeActionObserver() {
		mouse.removeActionObserver(this.id);
	}
	
	public void setColor(float[] color) {
		this.color = color;
	}
	
	public void dispose() {
		this.removeActionObserver();
		if(parent!=null) {
			parent.removeChild(this.getID());
		}
	}

}
