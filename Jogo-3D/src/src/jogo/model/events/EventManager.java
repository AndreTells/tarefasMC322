package jogo.model.events;

import java.util.LinkedList;
import java.util.List;

import jogo.model.board.IBoardController;
import jogo.model.board.IBoardEvent;

public class EventManager implements IEventManager{
	static List<Event> events;
	
	public EventManager() {
		events = new LinkedList<Event>();
		String path =  System.getProperty("user.dir") + "\\..\\assets\\events\\";
		events.addAll(CityEvent.getEvents(path+"CityEvents.csv"));
		//for(Event event: events) {
		//	System.out.println(event);
		//}
	}
	
	public String ExecuteRandomEvent(IBoardController board) {
		int index = getRandomNumber(0,events.size());
		//System.out.println(events.get(index));
		return events.get(index).executeEvent(board);
	}
	
	private int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
