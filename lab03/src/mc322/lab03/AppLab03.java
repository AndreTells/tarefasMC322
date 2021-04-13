package mc322.lab03;

public class AppLab03 {
	public static void main(String args[]) {
		SeqAnim seq = new SeqAnim("080402MCMVM");
		Animacao a = new Animacao(seq);
		System.out.println(a.apresenta());
		while(!a.terminou){
		    a.passo();
		    System.out.println(a.apresenta());
		}
	}
}
