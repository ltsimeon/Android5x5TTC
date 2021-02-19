package edu.quinnipiac.ser210.tictactoeapp;

import java.util.Scanner;

/**
 * Tic-Tac-Toe: Two-player console, non-graphics
 * 
 * @author ltsimeon
 * @date 2/3/2021
 */
public class TTTConsole {

	public static Scanner in = new Scanner(System.in); // the input Scanner

	public static TicTacToe TTTboard = new TicTacToe();

	/** The entry main method (the program starts here)
	public static void main(String[] args) {

		int currentState = TicTacToe.PLAYING;
		String userInput = "";
		// game loop
		do {
			boolean inputValid = false;
			int userInputInt = 0;

			do {
				if (TTTboard.getCurPlayer() == TicTacToe.HUMAN_PLAYER) { // don't actually let the player make a move unless it's their turn.
					System.out.println("Input a square to fill in...");
					userInput = in.next();
					try {
						userInputInt = Integer.parseInt(userInput);
						if (userInputInt >= 0 && userInputInt <= 24) {
							if (TTTboard.squareEmpty(userInputInt)) {
								inputValid = true;
							} else
								System.out.println("That square is taken.");
						} else
							System.out.println("That square doesn't exist.");
					} catch (NumberFormatException nfe) {
						System.out.println("That's not a number.");
					}
				} else {
					inputValid = true; // Computer can't mistype or make an invalid move.
					userInputInt = TTTboard.getComputerMove();
				}

			} while (!inputValid);

			TTTboard.setMove(TTTboard.getCurPlayer(), userInputInt);

			TTTboard.printBoard();

			TTTboard.changeTurns();

			// Print message if game-over
			currentState = TTTboard.checkForWinner();

			if (currentState == ITicTacToe.CROSS_WON) {
				System.out.println("'X' won! Bye!");
			} else if (currentState == ITicTacToe.NOUGHT_WON) {
				System.out.println("'O' won! Bye!");
			} else if (currentState == ITicTacToe.TIE) {
				System.out.println("It's a TIE! Bye!");
			}

		} while ((currentState == ITicTacToe.PLAYING) && (!userInput.equals("q"))); // repeat if not game-over
	} */

}