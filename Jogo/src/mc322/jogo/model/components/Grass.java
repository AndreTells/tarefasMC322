package mc322.jogo.model.components;

import mc322.jogo.ColorEnum;

public class Grass extends Component{
	public Grass() {
		this.rank = 0;
		this.setColor(ColorEnum.GRASS.getColor());
		this.setModifier(0, 1,0, 0, 0);
	}
}
