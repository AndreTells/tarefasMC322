package jogo.model.boardmodel;

public class BoardManager implements IBoardModelBuilder{
	private static Player player;
	private static BoardModel board;
	
	public Player getPlayer() {
		if(player ==null) {
			if(board == null) {
				this.getBoard();
			}
			
			player = new Player(board);
		}
		return player;
	}
	
	public IBoardController getBoard() {
		if(board ==null) {
			board = new BoardModel();
		}
		return board;
	}
	
	public IBoardEvent getBoardEvent() {
		return board;
	}
}
