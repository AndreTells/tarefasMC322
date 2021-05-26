package mc322.jogo.model.board.components;

import mc322.jogo.model.board.BoardModel;
import mc322.jogo.model.board.CellModel;

public abstract class ConstructableComponent extends Component{
	
	public boolean construct() {
		BoardModel board = cell.getBoard();
		if(this.cost > board.getProductionValue()) {
			return false;
		}
		board.useProduction(cost);
		return true;
	}
}

