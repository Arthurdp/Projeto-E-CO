package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import eco.Pessoa;

class TestePessoa {

	Pessoa pessoa1;
	Pessoa pessoa2;
	Pessoa pessoa3;
	Pessoa pessoa4;
	Pessoa pessoa5;
	
	@BeforeEach
	void criarPessoas() {
		pessoa1 = new Pessoa("Tiririca", "213456781-1", "CE", "abestado,fazer menino,comer farinha", "PCB");
		pessoa2 = new Pessoa("Robervaldo", "123456781-1", "RJ", "trafico,caixa2,sonegacaoo,lavagem de dinheiro");
		pessoa3 = new Pessoa("Robervaldo", "123456781-2", "RJ", "");
		pessoa4 = new Pessoa("Tiririca", "213456781-1", "CE", "", "PCB");
		pessoa5 = new Pessoa("Eu", "913456781-1", "PB", "", null);
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
		assertEquals(pessoa1.exibirPessoa(), "Tiririca - 213456781-1 (CE) - PCB - Interesses: abestado,fazer menino,comer farinha");
		assertEquals(pessoa2.exibirPessoa(), "Robervaldo - 123456781-1 (RJ) - Interesses: trafico,caixa2,sonegacaoo,lavagem de dinheiro");
		assertEquals(pessoa3.exibirPessoa(), "Robervaldo - 123456781-2 (RJ)");
		assertEquals(pessoa4.exibirPessoa(), "Tiririca - 213456781-1 (CE) - PCB");
		assertEquals(pessoa5.exibirPessoa(), "Eu - 913456781-1 (PB)");
	}

}
