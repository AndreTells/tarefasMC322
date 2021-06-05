package jogo.controller;

import java.awt.event.MouseEvent;
import java.util.List;

import jogo.view.glelements.IActor;

public class CellController implements IActor{
	private int map_x;
	private int map_y;
	private NextTurnController controller;
	
	public CellController(int map_x,int map_y,NextTurnController controller) {
		this.map_x = map_x;
		this.map_y = map_y;
		this.controller = controller;
	}
	@Override
	public void act(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(map_x+" "+map_y);
		if(e.getButton() == e.BUTTON1) {
			controller.view.setInfo(controller.model.getCellInfo(map_x, map_y));
		}
		
		else if(e.getButton() == e.BUTTON3) {
			if(!controller.model.isClaimed(map_x, map_y)) {
				controller.view.createSubMenu(e.getX()+40,
						e.getComponent().getHeight() -  e.getY()-20,
						new String[] {"Claim"},
						new SubMenuController(controller.view),
						new SubMenuItemController[] {new SubMenuItemController(controller,map_x,map_y,"Claim")});
				return;
			}
			
			List<String> possible = controller.model.getPossibleActions(map_x, map_y);
			SubMenuItemController[] menu_item_controllers = new SubMenuItemController[possible.size()]; 
			for(int i=0;i<possible.size();i++) {
				menu_item_controllers[i] = new SubMenuItemController(controller,map_x,map_y,possible.get(i));
			}
			String[] possible_arr = new String[possible.size()];
			possible.toArray(possible_arr);
			controller.view.createSubMenu(e.getX()+40,
					e.getComponent().getHeight() -  e.getY()-20,
					possible_arr,
					new SubMenuController(controller.view),
					menu_item_controllers);
		}
	}
	@Override
	public void act(MouseEvent e, boolean missed) {
		if(!missed) {
			act(e);
		}
	}

}
