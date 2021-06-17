package mc322.jogo.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mc322.jogo.model.board.BoardModel;
import mc322.jogo.model.board.IBoardModelBuilder;
import mc322.jogo.view.board.AppView;
import mc322.jogo.view.board.IBoardViewController;

public class BoardController implements IControllerBoardView, IRBoardViewController{
	private IBoardViewController board_view;
	private IBoardModelBuilder board_model;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!board_model.gameOver()) {
			board_model.calculateStats();
			updateStats();
			if(board_model.checkLoseConditions()) {
				board_view.setGameOver();
			}
		}
		
	}
	
	public void updateStats() {
		board_view.setPopulation(board_model.getPopulation());
		board_view.setProduction(board_model.getProduction());
		board_view.setFood(board_model.getFood());
	}
	
	public void updateColors() {
		for(int i=0;i < board_model.getMapHeight();i++) {
			for(int j=0; j < board_model.getMapLength();j++) {
				updateCellColor(j , i);
			}
		}
	}
	
	public void updateCellColor(int x , int y) {
		Color color = board_model.getCellColor(x, y);
		board_view.setCellColor(color, x, y);
	}

	public void setInfo(String info) {
		board_view.setInfo(info);
	}
	
	public void setBoardPanel(IBoardViewController board_view) {
		this.board_view = board_view;
		board_view.setBoardController(this);
		
	}
	
	public void setBoardModel(IBoardModelBuilder board_model) {
		this.board_model = board_model;
	}
	
	public IBoardModelBuilder getBoardModel() {
		return this.board_model;
	}
	

}
