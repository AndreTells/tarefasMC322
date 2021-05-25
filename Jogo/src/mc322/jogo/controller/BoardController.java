package mc322.jogo.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mc322.jogo.model.BoardModel;
import mc322.jogo.view.BoardView;

public class BoardController implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!BoardModel.gameOver()) {
			BoardModel.calculateStats();
			updateStats();
			if(BoardModel.checkLoseConditions()) {
				BoardView.setGameOver();
			}
		}
		
	}
	
	public static void updateStats() {
		BoardView.setPopulation(BoardModel.getPopulation());
		BoardView.setProduction(BoardModel.getProduction());
		BoardView.setFood(BoardModel.getFood());
	}
	
	public static void updateColors() {
		for(int i=0;i < BoardModel.getMapHeight();i++) {
			for(int j=0; j < BoardModel.getMapLength();j++) {
				updateCellColor(j , i);
			}
		}
	}
	
	public static void updateCellColor(int x , int y) {
		Color color = BoardModel.getCellColor(x, y);
		BoardView.setCellColor(color, x, y);
	}

}
