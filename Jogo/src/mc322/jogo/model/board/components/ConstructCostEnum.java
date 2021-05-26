package mc322.jogo.model.board.components;

public enum ConstructCostEnum {
	CLAIM(10),
	CITY(30),
	LUMBERMILL(40),
	FARM(40);

	private int cost;
	private ConstructCostEnum(int cost){
		this.cost = cost;
	}
	
	public int getCost() {
		return this.cost;
	}
}
