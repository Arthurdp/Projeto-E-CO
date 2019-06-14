package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eco.PLP;

class TestePLP {
	PLP plp1;
	PLP plp2;
	
	@Test
	void testToString() {
		plp1 = new PLP("111111111-1", 2012, 22, "criminalizacao da us7", "saude", "http://example.com/coisa do djabo", "1000");
		plp2 = new PLP("111111111-1", 2019, 40, "3 anos de prisao para o crador da us7", "seguranca", "http://example.com/brincadeira", "1000");

		
		assertEquals("Projeto de Lei Complementar - PLP 22/2012 - 111111111-1 - criminalizacao da us7 - 1000 - EM VOTACAO (CCJC)", plp1.toString());
		assertEquals("Projeto de Lei Complementar - PLP 40/2019 - 111111111-1 - 3 anos de prisao para o crador da us7 - 1000 - EM VOTACAO (CCJC)",plp2.toString()); 
		
	}

}
