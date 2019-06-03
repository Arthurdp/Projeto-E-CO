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
		pessoa1 = new Pessoa("Robervaldo", "123456789-1", "RJ", "trafico,caixa2,sonegação,lavagem de dinheiro");
		pessoa2 = new Pessoa("Tiririca", "213456789-1", "CE", "abestado,fazer menino,comer farinha", "PCB");
	}
	
	@Test
	void TesteConstrutor() {
		try {
			Pessoa pessoa = new Pessoa(null, "222222222-2","AL", "fdsfsdf,safsasa,dadas");
			fail("Deveria lançar excessão!");
		}catch(NullPointerException e) {}
		
		try {
			Pessoa pessoa = new Pessoa("22222222222", null, "AL", "fdsfsdf,safsasa,dadas");
			fail("Deveria lançar excessão!");
		}catch(NullPointerException e) {}
		
		try {
			Pessoa pessoa = new Pessoa("AL", "222222222-2", null, "fdsfsdf,safsasa,dadas");
			fail("Deveria lançar excessão!");
		}catch(NullPointerException e) {}
		
		try {
			Pessoa pessoa = new Pessoa("AL", "222222222-2", "fdsfsdf,safsasa,dadas", null);
			fail("Deveria lançar excessão!");
		}catch(NullPointerException e) {}
		
		try {
			Pessoa pessoa = new Pessoa("", "111111111-1", "PB", "mnbdbf,fsdfsdf,fdsfsdf");
			fail("Deveria lançar excessão!");
		}catch(IllegalArgumentException e) {}
		
		try {
			Pessoa pessoa = new Pessoa("hdjsh", "", "CE", "kjghdkjgxdk,sddxvxf,vfcvcf");
			fail("Deveria lançar excessão!");
		}catch(IllegalArgumentException e) {}
		
		try {
			Pessoa pessoa = new Pessoa("hdjsh", "333333333-3", "", "kjghdkjgxdk,sddxvxf,vfcvcf");
			fail("Deveria lançar excessão!");
		}catch(IllegalArgumentException e) {}
	}
	
	@Test
	void testeExibirPessoa() {
		assertEquals("Robervaldo - 123456789-1 (RJ) - interesses: trafico,caixa2,sonegação,lavagem de dinheiro", pessoa1.exibirPessoa());
		assertEquals("Tiririca - 213456789-1 (CE) - PCB - interesses: abestado, fazer menino, comer farinha", pessoa2.exibirPessoa());
		pessoa2.virouDeputado("11112016");
	}

}
