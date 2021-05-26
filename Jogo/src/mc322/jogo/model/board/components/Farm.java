package mc322.jogo.model.board.components;

import mc322.jogo.model.board.CellModel;

public class Farm extends ConstructableComponent{
	public Farm () {
		this.rank = 3;
		this.setColor(ColorEnum.FARM.getColor());
		this.cost = ConstructCostEnum.FARM.getCost();
		this.setModifier(14, 0,0);
	}
	
	
	public static boolean isConstructalbe(CellModel cell) {
		if(cell.hasComponent(Forest.class) || cell.hasComponent(Water.class)) {
			return false;
		}
		else if(cell.hasComponent(Grass.class)&& cell.isIrigated()) {
			return true;
		}
		
		
		return false;
	}
}
