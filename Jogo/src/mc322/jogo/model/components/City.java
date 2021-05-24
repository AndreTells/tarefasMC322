package mc322.jogo.model.components;

import mc322.jogo.ColorEnum;
import mc322.jogo.model.CellModel;

public class City extends Component {
	public City () {
		this.rank = 3;
		this.setColor(ColorEnum.CITY.getColor());
	}
	
	
	public static boolean isConstructalbe(CellModel cell) {
		if(cell.hasComponent(Mountain.class) || cell.hasComponent(Water.class)) {
			return false;
		}
		
		return true;
	}
}
