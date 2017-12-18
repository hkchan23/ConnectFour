import java.util.Scanner;

/*
 * Implement a Connect Four game.
 * https://en.wikipedia.org/wiki/Connect_Four
 * @author Keith H K Chan
 */
public class ConnectFour {
	private int cols;
	private int rows;
	private String[][] board;
	private int maxMoves;
	private int currentMove;
	private String player1;
	private String player2;
	private String player1Name;
	private String player2Name;
	private String playerTurn;
	private int connectForWin = 4;
	
	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}
	
	public String[][] getBoard() {
		return board;
	}

	public int getMaxMoves() {
		return maxMoves;
	}
	
	public int getCurrentMove() {
		return currentMove;
	}

	public String getPlayer1() {
		return player1;
	}

	public String getPlayer2() {
		return player2;
	}
	
	public String getPlayer1Name() {
		return player1Name;
	}

	public String getPlayer2Name() {
		return player2Name;
	}

	public String getPlayerTurn() {
		return playerTurn;
	}

	public ConnectFour (int cols, int rows, String player1, String player1Name, String player2, String player2Name) {
		this.cols = cols;
		this.rows = rows;
		this.player1 = player1;
		this.player1Name = player1Name;
		this.player2 = player2;
		this.player2Name = player2Name;
		board = new String[rows][cols];
		maxMoves = cols*rows;
		reset();
	}
	
	//Reset the game
	public void reset() {
		currentMove = 0;
		playerTurn = player1;
		emptyBoard();
	}
	
	//Empty the board
	private void emptyBoard() {
		for (int rownum=0; rownum < rows; rownum++) {
			for (int colnum=0; colnum < cols; colnum++) {
				board[rownum][colnum] = " ";
			}
		}
	}
	
	//Print the board
	public void printBoard() {
		for (int rownum=rows-1; rownum >= 0; rownum--) {
			for (int colnum=0; colnum < cols; colnum++) {
				System.out.print("|"+board[rownum][colnum]);
			}
			System.out.print("|\n");
		}
	}

	//Check the input is validate move
	public boolean isValidateMove(int col) {
		// Check Input is within the column range
		if (col < 1 || col > cols) {
			System.out.println("Input should be between 1 and " + cols + ".");
			return false;
		}
		// Check no more move
		if (currentMove == maxMoves) {
			System.out.println("All Columns are full.");
			return false;
		}
		// Check the column is full
		if (!board[rows-1][col-1].equals(" ")) {
			System.out.println("Column " + col + " is full.");
			return false;
		}
		return true;
		
	}
	
	//Drop the disc into column
	public void dropDisc (int col) {
		for (int rownum = 0;rownum<rows;rownum++) {
			if (board[rownum][col-1].equals(" ")) {
				board[rownum][col-1] = playerTurn;
				currentMove++;
				nextPlayerTurn();
				break;
			}
		}
	}
	
	//Switch to next Player
	public void nextPlayerTurn() {
		if (playerTurn.equals(player1)) {
			playerTurn = player2;
		} else {
			playerTurn = player1;
		}
	}
	
	// Check Win player with four direction
	public String checkWinPlayer() {
		try {
		for (int rownum = 0;rownum<rows;rownum++) {
			for (int colnum = 0;colnum<cols;colnum++) {
				String player = board[rownum][colnum];
				if (player.equals(" ")) {
					continue;
				}
				
				if (colnum + (connectForWin-1) < cols) {
					// Check Horizontal for connect 4
					if (checkConnectWin(player, rownum, colnum, 0, 1)) {
						return player;
					}
				}
				
				if (rownum + (connectForWin-1) < rows) {
					// Check Vertical for connect 4
					if (checkConnectWin(player, rownum, colnum, 1, 0)) {
						return player;
					}
					
					if (colnum + (connectForWin-1) < cols) {
						// Check diagonal "/" direction for connect 4
						if (checkConnectWin(player, rownum, colnum, 1, 1)) {
							return player;
						}
					}
					
					if (colnum - (connectForWin-1) >= 0) {
						// Check diagonal "\" direction for connect 4
						if (checkConnectWin(player, rownum, colnum, 1, -1)) {
							return player;
						}
					}
				}
			}
		}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//Check the match connect for Win
	public boolean checkConnectWin(String player, int rownum, int colnum, int rowDelta, int colDelta) {
		boolean win = true;
		for (int i=0;i<connectForWin-1;i++) {
			rownum+=rowDelta;
			colnum+=colDelta;
			if (!player.equals(board[rownum][colnum])) {
				win = false;
				break;
			}
		}
		return win;
	}


}
