package mc322.jogo.controller;

import mc322.jogo.model.board.IBoardModelBuilder;
import mc322.jogo.view.board.IBoardViewController;
import mc322.jogo.view.board.IViewBuilder;

public class Builder implements IRBuilder{
	
	public void buildGame(IViewBuilder view,IBoardModelBuilder model, int map_height, int map_length) {
		//BoardModel.init();
		//AppView.init();
		
		BoardController controller = new BoardController();
		controller.setBoardPanel(view.getBoardPanel());
		controller.setBoardModel(model);
		
		IBoardViewController board = view.getBoardPanel();
		
		for(int i=0;i<map_length;i++) {
			for(int j =0;j<map_height;j++) {
				CellController cell_controller = new CellController(i,j,controller);
				board.setCellController(cell_controller, i, j);
			}
		}
	
		
		controller.updateStats();
		controller.updateColors();
				
	}

}
