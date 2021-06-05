package jogo.model.events;
import jogo.model.board.IBoardEvent;

public interface IRBoardEvent {
	public void executeEvent(IBoardEvent board);
}
