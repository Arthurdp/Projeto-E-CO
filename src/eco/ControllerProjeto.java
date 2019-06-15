package eco;

import java.util.HashMap;
import java.util.Map;

public class ControllerProjeto {
	/**
	 * inicia um novo validador;
	 */
	private Validador validador = new Validador();
	/**
	 * Mapa contendo todos os projetos cadastrados.
	 */
	private static Map<String, Projeto> projetos;
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
		projetos = new HashMap<>();
		this.contadorPL = 1;
		this.contadorPLP = 1;
		this.contadorPEC = 1;
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
		validador.validaEntrada(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		validador.validaEntrada(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		validador.validaEntrada(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		validador.validaEntrada(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		if(!ControllerPessoa.contem(dni)) 
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		if(!ControllerPessoa.eDeputado(dni))
			throw new IllegalArgumentException("Erro ao cadastrar projeto: pessoa nao eh deputado");
		if(ano < 1988)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano anterior a 1988");
		if(ano > 2019)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano posterior ao ano atual");
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
		validador.validaEntrada(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		validador.validaEntrada(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		validador.validaEntrada(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		validador.validaEntrada(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		validador.validaEntrada(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		if(!ControllerPessoa.contem(dni)) 
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		if(!ControllerPessoa.eDeputado(dni))
			throw new IllegalArgumentException("Erro ao cadastrar projeto: pessoa nao eh deputado");
		if(ano < 1988)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano anterior a 1988");
		if(ano > 2019)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano posterior ao ano atual");
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
		validador.validaEntrada(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		validador.validaEntrada(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		validador.validaEntrada(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		validador.validaEntrada(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		validador.validaEntrada(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		if(!ControllerPessoa.contem(dni)) 
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		if(!ControllerPessoa.eDeputado(dni))
			throw new IllegalArgumentException("Erro ao cadastrar projeto: pessoa nao eh deputado");
		if(ano < 1988)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano anterior a 1988");
		if(ano > 2019)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano posterior ao ano atual");
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
	
	public static Projeto getProjeto(String codigo) {
		return projetos.get(codigo);
	}
	

}
