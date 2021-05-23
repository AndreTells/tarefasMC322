package mc322.jogo.model.components;

import mc322.jogo.ColorEnum;

public class Mountain extends Component{
	public Mountain(){
		this.rank = 0;
		this.setColor(ColorEnum.MOUNTAIN.getColor());
	}
}
