package simpleGame.core;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestSequenceDiagram {

	@Mock
	private Board board;
	@InjectMocks
	private Game game;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void sequenceTest() {
		//1 pawn
		when(board.numberOfPawns()).thenReturn(1);
		when(board.maxGold()).thenReturn(1);
		assertTrue(game.isGameOver());
		//2 pawns 1 gold
		when(board.numberOfPawns()).thenReturn(2);
		when(board.maxGold()).thenReturn(1);
		assertFalse(game.isGameOver());
		//2 pawns 3 gold
		when(board.numberOfPawns()).thenReturn(2);
		when(board.maxGold()).thenReturn(3);
		assertTrue(game.isGameOver());
		//1 pawn 3 gold
		when(board.numberOfPawns()).thenReturn(1);
		when(board.maxGold()).thenReturn(3);
		assertTrue(game.isGameOver());
	}
	
	@Test
	public void maxGoldWithInjectTest() {
		
		
	}
	
	
	@Test
	public void numberOfPawnsTest() {
		
		
		
	}
}
