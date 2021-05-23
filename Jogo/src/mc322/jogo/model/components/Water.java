package mc322.jogo.model.components;

import mc322.jogo.ColorEnum;

public class Water extends Component{
	public Water() {
		this.rank = 4;
		this.setColor(ColorEnum.WATER.getColor());
	}
}
