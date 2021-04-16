package mc322.lab04;

public class Board {
	char [][] board;
	
	Board(){
		board= new char[7][7];
		board[0] ="  PPP  \n".toCharArray();
		board[1] ="  PPP  \n".toCharArray();
		board[2] ="PPPPPPP\n".toCharArray();
		board[3] ="PPP-PPP\n".toCharArray();
		board[4] ="PPPPPPP\n".toCharArray();
		board[5] ="  PPP  \n".toCharArray();
		board[6] ="  PPP  \n".toCharArray(); 
	}
	void move_piece(int start[], int end[]) {
		board[start[0]][start[1]] = '-';
		board[(start[0]+end[0])/2][(start[1]+end[1])/2] = '-';
		board[end[0]][end[1]] = 'P';
	}
	
	String to_string(){
		String board_s = new String();
		for(int i=0;i<board.length ;i++) {
			for(int j=0;j<board[0].length;j++) {
				board_s+=board[i][j];
			}
			
		}
		return board_s;
	}
	
	void print_board() {
		for(int i=0;i<board.length;i++) {
			System.out.print(7-i);
			for(int j=0;j<board[0].length;j++) {
				System.out.print(" "+board[i][j]);
			}
		}
		System.out.print("  a b c d e f g\n");
	}

	
}
