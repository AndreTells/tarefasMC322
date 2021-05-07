package mc322.lab06;

import java.util.LinkedList;
import java.util.List;

import mc322.lab06.componentes.Buraco;
import mc322.lab06.componentes.Componente;
import mc322.lab06.componentes.Heroi;
import mc322.lab06.componentes.Ouro;
import mc322.lab06.componentes.Wumpus;

public class Montador {
	private static boolean getLayout(String path,List<Componente> layout) {
		CSVHandling csv = new CSVHandling();
 		csv.setDataSource(path);
		String layout_s [][] = csv.requestCommands();
		
		int num_players = 0;
		int num_buracos = 0;
		int num_ouro = 0;
		int num_wumpus = 0;
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) { 
				
				char icon = layout_s[(4*i)+j][1].charAt(0);
				if(icon != '_') {
					
					switch(icon) {
						case('P'):
							layout.add(new Heroi(i,j));
							num_players +=1;
							break;
						case('B'):
							layout.add(new Buraco(i,j));
							num_buracos+=1;
							break;
						case('O'):
							layout.add(new Ouro(i,j));
							num_ouro +=1;
							break;
						case('W'):
							layout.add(new Wumpus(i,j));
							num_wumpus +=1;
							break;
					}	
				}
				
			}
		}
		
		
		if(num_players!= 1 || layout_s[0][1].charAt(0) != 'P'|| num_wumpus !=1 || num_ouro !=1|| num_buracos < 2 || num_buracos > 3) {
			return false;
		}
		
		return true;
	}
	
	public static boolean iniciaJogo(String path) {
		
		List<Componente> layout = new LinkedList<Componente>();
		if(!getLayout(path,layout)) {
			return false;
		}
		Caverna.initCaverna();
		
		for(Componente comp : layout) {
			Caverna.connectComponente(comp);
		}
		
		return true;
	}

}
