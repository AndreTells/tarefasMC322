package jogo.model.boardmodel;

public interface IBoardModelBuilder {
	public Player getPlayer();
	
	public IBoardController getBoard();
	
	public IBoardEvent getBoardEvent();
}
