package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eco.ControllerPartido;

class TesteControllerPartido {
	
	ControllerPartido partidos;
	
	@BeforeEach
	void partidos() {
		partidos = new ControllerPartido();
	}

	@Test
	void testCadastrarPartido() {
		this.partidos.cadastrarPartido("PCB");
		this.partidos.cadastrarPartido("PPP");
		assertEquals(partidos.exibirBase(), "PCB,PPP");
		
		try {
			this.partidos.cadastrarPartido(null);
			fail("Deveria lancar excecao!");
		}catch(NullPointerException e) {}
		
		try {
			this.partidos.cadastrarPartido("");
			fail("Deveria lancar excecao!");
		}catch(IllegalArgumentException e) {}
	}

	@Test
	void testExibirBase() {
		assertEquals(partidos.exibirBase(), "");
		this.partidos.cadastrarPartido("PCB");
		this.partidos.cadastrarPartido("PPP");
		assertEquals(partidos.exibirBase(), "PCB,PPP");
	}

}
