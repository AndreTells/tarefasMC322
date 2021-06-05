package jogo.model.board;

import java.awt.Color;
import java.util.List;

import jogo.model.board.components.City;
import jogo.model.board.components.Component;
import jogo.model.board.components.ConstructCostEnum;
import jogo.model.board.components.ConstructableComponent;
import jogo.model.board.components.Farm;
import jogo.model.board.components.LumberMill;
import jogo.model.board.mapgenerator.MapGenerator;

public class BoardModel implements IBoardModelBuilder{
	private CellModel map[][];
	private int population;
	private int population_limit;
	private int production;
	private int food;
	private int food_target;
	private int modifier[];
	private boolean game_over;
	private int turn;
	/*
	 *modifier
	 0-> food
	 1->production
	 2->population_limit 
	*/
	
	public BoardModel() {
		create(10,10);
		turn = 0;
		MapGenerator map_generator = new MapGenerator(this);
		map_generator.generateRandomMap();
	}
	
	private void create(int map_height,int map_length) {
		map = new CellModel[map_height][map_length];
		for(int i =0;i< map_height;i++) {
			for(int j=0; j<map_length;j++) {
				map[i][j] = new CellModel(this,j,i,j-1 < 0 ? null:map[i][j-1],i-1 < 0 ? null:map[i-1][j]);
			}
		}
		population = 1;
		population_limit = 1;
		production = 80;
		food = 0;
		food_target = 6;
		
		modifier = new int [3];
		for(int i=0;i<modifier.length;i++) {
			modifier[i] = 0;
		}
		
		game_over = false;
	}
	
	public void addComponent(Component comp,int x,int y) {
		map[y][x].addComponent(comp);
		
	}
	
	public  void constructComponent(String comp_name,int x, int y) {
		//trhow exeption
		ConstructableComponent comp = null;
		if(comp_name.equals("City")) {
			comp = new City();
		}
		else if(comp_name.equals("Farm")) {
			comp = new Farm();
		}
		else if(comp_name.equals("LumberMill")) {
			comp = new LumberMill();
		}
		else{
			return;
		}
		
		if(comp.construct(this)) {
			addComponent(comp,x,y);
		}
	}
	

	public void addModifier(int external_modifier[]) {
		for(int i=0;i<modifier.length;i++) {
			modifier[i] += external_modifier[i];
		}
	}
	
	public void removeModifiers(int external_modifier[]) {
		for(int i=0;i<modifier.length;i++) {
			modifier[i] -= external_modifier[i];
		}
	}
	
	public boolean hasComponent(Class<?> cls, int x, int y) {
		return map[y][x].hasComponent(cls);
	}
	
	public void calculateStats() {
		food += modifier[0];
		production += modifier[1];
		population_limit += modifier[2];
		modifier[2] = 0;
		while(food >= food_target) {
			food -= food_target;
			population +=1;
			modifier[0] -=1;
			food_target = (population*2) +3;
		}
		turn+=1;
	}
	
	public boolean checkLoseConditions() {
		if(population > population_limit || population == 0) {
			game_over = true;
			return true;
		}
		if(modifier[0]<=0) {
			return true;
		}
		
		return false;
	}
	
	public boolean isClaimed(int x,int y) {
		return map[y][x].isClaimed();
	}
	
	public void claim(int x,int y) {
		if(production >=ConstructCostEnum.CLAIM.getCost()) {
			map[y][x].claim();
			useProduction(ConstructCostEnum.CLAIM.getCost());	
		}
		
	}
	
	public void useProduction(int value) {
		production -=value;
	}

	//---- get methods 
	public boolean gameOver() {
		return game_over;
	}
	
	public String getCellHighestComponents(int x, int y) {
		return map[y][x].getHighestComponent();
	}
	
	public String getCellInfo(int x, int y) {
		return map[y][x].getInfo();
	}

	public String getPopulation() {
		return ""+population+"/"+population_limit+" "+(modifier[2] >= 0? "+"+modifier[2]:modifier[2]);
	}
	
	public String getProduction() {
		return ""+production+" "+(modifier[1] >= 0? "+"+modifier[1]:modifier[1]);
	}

	public int getProductionValue() {
		return production;
	}
	
	public String getFood() {
		return ""+food+"/"+food_target +" "+(modifier[0] >= 0? "+"+modifier[0]:modifier[0]);
	}

	public int getMapHeight() {
		return map.length;
	}
	
	public int getMapLength() {
		return map[0].length;
	}

	public List<String> getPossibleActions(int x,int y){
		return map[y][x].getPossibleActions();
	}
	
}
