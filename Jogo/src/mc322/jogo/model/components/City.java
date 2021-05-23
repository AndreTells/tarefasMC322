package mc322.jogo.model.components;

import mc322.jogo.ColorEnum;

public class City extends Component{
	public City () {
		this.rank = 3;
		this.setColor(ColorEnum.CITY.getColor());
	}
}
