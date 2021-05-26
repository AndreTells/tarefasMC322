package mc322.jogo.model.events;

import mc322.jogo.model.board.IBoardEvent;

public class GlobalRandomEvent extends Event{
	protected int modifier[];
	/*
	 *modifier
	 0-> food
	 1->production
	 2->population_limit 
	*/
	public GlobalRandomEvent(float probability, String text, int []modifier) {
		super(probability,text);
		this.modifier = modifier;
	}
	
	public void executeEvent(IBoardEvent board) {
		// TODO Auto-generated method stub
		board.addModifier(modifier);
	}
}