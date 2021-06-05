package jogo.view;

import jogo.controller.CellController;
import jogo.controller.NextTurnController;

public interface IViewBuilder { 
	public void setPopulation(String population_text);
	
	public void setProduction(String production_text);
	
	public void setFood(String food_text);
	
	public void setCellListener(int i,int j,CellController controller); 
	
	public ICellViewController getCell(int i,int j);
	
	public void setNextTurnController(NextTurnController controller);
	
	public void updateDetectionBox(int i,int j);
	
	public void setInfo(String info_text);
}
