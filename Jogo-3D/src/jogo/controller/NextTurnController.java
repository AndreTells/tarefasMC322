package jogo.controller;

import java.awt.event.MouseEvent;

import jogo.view.glelements.IActor;

public class NextTurnController implements IActor{

	public NextTurnController() {
		
	}
	@Override
	public void act(MouseEvent e) {
		System.out.println("next turn");	
	}
	
}
