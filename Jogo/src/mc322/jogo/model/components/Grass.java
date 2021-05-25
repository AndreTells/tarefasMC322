package mc322.jogo.model.components;

import mc322.jogo.ColorEnum;

public class Grass extends Component{
	public Grass() {
		this.rank = 0;
		this.setColor(ColorEnum.GRASS.getColor());
		this.setModifier(1, 0,0);
	}
}
