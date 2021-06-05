package jogo.controller;

import java.awt.event.MouseEvent;

import jogo.view.IViewBuilder;
import jogo.view.glelements.IActor;

public class SubMenuController implements IActor{
	private IViewBuilder view;
	
	public SubMenuController(IViewBuilder view) {
		this.view = view;
	}
	
	@Override
	public void act(MouseEvent e) {
		// TODO Auto-generated method stub
		view.closeSubMenu();
	}

	@Override
	public void act(MouseEvent e, boolean missed) {
		if(!missed) {
			act(e);
		}
		view.closeSubMenu();
	}

}
