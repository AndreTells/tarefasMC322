package mc322.jogo.model.board.components;

import mc322.jogo.model.board.BoardModel;
import mc322.jogo.model.board.CellModel;

public abstract class ConstructableComponent extends Component{
	
	public boolean construct() {
		if(this.cost > BoardModel.getProductionValue()) {
			return false;
		}
		BoardModel.useProduction(cost);
		return true;
	}
}
