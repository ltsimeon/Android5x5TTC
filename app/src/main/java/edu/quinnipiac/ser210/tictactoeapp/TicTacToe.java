/**
 * TicTacToe class implements the interface
 * @author ltsimeon
 * @date 2/3/2021
 */

package edu.quinnipiac.ser210.tictactoeapp;

public class TicTacToe implements ITicTacToe {
		 
	// The game board and the game status
	    private static final int ROWS = 5, COLS = 5; // number of rows and columns
	    private int[][] board = new int[ROWS][COLS]; // game board in 2D array
	    private int currentPlayer;
	    private int crossesPlayer; // whoever went first
	   
	// Name-constants to represent the players
		public static final int HUMAN_PLAYER = 0;
		public static final int COMPUTER_PLAYER = 1;
	  
	/**
	 * clear board and set current player   
	 */
	public TicTacToe(){
		clearBoard();
		if (Math.random() > 0.5) {
			currentPlayer = COMPUTER_PLAYER;
			// System.out.println("You get to be O, so I go first.");
		} else { 
			currentPlayer = HUMAN_PLAYER;
			// System.out.println("You're X, you go first.");
		}
		crossesPlayer = currentPlayer; 
	}
	
	@Override
	public void clearBoard() {
		for (int[] rows : board) {
			for (int c : rows ) {
				rows[c]= ITicTacToe.EMPTY;
			}
		}		
	}

	@Override
	public void setMove(int player, int location) {
		int xCoord = location % COLS;
		int yCoord = (int) Math.floor(location/ROWS);
		if (player == crossesPlayer) {
			board[yCoord][xCoord] = CROSS;
		} else board[yCoord][xCoord] = NOUGHT;
	}

	@Override
	public int getComputerMove() {
		int move = 0;
		{
		if (board[2][2] == EMPTY) move = 12; 
			else { 
				boolean moveValid = false;
				do {
					move = (int) Math.floor(Math.random()*ROWS*COLS);
					int xCoord = move % COLS;
					int yCoord = (int) Math.floor(move/ROWS);
					moveValid = (board[yCoord][xCoord] == EMPTY);
				} while (!moveValid);
			}
		}
		return move;
	}

	@Override
	public int checkForWinner() {
		int gameState = 0;
		for (int[] row : board ) { // check horizontal matches
			if (fourInRow(row[0],row[1],row[2],row[3],row[4])) {
				gameState = 1 + row[2]; // crosses is 1, noughts is 2; crosses won is 2, noughts won is 3. How convenient
			}
		}
		for (int column = 0; column < COLS; column++) { // check vertical matches
			if (fourInRow(board[0][column],board[1][column],board[2][column],board[3][column],board[4][column])) {
				gameState = 1 + board[2][column]; // same trick as in horizontal matches
			}
		}
		 // check diagonal matches
		if (fourInRow(board[0][0],board[1][1],board[2][2],board[3][3],board[4][4]) // Top left to bottom right.
		||  fourInRow(board[4][0],board[3][1],board[2][2],board[1][3],board[0][4]))  // Bottom left to top right
			gameState = 1+board[2][2];
		if( fourInRow(board[1][0],board[2][1],board[3][2],board[4][3],0) // subdiagonal 1 TL-BR. these are only four long so the fifth square is irrelevant
		||  fourInRow(board[3][0],board[2][1],board[1][2],board[0][3],0)) // subdiagonal 1 BL-TR
			gameState = 1+board[2][1];
	    if( fourInRow(board[0][1],board[1][2],board[2][3],board[3][4],0) // subdiagonal 2 TL-BR
		||  fourInRow(board[4][1],board[3][2],board[2][3],board[1][4],0))  // subdiagonal 2 BL-TR
	    	gameState = 1+board[2][3]; // yeah this part is a mess but it works correctly for all six diagonal fours!
	    	
		if (gameState == 0) { // now check for a draw
			int squaresFilled = 0;
			for (int[] rows : board) {
				for (int c : rows ) {
					if(rows[c] != EMPTY) {
						squaresFilled++;
					}
				}
			}
			if (squaresFilled >= ROWS * COLS) {
				gameState = 1;
			}
		}
		return gameState;
	}
	
	  /**
	   *  Print the game board. Not used in Android ver
	   */
	   public  void printBoard() {
	      for (int row = 0; row < ROWS; ++row) {
	         for (int col = 0; col < COLS; ++col) {
	            printCell(board[row][col]); // print each of the cells
	            if (col != COLS - 1) {
	               System.out.print("|");   // print vertical partition
	            }
	         }
	         System.out.println();
	         if (row != ROWS - 1) {
	            System.out.println("-------------------"); // print horizontal partition
	         }
	      }
	      System.out.println();
	   }
	 
	   /**
	    * Print a cell with the specified "content" 
	    * @param content either CROSS, NOUGHT or EMPTY
	    */
	   public void printCell(int content) {
	      switch (content) {
	         case EMPTY:  System.out.print("   "); break;
	         case NOUGHT: System.out.print(" O "); break;
	         case CROSS:  System.out.print(" X "); break;
	      }
	   }

	   // Returns the current symbol in the given cell, in 0 is top left, 24 is bottom right syntax.
	   public String returnCell(int cell) {
		   switch (board[(int)Math.floor(cell/5)][cell%5]) {
			   case EMPTY:  return "_";
			   case NOUGHT: return "O";
			   case CROSS:  return "X";
		   }
		   return "_";
	   }
	   
	   public int getCurPlayer() {
		   return currentPlayer;
	   }
	   
	   public void changeTurns() {
		   currentPlayer = 1-currentPlayer;
	   }
	   
	   public boolean squareEmpty(int location) {
		   int xCoord = location % COLS;
		   int yCoord = (int) Math.floor(location/ROWS);
		   return (board[yCoord][xCoord] == EMPTY);
	   }
	   
	   public boolean fourInRow(int a, int b, int c, int d, int e) {
		   return ((a != EMPTY && a == b && b == c && c == d)
				|| (e != EMPTY && e == d && d == c && c == b));
	   }

	   public boolean wasPlayerCrosses() {
	   	return (crossesPlayer == HUMAN_PLAYER);
	   }
}
