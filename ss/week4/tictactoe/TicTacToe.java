package week4.tictactoe;

/**
 * Executable class for the game Tic Tac Toe. The game can be played against the
 * computer. Lab assignment Module 2
 * 
 * @author Theo Ruys
 * @version $Revision: 1.4 $
 */
public class TicTacToe {
	
	public static void main(String[] args) {

		if (args.length == 2) {
			Player player1 = new HumanPlayer(args[0], Mark.XX);
			Player player2 = new HumanPlayer(args[1], Mark.OO);
			
			Game newGame = new Game(player1, player2);
			newGame.start();
		} else {
			System.out.println("Error: Number of players can only be 2.");
		}


	}
}
