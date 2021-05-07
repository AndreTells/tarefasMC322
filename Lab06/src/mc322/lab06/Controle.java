package mc322.lab06;

import java.util.Random;
import java.util.Scanner;

import mc322.lab06.componentes.Componente;
import mc322.lab06.componentes.Heroi;

public class Controle {
	private static Heroi heroi;
	private static boolean game_has_ended;
	private static boolean heroi_mmoved;
	
	public static void initControle(Heroi heroi_input) {
		heroi = heroi_input;
		game_has_ended = false;
		heroi_mmoved = false;
	}
		
	public static void requestName() {
		Scanner keyboard = new Scanner(System.in);
		String name = keyboard.nextLine();
		heroi.setName(name);
	}
	
	public static boolean requestCommand() {
		if(game_has_ended) {
			return false;
		}
		Scanner keyboard = new Scanner(System.in);
		String command = keyboard.nextLine().toLowerCase();
		boolean result = false;
		switch(command.charAt(0)) {
			case 'w':
				result = heroi.moveUp();
				heroi_mmoved = result;
				break;
			case 'a':
				result = heroi.moveLeft();
				heroi_mmoved = result;
				break;
			case 's':
				result = heroi.moveDown();
				heroi_mmoved = result;
				break;
			case 'd':
				result = heroi.moveRight();
				heroi_mmoved = result;
				break;
			case 'k':
				result = heroi.prepareArrow();
				heroi_mmoved = false;
				break;
			case 'c':
				result = heroi.getGold();
				heroi_mmoved = false;
				break;
			case 'q':
				Caverna.print();
				System.out.println("Volte Sempre !");
				result = false;
				break;
		}
		if(!result) {
			return false;
		}
		return true;
	}

	public static boolean heroiMoved() {
		return heroi_mmoved;
	}
	
	public static void printInfo() {
		System.out.println("Player: "+heroi.getName());
		System.out.println("Score: "+heroi.getScore());
		if(game_has_ended && heroi.isAlive()) {
			System.out.println("Voce ganhou =D !!!");
		}
		else if(game_has_ended) {
			System.out.println("Voce perdeu =( ...");
		}
	}

	public static void checkVictory() {
		if(heroi.isAtStart() && heroi.hasGold()) {
			heroi.exitCave();
			game_has_ended = true;
		}
	}
	
	public static void checkDefeat() {
		if(heroi.isInHole() || heroi.isWithWumpus()!=null) {
			heroi.killed();
			game_has_ended = true;
			heroi.exitCave();
		}
	}

	public static void checkWumpus() {
		Componente wumpus = heroi.isWithWumpus();
		if(wumpus!=null) {
			 Random random = new Random();
			 Double result = random.nextDouble();
			 System.out.println(heroi.hasPreparedArrow());
			if(heroi.hasPreparedArrow() && result>0.5) {
				heroi.killWumpus(wumpus);
			}
		}
		heroi.unprepareArrow();
	}


}
