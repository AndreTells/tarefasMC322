package mc322.jogo.model;

import java.awt.Color;
import java.util.List;

import mc322.jogo.model.components.Component;

public class BoardModel {
	private static CellModel map[][];
	private static int num_city_cells;
	private static int population;
	private static int production;
	private static int food;
	private static int happiness;
	
	
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
				map[i][j] = new CellModel(j,i,i-1 < 0 ? null:map[j][i-1],j-1 < 0 ? null:map[j-1][i]);
			}
		}
		num_city_cells = 0;
		population = 0;
		production = 50;
		food = 0;
		happiness = 0;
	}
	
	public static void addComponent(Component comp,int x,int y) {
		map[y][x].addComponent(comp);
		comp.setCell(map[y][x]);
	}

	public static boolean hasComponent(Class cls, int x, int y) {
		return map[y][x].hasComponent(cls);
	}
	//---- get methods 
	public static Color getCellColor(int x, int y) {
		return map[y][x].getColor();
	}
	
	public static String getCellInfo(int x, int y) {
		return map[y][x].getInfo();
	}

	public static int getPopulation() {
		return population;
	}
	
	public static int getProduction() {
		return production;
	}

	public static int getFood() {
		return food;
	}

	public static int getHappiness() {
		return happiness;
	}

	public static int getMapHeight() {
		return map.length;
	}
	
	public static int getMapLength() {
		return map[0].length;
	}

	public static List<Component> getPossibleActions(int x,int y){
		return map[y][x].getPossibleActions();
	}
	
}
