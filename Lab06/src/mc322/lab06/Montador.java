package mc322.lab06;

public class Montador {
	private static boolean getLayout(String path,char layout[][]) {
		CSVHandling csv = new CSVHandling();
 		csv.setDataSource(path);
		String layout_s [] = csv.requestCommands();
		
		int num_players = 0;
		int num_buracos = 0;
		int num_ouro = 0;
		int num_wumpus = 0;
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				layout[i][j] = layout_s[(4*i)+j].charAt(4);
				if(layout[i][j] == 'P') {
					num_players +=1;
				}
				else if(layout[i][j] == 'B'){
					num_buracos+=1;
				}
				else if(layout[i][j] == 'O') {
					num_ouro +=1;
				}
				else if(layout[i][j] == 'W') {
					num_wumpus +=1;
				}
				
			}
		}
		
		if(num_players!= 1 || layout[0][0] != 'P'|| num_wumpus !=1 || num_ouro !=1|| num_buracos < 2 || num_buracos > 3) {
			return false;
		}
		
		
		return true;
	}
	
	public static boolean iniciaJogo(String path) {
		
		char layout [][] = new char [4][4];
		if(!getLayout(path,layout)) {
			return false;
		}
		Caverna.initCaverna();
		Caverna.setLayout(layout);
		
		return true;
	}

}
