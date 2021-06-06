package jogo.model.board.components;

import jogo.model.board.BoardModel;
import jogo.model.board.CellModel;

public abstract class ConstructableComponent extends Component{
	
	public boolean construct(BoardModel board) {
		if(this.cost > board.getProductionValue()) {
			System.out.println(this.cost+" "+  board.getProductionValue());
			return false;
		}
		board.useProduction(cost);
		return true;
	}
}

