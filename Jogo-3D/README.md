# Projeto (A ser definido)
## Descrição Resumida do Projeto
 O jogo consiste em expandir sua cidade até possuir mais de 50%. Porém, o jogador perderá se:
 - Tiver 0 de população ao final de um turno
 - Tiver mais população do que espaco disponível
 - Não produzir ganhar coomida ao final de algum turno
<br>
Para evitar tal, o jogador poderá escolher o que construir em cada espaço do mapa que pertencer a sí, aumentando ou diminuindo atributos de sua cidade. Além disso, eventos aleatórios ocorrem no início de cada turno, esses atrapalhando o jogador e impedindo que este vença facilmente.

## Equipe
- André Silva Telles - 165263

## Vídeos do Projeto
<colocar vídeo de prévia do projeto>

# Slides do Projeto

## Slides de Prévia
<colocar slides de prévia do projeto>

# Documentção dos Componentes
# Diagramas
## Diagrama Geral do Projeto

## Diagrama Geral de Componentes
![Diagrama geral do Projeto](https://github.com/AndreTells/tarefasMC322/blob/main/Jogo-3D/images/diagrama-geral-do-projeto.jpg)

## Componente Builder
Este Componente pertence ao controller e é responsável por e conectar todos os outros componentes
![Builder](https://github.com/AndreTells/tarefasMC322/blob/main/Jogo-3D/images/diagrama-builder.jpg)
**Ficha Técnica**
item | detalhamento
----- | -----
Classe | package jogo.controller.Builder
Autores | André Silva Telles
Interfaces |  IRBoardModelBuilder<br>  IRScreenManager<br>  IRUIManager<br>  IRMouse<br>  IRBoard3DManager<br>  IREventManager

### Interfaces
![Builder-Interfaces](https://github.com/AndreTells/tarefasMC322/blob/main/Jogo-3D/images/diagrama-interfaces-builder.jpg)
interface agregadora do componente em java:
~~~java 
public interface IGameBuilder 
   extends IRBoardModelBuilder,IRScreenManager,
	    IRUIManager,IRMouse,IRBoard3DManager,IREventManager
     {
     public void buildGame();
}
~~~

O metodo build desta é o resposável por conectar todos os componentes do jogo

## Componente Screen 
Tem a função de apresentar o UI, parte em 2D, e o tabuleiro, parte em 3D para o usuário em um JFrame.

## Componente BoardView3D
Resposável por armazenar como cada celula é apresentada para o usuário. Controlando:
- Os modelos 3D disponíveis ao jogo
- Carregar os modelos 3D nos assets	
- O estado atual de cada celula
- como desenhar tais celulas na tela

## Componente UI
Responsável por organizar os elementos 2D que seram apresentados na tela.
Para tal, foi empregado o design pattern _Composite_ . Este consite em ter um elemento geral, o componente, e 2 sub elementos que herdam deste, o compostos e a folha. O composto pode possuir vários componentes como filho e a maioria de seus métodos é repassado para seus filhos. Já a folha fica encarregada de executar a operação implementada. Os compostos implementados foram:
- GLButton
- GLContainer
- GLPopUPMenu
- UI

e as folhas foram:
- GLLabel
- GLRectangle

O método principal deste é o _draw(GL2 gl)_. Este expressa como desenhar os elementos na tela

## Componente Mouse 
serve como uma ponte entre o componente screen, os componentes desenhados na screen(UI e BoardView3D) e o controller. Checando se algum elemento foi ativado ou não(clicado, arrastado, movimentos, etc...) e informa sinaliza que o controller deve iniciar alguma ação

## Componente GameController 
responsável por executar as ações requisitadas pelo usuário através do mouse

## Componente BoardModel
responsável por guardar as informações do jogador e do tabuleiro, por exemplo guardar:
- a quantidade de comida que o jogador possui
- quais celulas foram tomadas pelo jogador
- as informações das celulas(posição, componentes na celula, atributos da celula, etc...)
- etc...

## Componente Events 
responsável por ler e executar eventos aleatórios no tabuleiro


## Detalhamento das Interfaces


### Interface IRBoard3DManager

~~~java 
public interface IRBoard3DManager {
   public void connect(IBoard3DManager board_view_manager);
}
~~~
Método | Objetivo
-------| --------
connect | conectar o Componente Builder com o Componente BoardView3D 

### Interface IRBoardModelBuilder 

~~~java 
public interface IRBoardModelBuilder {
   public void connect(IBoardModelBuilder model);
}
~~~
Método | Objetivo
-------| --------
connect | conectar o Componente Builder com o Componente BoardModel 

### Interface IREventManager

~~~java 
public interface IREventManager {
   public void connect(IEventManager event_manager);
}
~~~
Método | Objetivo
-------| --------
connect | conectar o Componente Builder com o Componente Events

### Interface IRMouse

~~~java 
public interface IRMouse {
   public void connect(IMouse mouse);
}
~~~
Método | Objetivo
-------| --------
connect | conectar o Componente Builder com o Componente Mouse

### Interface IRScreenManager

~~~java 
public interface IRScreenManager {
   public void connect(IScreenManager screen);
}
~~~
Método | Objetivo
-------| --------
connect | conectar o Componente Builder com o Componente Screen

### Interface IRUIManager

~~~java 
public interface IRUIManager {
   public void connect(IUIManager ui_manager);
}
~~~
Método | Objetivo
-------| --------
connect | conectar o Componente Builder com o Componente UI

### Interface IGameBuilder

~~~java 
public interface IGameBuilder
	extends IRBoardModelBuilder,IRScreenManager,
	IRUIManager,IRMouse,IRBoard3DManager,IREventManager
	{
	public void buildGame();
	
}
~~~
Método | Objetivo
-------| --------
buildGame | conecta os Componentes do jogo uns com os outros
