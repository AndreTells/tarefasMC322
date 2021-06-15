package jogo.model.board.components;

import jogo.model.board.BoardModel;
import jogo.model.board.CellModel;
import jogo.model.board.Player;

public abstract class ConstructableComponent extends Component{
	
	public boolean construct(Player player) {
		if(this.cost > player.getProductionValue()) {
			System.out.println(this.cost+" "+  player.getProductionValue());
			return false;
		}
		player.useProduction(cost);
		return true;
	}
	
}

