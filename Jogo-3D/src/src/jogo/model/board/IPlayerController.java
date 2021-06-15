package jogo.model.board;

public interface IPlayerController {
	public String getPopulation();
	
	public int getPopulationValue();
	
	public int getPopulationLimitValue();
	
	public String getProduction();
	
	public String getFood();
	
	public int getFoodValue();
	
	public void addModifier(int modifier[]);
	
	public void claim(int x,int y);

	public  void constructComponent(String comp_name,int x, int y);
}
