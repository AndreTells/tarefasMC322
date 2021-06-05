package jogo.model.board.components;

import jogo.model.board.CellModel;

public class City extends ConstructableComponent {
	public City () {
		this.rank = 3;
		this.cost = ConstructCostEnum.CITY.getCost();
		this.setModifier(0, 5,1);
	}
	
	
	public static boolean isConstructalbe(CellModel cell) {
		if(cell.hasComponent(Mountain.class) || cell.hasComponent(Water.class) || cell.hasComponent(Forest.class)) {
			return false;
		}
		
		return true;
	}

}
