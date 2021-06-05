 package jogo.model.events;

import jogo.model.board.IBoardEvent;

public abstract class Event implements IRBoardEvent{
	protected float probability;
	protected String text;
	
	protected Event(String text,float probability) {
		this.probability = probability;
		this.text = text;
	}
	
	public String toString() {
		return ""+text+":"+probability;
	}
	
	public abstract void executeEvent(IBoardEvent board);
}
