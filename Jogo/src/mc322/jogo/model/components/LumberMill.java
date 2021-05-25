package mc322.jogo.model.components;

import mc322.jogo.ColorEnum;
import mc322.jogo.model.CellModel;

public class LumberMill extends ConstructableComponent{
	public LumberMill () {
		this.rank = 3;
		this.setColor(ColorEnum.LUMBERMILL.getColor());
		this.cost = ConstructCostEnum.LUMBERMILL.getCost();
		this.setModifier(0, 14,0);
	}
	
	
	public static boolean isConstructalbe(CellModel cell) {
		if(cell.hasComponent(Forest.class)) {
			return true;
		}
		
		
		return false;
	}
}
