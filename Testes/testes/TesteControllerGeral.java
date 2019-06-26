package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eco.ControllerGeral;

class TesteControllerGeral {
	
	ControllerGeral controllerGeral;
	

	public TesteControllerGeral() {
		this.controllerGeral = new ControllerGeral();
	}
	
	@Test
	void testVotarComissao() {
		
	}
	
	@Test
	void testVotarPlenario() {
		controllerGeral.cadastrarPessoa("Eu", "111111111-1", "PB", "desviar coisas, assistir netflix, jogar lol, dormir", "PPP");
		controllerGeral.cadastrarPessoa("Tu", "111111111-2", "RJ", "desviar coisas, dormir, assistir netflix", "PPP");
		controllerGeral.cadastrarPessoa("nois", "111111111-3", "SP", "desviar coisas", "PPP");
		controllerGeral.cadastrarPessoa("botanela", "111111111-4", "RN", "assistir netflix", "PPP");
		controllerGeral.cadastrarPessoa("o", "111111111-5", "RN", "desviar coisas, jogar lol", "PPP");
		controllerGeral.cadastrarPessoa("bonde", "111111111-6", "MG", "jogar lol", "PPL");
		controllerGeral.cadastrarPessoa("chegou", "111111111-7", "PE", "Nenhum", "PPL");
		controllerGeral.cadastrarPessoa("eh", "111111111-8", "AM", "Nenhum", "PPL");
		controllerGeral.cadastrarPessoa("os", "111111111-9", "PB", "Nenhum", "PPL");
		controllerGeral.cadastrarPessoa("predador", "111111111-0", "SP", "Nenhum", "PPL");
		
		controllerGeral.cadastrarDeputado("111111111-1","03112012");
		controllerGeral.cadastrarDeputado("111111111-2","12122012");
		controllerGeral.cadastrarDeputado("111111111-3","05052005");
		controllerGeral.cadastrarDeputado("111111111-4","28022006");
		controllerGeral.cadastrarDeputado("111111111-5","02072006");
		controllerGeral.cadastrarDeputado("111111111-6","02062010");
		controllerGeral.cadastrarDeputado("111111111-7","25072006");
		controllerGeral.cadastrarDeputado("111111111-8","19022015");
		controllerGeral.cadastrarDeputado("111111111-9","01072016");
		controllerGeral.cadastrarDeputado("111111111-0","30082008");
		
		controllerGeral.cadastrarPL("111111111-1", 2017, "Ementa PL conc", "desviar coisas, assistir netflix, jogar lol, dormir", "http://example.com/lol", true);
		controllerGeral.cadastrarPL("111111111-0", 2017, "Ementa PL conc", "desviar coisas, assistir netflix, jogar lol", "http://example.com/sono", true);
		controllerGeral.cadastrarPL("111111111-7", 2017, "Ementa PL conc", "desviar coisas, assistir netflix", "http://example.com/assistir", true);
		controllerGeral.cadastrarPL("111111111-5", 2017, "Ementa PL conc", "desviar coisas", "http://example.com/desviamento", true);

		controllerGeral.cadastrarPLP("111111111-2", 2017, "Ementa PLP conc", "dormir", "http://example.com/desviamento", "157");
		controllerGeral.cadastrarPLP("111111111-3", 2017, "Ementa PLP conc", "jogar lol", "http://example.com/desviamento", "147");
		controllerGeral.cadastrarPLP("111111111-4", 2017, "Ementa PLP conc", "assistir netflix", "http://example.com/desviamento", "178");
		controllerGeral.cadastrarPLP("111111111-6", 2017, "Ementa PLP conc", "desviar coisas", "http://example.com/desviamento", "190");
		
		controllerGeral.cadastrarPEC("111111111-8", 2017, "Ementa PEC conc", "jogar lol", "http://example.com/desviamento", "7,8");
		controllerGeral.cadastrarPEC("111111111-9", 2017, "Ementa PEC conc", "dormir", "http://example.com/desviamento", "7,9");
		controllerGeral.cadastrarPEC("111111111-0", 2017, "Ementa PEC conc", "assistir netflix", "http://example.com/desviamento", "5,8");
		controllerGeral.cadastrarPEC("111111111-1", 2017, "Ementa PEC conc", "desviar coisas", "http://example.com/desviamento", "7,2");
		
		controllerGeral.cadastrarPartido("PPP");
		controllerGeral.cadastrarComissao("CCJC", "111111111-1,111111111-2,111111111-3,111111111-4");
		controllerGeral.cadastrarComissao("CTF", "111111111-1,111111111-2,111111111-3,111111111-4");
		
		
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("", "LIVRE", "CTF"), "Erro ao votar proposta: codigo nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("pl kk", "LIVRE", "CTF"), "Erro ao votar proposta: projeto inexistente");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("PL 1/2017", "", "CTF"), "Erro ao votar proposta: status governista nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("PL 1/2017", "LIVRE", ""), "Erro  ao votar proposta: proximo local vazio");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarPlenario("pl hahaha", "LIVRE", "111111111-1,111111111-2,111111111-3,111111111-4,111111111-5,111111111-6"), "Erro  ao votar proposta: projeto inexistente");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarPlenario("PL 1/2017", "LIVRE", ""), "Erro  ao votar proposta: presentes nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarPlenario("PL 1/2017", "LIVRE", "abc abc abc"), "Erro  ao votar proposta: dni invalido");
		
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("", "LIVRE", "CTF"), "Erro ao votar proposta: codigo nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("plp kkk", "LIVRE", "CTF"), "Erro ao votar proposta: projeto inexistente");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("PLP 1/2017", "", "CTF"), "Erro ao votar proposta: status governista nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("PLP 1/2017", "LIVRE", ""), "Erro  ao votar proposta: proximo local vazio");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarPlenario("plp hahaha", "LIVRE", "111111111-1,111111111-2,111111111-3,111111111-4,111111111-5,111111111-6"), "Erro  ao votar proposta: projeto inexistente");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarPlenario("PLP 1/2017", "LIVRE", ""), "Erro  ao votar proposta: presentes nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarPlenario("PLP 1/2017", "LIVRE", "abc abc abc"), "Erro  ao votar proposta: dni invalido");
		
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("", "LIVRE", "CTF"), "Erro ao votar proposta: codigo nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("pec kk", "LIVRE", "CTF"), "Erro ao votar proposta: projeto inexistente");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("PEC 1/2017", "", "CTF"), "Erro ao votar proposta: status governista nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("PEC 1/2017", "LIVRE", ""), "Erro  ao votar proposta: proximo local vazio");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarPlenario("pec hahaha", "LIVRE", "111111111-1,111111111-2,111111111-3,111111111-4,111111111-5,111111111-6"), "Erro  ao votar proposta: projeto inexistente");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarPlenario("PEC 1/2017", "LIVRE", ""), "Erro  ao votar proposta: presentes nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarPlenario("PEC 1/2017", "LIVRE", "abc abc abc"), "Erro  ao votar proposta: dni invalido");
		
		controllerGeral.votarComissao("PLP 1/2017", "LIVRE", "CTF");
		controllerGeral.votarComissao("PLP 1/2017", "LIVRE", "plenario");
		
		controllerGeral.votarComissao("PEC 1/2017", "LIVRE", "CTF");
		controllerGeral.votarComissao("PEC 1/2017", "LIVRE", "plenario");
		
		controllerGeral.votarComissao("PL 1/2017", "LIVRE", "CTF");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("PL 1/2017", "LIVRE", "CTF"), "Erro ao votar proposta: proposta ja votada nessa comicao");
		controllerGeral.votarComissao("PL 1/2017", "LIVRE", "plenario");
		controllerGeral.votarComissao("PL 2/2017", "OPOSICAO", "CTF");
		controllerGeral.votarComissao("PL 3/2017", "GOVERNISTA", "CTF");
		controllerGeral.votarComissao("PL 4/2017", "LIVRE", "CTF");
		
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("PL 1/2017", "LIVRE", "CTTT"),"Erro ao votar proposta: comissao nao cadastrada");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("PL 1/2017", "LIVRE", "CTF"),"Erro ao votar proposta: proposta encaminhada ao plenario");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("PLP 1/2017", "LIVRE", "CTF"),"Erro ao votar proposta: proposta encaminhada ao plenario");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("PEC 1/2017", "LIVRE", "CTF"),"Erro ao votar proposta: proposta encaminhada ao plenario");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("PL 2/2017", "LIVRE", "plenario"),"Erro ao votar proposta: tramitacao encerrada");
		controllerGeral.votarComissao("PL 3/2017", "LIVRE", "plenario");
		controllerGeral.votarComissao("PL 4/2017", "LIVRE", "plenario");
		
		assertTrue(controllerGeral.votarPlenario("PL 1/2017", "LIVRE", "111111111-1,111111111-2,111111111-3,111111111-4,111111111-5,111111111-6,111111111-7"));
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarPlenario("PLP 1/2017", "LIVRE", "111111111-1,111111111-2,111111111-3,111111112-4,111111111-5,111111111-6"), "Erro ao votar proposta: deputado nao cadastrado");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarPlenario("PEC 1/2017", "LIVRE", "111111111-1,111111111-2,111111111-3,111111112-4,111111111-5,111111111-6"), "Erro ao votar proposta: deputado nao cadastrado");
		assertFalse(controllerGeral.votarPlenario("PL 3/2017", "OPOSICAO", "111111111-1,111111111-2,111111111-3,111111111-4,111111111-5,111111111-6,111111111-7"));
		assertTrue(controllerGeral.votarPlenario("PL 4/2017", "GOVERNISTA", "111111111-1,111111111-2,111111111-3,111111111-4,111111111-5,111111111-6,111111111-7"));
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarComissao("PL 1/2017", "LIVRE", "CTF"),"Erro ao votar proposta: proposta encaminhada ao plenario");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarPlenario("PEC 1/2017", "LIVRE", ""),"Erro ao votar proposta: presentes vazio");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarPlenario("PL 1/2017", "GOVERNISTA", "111111111-1,111111111-2,111111111-3,111111111-4,111111111-5"),"Erro ao votar proposta: quorum invalido");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarPlenario("PLP 1/2017", "GOVERNISTA", "111111111-1,111111111-2,111111111-3,111111111-4,111111111-5"),"Erro ao votar proposta: quorum invalido");
		//assertThrows(IllegalArgumentException.class, ()-> controllerGeral.votarPlenario("PEC 1/2017", "GOVERNISTA", "111111111-1,111111111-2,111111111-3,111111111-4,111111111-5"),"Erro ao votar proposta: quorum invalido");
	}

}
