package jogo.controller;

import jogo.controller.gamecontroller.CellController;
import jogo.controller.gamecontroller.TurnController;
import jogo.model.boardmodel.IBoardController;
import jogo.model.boardmodel.IBoardModelBuilder;
import jogo.model.boardmodel.IPlayerController;
import jogo.model.events.IEventManager;
import jogo.view.boardview3d.IBoard3DManager;
import jogo.view.boardview3d.ICellViewController;
import jogo.view.mouse.IMouse;
import jogo.view.screen.IScreenManager;
import jogo.view.ui.IStats;
import jogo.view.ui.IUIManager;

public class Builder implements IGameBuilder{
	
	public void buildGame(
			IBoardModelBuilder model,
			IScreenManager screen,
			IUIManager ui_manager,
			IMouse mouse,
			IBoard3DManager board_view_manager,
			IEventManager event_manager) {
		//BoardModel.init();
		//AppView.init();
		
		
		IBoardController board_model = model.getBoard();
		IPlayerController player = model.getPlayer();
		
		//setups the screen
		screen.set2D(ui_manager.getContainer());
		screen.set3D(board_view_manager);
		
		screen.setMouse(mouse);
		ui_manager.setMouse(mouse);
		
		mouse.addDraggObservers("camera-dragg", board_view_manager.getCameraDraggObserver());
		mouse.addMotionObservers("camera-motion", board_view_manager.getCameraMotionObserver());
		mouse.addActionObservers("picker", board_view_manager.getCellPicker());
		
		IStats stats_view = ui_manager.getUI();
		
		event_manager.setBoard(model.getBoardEvent());
		
		TurnController controller = new TurnController(player,board_model,stats_view,board_view_manager,event_manager);
		stats_view.setTurnListener(controller);
		
		int map_length = board_model.getMapLength();
		int map_height = board_model.getMapHeight();
		
		for(int i=0;i<map_length;i++) {
			for(int j =0;j<map_height;j++) {
				CellController cell_controller = new CellController(i,j,controller);
				board_view_manager.setCellActionObserver(i, j, cell_controller);
			}
		}
		
		controller.updateStats();
		controller.updataMap();
	}

}
