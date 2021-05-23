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
		int population_i = BoardModel.getPopulation();
		int production_i = BoardModel.getProduction();
		int food_i = BoardModel.getFood();
		int happiness_i = BoardModel.getHappiness();
		
		BoardView.setPopulation(population_i);
		BoardView.setProduction(production_i);
		BoardView.setFood(food_i);
		BoardView.setHappiness(happiness_i);
		
		//getting the colours of every cell
		for(int i=0;i < BoardModel.getMapHeight();i++) {
			for(int j=0; j < BoardModel.getMapLength();j++) {
				Color color = BoardModel.getCellColor(j, i);
				BoardView.setCellColor(color, j, i);
			}
		}
				
	}
}
