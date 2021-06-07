package jogo.view.glelements.graphics2d;

import java.util.List;

import com.jogamp.opengl.GL2;

public interface IComposite2DGraphics extends IComponent2DGraphics{
	
	public List<IComponent2DGraphics> getAllChildren();
	
	//return the child if it is found and null otherwise
	public IComponent2DGraphics getChild(String child_id);
	
	public void addChild(IComposite2DGraphics child);
	
	public void addChild(ILeaf2DGraphics child);
	
	//return true if child is found and removed and false otherwise
	public boolean removeChild(String child_id);
	
	default public void draw(GL2 gl) {
		gl.glPushMatrix();
		float[] pos = this.getPos();
		gl.glTranslatef(pos[0], pos[1],0);
		
		for(IComponent2DGraphics element: this.getAllChildren()) {
			element.draw(gl);
		}
		
		gl.glPopMatrix();
	}

	//deletes this element and all below it
	default public void dispose() {
		for(IComponent2DGraphics element: this.getAllChildren()) {
			element.dispose();
		}
		
		IComposite2DGraphics parent = this.getParent();
		if(parent!=null) {
			parent.removeChild(this.getID());
		}
	}

}
