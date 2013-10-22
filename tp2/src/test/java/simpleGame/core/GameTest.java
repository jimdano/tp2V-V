package simpleGame.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jimmy Dano & Anthony Le Mee
 * 
 */
public class GameTest {

	/** Déclaration des attributs */
	
	/** the game used for tests */
	private Game game;

	/** properties of the board used in the game */
	private Board board;
	private int somePawns = 2;
	private int xLength = 5;
	private int yLength = 5;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		game = new Game();
		game.setBoard(new Board(somePawns, xLength, yLength));
		board = game.getBoard();
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