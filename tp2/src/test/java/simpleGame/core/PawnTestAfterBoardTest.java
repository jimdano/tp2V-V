package simpleGame.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simpleGame.exception.OutOfBoardException;

/**
 * @author Jimmy Dano & Anthony Le Mee
 * 
 */
public class PawnTestAfterBoardTest {

	/**
	 * board used in the tests
	 */
	private Board board;
	/**
	 * pawns used in the board
	 */
	private Pawn pawn;
	private Pawn pawn1;
	/**
	 * values for pawns instanctiation
	 */
	private int x = 1;
	private int y = 1;
	private char c = 'c';
	/**
	 * the size of the board
	 */
	private int xBoard = 5;
	private int yBoard = 5;
	private int nbOfPawns = 2;
	/**
	 * directions used in the tests
	 */
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
	 * @see {@link simpleGame.core.Pawn#getX()}.
	 * @passed yes
	 */
	@Test
	public void testGetX() {
		assertEquals(pawn1.getX(), x);
	}

	/**
	 * @see {@link simpleGame.core.Pawn#getY()}.
	 * @passed yes
	 */
	@Test
	public void testGetY() {
		assertEquals(pawn1.getY(), y);
	}

	/**
	 * @see {@link simpleGame.core.Pawn#getLetter()}.
	 * @passed yes
	 */
	@Test
	public void testGetLetter() {
		assertEquals(pawn1.getLetter(), c);
	}

	/**
	 * @see {@link simpleGame.core.Pawn#getGold()}.
	 * @passed yes
	 */
	@Test
	public void testGetGold() {
		assertEquals(pawn1.getGold(), 0);
	}

	/**
	 * @see {@link simpleGame.core.Pawn#Pawn(char, int, int, simpleGame.core.Board)}.
	 */
	@Test
	public void testPawn() {
		pawn = new Pawn(c, x, y, board);
		assertTrue(pawn.getGold() == 0);
		assertTrue(pawn.getLetter() == c);
		assertTrue(pawn.getX() == x);
		assertTrue(pawn.getY() == y);
	}

	/**
	 * @see {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 * @Oracle test to move in a direction which should be out of the board.
	 * @passed yes
	 * @exception OutOfBoardException
	 * @throws OutOfBoardException 
	 */
	@Test(expected = OutOfBoardException.class)
	public void testMove1() throws OutOfBoardException {
		pawn.move(left);
	}

	/**
	 * @see {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 * @Oracle test to move and make a pawn attack and kill another one.
	 * @passed yes
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
	 * @see {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 * @Oracle test to move and make a pawn attack another one without killing it.
	 * @passed yes
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
							assertTrue(pawn.getGold() == pawn1.getGold() + 1);
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
	 * @see {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 * @Oracle test to move when the board has no places tu fill.
	 * @passed yes
	 * @exception OutOfBoardException
	 * @throws OutOfBoardException 
	 */
	@Test(expected = Exception.class)
	public void testMove4() throws Exception {
		board = new Board(1, 0, 0);
		pawn.move(left);
	}

	/**
	 * @see {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 * @Oracle test to move left when the board has a negative size for the X axis
	 * @passed yes
	 * @exception OutOfBoardException
	 * @throws OutOfBoardException 
	 */
	@Test(expected = Exception.class)
	public void testMove5() throws Exception {
		board = new Board(1, -1, 0);
		pawn.move(left);
	}

	/**
	 * @see {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 * @Oracle test to move twice in the same direction and when we go further than the size of the board.
	 * @passed yes
	 * @exception OutOfBoardException
	 * @throws OutOfBoardException 
	 */
	@Test(expected = OutOfBoardException.class)
	public void testMove6() throws OutOfBoardException {
		pawn = new Pawn(c, x - 1, y - 1, board);
		pawn.move(down);
		pawn.move(up);
		pawn.move(down);
		pawn.move(down);
	}

	/**
	 * @see {@link simpleGame.core.Pawn#isDead()}.
	 * @Oracle assert a pawn is alive at the beginning.
	 * @passed yes
	 */
	@Test
	public void testIsDead() {
		assertFalse(pawn.isDead());
	}
}
