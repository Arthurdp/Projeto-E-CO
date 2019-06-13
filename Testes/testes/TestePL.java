package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eco.PL;

class TestePL {

	private PL plTeste;
	
	@BeforeEach
	void criaPL() {
		PL plTeste = new PL(null, 0, 0, null, null, null, false);
	}
	
	@Test
	void testPL() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

}
