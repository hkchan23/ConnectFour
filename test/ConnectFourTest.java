import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConnectFourTest {
	private ConnectFour connectFour;
	@Before
	public void setUp() throws Exception {
		connectFour = new ConnectFour(7,6,"R","RED","G", "GREEN");
	}

	@Test
	public void testGetPlayerTurn() {
		assertEquals("failure - Player Turn is not equal", connectFour.getPlayer1(), connectFour.getPlayerTurn());
	}

	@Test
	public void testIsValidateMove() {
		for (int i=1; i<=connectFour.getCols();i++) {
			assertTrue("failure - " + i + " should be validate move", connectFour.isValidateMove(i));
		}
	}
	
	@Test
	public void testIsNotValidateMove() {
		assertFalse("failure - 0 should not be validate move", connectFour.isValidateMove(0));
		assertFalse("failure - "+(connectFour.getCols()+1)+" should be not validate move", connectFour.isValidateMove(connectFour.getCols()+1));
	}
	
	@Test
	public void testIsNotValidateMoveOnAllFull() {
		for (int i=0; i<connectFour.getRows();i++) {
			for (int j=1;j<=connectFour.getCols();j++) {
			connectFour.dropDisc(j);
			}
		}
		connectFour.printBoard();
		for (int i=1; i<=connectFour.getCols();i++) {
			assertFalse("failure - " + i + " should not be validate move", connectFour.isValidateMove(i));
		}
	}
	
	@Test
	public void testIsNotValidateMoveOnOneColumnFull() {
		for (int i=0; i<connectFour.getRows();i++) {
			connectFour.dropDisc(1);
		}
		connectFour.printBoard();
		assertFalse("failure - " + 1 + " should not be validate move", connectFour.isValidateMove(1));
	}
	
	@Test
	public void testNextPlayerTurn() {
		String currPlayerTurn = connectFour.getPlayerTurn();
		connectFour.nextPlayerTurn();
		String nextPlayerTurn = connectFour.getPlayerTurn();
		assertNotEquals("failure - Player Turn is equal", currPlayerTurn, nextPlayerTurn);
		
	}

	@Test
	public void testDropDiscColumn() {
		for (int i=0; i<connectFour.getRows();i++) {
			for (int j=1;j<=connectFour.getCols();j++) {
			connectFour.dropDisc(j);
			}
		}
		connectFour.printBoard();
		int count = 1;
		String player;
		for (int rownum=0; rownum<connectFour.getRows();rownum++) {
			for (int colnum=0;colnum<connectFour.getCols();colnum++) {
				if (count%2==0) {
					player = connectFour.getPlayer2();
				} else {
					player = connectFour.getPlayer1();
				}
				assertTrue("failure - board["+ rownum + "][" + colnum + "] should be filled with " + player, connectFour.getBoard()[rownum][colnum].equals(player));
				count++;
			}
		}
	}
	
	@Test
	public void testCheckHorizontal() {
		connectFour.dropDisc(4);
		connectFour.dropDisc(4);
		connectFour.dropDisc(5);
		connectFour.dropDisc(5);
		connectFour.dropDisc(3);
		connectFour.dropDisc(2);
		connectFour.dropDisc(6);
		connectFour.printBoard();
		assertTrue("failure - RED should be WIN ", connectFour.checkWinPlayer().equals("R"));
		
		connectFour.reset();
		connectFour.dropDisc(4);
		connectFour.dropDisc(4);
		connectFour.dropDisc(5);
		connectFour.dropDisc(5);
		connectFour.dropDisc(2);
		connectFour.dropDisc(3);
		connectFour.dropDisc(6);
		connectFour.dropDisc(3);
		connectFour.dropDisc(6);
		connectFour.dropDisc(2);
		connectFour.printBoard();
		assertTrue("failure - GREEN should be WIN ", connectFour.checkWinPlayer().equals("G"));
		
		connectFour.reset();
		connectFour.dropDisc(1);
		connectFour.dropDisc(2);
		connectFour.dropDisc(3);
		connectFour.dropDisc(2);
		connectFour.dropDisc(4);
		connectFour.dropDisc(3);
		connectFour.dropDisc(5);
		connectFour.dropDisc(4);
		connectFour.dropDisc(6);
		connectFour.printBoard();
		assertTrue("failure - RED should be WIN ", connectFour.checkWinPlayer().equals("R"));
		
		connectFour.reset();
		connectFour.dropDisc(2);
		connectFour.dropDisc(1);
		connectFour.dropDisc(7);
		connectFour.dropDisc(3);
		connectFour.dropDisc(6);
		connectFour.dropDisc(3);
		connectFour.dropDisc(5);
		connectFour.dropDisc(1);
		connectFour.dropDisc(4);
		connectFour.printBoard();
		assertTrue("failure - RED should be WIN ", connectFour.checkWinPlayer().equals("R"));
		
		connectFour.reset();
		connectFour.dropDisc(2);
		connectFour.dropDisc(1);
		connectFour.dropDisc(1);
		connectFour.dropDisc(4);
		connectFour.dropDisc(4);
		connectFour.dropDisc(5);
		connectFour.dropDisc(4);
		connectFour.dropDisc(6);
		connectFour.dropDisc(4);
		connectFour.dropDisc(7);
		connectFour.printBoard();
		assertTrue("failure - GREEN should be WIN ", connectFour.checkWinPlayer().equals("G"));
		
	}
	
	@Test
	public void testCheckVertical() {
		connectFour.dropDisc(1);
		connectFour.dropDisc(2);
		connectFour.dropDisc(1);
		connectFour.dropDisc(2);
		connectFour.dropDisc(1);
		connectFour.dropDisc(3);
		connectFour.dropDisc(1);
		connectFour.printBoard();
		assertTrue("failure - RED should be WIN ", connectFour.checkWinPlayer().equals("R"));
		
		connectFour.reset();
		connectFour.dropDisc(2);
		connectFour.dropDisc(3);
		connectFour.dropDisc(2);
		connectFour.dropDisc(3);
		connectFour.dropDisc(2);
		connectFour.dropDisc(2);
		connectFour.dropDisc(4);
		connectFour.dropDisc(3);
		connectFour.dropDisc(5);
		connectFour.dropDisc(3);
		connectFour.printBoard();
		assertTrue("failure - GREEN should be WIN ", connectFour.checkWinPlayer().equals("G"));
		
		connectFour.reset();
		connectFour.dropDisc(1);
		connectFour.dropDisc(2);
		connectFour.dropDisc(4);
		connectFour.dropDisc(2);
		connectFour.dropDisc(4);
		connectFour.dropDisc(5);
		connectFour.dropDisc(4);
		connectFour.dropDisc(6);
		connectFour.dropDisc(4);
		connectFour.printBoard();
		assertTrue("failure - RED should be WIN ", connectFour.checkWinPlayer().equals("R"));
		
		
		connectFour.reset();
		connectFour.dropDisc(7);
		connectFour.dropDisc(6);
		connectFour.dropDisc(6);
		connectFour.dropDisc(7);
		connectFour.dropDisc(3);
		connectFour.dropDisc(4);
		connectFour.dropDisc(7);
		connectFour.dropDisc(3);
		connectFour.dropDisc(7);
		connectFour.dropDisc(6);
		connectFour.dropDisc(7);
		connectFour.dropDisc(3);
		connectFour.dropDisc(7);
		connectFour.printBoard();
		assertTrue("failure - RED should be WIN ", connectFour.checkWinPlayer().equals("R"));
		
	}
	
	@Test
	public void testCheckDiagonalLeftToRight() {
		connectFour.dropDisc(2);
		connectFour.dropDisc(3);
		connectFour.dropDisc(3);
		connectFour.dropDisc(4);
		connectFour.dropDisc(4);
		connectFour.dropDisc(5);
		connectFour.dropDisc(4);
		connectFour.dropDisc(5);
		connectFour.dropDisc(6);
		connectFour.dropDisc(4);
		connectFour.dropDisc(5);
		connectFour.dropDisc(6);
		connectFour.dropDisc(5);
		connectFour.printBoard();
		assertTrue("failure - RED should be WIN ", connectFour.checkWinPlayer().equals("R"));
		
		connectFour.reset();
		connectFour.dropDisc(2);
		connectFour.dropDisc(3);
		connectFour.dropDisc(3);
		connectFour.dropDisc(4);
		connectFour.dropDisc(4);
		connectFour.dropDisc(5);
		connectFour.dropDisc(4);
		connectFour.dropDisc(5);
		connectFour.dropDisc(6);
		connectFour.dropDisc(4);
		connectFour.dropDisc(5);
		connectFour.dropDisc(6);
		connectFour.dropDisc(6);
		connectFour.dropDisc(5);
		connectFour.dropDisc(6);
		connectFour.dropDisc(7);
		connectFour.dropDisc(7);
		connectFour.dropDisc(7);
		connectFour.dropDisc(4);
		connectFour.dropDisc(7);
		connectFour.dropDisc(7);
		connectFour.printBoard();
		assertTrue("failure - RED should be WIN ", connectFour.checkWinPlayer().equals("R"));
		
	}
	
	@Test
	public void testCheckDiagonalRightToLeft() {
		connectFour.dropDisc(7);
		connectFour.dropDisc(6);
		connectFour.dropDisc(6);
		connectFour.dropDisc(7);
		connectFour.dropDisc(6);
		connectFour.dropDisc(7);
		connectFour.dropDisc(4);
		connectFour.dropDisc(5);
		connectFour.dropDisc(6);
		connectFour.dropDisc(4);
		connectFour.dropDisc(5);
		connectFour.dropDisc(6);
		connectFour.dropDisc(5);
		connectFour.dropDisc(4);
		connectFour.dropDisc(4);
		connectFour.printBoard();
		assertTrue("failure - RED should be WIN ", connectFour.checkWinPlayer().equals("R"));
		
		connectFour.reset();
		connectFour.dropDisc(1);
		connectFour.dropDisc(3);
		connectFour.dropDisc(3);
		connectFour.dropDisc(1);
		connectFour.dropDisc(3);
		connectFour.dropDisc(2);
		connectFour.dropDisc(2);
		connectFour.dropDisc(1);
		connectFour.dropDisc(1);
		connectFour.dropDisc(1);
		connectFour.dropDisc(3);
		connectFour.dropDisc(3);
		connectFour.dropDisc(4);
		connectFour.dropDisc(4);
		connectFour.dropDisc(2);
		connectFour.printBoard();
		assertTrue("failure - RED should be WIN ", connectFour.checkWinPlayer().equals("R"));
	}

}
