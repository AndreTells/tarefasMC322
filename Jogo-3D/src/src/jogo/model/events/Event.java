 package jogo.model.events;

import jogo.model.board.IBoardController;
import jogo.model.board.IBoardEvent;

public abstract class Event{
	protected String text;
	
	protected Event(String text) {
		this.text = text;
	}
	
	public String toString() {
		return ""+text+".\n"+this.getDescription();
	}
	
	public abstract String executeEvent(IBoardController board);
	
	public abstract String getDescription();
}
