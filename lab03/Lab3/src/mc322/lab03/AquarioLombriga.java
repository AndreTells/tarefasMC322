package mc322.lab03;

public class AquarioLombriga{
    private int tam_l;//tamano da lobbriga
    private int tam_a;//tamanho do aquario
    private int pos;
    private boolean lado;//lado para o qual a lobriga esta virada
                         //false--esquerda; true--direita
    
    AquarioLombriga(int tam_l, int tam_a, int pos, boolean lado){
        this.tam_l = tam_l;
        this.tam_a = tam_a;
        
        if(tam_a<tam_l){
            this.tam_a+=tam_l-tam_a;
        }
        
        this.pos = pos;
        this.lado = lado;
        
        if(this.lado && this.pos-(this.tam_l-1) < 0){
            this.pos = this.tam_l-1;
        }
        
        if(!this.lado && this.pos+(this.tam_l-1) >this.tam_a-1){
            this.pos = 0;
        }
    }
    
    public void crescer(){
        if(this.lado && this.pos-(this.tam_l-1) > 0){
            this.tam_l+=1;
        }
        else if(this.pos+(this.tam_l-1) <this.tam_a-1){
            this.tam_l+=1;
        }
    }
    public void virar(){
        if(this.lado){
            this.pos -=(this.tam_l-1);
        }
        else{
            this.pos +=(this.tam_l-1);
        }
        this.lado = !this.lado;
    }
    
    public void mover(){
        if(this.lado){
            if(this.pos == this.tam_a-1){
                this.virar();
            }
            else{
                this.pos+=1;
            }
        }
        else{
            if(this.pos == 0){
                this.virar();
            }
            else{
                this.pos-=1;
            }
        }
    }
    public String apresentar(){
        char[] aquario = new char[this.tam_a];
        aquario[this.pos] = 'O';
        if(this.lado){
            for(int i=0; i <this.tam_a;i++){
                if(i!=this.pos){
                    if(i<this.pos && i>=this.pos-(this.tam_l-1)){
                        aquario[i] = '@';
                    }
                    else{
                        aquario[i] = '#';
                    }
                    
                }
            }
        }
        else{
            for(int i=0; i <this.tam_a;i++){
                if(i!=this.pos){
                    if(i>this.pos && i<=this.pos+(this.tam_l-1)){
                        aquario[i] = '@';
                    }
                    else{
                        aquario[i] = '#';
                    }
                    
                }
            }
        }
        return new String(aquario);
    }
}