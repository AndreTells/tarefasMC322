package mc322.jogo.model.events;

import mc322.jogo.model.board.IBoardEvent;

public class CityEvent extends Event{
	private int modifier[];
	CityEvent(String text,float probability,int modifier[]){
		super(text,probability);
		this.modifier = modifier;
	}

	@Override
	public void executeEvent(IBoardEvent board) {
		board.addModifier(modifier);
	}
}
