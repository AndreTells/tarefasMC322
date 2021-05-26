package mc322.jogo.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mc322.jogo.model.board.BoardModel;
import mc322.jogo.view.board.AppView;

public class BoardController implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!BoardModel.gameOver()) {
			BoardModel.calculateStats();
			updateStats();
			if(BoardModel.checkLoseConditions()) {
				AppView.setGameOver();
			}
		}
		
	}
	
	public static void updateStats() {
		AppView.setPopulation(BoardModel.getPopulation());
		AppView.setProduction(BoardModel.getProduction());
		AppView.setFood(BoardModel.getFood());
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
		AppView.setCellColor(color, x, y);
	}

}
