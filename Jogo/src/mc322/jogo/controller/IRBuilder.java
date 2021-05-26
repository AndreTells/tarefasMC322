package mc322.jogo.controller;

import mc322.jogo.model.board.IBoardModelBuilder;
import mc322.jogo.view.board.IViewBuilder;

public interface IRBuilder {
	
	public void buildGame(IViewBuilder view,IBoardModelBuilder model, int map_height, int map_length);
}
