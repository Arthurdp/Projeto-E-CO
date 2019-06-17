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
	void testVotarPlenario() {
		controllerGeral.cadastrarPessoa("Eu", "111111111-1", "PB", "desviar coisas", "PPP");
		controllerGeral.cadastrarPessoa("Tu", "111111111-2", "RJ", "desviar coisas", "PPP");
		controllerGeral.cadastrarPessoa("nois", "111111111-3", "SP", "desviar coisas", "PPP");
		controllerGeral.cadastrarPessoa("botanela", "111111111-4", "RN", "Nenhum", "PPL");
		controllerGeral.cadastrarDeputado("111111111-1","03112012");
		controllerGeral.cadastrarDeputado("111111111-2","12122012");
		controllerGeral.cadastrarDeputado("111111111-3","05052005");
		controllerGeral.cadastrarDeputado("111111111-4","02072006");
		controllerGeral.cadastrarPartido("PPP");
		controllerGeral.cadastrarPL("111111111-1", 2001, "nada nada nada", "desviar coisas", "https://example.net/jogos%40aposta", true);
		
		boolean test = true;
		boolean ver = controllerGeral.votarPlenario("PL 1/2001", "LIVRE", "111111111-1,111111111-2,111111111-3,111111111-4");
		boolean ver1 = controllerGeral.votarPlenario("PL 1/2001", "GOVERNISTA", "111111111-1,111111111-2,111111111-3,111111111-4");
		boolean ver2 = controllerGeral.votarPlenario("PL 1/2001", "OPOSICAO", "111111111-1,111111111-2,111111111-3,111111111-4");
		assertEquals(test, ver);
		assertEquals(test, ver1);
		assertEquals(!test, ver2);
		
	}
	@Test
	void testVotarComissao() {
		
	}

}
