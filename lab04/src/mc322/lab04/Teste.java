package mc322.lab04;

public class Teste {
	public static void main(String arg []) {
		
		String boards []= AppRestaUm.executaJogo("D:/eclipse-workplace/Lab04/src/db/tab_1.csv");
		System.out.print("\n-------------------------------------\n");
		for(int i=0;i<boards.length;i++) {
			System.out.print(boards[i]);
		}
	}
}

