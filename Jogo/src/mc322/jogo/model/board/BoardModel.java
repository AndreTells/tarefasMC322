package mc322.jogo.model.board;

import java.awt.Color;
import java.util.List;

import mc322.jogo.model.board.components.Component;
import mc322.jogo.model.board.components.ConstructCostEnum;
import mc322.jogo.model.board.components.ConstructableComponent;

public class BoardModel {
	private static CellModel map[][];
	private static int population;
	private static int population_limit;
	private static int production;
	private static int food;
	private static int food_target;
	private static int modifier[];
	private static boolean game_over;
	/*
	 *modifier
	 0-> food
	 1->production
	 2->population_limit 
	*/
	
	public static void init(int map_height,int map_length){
		create(map_height,map_length);
	}	
	public static void init() {
		create(10,10);
	}
	
	private static void create(int map_height,int map_length) {
		map = new CellModel[map_height][map_length];
		for(int i =0;i< map_height;i++) {
			for(int j=0; j<map_length;j++) {
				map[i][j] = new CellModel(j,i,j-1 < 0 ? null:map[i][j-1],i-1 < 0 ? null:map[i-1][j]);
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
	
	public static void addComponent(Component comp,int x,int y) {
		map[y][x].addComponent(comp);
		
	}
	
	public static  void constructComponent(ConstructableComponent comp,int x, int y) {
		if(comp.construct()) {
			addComponent(comp,x,y);
		}
	}
	

	public static void addModifier(int external_modifier[]) {
		for(int i=0;i<modifier.length;i++) {
			modifier[i] += external_modifier[i];
		}
	}
	
	public static void removeModifiers(int external_modifier[]) {
		for(int i=0;i<modifier.length;i++) {
			modifier[i] -= external_modifier[i];
		}
	}
	
	public static boolean hasComponent(Class cls, int x, int y) {
		return map[y][x].hasComponent(cls);
	}
	
	public static void calculateStats() {
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
		
	}
	
	public static boolean checkLoseConditions() {
		if(population > population_limit || population == 0) {
			game_over = true;
			return true;
		}
		if(modifier[0]<=0) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isClaimed(int x,int y) {
		return map[y][x].isClaimed();
	}
	
	public static void claim(int x,int y) {
		if(production >=ConstructCostEnum.CLAIM.getCost()) {
			map[y][x].claim();
			useProduction(ConstructCostEnum.CLAIM.getCost());	
		}
		
	}
	
	public static void useProduction(int value) {
		production -=value;
	}

	//---- get methods 
	public static boolean gameOver() {
		return game_over;
	}
	
	public static Color getCellColor(int x, int y) {
		return map[y][x].getColor();
	}
	
	public static String getCellInfo(int x, int y) {
		return map[y][x].getInfo();
	}

	public static String getPopulation() {
		return ""+population+"/"+population_limit+" "+(modifier[2] >= 0? "+"+modifier[2]:modifier[2]);
	}
	
	public static String getProduction() {
		return ""+production+" "+(modifier[1] >= 0? "+"+modifier[1]:modifier[1]);
	}

	public static int getProductionValue() {
		return production;
	}
	
	public static String getFood() {
		return ""+food+"/"+food_target +" "+(modifier[0] >= 0? "+"+modifier[0]:modifier[0]);
	}

	public static int getMapHeight() {
		return map.length;
	}
	
	public static int getMapLength() {
		return map[0].length;
	}

	public static List<ConstructableComponent> getPossibleActions(int x,int y){
		return map[y][x].getPossibleActions();
	}
	
}
