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
	private int somePawns = 1;
	private Pawn p;
	private int yPawn = 2; 
	private int xPawn = 2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		board = new Board(somePawns, xLength, yLength);
	}

	/**
	 * Test method for {@link simpleGame.core.Board#getXSize()}.
	 */
	@Test
	public void testGetXSize() {
		assertEquals(board.getXSize(), xLength);
	}

	/**
	 * Test method for {@link simpleGame.core.Board#getYSize()}.
	 */
	@Test
	public void testGetYSize() {
		assertEquals(board.getYSize(), yLength);
	}

	/**
	 * Test method for {@link simpleGame.core.Board#Board(int, int, int)}.
	 */
	@Test (expected=IndexOutOfBoundsException.class)
	public void testBoard() {
		assertTrue(board.getNextPawn()!=null);
		board = new Board(0, xLength, yLength);
		assertTrue(board.getNextPawn()==null);
	}

	/**
	 * Test method for {@link simpleGame.core.Board#getSquareContent(int, int)}.
	 */
	@Test
	public void testGetSquareContent() {
		p = new Pawn('x', xPawn, yPawn, board);
		board.addPawn(p);
		assertTrue(p == board.getSquareContent(xPawn, yPawn));
		board = new Board(1, xPawn, yPawn);
		if(board.getSquareContent(xPawn, yPawn) != null)
			board.removePawn(board.getSquareContent(xPawn, yPawn));
		assertTrue(null == board.getSquareContent(xPawn, yPawn));
	}

	/**
	 * Test method for {@link simpleGame.core.Board#removePawn(simpleGame.core.Pawn)}.
	 */
	@Test
	public void testRemovePawn() {
		p = new Pawn('x', xPawn, yPawn, board);
		assertTrue(board.numberOfPawns() == somePawns);
		if(board.getSquareContent(xPawn, yPawn) != null)
			p = new Pawn('x', 0, 0, board);
		board.addPawn(p);
		assertTrue(board.numberOfPawns() == somePawns+1);
		board.removePawn(p);
		assertTrue(board.numberOfPawns() == somePawns);
		
	}

	/**
	 * Test method for {@link simpleGame.core.Board#addPawn(simpleGame.core.Pawn)}.
	 */
	@Test
	public void testAddPawn() {
		p = new Pawn('x', xPawn, yPawn, board);
		assertTrue(board.numberOfPawns() == somePawns);
		if(board.getSquareContent(xPawn, yPawn) != null)
			p = new Pawn('x', 0, 0, board);
		board.addPawn(p);
		assertTrue(board.numberOfPawns() == somePawns+1);
	}

	/**
	 * Test method for {@link simpleGame.core.Board#isBonusSquare(int, int)}.
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
	 * Test method for {@link simpleGame.core.Board#numberOfPawns()}.
	 */
	@Test
	public void testNumberOfPawns() {
		assertEquals(somePawns, board.numberOfPawns());
	}

	/**
	 * Test method for {@link simpleGame.core.Board#maxGold()}.
	 */
	@Test
	public void testMaxGold() {
		assertEquals(board.maxGold(), 0);
	}

	/**
	 * Test method for {@link simpleGame.core.Board#getNextPawn()}.
	 */
	@Test
	public void testGetNextPawn() {
		board = new Board(1, xLength, yLength);
		assertTrue(board.getNextPawn()!=null);
	}

	/**
	 * Test method for {@link simpleGame.core.Board#squareContentSprite(int, int)}.
	 */
	@Test
	public void testSquareContentSprite() {
		assertNotNull(board.squareContentSprite(1,1));
	}

	/**
	 * Test method for {@link simpleGame.core.Board#toString()}.
	 */
	@Test
	public void testToString() {
		assertNotNull(board.toString());
	}

}
