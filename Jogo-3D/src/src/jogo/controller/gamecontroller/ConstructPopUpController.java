package jogo.controller.gamecontroller;

import java.awt.event.MouseEvent;

import jogo.view.IActor;
import jogo.view.IPopUpMenu;

public class ConstructPopUpController implements IActor{
	private NextTurnController controller;
	private int map_x;
	private int map_y;
	private String command;
	private String btn_id;
	private IPopUpMenu menu;
	
	public ConstructPopUpController(NextTurnController controller,int map_x,int map_y,String  command) {
		this.controller = controller;
		this.map_x = map_x;
		this.map_y = map_y;
		this.command = command;
	}
	
	@Override
	public void act(MouseEvent e) {
		if(command.equals("Claim")) {
			controller.model.claim(map_x, map_y);

			controller.view.setInfo(controller.model.getCellInfo(map_x, map_y));
			controller.updateStats();
			return;
		}
		else if(command.equals("None")) {
			return;
		}
		controller.model.constructComponent(command, map_x, map_y);
		controller.view.setInfo(controller.model.getCellInfo(map_x, map_y));
		controller.updateStats();
		controller.updataMap();
	}
	
	public void setPopUpMenu(IPopUpMenu menu) {
		this.menu = menu;
	}

	//throws exception if no popup menu was set
	@Override
	public void act(MouseEvent e, boolean missed) {
		if(!missed) {
			act(e);
			controller.view.closeSubMenu();
		}
		
		menu.checkItem();
		if(menu.allChecked()) {
			controller.view.closeSubMenu();
		}
		
	}

}
