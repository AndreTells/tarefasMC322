package mc322.jogo.model.board;

import java.awt.Color;
import java.util.List;

import mc322.jogo.model.board.components.ConstructableComponent;

public interface IBoardModelBuilder {
	public String getPopulation();
	public String getProduction();
	public int getProductionValue();
	public String getFood();
	public Color getCellColor(int x, int y);
	public boolean gameOver();
	public void calculateStats();
	public int getMapHeight();
	public int getMapLength();
	public boolean checkLoseConditions();
	public boolean isClaimed(int x,int y);
	public void claim(int x,int y);
	public List<ConstructableComponent> getPossibleActions(int x,int y);
	public String getCellInfo(int x, int y);
	public  void constructComponent(ConstructableComponent comp,int x, int y);
}
