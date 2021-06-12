package jogo.model.events;

import java.util.LinkedList;
import java.util.List;

import jogo.model.board.IBoardEvent;

public class EventManager {
	static List<Event> events;
	
	public static void init() {
		events = new LinkedList<Event>();
		String path =  System.getProperty("user.dir") + "\\..\\assets\\events\\";
		events.addAll(CityEvent.getEvents(path+"CityEvents.csv"));
		//for(Event event: events) {
		//	System.out.println(event);
		//}
	}
	
	public static String ExecuteRandomEvent(IBoardEvent board) {
		int index = getRandomNumber(0,events.size());
		//System.out.println(events.get(index));
		return events.get(index).executeEvent(board);
	}
	
	private static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
