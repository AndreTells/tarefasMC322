package jogo.controller;

import java.awt.event.MouseEvent;
import java.util.List;

import jogo.view.IActor;
import jogo.view.IPopUpMenu;

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
		controller.view.setInfo(controller.model.getCellInfo(map_x, map_y));
		
		if(e.getButton() == MouseEvent.BUTTON3) {
			float formated_x = ((2.0f*e.getX())/e.getComponent().getWidth()) - 1.0f;
			float formated_y = 1.0f - (2.0f * e.getY())/e.getComponent().getHeight();
			
			if(!controller.model.isClaimed(map_x, map_y)) {
				IPopUpMenu menu = controller.view.createSubMenu(formated_x,
						formated_y,
						new String[] {"Claim"});
				PopUpItemController item_controller = new PopUpItemController(controller,map_x,map_y,"Claim");
				menu.setActionObservers(new PopUpItemController[] {item_controller});
				item_controller.setPopUpMenu(menu);
				return;
			}
			
			List<String> possible = controller.model.getPossibleActions(map_x, map_y);

			String[] possible_arr = new String[possible.size()];
			possible.toArray(possible_arr);
			
			IPopUpMenu menu = controller.view.createSubMenu(formated_x,
					formated_y,
					possible_arr);
			
			PopUpItemController[] menu_item_controllers = new PopUpItemController[possible.size()]; 
			for(int i=0;i<possible.size();i++) {
				menu_item_controllers[i] = new PopUpItemController(controller,map_x,map_y,possible.get(i));
				menu_item_controllers[i].setPopUpMenu(menu);
			}
			menu.setActionObservers(menu_item_controllers);
		}
	}
	@Override
	public void act(MouseEvent e, boolean missed) {
		if(!missed) {
			act(e);
		}
	}

}
