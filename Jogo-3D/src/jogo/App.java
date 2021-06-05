package jogo;

import jogo.view.GameFrame;
import jogo.controller.Builder;
import jogo.model.board.BoardModel;

public class App {
	public static void main( String[] args ) {
		GameFrame frame = new GameFrame();
		BoardModel model = new BoardModel();
		Builder builder = new Builder();
		builder.buildGame(frame.getView(), model);
		
	}
}
