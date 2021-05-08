# Lab06

## Arquivos Java do Jogo
Para acionar o jogo basta seguir os passos abaixo:
* baixar os arquivos
* abrir o cmd e entrar na pasta bin do jogo
* utilizar o comando :
    > java mc322.lab06.AppMundoWumpus <caminho para um arquivo csv que codifique uma caverna>

Quando o jogo for iniciado, o usuário deve digitar um nome para sí mesmo e dar enter.Apos isso,
os comandos são executados digitando sua respectiva letra e apertando enter \
<br/>
Comandos possíveis:
* a &#8594; mover o jogador para a esquerda
* d &#8594; mover o jogador para a direita
* w &#8594; mover o jogador para cima
* s &#8594; mover o jogador para baixo
* k &#8594; equipar a flecha
* c &#8594; pegar ouro quando estiver em cima do mesmo
* q &#8594; sair do jogo

É importante mencionar que qualquer ação que viole as regras do jogo farão com que o programa
encerre imediatamente.Abaixo estão os casos que podem causar tal:
* tentar andar para fora do tabuleiro
* tentar equipar uma flecha quando outra flecha ja foi equipada
* tentar pegar ouro quando não há nenhum na sala

<br/>

[pasta com arquivos java](src/mc322/lab06)

## Destaques de Arquitetura

### `Facil Expansão dos componentes`
Como a associar uma sala a um componente é feito pelo código
~~~java
public boolean setSala(Sala sala) {
   ...
   this.addEffects();
   ...
}
~~~
 perimitndo que cada componente novo que seja adicionado possa dar @Override do metodo addEffects() e adicionar seus respectivos efeitos. Dessa forma, o código não só preserva generalidade da classe Componente como também permite a facil expansão do projeto através da adiçao de novos componentes.

### `Polimorfismo quando guardando Compnentes de uma sala`
~~~java
public class Sala {
	  private List<Componente> componentes;
   ...
}
~~~
Tal lista se utiliza do polimorfismo para que possa guardar qualquer tipo de componente sem a necessidade de editar a classe para acomodar novos tipos de componentes. Além disso, a lista é um atributo privado, impedindo que qulaquer outra classe de acessa-lo diretamente, ou seja uma aplicação de encapsulamento que torna o código mais modularizado.  

### `Arquitetura da Classe Heroi`
~~~java
public class Heroi extends Componente{
    private String name;
    private int score;
    private int num_arrows;
    private boolean alive;
    private boolean arrow_ready;
    private boolean has_gold;
    ...
 }
~~~
Como demonstrado acima, a classe Heroi é a responsável por todos as caracteristicas ligadas ao heroi, como na Arquitetura descrita no arquivo.
