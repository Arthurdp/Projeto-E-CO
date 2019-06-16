package eco;

import java.util.HashMap;
import java.util.Map;

public class ControllerProjeto {
	
	/**
	 * Mapa contendo todos os projetos cadastrados.
	 */
	private Map<String, Projeto> projetos;
	private Validador validador;
	/**
	 * Contador de projetos de lei.
	 */
	private int contadorPL;
	
	/**
	 * contador de Projetos de Lei Complementares.
	 */
	private int contadorPLP;
	
	/**
	 * contador de Projetos de Emenda Constitucional.
	 */
	private int contadorPEC;
	
	public ControllerProjeto(){
		this.projetos = new HashMap<>();
		this.validador = new Validador();
		this.contadorPL = 1;
		this.contadorPLP = 1;
		this.contadorPEC = 1;
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
	public void cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		String key = "PL " + this.contadorPL + "/" + ano;
		Projeto novoProjeto = new PL(dni, ano, this.contadorPL, ementa, interesses, url, conclusivo);
		this.projetos.put(key,novoProjeto );
		this.contadorPL++;
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
	public void cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		String key = "PLP " + this.contadorPLP + "/" + ano;
		Projeto novoProjeto = new PLP(dni, ano, this.contadorPLP, ementa, interesses, url, artigos);
		this.projetos.put(key, novoProjeto);
		this.contadorPLP++;
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
	public void cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		String key = "PEC " + this.contadorPEC + "/" + ano;
		Projeto novoProjeto = new PEC(dni, ano, this.contadorPEC, ementa, interesses, url, artigos);
		this.projetos.put(key, novoProjeto);
		this.contadorPEC++;
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
