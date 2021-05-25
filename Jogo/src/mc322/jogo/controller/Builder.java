package mc322.jogo.controller;

import java.awt.Color;

import mc322.jogo.model.BoardModel;
import mc322.jogo.model.MapGenerator;
import mc322.jogo.view.BoardView;

public class Builder {
	public static void buildGame() {
		BoardModel.init();
		BoardView.init();
		MapGenerator.generateRandomMap();
		
		//initializing the starting settings
		
		BoardController.updateStats();
		
		//initializing the color of all cells
		BoardController.updateColors();
				
	}
}
