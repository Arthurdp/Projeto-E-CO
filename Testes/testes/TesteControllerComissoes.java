package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eco.ControllerComissoes;
import eco.ControllerGeral;
import eco.ControllerPessoa;
import eco.ControllerProjeto;

class TesteControllerComissoes {
	
	
	ControllerComissoes controller;
	ControllerGeral controllerGeral;
	
	public TesteControllerComissoes() {
		this.controller = new ControllerComissoes();
		this.controllerGeral = new ControllerGeral();
	}

	@Test
	void testCadastrarPartido() {
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPartido(" "),"Erro ao cadastrar partido: partido nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> controller.cadastrarPartido(null),"Erro ao cadastrar partido: partido nao pode ser vazio ou nulo");
		
	}

	@Test
	void testExibirBase() {
		controller.cadastrarPartido("PPP");
		controller.cadastrarPartido("PCC");
		controller.cadastrarPartido("HPV");
		controller.cadastrarPartido("PVP");
		assertEquals("HPV,PCC,PPP,PVP", controller.exibirBase());
		
		
	}

	@Test
	void testCadastrarComissao() {
		controllerGeral.cadastrarPessoa("Eu", "111111111-1", "PB", "Nenhum", "PPP");
		controllerGeral.cadastrarPessoa("Tu", "111111111-2", "RJ", "Nenhum", "PCC");
		controllerGeral.cadastrarPessoa("nois", "111111111-3", "SP", "Nenhum", "PVP");
		controllerGeral.cadastrarPessoa("botanela", "111111111-4", "RN", "Nenhum", "HPV");
		controllerGeral.cadastrarDeputado("111111111-1","03112012");
		controllerGeral.cadastrarDeputado("111111111-2","12122012");
		controllerGeral.cadastrarDeputado("111111111-3","05052005");
		
		controllerGeral.cadastrarComissao("bonde", "111111111-1,111111111-2,111111111-3");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarComissao("", "111111111-1,111111111-2,111111111-3"),"Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarComissao("bonde1", ""),"Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarComissao("bonde", "111111111-1,111111111-2,111111111-3"),"Erro ao cadastrar comissao: tema existente");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarComissao("bonde2", "111111111-1,1111h1111-2,111111111-3"),"Erro ao cadastrar comissao: dni invalido");
		assertThrows(NullPointerException.class, ()-> controllerGeral.cadastrarComissao("bonde3", "111111111-1,111111111-2,111111111-9"),"Erro ao cadastrar comissao: pessoa inexistente");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarComissao("bonde4", "111111111-1,111111111-2,111111111-4"),"Erro ao cadastrar comissao: pessoa nao eh deputado");

		
	}
	
	
	
	
	
}
