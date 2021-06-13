package jogo.controller;

import jogo.controller.gamecontroller.CellController;
import jogo.controller.gamecontroller.NextTurnController;
import jogo.model.board.IBoardModelBuilder;
import jogo.view.ICellViewController;
import jogo.view.IViewBuilder;

public class Builder{
	
	public void buildGame(IViewBuilder view,IBoardModelBuilder model) {
		//BoardModel.init();
		//AppView.init();
		
		NextTurnController controller = new NextTurnController(model,view);
		view.setNextTurnController(controller);
		
		int map_length = model.getMapLength();
		int map_height = model.getMapHeight();
		
		for(int i=0;i<map_length;i++) {
			for(int j =0;j<map_height;j++) {
				CellController cell_controller = new CellController(i,j,controller);
				view.setCellListener(i, j, cell_controller);
			}
		}
		
		controller.updateStats();
		controller.updataMap();
	}

}
