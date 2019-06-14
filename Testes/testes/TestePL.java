package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eco.PL;

class TestePL {

	 PL plTeste;
	 PL plTeste2;
	
	@Test
	void testToString() {
		PL plTeste = new PL("111111111-1", 2018, 3, "proibir a us7", "Saude", "http://example.com/namoralzinha_vei", true);
		PL plTeste2 = new PL("111111111-1", 2018, 7, "anular a us7", "Saude", "http://example.com/não ta dando", false);
		assertEquals("Projeto de Lei - PL 3/2018 - 111111111-1 - proibir a us7 - Conclusiva - EM VOTACAO (CCJC)", plTeste.toString());
		assertEquals("Projeto de Lei - PL 7/2018 - 111111111-1 - anular a us7 - EM VOTACAO (CCJC)", plTeste2.toString());
	}

}
