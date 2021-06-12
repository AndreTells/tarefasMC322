package jogo.view.glelements.graphics2d;

import jogo.view.IActor;

public interface ILeaf2DGraphics extends IComponent2DGraphics{
	
	public void setActionObserver(IActor actor,int rank);
	
	public void removeActionObserver();
	
	public void setColor(float[] color);
}