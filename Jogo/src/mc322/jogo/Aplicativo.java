package mc322.jogo;

import mc322.jogo.controller.Builder;
import mc322.jogo.model.board.BoardModel;
import mc322.jogo.view.board.AppView;

public class Aplicativo {
	public static void main(String Args[]) {
		AppView view = new AppView();
		BoardModel model = new BoardModel();
		Builder builder = new Builder();
		builder.buildGame(view, model, 10, 10);
	}
}
