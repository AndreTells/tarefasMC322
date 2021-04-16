package mc322.lab04;

public class AppRestaUm {
	static Board board;
	
	static int[] translate_pos(String pos_s) {
		int [] pos = new int[2];
		pos[0] = 6 - ( ((int) pos_s.charAt(1)) - ((int)'1') );
		pos[1] = ( ((int) pos_s.charAt(0)) - ((int)'a') );
		return pos;
	}
	
	
	
 	static String [] executaJogo(String path) {
 		//pega comandos do arquivo CSV
		CSVReader csv = new CSVReader();
 		csv.setDataSource(path);
		String commands [] = csv.requestCommands();
 		//String commands [] = {"f4:d4","c4:e4"};
		
		//cria array de strings para guardar o estado dos tabuleiros apos cada movimento
		String board_states[] = new String[commands.length+1];
 		
		//inicia um tabuleiro
		Board board = new Board();
		
		//imprime o tabuleiro inicial no formato requisitado
		System.out.println("Tabuleiro inicial:");
		board.print_board();
		System.out.print("\n");
		//adiciona o tabuleiro inicial ao array de estados do tabuleiro
		board_states[0] = board.to_string();
		
		for(int i=0;i<commands.length;i++) {
			//separa o comando em duas strings
			//a posicao inicial init_s
			//a posicao final end_s
			String init_s = commands[i].substring(0, 2);
			String end_s = commands[i].substring(3);
			
			//transforma as posicoes em formato de string em dois numeros
			//que indicam a posicao
			int [] pos_init = translate_pos(init_s);
			int [] pos_end = translate_pos(end_s);
			board.move_piece(pos_init, pos_end);
			
			//imprime o tabuleiro no formato requisitado
			System.out.println("Source: "+init_s);
			System.out.println("Target: "+end_s);
			board.print_board();
			System.out.print("\n");
			
			//adiciona o estado atual do tabuleiro ao vetor de estados
			board_states[i+1] = board.to_string();
		}
		
		return board_states;
	}

}
