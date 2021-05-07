package mc322.lab06;

import mc322.lab06.componentes.Buraco;
import mc322.lab06.componentes.Heroi;
import mc322.lab06.componentes.Ouro;
import mc322.lab06.componentes.Wumpus;

public class Caverna {
	private static Sala salas[][];
	public static void initCaverna() {
		salas = new Sala[4][4];
		for(int i =0;i<4;i++) {
			for(int j=0;j<4;j++) {
				salas[i][j] = new Sala(i,j,i-1 < 0 ? null:salas[i-1][j],j-1 < 0 ? null:salas[i][j-1]);
			}
		}
	}
	

	public static void setLayout(char layout[][]) {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				switch(layout[i][j]) {
					case 'P':
						salas[i][j].addComponente(new Heroi(i,j));
						break;
					case 'B':
						salas[i][j].addComponente(new Buraco(i,j));
						break;
					case 'O':
						salas[i][j].addComponente(new Ouro(i,j));
						break;
					case 'W':
						salas[i][j].addComponente(new Wumpus(i,j));
						break;

				}
				
			}
				
		}
	}

	public static void print() {
		for(int i=0;i<salas.length;i++) {
			System.out.print(i+1);
			for(int j =0;j<salas[0].length; j++) {
				System.out.print(" "+salas[i][j].getIcon());
			}
			System.out.print("\n");
		}
		System.out.println("  1 2 3 4");
	}

}

