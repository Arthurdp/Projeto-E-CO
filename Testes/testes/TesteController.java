package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eco.Controller;
import eco.ControllerPessoa;
import eco.ControllerProjeto;

class TesteController {
	
	
	Controller controller = new Controller();
	ControllerPessoa controllerPessoa= new ControllerPessoa();
	ControllerProjeto controllerProjeto = new ControllerProjeto();
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
		controllerPessoa.cadastrarPessoa("Eu", "111111111-1", "PB", "Nenhum", "PPP");
		controllerPessoa.cadastrarPessoa("Tu", "111111111-2", "RJ", "Nenhum", "PCC");
		controllerPessoa.cadastrarPessoa("nois", "111111111-3", "SP", "Nenhum", "PVP");
		controllerPessoa.cadastrarPessoa("botanela", "111111111-4", "RN", "Nenhum", "HPV");
		controllerPessoa.cadastraDeputado("111111111-1","03112012");
		controllerPessoa.cadastraDeputado("111111111-2","12122012");
		controllerPessoa.cadastraDeputado("111111111-3","05052005");
		
		controller.cadastrarComissao("bonde", "111111111-1,111111111-2,111111111-3");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarComissao("", "111111111-1,111111111-2,111111111-3"),"Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarComissao("bonde1", ""),"Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarComissao("bonde", "111111111-1,111111111-2,111111111-3"),"Erro ao cadastrar comissao: tema existente");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarComissao("bonde2", "111111111-1,1111h1111-2,111111111-3"),"Erro ao cadastrar comissao: dni invalido");
		assertThrows(NullPointerException.class, ()-> controller.cadastrarComissao("bonde3", "111111111-1,111111111-2,111111111-9"),"Erro ao cadastrar comissao: pessoa inexistente");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarComissao("bonde4", "111111111-1,111111111-2,111111111-4"),"Erro ao cadastrar comissao: pessoa nao eh deputado");

		
	}
	
	@Test
	void testVotarPlenario() {
		controllerPessoa.cadastrarPessoa("Eu", "111111111-1", "PB", "desviar coisas", "PPP");
		controllerPessoa.cadastrarPessoa("Tu", "111111111-2", "RJ", "desviar coisas", "PPP");
		controllerPessoa.cadastrarPessoa("nois", "111111111-3", "SP", "desviar coisas", "PPP");
		controllerPessoa.cadastrarPessoa("botanela", "111111111-4", "RN", "Nenhum", "PPL");
		controllerPessoa.cadastraDeputado("111111111-1","03112012");
		controllerPessoa.cadastraDeputado("111111111-2","12122012");
		controllerPessoa.cadastraDeputado("111111111-3","05052005");
		controllerPessoa.cadastraDeputado("111111111-4","02072006");
		controller.cadastrarPartido("PPP");
		controllerProjeto.cadastrarPL("111111111-1", 2001, "nada nada nada", "desviar coisas", "https://example.net/jogos%40aposta", true);
		
		boolean test = true;
		boolean ver = controller.votarPlenario("PL 1/2001", "LIVRE", "111111111-1,111111111-2,111111111-3,111111111-4");
		boolean ver1 = controller.votarPlenario("PL 1/2001", "GOVERNISTA", "111111111-1,111111111-2,111111111-3,111111111-4");
		boolean ver2 = controller.votarPlenario("PL 1/2001", "OPOSICAO", "111111111-1,111111111-2,111111111-3,111111111-4");
		assertEquals(test, ver);
		assertEquals(test, ver1);
		assertEquals(!test, ver2);
		
	}

}
