package eco;

import java.util.ArrayList;
import java.util.List;

/**
 * controller que contem os demais controllers, com a finalidade de facilitar a troca de informacoes entre os objetos
 * e generalizar mais o codigo.
 *
 */
public class ControllerGeral {
	
	ControllerComissoes controllerComissoes;
	ControllerPessoa controllerPessoa;
	ControllerProjeto controllerProjeto;
	Validador validador;
	
	public ControllerGeral() {
		this.controllerComissoes = new ControllerComissoes();
		this.controllerPessoa = new ControllerPessoa();
		this.controllerProjeto = new ControllerProjeto();
		this.validador = new Validador();
	}
	
	/**
	 * cadastra uma nova pessoa sem partido no sistema, adicionando um objeto do tipo pessoa no mapa de pessoas.
	 * @param nome nome da pessoa que sera adicionada.
	 * @param dni dni da pessoa que sera adicionada.
	 * @param estado estado da pessoa que sera adicionada.
	 * @param interesses interesses da pessoa que sera adicionada.
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		controllerPessoa.cadastrarPessoa(nome, dni, estado, interesses);
	}
	
	/**
	 * cadastra uma nova pessoa com partido no sistema, adicionando um objeto do tipo pessoa no mapa de pessoas dentro do ControllerPessoa.
	 * @param nome nome da pessoa que sera adicionada.
	 * @param dni dni da pessoa que sera adicionada.
	 * @param estado estado da pessoa que sera adicionada.
	 * @param interesses interesses da pessoa que sera adicionada.
	 * @param partido e o partido que a pessoa e filiada
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		controllerPessoa.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}

	/**
	 * inicia a vida publica de uma pessoa, tornando a mesma em um deputado.
	 * @param dni da pessoa que vai se tornar um deputado.
	 * @param data data do momento em que a pessoa se tornou deputado.
	 */
	public void cadastrarDeputado(String DNI, String dataDeInicio) {
		controllerPessoa.cadastraDeputado(DNI, dataDeInicio);
	}
	
	/**
	 * cadastra um partido no sistema, adicionando uma string na lista de partidos.
	 * @param partido sigla do partido a ser cadastrado.
	 */
	public void cadastrarPartido(String partido) {
		this.controllerComissoes.cadastrarPartido(partido);
	}
	
	/**
	 * exibe dados de uma pessoa cadastrada no sistema determinada pelo seu dni. 
	 * @param dni dni da pessoa a ser exibida;
	 * @return a representacao textual de uma pessoa cadastrada.
	 */
	public String exibirPessoa(String DNI) {
		return controllerPessoa.exibirPessoa(DNI);
	}
	
	/** 
	 * exibe todos os partidos cadastrados, em ordem lexicografica.
	 * @return as strings que representam os partidos cadastrados.
	 */
	public String exibirBase() {
		return controllerComissoes.exibirBase();
	}
	
	/**
	 * Cadastra uma nova comissao inserindo-a no mapa de comissoes dentro do controllerComissao
	 * @param tema tema da comissao
	 * @param politicos string contendo os dni dos politicos participantes da comissao
	 */
	public void cadastrarComissao(String tema, String politicos) {
		validador.validaEntrada(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
		validador.validaEntrada(politicos, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
		if(controllerComissoes.getComissoes().containsKey(tema))
			throw new IllegalArgumentException("Erro ao cadastrar comissao: tema existente");
		String[] politics = politicos.split(",");
		for(String dni : politics) {
			validador.validaDni(dni, "Erro ao cadastrar comissao: dni invalido");
			if(controllerPessoa.retornaPessoa(dni) == null) 
				throw new NullPointerException("Erro ao cadastrar comissao: pessoa inexistente");
			if(controllerPessoa.retornaPessoa(dni).getDeputado() == null)
				throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa nao eh deputado");
		}
		this.controllerComissoes.cadastrarComissao(tema, politicos);
	}
	
	/**
	 * Cadastra um novo projeto de lei criando o objeto PL e adicionando-o ao mapa de projetos dentro de controllerProjetos.
	 * @param dni dni do autor do projeto
	 * @param ano ano de criacaoo do projeto
	 * @param ementa descricao do objetivo do projeto
	 * @param interesses ineresses relacionados ao projeto criado
	 * @param url endereco eletronico do projeto
	 * @param conclusivo informa se o projeto eh conclusivo ou nao
	 */
	public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		validador.validaEntrada(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		validador.validaEntrada(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		validador.validaEntrada(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		validador.validaEntrada(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		if(controllerPessoa.retornaPessoa(dni) == null) 
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		if(controllerPessoa.retornaPessoa(dni).getDeputado() == null)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: pessoa nao eh deputado");
		if(ano < 1988)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano anterior a 1988");
		if(ano > 2019)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano posterior ao ano atual");
		return this.controllerProjeto.cadastrarPL(dni, ano, ementa, interesses, url, conclusivo);
	}
	
	/**
	 * cadastra no sistema um projeto do tipo PLP
	 * @param dni e o dni do politico que criou o projeto
	 * @param ano ano de inicio do projeto
	 * @param ementa descricao do objetivo do projeto
	 * @param interesses sao os interesses sociais relacionados ao projeto
	 * @param url e o endereco digital do projeto
	 * @param artigos sao os artigos da constituicao sendo ementados
	 * @return
	 */
	public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		validador.validaEntrada(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		validador.validaEntrada(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		validador.validaEntrada(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		validador.validaEntrada(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		validador.validaEntrada(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		if(controllerPessoa.retornaPessoa(dni) == null) 
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		if(controllerPessoa.retornaPessoa(dni).getDeputado() == null)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: pessoa nao eh deputado");
		if(ano < 1988)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano anterior a 1988");
		if(ano > 2019)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano posterior ao ano atual");
		return this.controllerProjeto.cadastrarPLP(dni, ano, ementa, interesses, url, artigos);
	}
	
	/**
	 * cadastra no sistema um projeto do tipo PEC
	 * @param dni e o dni do politico que criou o projeto
	 * @param ano ano de inicio do projeto
	 * @param ementa descricao do objetivo do projeto
	 * @param interesses sao os interesses sociais relacionados ao projeto
	 * @param url e o endereco digital do projeto
	 * @param artigos sao os artigos da constituicao sendo ementados
	 * @return
	 */
	public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		validador.validaEntrada(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		validador.validaEntrada(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		validador.validaEntrada(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		validador.validaEntrada(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		validador.validaEntrada(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		if(controllerPessoa.retornaPessoa(dni) == null)
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		if(controllerPessoa.retornaPessoa(dni).getDeputado() == null)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: pessoa nao eh deputado");
		if(ano < 1988)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano anterior a 1988");
		if(ano > 2019)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano posterior ao ano atual");
		return this.controllerProjeto.cadastrarPEC(dni, ano, ementa, interesses, url, artigos);
	}
	
	/**
	 * exibe a representacao em String do projeto (toString)
	 * @param codigo do projeto que sera selecionado
	 * @return retorna o valor do metodo toString de Projeto
	 */
	public String exibirProjeto(String codigo) {
		return this.controllerProjeto.exibirProjeto(codigo);
	}
	
	/**
	 * este metodo vai definir se um projeto de lei que esta no plenario sera aprovado ou nao
	 * com base no statusGovernista e nos politicos presentes
	 * @param codigo e o codigo que referencia o projeto
	 * @param statusGovernista e se o criterio utilizado para que o voto seja a favor ou nao com base no partido dos politicos
	 * @param presentes sao os politicos que estao presentes para a referida votacao
	 * @return retorna um booleano com base no resultado da votacao
	 */
	public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {
		validador.validaEntrada(presentes, "Erro ao votar proposta: presentes nao pode ser vazio ou nulo");
		List<Pessoa> politicosCadastrados = new ArrayList<>();
		List<Pessoa> politicosPresentes = new ArrayList<>();
		
		for(Pessoa p : controllerPessoa.getPessoas().values()) {
			if(p.getDeputado() != null) {
				politicosCadastrados.add(p);
			}
		}

		for(String dni : presentes.split(",")) {
			validador.validaDni(dni, "Erro ao votar proposta: dni invalido");
		}
		
		for(String dni : presentes.split(",")) {
			if(controllerPessoa.retornaPessoa(dni) == null) 
				throw new IllegalArgumentException("Erro ao votar proposta: Deputado nao cadastrado");
			
		}
		
		for(String dni : presentes.split(",")) {
			politicosPresentes.add(controllerPessoa.retornaPessoa(dni));
		}
			
		
		
		if(controllerProjeto.votarPlenario(codigo, statusGovernista, politicosCadastrados,politicosPresentes, controllerComissoes.getPartidos())) {
			if(controllerProjeto.getProjetos().get(codigo).getSituacaoAtual().equals("APROVADO"))
				controllerPessoa.getPessoas().get(controllerProjeto.getProjetos().get(codigo).getAutor()).getDeputado().aprovouUmaLei();
			
			return true;
		}
				
		return false;
	}
	/**
	 * Simula um voto para uma comissao comecando pela CCJC e passando para o proximo local. recebo o codigo do projeto que sera votado, o status governista e o proximo local que sera votado.
	 * Retorna True caso o projeto tenha sido aprovado, ou false caso contrario.
	 * @param codigo codigo de um projeto
	 * @param statusGovernista status GOVERNISTA, LIVRE OU OPOSICAO
	 * @param proximoLocal proximo local que sera votado.
	 * @return retorna um valor booleano
	 */
	public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
		validador.validaEntrada(codigo, "Erro ao votar proposta: codigo nao pode ser vazio ou nulo");
		validador.validaEntrada(statusGovernista, "Erro ao votar proposta: status governista nao pode ser vazio ou nulo");
		validador.validaEntrada(proximoLocal, "Erro ao votar proposta: proximo local vazio");
		
		if (!statusGovernista.equals("GOVERNISTA") && !statusGovernista.equals("LIVRE") && !statusGovernista.equals("OPOSICAO"))
			throw new IllegalArgumentException("Erro ao votar proposta: status invalido");
		
		if(!controllerProjeto.getProjetos().containsKey(codigo))
			throw new IllegalArgumentException("Erro ao votar proposta: projeto inexistente");
		
		if (controllerProjeto.getProjetos().get(codigo).getLocalAtual().equals("plenario")) 
			throw new IllegalArgumentException("Erro ao votar proposta: proposta encaminhada ao plenario");
		
		if ((controllerProjeto.getProjetos().get(codigo).getLocalAtual().equals("-")) || controllerProjeto.getProjetos().get(codigo).getSituacaoAtual().equals("ARQUIVADO") || controllerProjeto.getProjetos().get(codigo).getSituacaoAtual().equals("APROVADO"))
			throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
		
		if(!controllerComissoes.getComissoes().containsKey("CCJC"))
			throw new IllegalArgumentException("Erro ao votar proposta: CCJC nao cadastrada");
		
		if(!controllerComissoes.getComissoes().containsKey(controllerProjeto.retornaLocalAtual(codigo)))
			throw new IllegalArgumentException("Erro ao votar proposta: comissao nao cadastrada");
		
		if(proximoLocal.equals(controllerProjeto.retornaLocalAtual(codigo)))
			throw new IllegalArgumentException("Erro ao votar proposta: proposta ja votada nessa comicao");
		
		List<Pessoa> deputados = new ArrayList<>();
		String politicos[] = controllerComissoes.getComissoes().get(controllerProjeto.getProjetos().get(codigo).getLocalAtual()).getPoliticos().split(",");
		for(String p : politicos) {
			deputados.add(controllerPessoa.retornaPessoa(p));
		}
		
		return controllerProjeto.getProjetos().get(codigo).votarComissao(statusGovernista, deputados, controllerComissoes.getPartidos(), controllerComissoes.getComissoes(), proximoLocal);
	}
	
	/**
	 * Exibe a tramitacao de um projeto de lei, informando os locais onde 
	 * ja foi votado e se foi aprovado ou nao nos locais, exibe tambem a situacao
	 * atual do projeto caso ainda nao tenha sido encerrado
	 * @param codigo codigo do projeto a ser exibido
	 * @return String contendo a tramitacao do projeto
	 */
	public String exibirTramitacao(String codigo) {
		return controllerProjeto.exibirTramitacao(codigo);
	}
	
	/**
	 * altera a estrategia utilizada pela pessoa, no metodo "pegarPropostaRelacionada" 
	 * @param dni pessoa que tera sua estrategia de seleçao alterada
	 * @param estrategia nome da estrategia que sera usada (guardada no objeto pessoa)
	 */
	public void configurarEstrategiaPropostaRelacionada(String dni, String estrategia) {
		validador.validaEntrada(dni, "Erro ao configurar estrategia: pessoa nao pode ser vazia ou nula");
		validador.validaDni(dni, "Erro ao configurar estrategia: dni invalido");
		validador.validaEntrada(estrategia, "Erro ao configurar estrategia: estrategia vazia");
		
		if(!estrategia.equals("APROVACAO") && !estrategia.equals("CONSTITUCIONAL") && !estrategia.equals("CONCLUSAO") )
			throw new IllegalArgumentException("Erro ao configurar estrategia: estrategia invalida");
		if(controllerPessoa.retornaPessoa(dni) == null)
			throw new IllegalArgumentException("Erro ao configurar estrategia: pessoa inexistente");
		
		
		controllerPessoa.configurarEstrategiaPropostaRelacionada(dni, estrategia);
	}
	
	/**
	 * pega um projeto de lei que ainda nao foi finalizado e que tenha interesses em comum com o cliente referenciado pelo dni
	 * passado com paramentro. Caso haja mais de um projeto de lei relacionado a pessoa, uma estrategia, dentre tres, sera utilizada
	 * para selecionar qual projeto sera retornado. 
	 * @param dni e o meio pelo qual a pessoa sera identificada e selecionada
	 * @return o codigo do projeto de lei (String)
	 */
	public String pegarPropostaRelacionada(String dni) {
		validador.validaEntrada(dni, "Erro ao pegar proposta relacionada: pessoa nao pode ser vazia ou nula");
		validador.validaDni(dni, "Erro ao pegar proposta relacionada: dni invalido");
		List<Projeto> lista = new ArrayList<>();
			for(Projeto projeto : controllerProjeto.retornaProjetosRelacionados(controllerPessoa.retornaPessoa(dni).getInteresses())) {
				if(!(projeto.getLocalAtual().equals("-")) && !projeto.getSituacaoAtual().equals("ARQUIVADO") && !projeto.getSituacaoAtual().equals("APROVADO"))
					lista.add(projeto);
			}

			return controllerPessoa.retornaPessoa(dni).getEstrategia().prioridade(lista);
	}
	/**
	 * Salva os dados do sistema em um diretorio separado na pasta arquivos.
	 */
	public void salvarSistema() {
		controllerPessoa.salvarSistema();
		controllerProjeto.salvarSistema();
		controllerComissoes.salvarSistema();
	}
	/**
	 * Carrega os dados do sistema, que estao em um diretorio separado na pasta arquivos.
	 */
	public void carregaSistema() {
		controllerPessoa.carregarSistema();
		controllerProjeto.carregarSistema();
		controllerComissoes.carregarSistema();		
	}
	/**
	 * Limpa os dados do sistema, que estao em um diretorio separado na pasta arquivos.
	 */
	public void limpaSistema() {
		controllerPessoa.limparSistema();
		controllerProjeto.limparSistema();
		controllerComissoes.limparSistema();		
	}
	
}

