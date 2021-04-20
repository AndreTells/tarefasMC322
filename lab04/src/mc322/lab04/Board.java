package mc322.lab04;

public class Board {
	private Space[][] pieces;
	
	Board(){
		// forma de facilmente definir um tabuleiro
		char board[][]= new char[7][7];
		board[0] ="  PPP  ".toCharArray();
		board[1] ="  PPP  ".toCharArray();
		board[2] ="PPPPPPP".toCharArray();
		board[3] ="PPP-PPP".toCharArray();
		board[4] ="PPPPPPP".toCharArray();
		board[5] ="  PPP  ".toCharArray();
		board[6] ="  PPP  ".toCharArray();
		
		pieces = new Space[board.length][board[0].length];
		for(int i =0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				if(board[i][j] != ' ') {
					if(board[i][j] == 'P'){
						pieces[i][j] = new Piece(i,j);
					}
					else {
						pieces[i][j] = new Hole(i,j);
					}
					
					
					pieces[i][j].setRelativePieces(i-1< 0 ? null : pieces[i-1][j],j-1< 0 ? null :pieces[i][j-1]);
				}
				else {
					pieces[i][j] = null;
				}
			}
		}
		
		
	}
	
	public String toString(){
		String board_s = new String();
		for(int i=0;i<pieces.length;i++) {
			for(int j=0;j<pieces[0].length;j++) {
				if(pieces[i][j] != null) {
					board_s +=pieces[i][j].icon;
				}
				else {
					board_s +=' ';
				}
			}
			board_s += '\n';
		}
		return board_s;
	}
	
	public void printBoard() {
		for(int i=0;i<pieces.length;i++) {
			System.out.print(7-i);
			for(int j=0;j<pieces[0].length;j++) {
				if(pieces[i][j] != null) {
					System.out.print(" "+pieces[i][j].icon);
				}
				else {
					System.out.print("  ");
				}
				
			}
			System.out.print('\n');
		}
		System.out.print("  a b c d e f g\n");
	}

	public void movePiece(int start[] , int end[]) {
		Hole transfer = (Hole) pieces[end[0]][end[1]];
		
		pieces[end[0]][end[1]] = ((Piece) pieces[start[0]][start[1]]).move((Hole) pieces[end[0]][end[1]]);
		pieces[start[0]][start[1]] = transfer;
		
		Hole hole_middle = new Hole(0,0);
		hole_middle.copy(pieces[(start[0]+end[0])/2][(start[1]+end[1])/2]); 
		pieces[(start[0]+end[0])/2][(start[1]+end[1])/2] = hole_middle; 
		
	}
}
