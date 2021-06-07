package jogo.view.glelements.graphics2d;

import jogo.view.IMouseObserver;
import jogo.view.IActor;
import java.awt.event.MouseEvent;

public class GL2DObserver implements IMouseObserver{
	private GLElement element;
	private IActor actor;
	private int rank;
	
	public GL2DObserver(GLElement element, IActor actor,int rank) {
		this.element = element;
		this.actor = actor;
		this.rank = rank;
	}
	
	@Override
	public boolean conditonIsMet(float mouse_x, float mouse_y) {
		float[] element_pos = element.getAbsolutePos();
		float[] dimensions = element.getDims();
		if((mouse_x>element_pos[0] && mouse_x<(element_pos[0]+dimensions[0])) 
				&& (mouse_y>element_pos[1] && mouse_y<(element_pos[1]+dimensions[1]))) {
			return true;
		}
		
		return false;
	}

	@Override
	public void performAction(MouseEvent e,boolean missed) {
		// TODO Auto-generated method stub
		actor.act(e,missed);
	}

	@Override
	public int getRank() {
		return rank;
	}

}
