package mc322.lab06;

import mc322.lab06.componentes.Wumpus;
import mc322.lab06.componentes.Componente;

public class AppMundoWumpus {

	public static void main(String Args[]) {
		if(!Montador.iniciaJogo(Args[0])) {
			return;
		}
		Caverna.printrevelado();
		Componente buraco = Caverna.getComponente(2, 3, Wumpus.class);
		buraco.unsetSala();
		Caverna.printrevelado();
	}
}
