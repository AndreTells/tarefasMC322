	package mc322.jogo.model.board.components;

public class Forest extends Component{
	public Forest() {
		this.rank = 1;
		this.setColor(ColorEnum.FOREST.getColor());
		this.setModifier(1, 1,0);
	}
}
