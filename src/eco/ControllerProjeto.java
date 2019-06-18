package eco;

import java.util.HashMap;
import java.util.Map;

public class ControllerProjeto {
	
	/**
	 * Mapa contendo todos os projetos cadastrados.
	 */
	private Map<String, Projeto> projetos;
	

	private Map<Integer, Integer> registradosPL;
	private Map<Integer, Integer> registradosPLP;
	private Map<Integer, Integer> registradosPEC;
	
	
	public ControllerProjeto(){
		this.projetos = new HashMap<>();
		this.registradosPL = new HashMap<>();
		this.registradosPLP = new HashMap<>();
		this.registradosPEC = new HashMap<>();
	}

	public Map<String, Projeto> getProjetos() {
		return projetos;
	}
	

	/**
	 * Cadastra um novo projeto de lei criando o objeto PL e adicionando-o ao mapa de projetos.
	 * @param dni dni do autor do projeto
	 * @param ano ano de criacaoo do projeto
	 * @param ementa descricao do objetivo do projeto
	 * @param interesses ineresses relacionados ao projeto criado
	 * @param url endereco eletronico do projeto
	 * @param conclusivo informa se o projeto eh conclusivo ou nao
	 */
	public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		if (this.registradosPL.containsKey(ano))
			this.registradosPL.put(ano, this.registradosPL.get(ano) + 1);
		else
			this.registradosPL.put(ano, 1);
		String key = "PL " + this.registradosPL.get(ano) + "/" + ano;
		Projeto novoProjeto = new PL(dni, ano, key, ementa, interesses, url, conclusivo);
		this.projetos.put(key,novoProjeto );
		return key;
	}
	/**
	 * Cadastra um novo projeto de lei complementar, criando o objeto PLP e adicionando-o ao mapa de projetos.
	 * @param dni dni do autor do projeto
	 * @param ano ano de criacaoo do projeto
	 * @param ementa descricao do objetivo do projeto
	 * @param interesses ineresses relacionados ao projeto criado
	 * @param url endereco eletronico do projeto
	 * @param artigos Artigos da constituição sendo complementados.
	 */
	public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		if (this.registradosPLP.containsKey(ano))
			this.registradosPLP.put(ano, this.registradosPLP.get(ano) + 1);
		else
			this.registradosPLP.put(ano, 1);
		String key = "PLP " + this.registradosPLP.get(ano) + "/" + ano;
		Projeto novoProjeto = new PLP(dni, ano, key, ementa, interesses, url, artigos);
		this.projetos.put(key, novoProjeto);
		return key;
	}
	/**
	 * Cadastra um novo Projeto de Emenda Constitucional, criando o objeto PEC e adicionando-o ao mapa de projetos.
	 * @param dni dni do autor do projeto
	 * @param ano ano de criacaoo do projeto
	 * @param ementa descricao do objetivo do projeto
	 * @param interesses ineresses relacionados ao projeto criado
	 * @param url endereco eletronico do projeto
	 * @param artigos Artigos da constituição sendo emendados
	 */
	public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		if (!this.registradosPEC.containsKey(ano))
			this.registradosPEC.put(ano, 1);
		else
			this.registradosPEC.put(ano, this.registradosPEC.get(ano) + 1);
			
		String key = "PEC " + this.registradosPEC.get(ano) + "/" + ano;
		Projeto novoProjeto = new PEC(dni, ano,key , ementa, interesses, url, artigos);
		this.projetos.put(key, novoProjeto);
		return key;
	}
	/**
	 * Exibe a representacao de um projeto de lei com suas informacoes no formato string
	 * @param codigo codigo do projeto de lei a ser exibido
	 * @return String com as informacoes do projeto de lei
	 */
	public String exibirProjeto(String codigo) {
		return projetos.get(codigo).toString();
	}
}
