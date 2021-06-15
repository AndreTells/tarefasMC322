package jogo.model.board;

import java.util.List;

import jogo.model.board.components.ConstructableComponent;
import jogo.model.events.EventManager;

public interface IBoardModelBuilder {
	public Player getPlayer();
	
	public IBoardController getBoard();
}
