package jogo.view.glelements.graphics2d;

import java.util.List;

import com.jogamp.opengl.GL2;

public interface IComposite2DGraphics extends IComponent2DGraphics{
	
	public List<IComponent2DGraphics> getAllChildren();
	
	//return the child if it is found and null otherwise
	public IComponent2DGraphics getChild(String child_id);
	
	//trhow exception if there is a child with the same name
	public void addChild(IComposite2DGraphics child);
	
	public void addChild(ILeaf2DGraphics child);
	
	//return true if child is found and removed and false otherwise
	public boolean removeChild(String child_id);
	
	public void draw(GL2 gl);

	//deletes this element and all below it
	public void dispose();

}
