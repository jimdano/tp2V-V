package simpleGame.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * 
 * @author jimmy Dano & Anthony Le Mee
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TestSequenceDiagram {

	/**
	 * one board for each one of the questions
	 */
	@Mock
	private Board board;
	private Board board2;
	/**
	 * the game in which we inject one of the board, the mocked one
	 */
	@InjectMocks
	private Game game;
	/**
	 * pawns mocked to be added in the arraylist of pawns
	 */
	@Mock
	private Pawn pawn1 = new Pawn('c',1,1, board);
	@Mock
	private Pawn pawn2 = new Pawn('c',1,1, board);
	/**
	 * property for the board2
	 */
	private ArrayList<Pawn> pawns = new ArrayList<Pawn>();

	@Before
	public void setUp() throws Exception {
		pawns.add(pawn1);
		pawns.add(pawn2);
		board2 = new Board();
		board2.setPawns(pawns);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * @Oracle test the different cases for the isGameOver method in game
	 */
	@Test
	public void sequenceTestProgramming2Test() {
		// 1 pawn 1 gold
		when(board.numberOfPawns()).thenReturn(1);
		when(board.maxGold()).thenReturn(1);
		assertTrue(game.isGameOver());
		verify(board, Mockito.times(1)).numberOfPawns();
		verify(board, Mockito.times(0)).maxGold();
		// 2 pawns 1 gold
		when(board.numberOfPawns()).thenReturn(2);
		when(board.maxGold()).thenReturn(1);
		assertFalse(game.isGameOver());
		verify(board, Mockito.times(2)).numberOfPawns();
		verify(board, Mockito.times(1)).maxGold();
		// 2 pawns 3 gold
		when(board.numberOfPawns()).thenReturn(2);
		when(board.maxGold()).thenReturn(3);
		assertTrue(game.isGameOver());
		verify(board, Mockito.times(3)).numberOfPawns();
		verify(board, Mockito.times(2)).maxGold();
		// 1 pawn 3 gold
		when(board.numberOfPawns()).thenReturn(1);
		when(board.maxGold()).thenReturn(3);
		assertTrue(game.isGameOver());
		verify(board, Mockito.times(4)).numberOfPawns();
		verify(board, Mockito.times(2)).maxGold();
	}

	/**
	 * @Oracle test the different cases for the maxGold method in game
	 */
	@Test
	public void maxGoldWithInjectTest() {

		when(pawn1.getGold()).thenReturn(-1);
		when(pawn2.getGold()).thenReturn(-1);
		assertEquals(board2.maxGold(), 0);
		verify(pawn1, Mockito.times(1)).getGold();
		verify(pawn2, Mockito.times(1)).getGold();

		when(pawn1.getGold()).thenReturn(1);
		when(pawn2.getGold()).thenReturn(-1);
		assertEquals(board2.maxGold(), 1);
		verify(pawn1, Mockito.times(2)).getGold();
		verify(pawn2, Mockito.times(2)).getGold();

		when(pawn1.getGold()).thenReturn(1);
		when(pawn2.getGold()).thenReturn(3);
		assertEquals(board2.maxGold(), 3);
		verify(pawn1, Mockito.times(3)).getGold();
		verify(pawn2, Mockito.times(3)).getGold();

		when(pawn1.getGold()).thenReturn(0);
		when(pawn2.getGold()).thenReturn(0);
		assertEquals(board2.maxGold(), 0);
		verify(pawn1, Mockito.times(4)).getGold();
		verify(pawn2, Mockito.times(4)).getGold();
	}
}
