package jogo.view.glelements;

import java.awt.event.MouseEvent;

public interface IActor {
	public void act(MouseEvent e);
	public void act(MouseEvent e,boolean missed);
}
