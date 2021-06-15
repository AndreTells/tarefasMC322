package jogo.controller;

import jogo.model.boardmodel.IBoardModelBuilder;
import jogo.model.events.IEventManager;
import jogo.view.boardview3d.IBoard3DManager;
import jogo.view.mouse.IMouse;
import jogo.view.screen.IScreenManager;
import jogo.view.ui.IUIManager;

public interface IGameBuilder {
	public void buildGame(
			IBoardModelBuilder model,
			IScreenManager screen,
			IUIManager ui_manager,
			IMouse mouse,
			IBoard3DManager board_view_manager,
			IEventManager event_manager);
}
