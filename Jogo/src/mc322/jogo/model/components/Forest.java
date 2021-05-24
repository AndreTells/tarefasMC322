package mc322.jogo.model.components;

import mc322.jogo.ColorEnum;

public class Forest extends Component{
	public Forest() {
		this.rank = 1;
		this.setColor(ColorEnum.FOREST.getColor());
		this.setModifier(0, 1,3, 1, 0);
	}
}
