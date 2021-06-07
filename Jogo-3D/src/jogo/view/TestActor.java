package jogo.view;

import java.awt.event.MouseEvent;

public class TestActor implements IActor{

	@Override
	public void act(MouseEvent e) {
		System.out.println("yay");
	}

	@Override
	public void act(MouseEvent e, boolean missed) {
		
	}
	
}
