package mc322.jogo.model.board.components;

public class Mountain extends Component{
	public Mountain(){
		this.rank = 5;
		this.setColor(ColorEnum.MOUNTAIN.getColor());
		this.setModifier(0, 10	,0);
	}
}
