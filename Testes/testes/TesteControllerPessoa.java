package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eco.ControllerPessoa;

class TesteControllerPessoa {

	ControllerPessoa controllerPessoa;
	

	@BeforeEach
	void criarController() {
		controllerPessoa = new ControllerPessoa();
		controllerPessoa.cadastrarPessoa("Eu", "111111111-1", "PB", "Nenhum");
		controllerPessoa.cadastrarPessoa("Eu", "111111111-2", "PB", "Nenhum", "PPK");
	}

	@Test
	void testExibirPessoa() {
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.exibirPessoa("0000000000-0"), "Erro ao exibir pessoa: pessoa nao encontrada");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.exibirPessoa(""), "Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> controllerPessoa.exibirPessoa(null), "Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.exibirPessoa("0000000000,0"), "Erro ao exibir pessoa: pessoa nao encontrada");
		
	}	

	@Test
	void testCadastrarPessoaStringStringStringString() {
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastrarPessoa("Eu", "1111111111-1", "PB", "Nenhum"), "Erro ao cadastrar pessoa: dni ja cadastrado");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastrarPessoa("", "1111111111-1", "PB", "Nenhum"), "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> controllerPessoa.cadastrarPessoa(null, "1111111111-1", "PB", "Nenhum"), "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastrarPessoa("Eu", "", "PB", "Nenhum"), "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> controllerPessoa.cadastrarPessoa("Eu", null, "PB", "Nenhum"), "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastrarPessoa("Eu", "1111111111-1", "", "Nenhum"), "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> controllerPessoa.cadastrarPessoa("Eu", "1111111111-1", null, "Nenhum"), "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastrarPessoa("Eu", "11111111l1-1", "PB", "Nenhum"), "Erro ao cadastrar pessoa: dni invalido");
	}

	@Test
	void testCadastrarPessoaStringStringStringStringString() {
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastrarPessoa("Eu", "1111111111-1", "PB", "Nenhum", "PPK"), "Erro ao cadastrar pessoa: dni ja cadastrado");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastrarPessoa("", "1111111111-1", "PB", "Nenhum", "PPK"), "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> controllerPessoa.cadastrarPessoa(null, "1111111111-1", "PB", "Nenhum", "PPK"), "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastrarPessoa("Eu", "", "PB", "Nenhum", "PPK"), "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> controllerPessoa.cadastrarPessoa("Eu", null, "PB", "Nenhum", "PPK"), "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastrarPessoa("Eu", "1111111111-1", "", "Nenhum", "PPK"), "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> controllerPessoa.cadastrarPessoa("Eu", "1111111111-1", null, "Nenhum", "PPK"), "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastrarPessoa("Eu", "11111111l1-1", "PB", "Nenhum", "PPK"), "Erro ao cadastrar pessoa: dni invalido");
	}

	@Test
	void testCadastraDeputado() {
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastraDeputado("", "25032017"), "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastraDeputado("1111111x11-2", "25032017"), "Erro ao cadastrar deputado: dni invalido");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastraDeputado("1111111111-3", "25032017"),"Erro ao cadastrar deputado: pessoa nao encontrada");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastraDeputado("1111111111-1", "25032017"),"Erro ao cadastrar deputado: pessoa sem partido");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastraDeputado("1111111111-2", ""),"Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastraDeputado("1111111111-2", "25o32017"),"Erro ao cadastrar deputado: data invalida");
		assertThrows(IllegalArgumentException.class, ()-> controllerPessoa.cadastraDeputado("1111111111-2", "25032023"),"Erro ao cadastrar deputado: data futura");
	}

}
