package mc322.jogo.view.board;

import java.awt.Color;

import mc322.jogo.controller.IControllerBoardView;
import mc322.jogo.controller.IControllerCellView;

public interface IBoardViewController {
	
	public void setInfo(String info);
	
	public void setPopulation(String population);
	
	public void setProduction(String production);
	
	public void setFood(String food);
	
	public void setCellColor(Color color, int x,int y);
	
	public void setGameOver();
	
	public void setBoardController(IControllerBoardView controller);
	
	public void setCellController(IControllerCellView controller,int x,int y);
}
