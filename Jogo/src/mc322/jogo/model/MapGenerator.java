package mc322.jogo.model;

import mc322.jogo.model.components.Component;
import mc322.jogo.model.components.Forest;
import mc322.jogo.model.components.Grass;
import mc322.jogo.model.components.Mountain;
import mc322.jogo.model.components.Water;

public class MapGenerator {
	
	public static void generateRandomMap() {
		generateMapBase(0.2);
		generateMapRiver();
		generateMapRiver();
		generateMapForest(0.05);
	}
	
	private static void generateMapBase(double limit){
		int map_height = BoardModel.getMapHeight();
		int map_length = BoardModel.getMapLength();
		for(int i=0; i < map_height;i++) {
			for(int j=0; j < map_length;j++) {
				double value = ImprovedNoise.noise(i, j, Math.random()+1);
				//System.out.println(value);
				if(value<limit) {
					BoardModel.addComponent(new Grass(), j, i);
				}
				else {
					BoardModel.addComponent(new Mountain(), j, i);
				}
					
			}
		}
	}

	
	private static void generateMapRiver() {
		int start_x = getRandomNumber(0,10);
		int start_y = getRandomNumber(0,10);
		
		int end_x = getRandomNumber(0,10);
		int end_y = getRandomNumber(0,10);
		int i =Math.min(start_x, end_x);
		int j = i ==start_x ? start_y:end_y;
		while(true) {
			if(i==Math.max(start_x, end_x) && j == (i ==start_x ? start_y:end_y)) {
				break;
			}
			if(!BoardModel.hasComponent(Mountain.class, i, j)) {
				BoardModel.addComponent(new Water(), i, j);
			}
			
			
			if(i!=Math.max(start_x, end_x)){
				i++;
			}
			if(j != (i ==start_x ? start_y:end_y)) {
				if(j<(i ==start_x ? start_y:end_y)) {
					j++;
				}
				else {
					j--;
				}
			}
		}
	}
	
	private static void generateMapForest(double limit) {
		int map_height = BoardModel.getMapHeight();
		int map_length = BoardModel.getMapLength();
		for(int i=0; i < map_height;i++) {
			for(int j=0; j < map_length;j++) {
				if(BoardModel.hasComponent(Mountain.class, j, i) || BoardModel.hasComponent(Water.class, j, i)){
					continue;
				}
				double value = ImprovedNoise.noise(i, j, Math.random()+1);
				//System.out.println(value);
				if(value>limit) {
					BoardModel.addComponent(new Forest(), j, i);
				}
					
			}
		}
	}
	
	//---- helper functions
	private static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
