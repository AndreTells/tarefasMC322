package jogo.controller;

import java.awt.event.MouseEvent;

import jogo.view.IActor;

public class SubMenuItemController implements IActor{
	private NextTurnController controller;
	private int map_x;
	private int map_y;
	private String command;
	
	public SubMenuItemController(NextTurnController controller,int map_x,int map_y,String  command) {
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
			controller.view.closeSubMenu();
			return;
		}
		else if(command.equals("None")) {
			controller.view.closeSubMenu();
			return;
		}
		controller.model.constructComponent(command, map_x, map_y);
		controller.view.setInfo(controller.model.getCellInfo(map_x, map_y));
		controller.updateStats();
		controller.updataMap();
	}

	@Override
	public void act(MouseEvent e, boolean missed) {
		if(!missed) {
			act(e);
		}
		controller.view.closeSubMenu();
	}

}
