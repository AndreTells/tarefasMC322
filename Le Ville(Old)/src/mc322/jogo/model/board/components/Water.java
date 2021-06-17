package mc322.jogo.model.board.components;

import mc322.jogo.model.board.CellModel;

public class Water extends Component{
	public Water() {
		this.rank = 4;
		this.setColor(ColorEnum.WATER.getColor());
		this.setModifier(0, 0,0);
	}
}
