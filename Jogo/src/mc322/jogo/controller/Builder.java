package mc322.jogo.controller;

import java.awt.Color;

import mc322.jogo.model.board.BoardModel;
import mc322.jogo.model.mapgenerator.MapGenerator;
import mc322.jogo.view.board.AppView;

public class Builder {
	public static void buildGame() {
		BoardModel.init();
		AppView.init();
		MapGenerator.generateRandomMap();
		
		//initializing the starting settings
		
		BoardController.updateStats();
		
		//initializing the color of all cells
		BoardController.updateColors();
				
	}
}
