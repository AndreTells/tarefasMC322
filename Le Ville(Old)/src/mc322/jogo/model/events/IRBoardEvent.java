package mc322.jogo.model.events;
import mc322.jogo.model.board.IBoardEvent;

public interface IRBoardEvent {
	public void executeEvent(IBoardEvent board);
}
