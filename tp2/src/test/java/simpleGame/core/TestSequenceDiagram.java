package simpleGame.core;

import static org.junit.Assert.*;

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
	@Mock 
	private Pawn pawn;
	@Mock
	private Pawn pawn1;
	@InjectMocks
	private Game game;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void isGameOverTest() {
		
		
	}
	
	@Test
	public void maxGoldTest() {
		
		
	}
	
	@Test
	public void maxGoldWithInjectTest() {
		
		
	}
	
	
	@Test
	public void numberOfPawnsTest() {
		
		
		
	}
}
