package mc322.lab03;

public class SeqAnim{
    public int AA;
    public int LL;
    public int PP;
    public char[] seq_m;
    
    SeqAnim(String seq){
        this.AA = Character.getNumericValue(seq.charAt(0))*10 + Character.getNumericValue(seq.charAt(1));
        this.LL = Character.getNumericValue(seq.charAt(2))*10 + Character.getNumericValue(seq.charAt(3));
        this.PP = Character.getNumericValue(seq.charAt(4))*10 + Character.getNumericValue(seq.charAt(5))-1;//-1 para aconsiderar o fato que PP comeca a contar a partir de 1
        
        this.seq_m = new char[seq.length()-6];
        for(int i=6;i<seq.length();i++){
            this.seq_m[i-6] = seq.charAt(i);
        }
    }
}