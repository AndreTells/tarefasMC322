package mc322.lab03;

public class Animacao {
    private AquarioLombriga lom;
    private char[] seq_m;
    private int passo_a;
    public boolean terminou;
    
    Animacao(SeqAnim seq){
        this.lom = new AquarioLombriga(seq.LL,seq.AA,seq.PP+seq.LL,true);
        this.seq_m = seq.seq_m;
        this.passo_a = 0;
        this.terminou = false;
    }
    public String apresenta(){
        return this.lom.apresentar();
    }
    
    public void passo(){
        if(this.terminou){return;}
        if(this.seq_m[this.passo_a] == 'C'){
            lom.crescer();
        }
        else if(this.seq_m[this.passo_a] == 'M'){
            lom.mover();
        }
        else{
            lom.virar();
        }
        
        this.passo_a+=1;
        if(this.passo_a == this.seq_m.length){
            this.terminou = true;
        }
    }
}