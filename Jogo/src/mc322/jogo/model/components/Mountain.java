package mc322.jogo.model.components;

import mc322.jogo.ColorEnum;

public class Mountain extends Component{
	public Mountain(){
		this.rank = 5;
		this.setColor(ColorEnum.MOUNTAIN.getColor());
		this.setModifier(0, 10	,0);
	}
}
