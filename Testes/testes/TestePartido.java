package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eco.Partido;

class TestePartido {

	@Test
	void testPartido() {
		Partido partido = new Partido("JDK");
		assertEquals("JDK", partido.toString());
	}

}
