package jogo.controller;

import java.awt.event.MouseEvent;

import jogo.view.IActor;

public class EventPopUpController implements IActor{
	private NextTurnController controller;
	
	EventPopUpController(NextTurnController controller){
		this.controller = controller;
	}
	
	@Override
	public void act(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void act(MouseEvent e, boolean missed) {
		controller.view.closeEventMenu();
	}
	
}
