package mc322.jogo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mc322.jogo.model.BoardModel;
import mc322.jogo.view.BoardView;

public class BoardController implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int population_i = BoardModel.getPopulation();
		int production_i = BoardModel.getProduction();
		int food_i = BoardModel.getFood();
		int happiness_i = BoardModel.getHappiness();
		
		BoardView.setPopulation(population_i);
		BoardView.setProduction(production_i);
		BoardView.setFood(food_i);
		BoardView.setHappiness(happiness_i);
		}

}
