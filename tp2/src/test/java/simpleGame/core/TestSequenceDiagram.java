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

@RunWith(MockitoJUnitRunner.class)
public class TestSequenceDiagram {

	@InjectMocks
	private Board board;
	@InjectMocks
	private Game game;
	@Mock
	private Pawn pawn1;
	@Mock
	private Pawn pawn2;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void sequenceTestProgramming2Test() {
		//1 pawn 1 gold
		when(board.numberOfPawns()).thenReturn(1);
		when(board.maxGold()).thenReturn(1);
		assertTrue(game.isGameOver());
		verify(board, Mockito.times(1)).numberOfPawns();
		verify(board, Mockito.times(0)).maxGold();
		//2 pawns 1 gold
		when(board.numberOfPawns()).thenReturn(2);
		when(board.maxGold()).thenReturn(1);
		assertFalse(game.isGameOver());
		verify(board, Mockito.times(2)).numberOfPawns();
		verify(board, Mockito.times(1)).maxGold();
		//2 pawns 3 gold
		when(board.numberOfPawns()).thenReturn(2);
		when(board.maxGold()).thenReturn(3);
		assertTrue(game.isGameOver());
		verify(board, Mockito.times(3)).numberOfPawns();
		verify(board, Mockito.times(2)).maxGold();
		//1 pawn 3 gold
		when(board.numberOfPawns()).thenReturn(1);
		when(board.maxGold()).thenReturn(3);
		assertTrue(game.isGameOver());
		verify(board, Mockito.times(4)).numberOfPawns();
		verify(board, Mockito.times(2)).maxGold();
	}
	
	@Test
	public void maxGoldWithInjectTest() {
		
		when(pawn1.getGold()).thenReturn(-1);
		when(pawn2.getGold()).thenReturn(-1);
		assertEquals(board.maxGold(), 0);
		verify(pawn1, Mockito.times(1)).getGold();
		verify(pawn2, Mockito.times(1)).getGold();
		
		when(pawn1.getGold()).thenReturn(-1);
		when(pawn2.getGold()).thenReturn(-1);
		assertEquals(board.maxGold(), 1);
		
		when(pawn1.getGold()).thenReturn(-1);
		when(pawn2.getGold()).thenReturn(-1);
		assertEquals(board.maxGold(), 1);
		
		when(pawn1.getGold()).thenReturn(-1);
		when(pawn2.getGold()).thenReturn(-1);
		assertEquals(board.maxGold(), 3);
	}
}
