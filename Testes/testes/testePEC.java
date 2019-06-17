package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eco.PEC;

class testePEC {
	
	PEC pec1;
	PEC pec2;
	
	@Test
	void testToString() {
		pec1 = new PEC("111111111-1", 2019, "PEC 88/2019" , "permite o aluno de lp2 desistir da us7", "saude, bem comum", "http://example.com/num_vai_dar_naum", "10,11,12,13");
		pec2 = new PEC("111111111-1", 2019, "PEC 99/2019" , "10 automatico pra quem fizer a us7", "justica", "http://example.com/seria_uma_boa", "9,11,12,13");
		
				
		assertEquals("Projeto de Emenda Constitucional - PEC 88/2019 - 111111111-1 - permite o aluno de lp2 desistir da us7 - 10, 11, 12, 13 - EM VOTACAO (CCJC)", pec1.toString()); 
		assertEquals("Projeto de Emenda Constitucional - PEC 99/2019 - 111111111-1 - 10 automatico pra quem fizer a us7 - 9, 11, 12, 13 - EM VOTACAO (CCJC)", pec2.toString()); 

	}

}
