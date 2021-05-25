package mc322.jogo.model.components;

import mc322.jogo.model.BoardModel;
import mc322.jogo.model.CellModel;

public abstract class ConstructableComponent extends Component{
	
	public boolean construct() {
		if(this.cost > BoardModel.getProductionValue()) {
			return false;
		}
		BoardModel.useProduction(cost);
		return true;
	}
}
