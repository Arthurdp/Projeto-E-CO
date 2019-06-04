package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eco.ControllerPessoa;
import eco.Pessoa;

class TesteControllerPessoa {

	ControllerPessoa controllerPessoa;
	

	@BeforeEach
	void criarController() {
		controllerPessoa = new ControllerPessoa();
		controllerPessoa.cadastrarPessoa("Eu", "1111111111-1", "PB", "Nenhum");
		controllerPessoa.cadastrarPessoa("Eu", "1111111111-1", "PB", "Nenhum", "PPK");
	}

	@Test
	void testExibirPessoa() {
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.exibirPessoa("0000000000-0"), "Erro ao exibir pessoa: pessoa nao encontrada");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.exibirPessoa(""), "Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.exibirPessoa(null), "Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.exibirPessoa("0000000000,0"), "Erro ao exibir pessoa: pessoa nao encontrada");
		
	}	

	@Test
	void testCadastrarPessoaStringStringStringString() {
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastrarPessoa("Eu", "1111111111-1", "PB", "Nenhum", "Erro ao cadastrar pessoa: dni ja cadastrado"));
	}

	@Test
	void testCadastrarPessoaStringStringStringStringString() {
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastrarPessoa("Eu", "1111111111-1", "PB", "Nenhum", "Erro ao cadastrar pessoa: dni ja cadastrado", "PPK"));
	}

	@Test
	void testCadastraDeputado() {
		fail("Not yet implemented");
	}

}
