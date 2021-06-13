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

## Slides do Projeto
<colocar slides de prévia do projeto>

## Documentção dos Componentes
![Diagrama geral do Projeto](https://github.com/AndreTells/tarefasMC322/blob/main/Jogo-3D/images/Diagrama-Geral-Do-Projeto.jpg)
A ideia geral foi separar os componentes do jogo de acordo com a arquitetura MVC.
No model, busquei criar uma matrix de celulas que possuem componentes. Uma celula pode possuir mais de um componente e é possível adicionar ou remover componentes.Além de tal matriz, o componente board também guarda os atributos do jogador, sendo capaz de:
- executar eventos aleatórios 
- checar as condições de derrota
- pegar o valor dos atributos

O outro componente no model é o Events. Esse é responsável por ler o arquivo de eventos aleatórios e escolher eventos aleatórios no início de cada turno.
<br>
Já no View, há um componete screen que possuirá:
- Mouse
- camera
- UI
- uma matriz de cellView


O mouse será um listener da tela que possuirá vários IMouseObservers para cada uma de suas ações(mover, clicar ou arrastar o mouse) e cada um possuirá uma ação(esta fica guardada em um Objeto IActor que pertence ao IMouseObserver, que será explicado melhor posteriormente) para caso o mouse consiga ou não cumprir o requisito de ação do IMouseObserver.

A camera guardará onde o jogador está no mapa, para onde ele está olhando e possuirá um IMouseObserver para caso o mouse arraste na tela, permitindo que o jogador mova a camera pelo mapa.
<br>O UI será composto de vários elementos 2D. Para a organização desses, foi utilizado o compoiste design pattern, em que há uma classe geral, no caso GLElementComponent, que possui métodos que todos devem possuir e duas sub-classes:
- Composto: no caso GLElementComposite, que possuí varios GLElementComponents e repassa vários métodos chamados para suas folhas, no caso o método mais importante que isto afeta é o draw, responsável por apresentar o elemento na tela
- Folha: no caso GLElementLeaf, este difere do composite por não possuir outros GLElementComponents e, como consequência, ser obrigado a implementar os métodos ao invés de repassar esta tarefa para outros
O UI será herdeiro de GLElementComposite ou seja, possuirá vários sub elementos(textos e botões).
<br>A matrix de cellView são a representação gráfica das celulas do model. Cada cellView possui:
- uma posiçao no mapa
- um modelo 3d, concedido pela classe GameModels
A classe GameModels é responsável por ler arquivos .obj e salvar os modelos 3d contidos nesses em estruturas facilmente acessadas pelo jogo(denominadas CustomObject).Cada CustomObject possui uma caixa que o engloba, permitindo que o raypicker(implementado dentro do componente GLElement),um IMouseObserver, possa checar se alguma celula foi clicada e tomar a ação correta.
