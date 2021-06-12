package jogo.model.board;

import java.util.List;

import jogo.model.board.components.ConstructableComponent;
import jogo.model.events.EventManager;

public interface IBoardModelBuilder {
	public String getPopulation();
	
	public String getProduction();
	
	public int getProductionValue();
	
	public String getFood();
	
	public String getCellHighestComponents(int x, int y);
	
	public boolean gameOver();
	
	public void calculateStats();
	
	public int getMapHeight();
	
	public int getMapLength();
	
	public boolean checkLoseConditions();
	
	public boolean isClaimed(int x,int y);
	
	public void claim(int x,int y);
	
	public List<String> getPossibleActions(int x,int y);
	
	public String getCellInfo(int x, int y);
	
	public  void constructComponent(String comp_name,int x, int y);

	public String runRandomEvent();
}
