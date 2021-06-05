package jogo.model.events;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EventLoader {
	private String dataSource;
	
	public EventLoader(String dataSource) {
		this.dataSource = dataSource;
	}
	
	public Event[] getEvents() {
		try {
			BufferedReader file = new BufferedReader(new FileReader(dataSource));

			
			int num_lines = Integer.parseInt(file.readLine().split(",")[0]);
			System.out.println(num_lines);
					
			String line = file.readLine();
			int i = 0;
			Event event_list[] = new Event[num_lines];
			while (line != null) {
				String event_s []= line.split(",");
				int modifier[] = new int[] {Integer.parseInt(event_s[2]),Integer.parseInt(event_s[3]),Integer.parseInt(event_s[4])};
				event_list[i] = new CityEvent(event_s[0],Float.parseFloat(event_s[1]),modifier);
				line = file.readLine();
				i++;
			}
			file.close();
			return event_list;
		} catch (IOException erro) {
			erro.printStackTrace();
			return null;
		}
	}
}
//EventLoader loader = new EventLoader("C:\\Users\\andre\\Desktop\\mc322\\Jogo\\data\\City_Events.csv");