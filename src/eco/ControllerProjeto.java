package eco;

import java.util.HashMap;
import java.util.Map;

public class ControllerProjeto {
	
	/**
	 * Mapa contendo todos os projetos cadastrados.
	 */
	private Map<String, Projeto> projetos;
	
	/**
	 * Contador de projetos de lei.
	 */
	private int contadorPL;
	
	/**
	 * contador de Projetos de Lei Complementares.
	 */
	private int contadorPLP;
	private Map<Integer, Integer> registrados;
	/**
	 * contador de Projetos de Emenda Constitucional.
	 */
	private int contadorPEC;
	
	public ControllerProjeto(){
		this.projetos = new HashMap<>();
		this.contadorPL = 1;
		this.contadorPLP = 1;
		this.contadorPEC = 1;
		this.registrados = new HashMap<>();
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
		String key = "PL " + this.registrados.get(ano) + "/" + ano;
		Projeto novoProjeto = new PL(dni, ano, this.contadorPL, ementa, interesses, url, conclusivo);
		this.projetos.put(key,novoProjeto );
		this.registrados.get(ano). += 1;
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
		String key = "PLP " + this.contadorPLP + "/" + ano;
		Projeto novoProjeto = new PLP(dni, ano, this.contadorPLP, ementa, interesses, url, artigos);
		this.projetos.put(key, novoProjeto);
		this.contadorPLP++;
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
		String key = "PEC " + this.contadorPEC + "/" + ano;
		Projeto novoProjeto = new PEC(dni, ano, this.contadorPEC, ementa, interesses, url, artigos);
		this.projetos.put(key, novoProjeto);
		this.contadorPEC++;
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
