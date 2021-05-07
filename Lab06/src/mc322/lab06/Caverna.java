package mc322.lab06;

import mc322.lab06.componentes.Componente;


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
	
	public static void connectComponente(Componente comp) {
		int pos [] = comp.getPos();
		comp.setSala(salas[pos[0]][pos[1]]);
	}

	public static Componente getComponente(int x,int y,Class<?> cls) {
		return salas[x][y].getComponente(cls);
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
	
	public static void printrevelado() {
		for(int i=0;i<salas.length;i++) {
			System.out.print(i+1);
			for(int j =0;j<salas[0].length; j++) {
				salas[i][j].visit();
				System.out.print(" "+salas[i][j].getIcon());
			}
			System.out.print("\n");
		}
		System.out.println("  1 2 3 4");
	}

	
}

