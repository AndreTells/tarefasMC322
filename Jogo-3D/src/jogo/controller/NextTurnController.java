package jogo.controller;

import java.awt.event.MouseEvent;

import jogo.model.board.IBoardModelBuilder;
import jogo.view.IActor;
import jogo.view.IPopUpMenu;
import jogo.view.IViewBuilder;

public class NextTurnController implements IActor{
	IBoardModelBuilder model;
	IViewBuilder view;
	
	public NextTurnController(IBoardModelBuilder model,IViewBuilder view) {
		this.model = model;
		this.view = view;
	}
	
	public void act(MouseEvent e) {
		if(!model.gameOver()) {

			model.calculateStats();
			updateStats();
			if(model.checkLoseConditions()) {
				view.setInfo("GAME OVER");
			}
			
			 IPopUpMenu menu = view.createEventPopUp("yay", new String[]{"ok"});
			 EventPopUpController pop_up_controller = new EventPopUpController(this);
			 menu.setActionObservers(new IActor[] {pop_up_controller});
		}
	}
	
	protected void updataMap() {
		int map_length = model.getMapLength();
		int map_height = model.getMapHeight();
		
		for(int i=0;i<map_length;i++) {
			for(int j =0;j<map_height;j++) {
				view.getCell(i, j).setObj(model.getCellHighestComponents(i, j));
				view.updateDetectionBox(i, j);
			}
		}
	}
	
	protected void updateStats() {
		view.setPopulation(model.getPopulation());
		view.setProduction(model.getProduction());
		view.setFood(model.getFood());
	}

	@Override
	public void act(MouseEvent e, boolean missed) {
		if(!missed) {
			act(e);
		}
	}
	
}
