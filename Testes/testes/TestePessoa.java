package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eco.Partido;
import eco.Pessoa;

class TestePessoa {
	Pessoa pessoa1;
	Pessoa pessoa2;
	
	@BeforeEach
	void criarPessoas() {
		pessoa1 = new Pessoa("Robervaldo", "12345678911", "RJ", "trafico,caixa2,sonegação,lavagem de dinheiro");
		pessoa2 = new Pessoa("Tiririca", "21345678911", "CE", "abestado,fazer menino,comer farinha", "PCB");
	}
	
	@Test
	void TesteConstrutor() {
		try {
			Pessoa pessoa = new Pessoa(null, "22222222222","AL", "fdsfsdf,safsasa,dadas");
			fail("Deveria lançar excessão!");
		}catch(NullPointerException e) {}
		
		try {
			Pessoa pessoa = new Pessoa("22222222222", null, "AL", "fdsfsdf,safsasa,dadas");
			fail("Deveria lançar excessão!");
		}catch(NullPointerException e) {}
		
		try {
			Pessoa pessoa = new Pessoa("AL", "22222222222", null, "fdsfsdf,safsasa,dadas");
			fail("Deveria lançar excessão!");
		}catch(NullPointerException e) {}
		
		try {
			Pessoa pessoa = new Pessoa("AL", "22222222222", "fdsfsdf,safsasa,dadas", null);
			fail("Deveria lançar excessão!");
		}catch(NullPointerException e) {}
		
		try {
			Pessoa pessoa = new Pessoa("", "11111111111", "PB", "mnbdbf,fsdfsdf,fdsfsdf");
			fail("Deveria lançar excessão!");
		}catch(IllegalArgumentException e) {}
		
		try {
			Pessoa pessoa = new Pessoa("hdjsh", "", "CE", "kjghdkjgxdk,sddxvxf,vfcvcf");
			fail("Deveria lançar excessão!");
		}catch(IllegalArgumentException e) {}
		
		try {
			Pessoa pessoa = new Pessoa("hdjsh", "33333333333", "", "kjghdkjgxdk,sddxvxf,vfcvcf");
			fail("Deveria lançar excessão!");
		}catch(IllegalArgumentException e) {}
	}
	
	@Test
	void testeExibirPessoa() {
		assertEquals("Robervaldo - 12345678911 (RJ) - interesses: trafico, caixa2, sonegação, lavagem de dinheiro", pessoa1.exibirPessoa());
		assertEquals("Tiririca - 21345678911 (CE) - PCB - interesses: abestado, fazer menino, comer farinha", pessoa2.exibirPessoa());
		pessoa2.virouDeputado("11112016");
	}

}
