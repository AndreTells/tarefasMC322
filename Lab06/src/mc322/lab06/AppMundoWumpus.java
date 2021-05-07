package mc322.lab06;	

public class AppMundoWumpus {

	public static void main(String Args[]) {
		if(!Montador.iniciaJogo(Args[0])) {
			return;
		}
		Controle.requestName();
		Caverna.print();
		while(Controle.requestCommand()) {
			if(Controle.heroiMoved()) {
				Controle.checkWumpus();
				Controle.checkVictory();
				Controle.checkDefeat();
			}
			Caverna.print();
		}
	}
}
