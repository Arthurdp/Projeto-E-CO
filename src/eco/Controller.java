package eco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsavel por controlar todo o sistema. controla o cadastro de pessoas,
 * deputados e partidos, e as representacoes de pessoas e partidos cadastrados.
 *
 */
public class Controller {
	
	/**
	 * inicia um novo validador;
	 */
	private Validador validador = new Validador();
	
	/**
	 * mapa de pessoas cadastradas.
	 */
	private HashMap<String, Pessoa> pessoas;
	private List<String> partidos;
	private HashMap<String, Comissao> comissoes;
	
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
	
	/**
	 * contador de Projetos de Emenda Constitucional.
	 */
	private int contadorPEC;		 
	
	public Controller() {
		this.validador = new Validador();
		this.pessoas = new HashMap<>();
		this.comissoes = new HashMap<>();
		this.partidos = new ArrayList<>();
		this.projetos = new HashMap<>();
		this.contadorPL = 1;
		this.contadorPLP = 1;
		this.contadorPEC = 1;
		
	}
	
	public HashMap<String, Comissao> getComissoes() {
		return comissoes;
	}

	public List<String> getPartidos() {
		return partidos;
	}

	/**
	 * cadastra uma nova pessoa sem partido no sistema, adicionando um objeto do tipo pessoa no mapa de pessoas.
	 * @param nome nome da pessoa que sera adicionada.
	 * @param dni dni da pessoa que sera adicionada.
	 * @param estado estado da pessoa que sera adicionada.
	 * @param interesses interesses da pessoa que sera adicionada.
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		Pessoa nova = new Pessoa(nome, dni, estado, interesses);
		if(pessoas.containsKey(dni))
			throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni ja cadastrado");
		pessoas.put(dni, nova);
		
	}
	
	/**
	 * cadastra uma nova pessoa com partido no sistema, adicionando um objeto do tipo pessoa no mapa de pessoas.
	 * @param nome nome da pessoa que sera adicionada.
	 * @param dni dni da pessoa que sera adicionada.
	 * @param estado estado da pessoa que sera adicionada.
	 * @param interesses interesses da pessoa que sera adicionada.
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		Pessoa nova = new Pessoa(nome, dni, estado, interesses, partido);
		if(pessoas.containsKey(dni))
			throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni ja cadastrado");
		pessoas.put(dni, nova);
	}
	
	/**
	 * inicia a vida publica de uma pessoa, tornando a mesma em um deputado.
	 * @param dni da pessoa que vai se tornar um deputado.
	 * @param data data do momento em que a pessoa se tornou deputado.
	 */
	public void cadastraDeputado(String dni, String data) {
		validador.validaEntrada(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar deputado: dni invalido");
		if(!pessoas.containsKey(dni))
			throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa nao encontrada");
		if(pessoas.get(dni).getPartido() == null)
			throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa sem partido");
		validador.validaEntrada(data, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
		validador.validaData(data, "Erro ao cadastrar deputado: data invalida");
		validador.validaDataFutura(data, "Erro ao cadastrar deputado: data futura");
		
		Pessoa pessoa = pessoas.get(dni);
		pessoa.virouDeputado(data);
	}
	
	/**
	 * exibe dados de uma pessoa cadastrada no sistema determinada pelo seu dni. 
	 * @param dni dni da pessoa a ser exibida;
	 * @return a representacao textual de uma pessoa cadastrada.
	 */
	public String exibirPessoa(String dni) {
			validador.validaEntrada(dni,"Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
			validador.validaDni(dni, "Erro ao exibir pessoa: dni invalido");
		if (!pessoas.containsKey(dni)) {
			throw new IllegalArgumentException("Erro ao exibir pessoa: pessoa nao encontrada");
		}
		return pessoas.get(dni).exibirPessoa();
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
		if(!pessoas.containsKey(dni)) 
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		if(pessoas.get(dni).getDeputado() == null)
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
		if(!pessoas.containsKey(dni)) 
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		if(pessoas.get(dni).getDeputado() == null)
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
		if(!pessoas.containsKey(dni))
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		if(pessoas.get(dni).getDeputado() == null)
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
	
	/**
	 * cadastra um partido no sistema, adicionando uma string na lista de partidos.
	 * @param partido sigla do partido a ser cadastrado.
	 */
	public void cadastrarPartido(String partido) {
		validador.validaEntrada(partido,"Erro ao cadastrar partido: partido nao pode ser vazio ou nulo");
		this.partidos.add(partido);
	}
	
	/** 
	 * exibe todos os partidos cadastrados, em ordem lexicografica.
	 * @return as strings que representam os partidos cadastrados.
	 */
	public String exibirBase() {
		Collections.sort(partidos);
		String saida = "";
		for(String partido : partidos) {
			saida += partido + ",";
		}
		if (saida.length() > 0)
			return saida.substring(0, saida.length() - 1);
		else
			return saida;
	}
	/**
	 * Cadastra uma nova comissao inserindo-a no mapa de comissoes
	 * @param tema tema da comissao
	 * @param politicos string contendo os dni dos politicos participantes da comissao
	 */
	public void cadastrarComissao(String tema, String politicos){
		Comissao nova = new Comissao(tema, politicos);
		comissoes.put(tema, nova);
		
	}
	
	public boolean interessesComuns(String interesses1, String interesses2) {
		String[] i1 = interesses1.split(",");
		String[] i2 = interesses2.split(",");
		
		for (String i : i1) {
			for(String j : i2) {
				if (i.equals(j))
					return true;
			}
		}
		return false;
	}
	
	public boolean eDaBase(String dni) {
		for (String p : this.partidos) {
			if (pessoas.get(dni).getPartido().equals(p))
				return true;
		}
		return false;
	}
	
	public int contaVotos(String codigo, String statusGovernista, String politicos) {
		int votosAprovar = 0;

		String politcs[]  = politicos.split(",");
		for (String dni : politcs){
			validador.validaDni(dni, "Erro ao votar proposta: dni invalido");
			if(!pessoas.containsKey(dni)) 
				throw new NullPointerException("Erro ao votar proposta: pessoa inexistente");
			if(pessoas.get(dni).getDeputado() == null)
				throw new IllegalArgumentException("Erro ao votar proposta: pessoa nao eh deputado");

			if(statusGovernista.equals("GOVERNISTA") && eDaBase(dni))
				votosAprovar += 1;
			if(statusGovernista.equals("OPOSICAO") && !eDaBase(dni))
				votosAprovar += 1;
			if(statusGovernista.equals("LIVRE")){
				if(interessesComuns(pessoas.get(dni).getInteresses(), projetos.get(codigo).getInteresses()))
					votosAprovar += 1;
			}
		}
		return votosAprovar;
	}
	
	public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {	
		int votosAprovar = contaVotos(codigo, statusGovernista, presentes);
		String presents[]  = presentes.split(",");
		int deputados = 0;
		for(String i : pessoas.keySet()) {
			if(!(pessoas.get(i).getDeputado() == null)) {
				deputados += 1;
			}
		}
		
		if (codigo.substring(0,3).equals("PL ")) {
			if (votosAprovar >= Math.floor((presents.length / 2) + 1))
				return true;
			
		}else if (!projetos.get(codigo).getTurno().equals("finalizado")) {
				if (codigo.substring(0,3).equals("PLP")) {
					if(projetos.get(codigo).getTurno().equals("1o turno"))
						projetos.get(codigo).setTurno("2o turno");
					else if(projetos.get(codigo).getTurno().equals("2o turno"))
						projetos.get(codigo).setTurno("finalizado");
					if(votosAprovar  >= (deputados/ 2) + 1)
						return true;
		
			}else if(codigo.substring(0,3).equals("PEC")) {
				if(projetos.get(codigo).getTurno().equals("1o turno"))
					projetos.get(codigo).setTurno("2o turno");
				else if(projetos.get(codigo).getTurno().equals("2o turno"))
					projetos.get(codigo).setTurno("finalizado");
				if(votosAprovar  >= (deputados * 5 / 3) + 1)
					return true;
		}
		}		
		return false;
	}
	
	public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
		if (proximoLocal.equals("plenario"))
			throw new IllegalArgumentException("");
		if (comissoes.get(projetos.get(codigo).getLocalAtual()).getProjetosVotados().contains(codigo)) 
			throw new IllegalArgumentException("");
		if (projetos.get(codigo).getLocalAtual().equals("-")) 
			throw new IllegalArgumentException("");
		
		
		int votosAprovados = contaVotos(codigo, statusGovernista, comissoes.get(projetos.get(codigo).getLocalAtual()).getPoliticos());
		String politicos[]  = comissoes.get(projetos.get(codigo).getLocalAtual()).getPoliticos().split(",");
		if (votosAprovados >= politicos.length / 2 + 1) {
			comissoes.get(projetos.get(codigo).getLocalAtual()).getProjetosVotados().add(codigo);
			projetos.get(codigo).setLocalAtual(proximoLocal);
			if (proximoLocal.equals("-")) {
				projetos.get(codigo).setSituacaoAtual("concluido");
				pessoas.get(projetos.get(codigo).getAutor()).getDeputado().aprovouUmaLei();
				return true;
			}
			projetos.get(codigo).setSituacaoAtual("EM VOTACAO (" + proximoLocal + ")");
			return true;
			
		}
		projetos.get(codigo).setSituacaoAtual("EM VOTACAO (" + proximoLocal + ")");
		comissoes.get(projetos.get(codigo).getLocalAtual()).getProjetosVotados().add(codigo);
		projetos.get(codigo).setLocalAtual(proximoLocal);
		return false;
	}
}

