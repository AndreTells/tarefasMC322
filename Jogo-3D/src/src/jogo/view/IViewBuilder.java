package jogo.view;

import jogo.controller.gamecontroller.CellController;
import jogo.controller.gamecontroller.NextTurnController;

public interface IViewBuilder { 
	public void setPopulation(String population_text);
	
	public void setProduction(String production_text);
	
	public void setFood(String food_text);
	
	public void setInfo(String info_text);
	
	public void setCellListener(int i,int j,CellController controller); 
	
	public ICellViewController getCell(int i,int j);
	
	public void setNextTurnController(NextTurnController controller);
	
	public void updateDetectionBox(int i,int j);
	
	public IPopUpMenu createSubMenu(float pos_x, float pos_y,String[] items);

	public void closeSubMenu();
	
	public IPopUpMenu createEventPopUp(String text, String[] items);
	
	public void closeEventMenu();
}
