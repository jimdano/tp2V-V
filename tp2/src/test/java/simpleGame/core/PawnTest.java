/**
 * 
 */
package simpleGame.core;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import simpleGame.exception.OutOfBoardException;

/**
 * @author 10003134
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PawnTest {

	@Mock
	private Board board;
	private Pawn pawn;
	private Pawn pawn1;
	private int x = 1;
	private int y = 1;
	private char c = 'c';
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
	 * Test method for {@link simpleGame.core.Pawn#Pawn(char, int, int, simpleGame.core.Board)}.
	 */
	@Test
	public void testPawn() {
		
	}

	/**
	 * Test method for {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
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
	 * Test method for {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
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
			
		} catch (OutOfBoardException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test method for {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
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
	 * Test method for {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
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
	 * Test method for {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
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
	 * Test method for {@link simpleGame.core.Pawn#move(simpleGame.core.Direction)}.
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
	 * Test method for {@link simpleGame.core.Pawn#isDead()}.
	 */
	@Test
	public void testIsDead() {
		assertFalse(pawn.isDead());
	}
}
