/**
 * 
 */
package simpleGame.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simpleGame.exception.OutOfBoardException;

/**
 * @author 10003134
 * 
 */
public class PawnTestAfterBoardTest {

	private Board board;
	private Pawn pawn;
	private Pawn pawn1;
	private int x = 1;
	private int y = 1;
	private char c = 'c';
	private int xBoard = 5;
	private int yBoard = 5;
	private int nbOfPawns = 2;
	private Direction up = Direction.Up;
	private Direction down = Direction.Down;
	private Direction left = Direction.Left;
	private Direction right = Direction.Right;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		board = new Board(nbOfPawns, xBoard, yBoard);
		pawn = new Pawn(c, x, y, board);
		pawn1 = new Pawn(c, x, y, board);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link simpleGame.core.Pawn#getX()}.
	 */
	@Test
	public void testGetX() {
		assertEquals(pawn1.getX(), x);
	}

	/**
	 * Test method for {@link simpleGame.core.Pawn#getY()}.
	 */
	@Test
	public void testGetY() {
		assertEquals(pawn1.getY(), y);
	}

	/**
	 * Test method for {@link simpleGame.core.Pawn#getLetter()}.
	 */
	@Test
	public void testGetLetter() {
		assertEquals(pawn1.getLetter(), c);
	}

	/**
	 * Test method for {@link simpleGame.core.Pawn#getGold()}.
	 */
	@Test
	public void testGetGold() {
		assertEquals(pawn1.getGold(), 0);
	}

	/**
	 * Test method for
	 * {@link simpleGame.core.Pawn#Pawn(char, int, int, simpleGame.core.Board)}.
	 */
	@Test
	public void testPawn() {

	}

	/**
	 * Test method for
	 * {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 * 
	 * @throws OutOfBoardException
	 */
	@Test(expected = OutOfBoardException.class)
	public void testMove1() throws OutOfBoardException {
		pawn.move(left);
	}

	/**
	 * Test method for
	 * {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 */
	@Test
	public void testMove2() {
		for (int i = 1; i < board.getXSize() - 1; i++) {
			for (int j = 1; j < board.getYSize() - 1; j++) {
				if (!board.isBonusSquare(i, j)) {
					pawn = new Pawn(c, i, j, board);
					pawn1 = new Pawn(c, i + 1, j, board);
					board.addPawn(pawn);
					board.addPawn(pawn1);
					try {
						pawn.move(right);
						assertFalse(pawn1.isDead());
					} catch (OutOfBoardException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}

	/**
	 * Test method for
	 * {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 */
	@Test
	public void testMove3() {
		boolean found = false;
		while (!found) {
			for (int i = 1; i < board.getXSize() - 1; i++) {
				for (int j = 1; j < board.getYSize() - 1; j++) {
					if (board.isBonusSquare(i, j)) {
						found = true;
						pawn = new Pawn(c, i, j, board);
						pawn1 = new Pawn(c, i + 1, j, board);
						board.addPawn(pawn);
						board.addPawn(pawn1);
						try {
							pawn.move(right);
							assertTrue(pawn1.isDead());
						} catch (OutOfBoardException e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
			if (!found)
				board = new Board(nbOfPawns, xBoard, yBoard);
		}
	}

	/**
	 * Test method for
	 * {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 * 
	 * @throws OutOfBoardException
	 */
	@Test(expected = Exception.class)
	public void testMove4() throws Exception {
		board = new Board(1, 0, 0);
		pawn.move(left);
	}

	/**
	 * Test method for
	 * {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 * 
	 * @throws OutOfBoardException
	 */
	@Test(expected = Exception.class)
	public void testMove5() throws Exception {
		board = new Board(1, -1, 0);
		pawn.move(left);
	}

	/**
	 * Test method for
	 * {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 * 
	 * @throws OutOfBoardException
	 */
	@Test(expected = OutOfBoardException.class)
	public void testMove6() throws OutOfBoardException {
		pawn = new Pawn(c, x - 1, y - 1, board);
		pawn.move(down);
		pawn.move(down);
	}

	/**
	 * Test method for {@link simpleGame.core.Pawn#isDead()}.
	 */
	@Test
	public void testIsDead() {
		assertFalse(pawn.isDead());
	}
}
