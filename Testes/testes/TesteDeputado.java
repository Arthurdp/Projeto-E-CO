package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eco.Pessoa;

class TesteDeputado {
	
	Pessoa pessoa1;
	Pessoa pessoa2;

	@BeforeEach
	void criarPessoas() {
		pessoa1 = new Pessoa("Robervaldo", "123456781-1", "RJ", "trafico,caixa2,sonegacaoo,lavagem de dinheiro");
		pessoa2 = new Pessoa("Tiririca", "213456781-1", "CE", "abestado,fazer menino,comer farinha", "PCB");
	}

	@Test
	void testDeputado() {
		pessoa2.virouDeputado("02062019");
		assertEquals("POL: Tiririca - 213456781-1 (CE) - PCB - Interesses: abestado,fazer menino,comer farinha - 02/06/2019 - 0 Leis", pessoa2.exibirPessoa());
		
		try {
			pessoa2.virouDeputado(null);
			fail("Deveria lancar excessao!");
		}catch(NullPointerException e) {}
		
		try {
			pessoa2.virouDeputado("");
			fail("Deveria lançar excessão!");
		}catch(IllegalArgumentException e) {}
		
		try {
			pessoa2.virouDeputado("20072019");
			fail("Deveria lançar excessão!");
		}catch(IllegalArgumentException e) {}
		
		try {
			pessoa2.virouDeputado("33072019");
			fail("Deveria lançar excessão!");
		}catch(IllegalArgumentException e) {}
	}

}
