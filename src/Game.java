import java.util.Scanner;

/*
 * Implement a Game Console for Connect Four.
 * https://en.wikipedia.org/wiki/Connect_Four
 * @author Keith H K Chan
 */
public class Game {

	public static void main(String[] args) {
		// Initial the connect four game with 7 columns and 6 rows
		// Player 1 is "R" and Player 2 is "G"
		ConnectFour connectFour = new ConnectFour(7,6,"R","RED","G","GREEN");
		connectFour.printBoard();
		boolean gameOver = false;
		Scanner scanner = new Scanner(System.in);
		do {
			if (connectFour.getPlayerTurn().equals(connectFour.getPlayer1())) {
				System.out.println("Player 1" +"["+connectFour.getPlayer1Name()+"] - choose column (1-7): ");
			} else {
				System.out.println("Player 2" +"["+connectFour.getPlayer2Name()+"] - choose column (1-7): ");
			}
				boolean error = true;
	        		String input = scanner.nextLine();
	        		int inputColumn = 0;
	        		try {
	        			inputColumn = Integer.parseInt(input);
	        			error = false;
	        		} catch (NumberFormatException ex) {
	        			System.out.println("Please enter number!");
	        		}
	        		if (!error && connectFour.isValidateMove(inputColumn)) {
	        			connectFour.dropDisc(inputColumn);
	        			connectFour.printBoard();
	        			String player = connectFour.checkWinPlayer();
	        			if (player != null) {
	        				if (connectFour.getPlayer1().equals(player)) {
	        					System.out.println("Player 1 ["+connectFour.getPlayer1Name()+"] wins!");
	        				} else {
	        					System.out.println("Player 2 ["+connectFour.getPlayer2Name()+"] wins!");
	        				}
	        				gameOver=true;
	        			} else {
	        				// Check no more move
	        				if (connectFour.getCurrentMove() == connectFour.getMaxMoves()) {
	        					System.out.println("Draw!");
	        					gameOver=true;
	        				}
	        			}
	        		}
	        
		} while (!gameOver);
		scanner.close();
	}
}
