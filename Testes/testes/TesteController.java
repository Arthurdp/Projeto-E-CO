package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eco.Controller;
import eco.ControllerPessoa;
import eco.ControllerProjeto;

class TesteController {
	
	
	Controller controller = new Controller();
	
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
		controller.cadastrarPessoa("Eu", "111111111-1", "PB", "Nenhum", "PPP");
		controller.cadastrarPessoa("Tu", "111111111-2", "RJ", "Nenhum", "PCC");
		controller.cadastrarPessoa("nois", "111111111-3", "SP", "Nenhum", "PVP");
		controller.cadastrarPessoa("botanela", "111111111-4", "RN", "Nenhum", "HPV");
		controller.cadastraDeputado("111111111-1","03112012");
		controller.cadastraDeputado("111111111-2","12122012");
		controller.cadastraDeputado("111111111-3","05052005");
		
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
		controller.cadastrarPessoa("Eu", "111111111-1", "PB", "desviar coisas", "PPP");
		controller.cadastrarPessoa("Tu", "111111111-2", "RJ", "desviar coisas", "PPP");
		controller.cadastrarPessoa("nois", "111111111-3", "SP", "desviar coisas", "PPP");
		controller.cadastrarPessoa("botanela", "111111111-4", "RN", "Nenhum", "PPL");
		controller.cadastraDeputado("111111111-1","03112012");
		controller.cadastraDeputado("111111111-2","12122012");
		controller.cadastraDeputado("111111111-3","05052005");
		controller.cadastraDeputado("111111111-4","02072006");
		controller.cadastrarPartido("PPP");
		controller.cadastrarPL("111111111-1", 2001, "nada nada nada", "desviar coisas", "https://example.net/jogos%40aposta", true);
		
		boolean test = true;
		boolean ver = controller.votarPlenario("PL 1/2001", "LIVRE", "111111111-1,111111111-2,111111111-3,111111111-4");
		boolean ver1 = controller.votarPlenario("PL 1/2001", "GOVERNISTA", "111111111-1,111111111-2,111111111-3,111111111-4");
		boolean ver2 = controller.votarPlenario("PL 1/2001", "OPOSICAO", "111111111-1,111111111-2,111111111-3,111111111-4");
		assertEquals(test, ver);
		assertEquals(test, ver1);
		assertEquals(!test, ver2);
		
	}
	
	@Test
	void testCadastrarPL() {
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPL("", 2018, "Abaixar opreço da coxinha", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true),"Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo" );
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPL("111111111-1", 2018, "", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true),"Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		assertThrows(IllegalArgumentException.class, ()-> 	controller.cadastrarPL("111111111-1", 2017, "Abaixar opreço da coxinha", "", "http://example.com/baixa_a_coxinha_véi", true),"Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo" );
		assertThrows(IllegalArgumentException.class, ()-> 	controller.cadastrarPL("111111111-1", 2016, "Abaixar opreço da coxinha", "Saude, Economia", " ", true),"Erro ao cadastrar projeto: url nao pode ser vazio ou nulo" );
		assertThrows(IllegalArgumentException.class, ()-> 	controller.cadastrarPL("11111kkk1-1", 2015, "Abaixar opreço da coxinha", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true), "Erro ao cadastrar projeto: dni invalido");
		assertThrows(NullPointerException.class, ()-> 	controller.cadastrarPL("221111111-1", 2014, "Abaixar opreço da coxinha", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true),"Erro ao cadastrar projeto: pessoa inexistente" );
		assertThrows(NullPointerException.class, ()-> 	controller.cadastrarPL("111111111-2", 2013, "Abaixar opreço da coxinha", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true),"Erro ao cadastrar projeto: pessoa nao eh deputado" );
		assertThrows(NullPointerException.class, ()-> 	controller.cadastrarPL("111111111-1", 1912, "Abaixar opreço da coxinha", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true),"Erro ao cadastrar projeto: ano anterior a 1988" );
		assertThrows(NullPointerException.class, ()-> 	controller.cadastrarPL("111111111-1", 3011, "Abaixar opreço da coxinha", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true), "Erro ao cadastrar projeto: ano posterior ao ano atual");
		
	}
	

	@Test
	void testCadastrarPLP() {
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPLP(" ", 2016, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200"), "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPLP("111111111-1", 2016, "   ", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200"), "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		assertThrows(NullPointerException.class, ()-> controller.cadastrarPLP("111111111-1", 1005, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200"), "Erro ao cadastrar projeto: ano anterior a 1988");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPLP("111111111-1", 2014, "feriadaum de 6 meses", "", "http://example.com/ninguem_aguenta_mano", "200"), "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPLP("111111111-1", 2013, "feriadaum de 6 meses", "Saude, Lazer", "", "200"), "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPLP("111111111-1", 2012, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "      "),"Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo" );
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPLP("111111111-w", 2011, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200"),"Erro ao cadastrar projeto: dni invalido");
		assertThrows(NullPointerException.class, ()-> controller.cadastrarPLP("111119111-1", 2010, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200"),"Erro ao cadastrar projeto: pessoa inexistente" );
		assertThrows(NullPointerException.class, ()-> controller.cadastrarPLP("111111111-2", 2009, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200"), "Erro ao cadastrar projeto: pessoa nao eh deputado" );
		assertThrows(NullPointerException.class, ()-> controller.cadastrarPLP("111111111-1", 9008, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200"),"Erro ao cadastrar projeto: ano posterior ao ano atual" );

	}

	@Test
	void testCadastrarPEC() {
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPEC(" ", 2012, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", "900"), "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo" );
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPEC("111111111-1", 2012, "", "Educacao", "http://example.com/sonho", "900"),"Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula" );
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPEC("111111111-1", 2011, "Permite o aluno faltar em dias de chuva sem levar falta", "", "http://example.com/sonho", "900"),"Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo" );
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPEC("111111111-1", 2010, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "  ", "900"), "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPEC("111111111-1", 2009, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", ""),"Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPEC("kkkkjjjjk-1", 2008, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", "900"), "Erro ao cadastrar projeto: dni invalido" );
		assertThrows(NullPointerException.class, ()-> controller.cadastrarPEC("111111111-3", 2007, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", "900"),"Erro ao cadastrar projeto: pessoa inexistente" );
		assertThrows(NullPointerException.class, ()-> controller.cadastrarPEC("111111111-2", 2006, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", "900"), "Erro ao cadastrar projeto: pessoa nao eh deputado" );
		assertThrows(NullPointerException.class, ()-> controller.cadastrarPEC("111111111-1", 0005, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", "900"), "Erro ao cadastrar projeto: ano anterior a 1988" );
		assertThrows(NullPointerException.class, ()-> controller.cadastrarPEC("111111111-1", 7004, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", "900"), "Erro ao cadastrar projeto: ano posterior ao ano atual");
		
		
	}

	@Test
	void testExibirProjeto() {
		controller.cadastrarPessoa("Eu", "111111111-1", "PB", "desviar coisas", "PPP");
		controller.cadastrarPessoa("Tu", "111111111-2", "RJ", "desviar coisas", "PPP");
		controller.cadastrarPessoa("nois", "111111111-3", "SP", "desviar coisas", "PPP");
		controller.cadastrarPessoa("botanela", "111111111-4", "RN", "Nenhum", "PPL");
		controller.cadastraDeputado("111111111-1","03112012");
		controller.cadastraDeputado("111111111-2","12122012");
		controller.cadastraDeputado("111111111-3","05052005");
		controller.cadastraDeputado("111111111-4","02072006");
		controller.cadastrarPartido("PPP");
		
		controller.cadastrarPL("111111111-1", 2018, "Abaixar opreço da coxinha", "Saude, Economia", "http://example.com/baixa_a_coxinha_véi", true);
		controller.cadastrarPL("111111111-1", 2018, "Abaixar opreço do pastel", "Saude, Economia", "http://example.com/baixa_o_pastel_véi", false);
		controller.cadastrarPLP("111111111-1", 2016, "feriadaum de 6 meses", "Saude, Lazer", "http://example.com/ninguem_aguenta_mano", "200");
		controller.cadastrarPEC("111111111-1", 2012, "Permite o aluno faltar em dias de chuva sem levar falta", "Educacao", "http://example.com/sonho", "900,901,902");
		
		assertEquals("Projeto de Lei - PL 1/2018 - 111111111-1 - Abaixar opreço da coxinha - Conclusiva - EM VOTACAO (CCJC)", controller.exibirProjeto("PL 1/2018"));
		assertEquals("Projeto de Lei - PL 2/2018 - 111111111-1 - Abaixar opreço do pastel - EM VOTACAO (CCJC)", controller.exibirProjeto("PL 2/2018"));
		assertEquals("Projeto de Lei Complementar - PLP 1/2016 - 111111111-1 - feriadaum de 6 meses - 200 - EM VOTACAO (CCJC)", controller.exibirProjeto("PLP 1/2016"));
		assertEquals("Projeto de Emenda Constitucional - PEC 1/2012 - 111111111-1 - Permite o aluno faltar em dias de chuva sem levar falta - 900, 901, 902 - EM VOTACAO (CCJC)", controller.exibirProjeto("PEC 1/2012"));
	}
	
	@Test
	void testExibirPessoa() {
		assertThrows(IllegalArgumentException.class, ()-> controller.exibirPessoa("0000000000-0"), "Erro ao exibir pessoa: pessoa nao encontrada");
		assertThrows(IllegalArgumentException.class, ()-> controller.exibirPessoa(""), "Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> controller.exibirPessoa(null), "Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.exibirPessoa("0000000000,0"), "Erro ao exibir pessoa: pessoa nao encontrada");
		
	}	

	@Test
	void testCadastrarPessoaStringStringStringString() {
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPessoa("Eu", "1111111111-1", "PB", "Nenhum"), "Erro ao cadastrar pessoa: dni ja cadastrado");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPessoa("", "1111111111-1", "PB", "Nenhum"), "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> controller.cadastrarPessoa(null, "1111111111-1", "PB", "Nenhum"), "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPessoa("Eu", "", "PB", "Nenhum"), "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> controller.cadastrarPessoa("Eu", null, "PB", "Nenhum"), "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPessoa("Eu", "1111111111-1", "", "Nenhum"), "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> controller.cadastrarPessoa("Eu", "1111111111-1", null, "Nenhum"), "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPessoa("Eu", "11111111l1-1", "PB", "Nenhum"), "Erro ao cadastrar pessoa: dni invalido");
	}

	@Test
	void testCadastrarPessoaStringStringStringStringString() {
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPessoa("Eu", "1111111111-1", "PB", "Nenhum", "PPK"), "Erro ao cadastrar pessoa: dni ja cadastrado");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPessoa("", "1111111111-1", "PB", "Nenhum", "PPK"), "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> controller.cadastrarPessoa(null, "1111111111-1", "PB", "Nenhum", "PPK"), "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPessoa("Eu", "", "PB", "Nenhum", "PPK"), "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> controller.cadastrarPessoa("Eu", null, "PB", "Nenhum", "PPK"), "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPessoa("Eu", "1111111111-1", "", "Nenhum", "PPK"), "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> controller.cadastrarPessoa("Eu", "1111111111-1", null, "Nenhum", "PPK"), "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastrarPessoa("Eu", "11111111l1-1", "PB", "Nenhum", "PPK"), "Erro ao cadastrar pessoa: dni invalido");
	}

	@Test
	void testCadastraDeputado() {
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastraDeputado("", "25032017"), "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastraDeputado("1111111x11-2", "25032017"), "Erro ao cadastrar deputado: dni invalido");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastraDeputado("1111111111-3", "25032017"),"Erro ao cadastrar deputado: pessoa nao encontrada");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastraDeputado("1111111111-1", "25032017"),"Erro ao cadastrar deputado: pessoa sem partido");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastraDeputado("1111111111-2", ""),"Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastraDeputado("1111111111-2", "25o32017"),"Erro ao cadastrar deputado: data invalida");
		assertThrows(IllegalArgumentException.class, ()-> controller.cadastraDeputado("1111111111-2", "25032023"),"Erro ao cadastrar deputado: data futura");
	}

}
