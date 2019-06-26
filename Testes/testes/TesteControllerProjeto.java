package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eco.ControllerComissoes;
import eco.ControllerGeral;
import eco.ControllerPessoa;
import eco.ControllerProjeto;

class TesteControllerProjeto {
	
	ControllerProjeto controllerProjeto;
	ControllerPessoa controllerPessoa;
	ControllerComissoes controller;
	ControllerGeral controllerGeral;

	public TesteControllerProjeto() {
		this.controllerProjeto = new ControllerProjeto();
		this.controllerPessoa = new ControllerPessoa();
		this.controller = new ControllerComissoes();
		this.controllerGeral = new ControllerGeral();
	}


	@Test
	void testCadastrarPL() {
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarPL("", 2018, "Abaixar opreço da coxinha", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true),"Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo" );
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarPL("111111111-1", 2018, "", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true),"Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		assertThrows(IllegalArgumentException.class, ()-> 	controllerGeral.cadastrarPL("111111111-1", 2017, "Abaixar opreço da coxinha", "", "http://example.com/baixa_a_coxinha_véi", true),"Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo" );
		assertThrows(IllegalArgumentException.class, ()-> 	controllerGeral.cadastrarPL("111111111-1", 2016, "Abaixar opreço da coxinha", "Saude, Economia", " ", true),"Erro ao cadastrar projeto: url nao pode ser vazio ou nulo" );
		assertThrows(IllegalArgumentException.class, ()-> 	controllerGeral.cadastrarPL("11111kkk1-1", 2015, "Abaixar opreço da coxinha", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true), "Erro ao cadastrar projeto: dni invalido");
		assertThrows(NullPointerException.class, ()-> 	controllerGeral.cadastrarPL("221111111-1", 2014, "Abaixar opreço da coxinha", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true),"Erro ao cadastrar projeto: pessoa inexistente" );
		assertThrows(NullPointerException.class, ()-> 	controllerGeral.cadastrarPL("111111111-2", 2013, "Abaixar opreço da coxinha", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true),"Erro ao cadastrar projeto: pessoa nao eh deputado" );
		assertThrows(NullPointerException.class, ()-> 	controllerGeral.cadastrarPL("111111111-1", 1912, "Abaixar opreço da coxinha", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true),"Erro ao cadastrar projeto: ano anterior a 1988" );
		assertThrows(NullPointerException.class, ()-> 	controllerGeral.cadastrarPL("111111111-1", 3011, "Abaixar opreço da coxinha", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true), "Erro ao cadastrar projeto: ano posterior ao ano atual");
		
	}
	

	@Test
	void testCadastrarPLP() {
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarPLP(" ", 2016, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200"), "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarPLP("111111111-1", 2016, "   ", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200"), "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		assertThrows(NullPointerException.class, ()-> controllerGeral.cadastrarPLP("111111111-1", 1005, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200"), "Erro ao cadastrar projeto: ano anterior a 1988");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarPLP("111111111-1", 2014, "feriadaum de 6 meses", "", "http://example.com/ninguem_aguenta_mano", "200"), "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarPLP("111111111-1", 2013, "feriadaum de 6 meses", "Saude, Lazer", "", "200"), "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarPLP("111111111-1", 2012, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "      "),"Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo" );
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarPLP("111111111-w", 2011, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200"),"Erro ao cadastrar projeto: dni invalido");
		assertThrows(NullPointerException.class, ()-> controllerGeral.cadastrarPLP("111119111-1", 2010, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200"),"Erro ao cadastrar projeto: pessoa inexistente" );
		assertThrows(NullPointerException.class, ()-> controllerGeral.cadastrarPLP("111111111-2", 2009, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200"), "Erro ao cadastrar projeto: pessoa nao eh deputado" );
		assertThrows(NullPointerException.class, ()-> controllerGeral.cadastrarPLP("111111111-1", 9008, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200"),"Erro ao cadastrar projeto: ano posterior ao ano atual" );

	}

	@Test
	void testCadastrarPEC() {
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarPEC(" ", 2012, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", "900"), "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo" );
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarPEC("111111111-1", 2012, "", "Educacao", "http://example.com/sonho", "900"),"Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula" );
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarPEC("111111111-1", 2011, "Permite o aluno faltar em dias de chuva sem levar falta", "", "http://example.com/sonho", "900"),"Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo" );
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarPEC("111111111-1", 2010, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "  ", "900"), "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarPEC("111111111-1", 2009, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", ""),"Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controllerGeral.cadastrarPEC("kkkkjjjjk-1", 2008, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", "900"), "Erro ao cadastrar projeto: dni invalido" );
		assertThrows(NullPointerException.class, ()-> controllerGeral.cadastrarPEC("111111111-3", 2007, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", "900"),"Erro ao cadastrar projeto: pessoa inexistente" );
		assertThrows(NullPointerException.class, ()-> controllerGeral.cadastrarPEC("111111111-2", 2006, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", "900"), "Erro ao cadastrar projeto: pessoa nao eh deputado" );
		assertThrows(NullPointerException.class, ()-> controllerGeral.cadastrarPEC("111111111-1", 0005, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", "900"), "Erro ao cadastrar projeto: ano anterior a 1988" );
		assertThrows(NullPointerException.class, ()-> controllerGeral.cadastrarPEC("111111111-1", 7004, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", "900"), "Erro ao cadastrar projeto: ano posterior ao ano atual");
		
		
	}
	
	@Test
	void testExibirProjeto() {
		controllerGeral.cadastrarPessoa("Eu", "111111111-1", "PB", "desviar coisas", "PPP");
		controllerGeral.cadastrarPessoa("Tu", "111111111-2", "RJ", "desviar coisas", "PPP");
		controllerGeral.cadastrarPessoa("nois", "111111111-3", "SP", "desviar coisas", "PPP");
		controllerGeral.cadastrarPessoa("botanela", "111111111-4", "RN", "Nenhum", "PPL");
		controllerGeral.cadastrarDeputado("111111111-1","03112012");
		controllerGeral.cadastrarDeputado("111111111-2","12122012");
		controllerGeral.cadastrarDeputado("111111111-3","05052005");
		controllerGeral.cadastrarDeputado("111111111-4","02072006");
		controllerGeral.cadastrarPartido("PPP");
		
		controllerGeral.cadastrarPL("111111111-1", 2018, "Abaixar opreço da coxinha", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true);
		controllerGeral.cadastrarPL("111111111-1", 2018, "Abaixar opreço do pastel", "Saude, Economia", "http://example.com/baixa_o_pastel_véi", false);
		controllerGeral.cadastrarPLP("111111111-1", 2016, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200");
		controllerGeral.cadastrarPEC("111111111-1", 2012, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", "900,901,902");
		
		assertEquals("Projeto de Lei - PL 1/2018 - 111111111-1 - Abaixar opreço da coxinha - Conclusiva - EM VOTACAO (CCJC)", controllerGeral.exibirProjeto("PL 1/2018"));
		assertEquals("Projeto de Lei - PL 2/2018 - 111111111-1 - Abaixar opreço do pastel - EM VOTACAO (CCJC)", controllerGeral.exibirProjeto("PL 2/2018"));
		assertEquals("Projeto de Lei Complementar - PLP 1/2016 - 111111111-1 - feriadaum de 6 meses - 200 - EM VOTACAO (CCJC)", controllerGeral.exibirProjeto("PLP 1/2016"));
		assertEquals("Projeto de Emenda Constitucional - PEC 1/2012 - 111111111-1 - Permite o aluno faltar em dias de chuva sem levar falta - 900, 901, 902 - EM VOTACAO (CCJC)", controllerGeral.exibirProjeto("PEC 1/2012"));
	}
}
