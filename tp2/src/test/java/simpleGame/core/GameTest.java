/**
 *
 */
package simpleGame.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import simpleGame.exception.OutOfBoardException;

/**
 * @author jimmy
 * 
 */
public class GameTest {

	/** Déclaration des attributs */
	
	// Attributs du pion 1
	private Pawn pawn1;
	private int yPawn1 = 1;
	private int xPawn1 = 1;
	
	// Attributs du pion 2
	private Pawn pawn2;
	private int yPawn2 = 1;
	private int xPawn2 = 0;
	
	// Attributs du game
	private Game game;

	// Attributs du board
	private Board board;
	private int somePawns = 2;
	private int xLength = 5;
	private int yLength = 5;
	private Direction down = Direction.Down;
	private Direction left = Direction.Left;
	private Direction right = Direction.Right;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		game = new Game();
		game.setBoard(new Board(somePawns, xLength, yLength));
		board = game.getBoard();
		pawn1 = new Pawn('a', xPawn1, yPawn1, game.getBoard());
		pawn2 = new Pawn('b', xPawn2, yPawn2, game.getBoard());
	}

	/**
	 * @see {@link simpleGame.core.Game#Game()}.
	 * @oracle must return true ; test correct initialization of board's attributes
	 * @passed yes
	 */
	@Test
	public void testGame() {
		assertNotNull(game.getBoard());
		assertEquals(2, board.numberOfPawns());
		assertEquals(5, board.getXSize());
		assertEquals(5, board.getYSize());
	}

	/**
	 * @see {@link simpleGame.core.Game#toString()}.
	 * @oracle must return true ; toString must return board's toString if there is not game over
	 * @passed yes
	 */
	@Test
	public void testToString() {

		assertEquals(game.toString(), board.toString());

	}

}