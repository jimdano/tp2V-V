package simpleGame.core;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import simpleGame.exception.OutOfBoardException;

/**
 * @author Jimmy Dano & Anthony Le Mee
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PawnTest {

	/**
	 * board mocked for tests
	 */
	@Mock
	private Board board;
	/**
	 * pawns used for the board
	 */
	private Pawn pawn;
	private Pawn pawn1;
	/**
	 * properties for the pawns
	 */
	private int x = 1;
	private int y = 1;
	private char c = 'c';
	/**
	 * directions tested
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
	@Test (expected=OutOfBoardException.class)
	public void testMove1() throws OutOfBoardException {
		
		/*utiliser le mock*/
		when(board.getYSize()).thenReturn(3);
		when(board.getXSize()).thenReturn(3);
		pawn.move(left);
	}
	
	/**
	 * @see {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 * @Oracle test to move and make a pawn attack and kill another one.
	 * @passed yes
	 */
	@Test
	public void testMove2() {
		
		/*utiliser le mock*/
		when(board.getYSize()).thenReturn(3);
		when(board.getXSize()).thenReturn(3);
		when(board.getSquareContent(x,y+1)).thenReturn(null);
		when(board.getSquareContent(x+1,y)).thenReturn(pawn1);
		when(board.isBonusSquare(x, y)).thenReturn(true);
		
		try {
			pawn.move(up);
			assertTrue(pawn.getX() == x && pawn.getY() == y+1);
			pawn.move(down);
			
			pawn.move(right);
			assertTrue(pawn1.isDead());
			assertTrue(pawn.getGold() == pawn1.getGold() + 1);
			
		} catch (OutOfBoardException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @see {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 * @Oracle test to move and make a pawn attack another one without killing it.
	 * @passed yes
	 */
	@Test
	public void testMove3() {
		
		/*utiliser le mock*/
		when(board.getYSize()).thenReturn(3);
		when(board.getXSize()).thenReturn(3);
		when(board.getSquareContent(x+1,y)).thenReturn(pawn1);
		when(board.isBonusSquare(x, y)).thenReturn(false);
		
		try {
			pawn.move(right);
			assertFalse(pawn1.isDead());
			
		} catch (OutOfBoardException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @see {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 * @Oracle test to move when the board has no places tu fill.
	 * @passed yes
	 * @exception OutOfBoardException
	 * @throws OutOfBoardException 
	 */
	@Test (expected=OutOfBoardException.class)
	public void testMove4() throws OutOfBoardException {
		
		/*utiliser le mock*/
		when(board.getYSize()).thenReturn(0);
		when(board.getXSize()).thenReturn(0);
		pawn.move(left);
	}
	
	/**
	 * @see {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 * @Oracle test to move left when the board has a negative size for the X axis
	 * @passed yes
	 * @exception OutOfBoardException
	 * @throws OutOfBoardException 
	 */
	@Test (expected=OutOfBoardException.class)
	public void testMove5() throws OutOfBoardException {
		
		/*utiliser le mock*/
		when(board.getYSize()).thenReturn(3);
		when(board.getXSize()).thenReturn(-1);
		pawn.move(left);
	}
	
	/**
	 * @see {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
	 * @Oracle test to move twice in the same direction and when we go further than the size of the board.
	 * @passed yes
	 * @exception OutOfBoardException
	 * @throws OutOfBoardException 
	 */
	@Test (expected=OutOfBoardException.class)
	public void testMove6() throws OutOfBoardException {
		
		/*utiliser le mock*/
		when(board.getYSize()).thenReturn(2);
		when(board.getXSize()).thenReturn(3);
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
