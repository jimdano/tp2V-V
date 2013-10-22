/**
 * 
 */
package simpleGame.core;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jimmy
 *
 */
public class BoardTest {

	private Board board;
	private int xLength = 5;
	private int yLength = 5;
	private int somePawns = 2;
	private int yPawn = 2; 
	private int xPawn = 2;
	private char n = 'n';
	private Pawn p ;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		p = new Pawn(n, xPawn, yPawn, board);
		board = new Board(somePawns, xLength, yLength);
	}

	/**
	 * @see {@link simpleGame.core.Board#getXSize()}.
	 * @oracle must return true ; board's x-size must be equals with attributes called xLength
	 * @passed yes
	 */
	@Test
	public void testGetXSize() {
		assertEquals(board.getXSize(), xLength);
	}
	
	/**
	 * @see {@link simpleGame.core.Board#setCurrentPawn()}.
	 * @oracle must affect the current pawn
	 * @passed yes
	 */
	@Test
	public void testSetCurrentPawn() {
		board.addPawn(p);
		board.setCurrentPawn(p);
		assertTrue(board.getCurrentPawn() == p);
	}

	/**
	 * @see {@link simpleGame.core.Board#getYSize()}.
	 * @oracle must return true ; board's y-size must be equals with attributes called yLength
	 * @passed yes
	 */
	@Test
	public void testGetYSize() {
		assertEquals(board.getYSize(), yLength);
	}

	/**
	 * @see {@link simpleGame.core.Board#Board(int, int, int)}.
	 * @exception IndexOutOfBoundsException
	 * @throws IndexOutOfBoundsException
	 * @oracle must throw IndexOutOfBoundsException
	 * @passed yes
	 */
	@Test (expected=IndexOutOfBoundsException.class)
	public void testBoard() {
		assertTrue(board.getNextPawn()!=null);
		board = new Board(0, xLength, yLength);
		assertTrue(board.getNextPawn()==null);
	}

	/**
	 * @see {@link simpleGame.core.Board#getSquareContent(int, int)}.
	 * @oracle return  the pawn insert before
	 * @passed yes
	 */
	@Test
	public void testGetSquareContent() {
		board.addPawn(p);
		assertTrue(p == board.getSquareContent(xPawn, yPawn));
		board = new Board(1, xPawn, yPawn);
		if(board.getSquareContent(xPawn, yPawn) != null)
			board.removePawn(board.getSquareContent(xPawn, yPawn));
		assertTrue(null == board.getSquareContent(xPawn, yPawn));
	}

	/**
	 * @see {@link simpleGame.core.Board#removePawn(simpleGame.core.Pawn)}.
	 * @oracle remove pawn insert before
	 * @passed yes
	 */
	@Test
	public void testRemovePawn() {
		assertTrue(board.numberOfPawns() == somePawns);
		if(board.getSquareContent(xPawn, yPawn) != null)
			p = new Pawn(n, 0, 0, board);
		board.addPawn(p);
		assertTrue(board.numberOfPawns() == somePawns+1);
		board.removePawn(p);
		assertTrue(board.numberOfPawns() == somePawns);
		
	}

	/**
	 * @see {@link simpleGame.core.Board#addPawn(simpleGame.core.Pawn)}.
	 * @oracle increase count of pawn when a new pawn was add into board
	 * @passed yes
	 */
	@Test
	public void testAddPawn() {
		assertTrue(board.numberOfPawns() == somePawns);
		if(board.getSquareContent(xPawn, yPawn) != null)
			p = new Pawn(n, 0, 0, board);
		board.addPawn(p);
		assertTrue(board.numberOfPawns() == somePawns+1);
	}

	/**
	 * @see {@link simpleGame.core.Board#isBonusSquare(int, int)}.
	 * @oracle certify that there is only one bonus case into the board
	 * @passed yes
	 */
	@Test
	public void testIsBonusSquare() {
		int nbOfBonus = 0;
		for(int i = 0; i<board.getXSize(); i ++){
			for(int j = 0; j<board.getYSize(); j ++){
				if(board.isBonusSquare(i, j))
					nbOfBonus ++;
			}
		}
		assertTrue(nbOfBonus == 1);
	}

	/**
	 * @see {@link simpleGame.core.Board#numberOfPawns()}.
	 * @oracle must return the good number of pawn at the initialization of the board
	 * @passed yes
	 */
	@Test
	public void testNumberOfPawns() {
		
		assertEquals(somePawns, board.numberOfPawns());
		for (int i = 0; i < board.getXSize(); i++) {
			for (int j = 0 ; j < board.getYSize(); j++) {
				if(board.getSquareContent(i, j) == null) {
					board.addPawn(p);
					assertEquals(somePawns + 1, board.numberOfPawns());
					board.removePawn(p);
					assertEquals(somePawns, board.numberOfPawns());
					break;
				}
			}
		}
		
	}

	/**
	 * @see {@link simpleGame.core.Board#maxGold()}.
	 * @see TestSeqenceDiagramm
	 * @oracle must return the number of max gold at the initialization of the board
	 * @passed yes
	 */
	@Test
	public void testMaxGold() {
		assertEquals(board.maxGold(), 0);
		Pawn p = board.getNextPawn();
		if(p!= null){
			p.setGold(5);
			assertTrue(board.maxGold() == 5);
			p.setGold(-1);
			assertTrue(board.maxGold() == 0);
		}
	}

	/**
	 * @see {@link simpleGame.core.Board#getNextPawn()}.
	 * @oracle return the next pawn
	 * @passed yes
	 */
	@Test
	public void testGetNextPawn() {
		board = new Board(1, xLength, yLength);
		assertTrue(board.getNextPawn()!=null);
	}

	/**
	 * @see {@link simpleGame.core.Board#squareContentSprite(int, int)}.
	 * @oracle prove that board is correctly create
	 * @passed yes
	 */
	@Test
	public void testSquareContentSprite() {
		for (int i = 0; i < board.getXSize(); i++) {
			for (int j = 0; j < board.getYSize(); j++) {
				
				if (board.isBonusSquare(i, j)) {
					assertEquals('#', board.squareContentSprite(i,j));
				}
				else if (board.getSquareContent(i, j) == null) {
					assertEquals('.', board.squareContentSprite(i,j));
				}
				else {
					Pawn courant = board.getSquareContent(i, j);
					if (board.getCurrentPawn().equals(courant)) {
						assertEquals('c', board.squareContentSprite(courant.getX(), courant.getY()));
					}else{
						assertTrue(courant.getLetter() != 'c' && 
								courant.getLetter() != '.' &&
								courant.getLetter() != '#');
					}
				}
			}
		}
	}

	/**
	 * @see {@link simpleGame.core.Board#toString()}.
	 * @oracle return representation of board
	 * @passed yes
	 */
	@Test
	public void testToString() {
		assertNotNull(board.toString());
	}

}
