package eco;

import java.util.ArrayList;
import java.util.List;

public class ControllerGeral {
	
	ControllerComissoes controller;
	ControllerPessoa controllerPessoa;
	ControllerProjeto controllerProjeto;
	Validador validador;
	
	public ControllerGeral() {
		this.controller = new ControllerComissoes();
		this.controllerPessoa = new ControllerPessoa();
		this.controllerProjeto = new ControllerProjeto();
		this.validador = new Validador();
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		controllerPessoa.cadastrarPessoa(nome, dni, estado, interesses);
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		controllerPessoa.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}
	
	public void cadastrarDeputado(String DNI, String dataDeInicio) {
		controllerPessoa.cadastraDeputado(DNI, dataDeInicio);
	}
	
	public void cadastrarPartido(String partido) {
		this.controller.cadastrarPartido(partido);
	}
	
	public String exibirPessoa(String DNI) {
		return controllerPessoa.exibirPessoa(DNI);
	}
	
	public String exibirBase() {
		return controller.exibirBase();
	}
	
	public void cadastrarComissao(String tema, String politicos) {
		validador.validaEntrada(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
		validador.validaEntrada(politicos, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
		if(controller.getComissoes().containsKey(tema))
			throw new IllegalArgumentException("Erro ao cadastrar comissao: tema existente");
		String[] politics = politicos.split(",");
		for(String dni : politics) {
			validador.validaDni(dni, "Erro ao cadastrar comissao: dni invalido");
			if(!controllerPessoa.getPessoas().containsKey(dni)) 
				throw new NullPointerException("Erro ao cadastrar comissao: pessoa inexistente");
			if(controllerPessoa.getPessoas().get(dni).getDeputado() == null)
				throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa nao eh deputado");
		}
		this.controller.cadastrarComissao(tema, politicos);
	}
	
	public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		validador.validaEntrada(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		validador.validaEntrada(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		validador.validaEntrada(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		validador.validaEntrada(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		if(!controllerPessoa.getPessoas().containsKey(dni)) 
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		if(controllerPessoa.getPessoas().get(dni).getDeputado() == null)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: pessoa nao eh deputado");
		if(ano < 1988)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano anterior a 1988");
		if(ano > 2019)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano posterior ao ano atual");
		return this.controllerProjeto.cadastrarPL(dni, ano, ementa, interesses, url, conclusivo);
	}
	
	public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		validador.validaEntrada(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		validador.validaEntrada(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		validador.validaEntrada(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		validador.validaEntrada(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		validador.validaEntrada(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		if(!controllerPessoa.getPessoas().containsKey(dni)) 
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		if(controllerPessoa.getPessoas().get(dni).getDeputado() == null)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: pessoa nao eh deputado");
		if(ano < 1988)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano anterior a 1988");
		if(ano > 2019)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano posterior ao ano atual");
		return this.controllerProjeto.cadastrarPLP(dni, ano, ementa, interesses, url, artigos);
	}
	
	public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		validador.validaEntrada(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		validador.validaEntrada(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		validador.validaEntrada(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		validador.validaEntrada(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		validador.validaEntrada(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		if(!controllerPessoa.getPessoas().containsKey(dni))
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		if(controllerPessoa.getPessoas().get(dni).getDeputado() == null)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: pessoa nao eh deputado");
		if(ano < 1988)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano anterior a 1988");
		if(ano > 2019)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano posterior ao ano atual");
		return this.controllerProjeto.cadastrarPEC(dni, ano, ementa, interesses, url, artigos);
	}
	
	public String exibirProjeto(String codigo) {
		return this.controllerProjeto.exibirProjeto(codigo);
	}
	
	public boolean eDaBase(String dni) {
		for (String p : this.controller.getPartidos()) {
			if (controllerPessoa.getPessoas().get(dni).getPartido().equals(p))
				return true;
		}
		return false;
	}
	/**
	 * Conta os votos que foram aprovados recebendo um codigo de um projeto, um status e uma String com os dnis dos politicos.
	 * Retorna um inteiro que representa a quantidade de votos aprovados.
	 * @param codigo codigo de um projeto
	 * @param statusGovernista status GOVERNISTA, LIVRE OU OPOSICAO
	 * @param politicos String com dnis de politicos separados por virgula.
	 * @return retorna um inteiro
	 */

	
	public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {
		String presents[]  = presentes.split(",");
		List<Pessoa> politicosPresentes = new ArrayList<>();
		int deputados = 0;
		for(Pessoa p : controllerPessoa.getPessoas().values()) {
			if(p.getDeputado() != null) {
				politicosPresentes.add(p);
				deputados += 1;
			}
		}
		if (codigo.substring(0,3).equals("PL ") || (codigo.substring(0,3).equals("PLP"))) {
			if(presents.length < Math.floor((deputados / 2)) + 1)
				throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
		}
		if(codigo.substring(0,3).equals("PEC")) {
			if(presents.length < Math.floor((3/5 * deputados)) + 1)
				throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
		}
		if (controllerProjeto.getProjetos().get(codigo).getSituacaoAtual().equals("ARQUIVADO") || controllerProjeto.getProjetos().get(codigo).getSituacaoAtual().equals("APROVADO"))
			throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");

		if(!controllerProjeto.getProjetos().get(codigo).getLocalAtual().equals("plenario"))
			throw new IllegalArgumentException("Erro ao votar proposta: tramitacao em comissao");
		validador.validaEntrada(codigo, "Erro ao votar proposta: codigo nao pode ser vazio ou nulo");
		validador.validaEntrada(statusGovernista, "Erro ao votar proposta: status governista nao pode ser vazio ou nulo");
		validador.validaEntrada(presentes, "Erro ao votar proposta: Deputados presentes nao pode ser vazio ou nulo");
		for(String dni : presentes.split(",")) {
			validador.validaDni(dni, "Erro ao votar proposta: dni invalido");
		}
		if(!controllerProjeto.getProjetos().containsKey(codigo))
			throw new IllegalArgumentException("Erro ao votar proposta: projeto inexistente");
		for(String dni : presentes.split(",")) {
			if(!controllerPessoa.getPessoas().containsKey(dni))
				throw new IllegalArgumentException("Erro ao votar proposta: Deputado não cadastrado");
		}
		
		int votosAprovar = controllerProjeto.getProjetos().get(codigo).contaVotos(codigo, statusGovernista, politicosPresentes, controller.getPartidos());

		if (codigo.substring(0,3).equals("PL ")) {
			if (votosAprovar >= Math.floor((presents.length / 2)) + 1) {
				controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("APROVADO");
				controllerPessoa.getPessoas().get(controllerProjeto.getProjetos().get(codigo).getAutor()).getDeputado().aprovouUmaLei();
				return true;
			}else 
				controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("ARQUIVADO");
		}
		
		if (codigo.substring(0,3).equals("PLP")) {
				if(votosAprovar  >= Math.floor((deputados / 2)) + 1) {
					if(controllerProjeto.getProjetos().get(codigo).getSituacaoAtual().equals("EM VOTACAO (Plenario - 1o turno)"))
						controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("EM VOTACAO (Plenario - 2o turno)");
					else if(controllerProjeto.getProjetos().get(codigo).getSituacaoAtual().equals("EM VOTACAO (Plenario - 2o turno)")) {
						controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("APROVADO");
						controllerPessoa.getPessoas().get(controllerProjeto.getProjetos().get(codigo).getAutor()).getDeputado().aprovouUmaLei();
					}
					return true;
				}
				else 
					controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("ARQUIVADO");
		}
		if(codigo.substring(0,3).equals("PEC")) {
			if(votosAprovar  >= Math.floor((3/5 * deputados)) + 1) {
				if(controllerProjeto.getProjetos().get(codigo).getSituacaoAtual().equals("EM VOTACAO (Plenario - 1o turno)"))
					controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("EM VOTACAO (Plenario - 2o turno)");
				else if(controllerProjeto.getProjetos().get(codigo).getSituacaoAtual().equals("EM VOTACAO (Plenario - 2o turno)")) {
					controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("APROVADO");
					controllerPessoa.getPessoas().get(controllerProjeto.getProjetos().get(codigo).getAutor()).getDeputado().aprovouUmaLei();
				}return true;
			}
			else 
				controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("ARQUIVADO");
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
		if ((controllerProjeto.getProjetos().get(codigo).getLocalAtual().equals("-")) || controllerProjeto.getProjetos().get(codigo).getSituacaoAtual().equals("ARQUIVADO") || controllerProjeto.getProjetos().get(codigo).getSituacaoAtual().equals("APROVADO")){
			throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
		}
		if(!controller.getComissoes().containsKey("CCJC"))
			throw new IllegalArgumentException("Erro ao votar proposta: CCJC nao cadastrada");
		
		if (controller.getComissoes().get(controllerProjeto.getProjetos().get(codigo).getLocalAtual()).getProjetosVotados().contains(codigo)) 
			throw new IllegalArgumentException("");
		
		if (controllerProjeto.getProjetos().get(codigo).getLocalAtual().equals("-")) 
			throw new IllegalArgumentException("");
		
		List<Pessoa> deputados = new ArrayList<>();
		String politicos[]  = controller.getComissoes().get(controllerProjeto.getProjetos().get(codigo).getLocalAtual()).getPoliticos().split(",");
		for(String p : politicos) {
			deputados.add(controllerPessoa.getPessoas().get(p));
		}
		int votosAprovados = controllerProjeto.getProjetos().get(codigo).contaVotos(codigo, statusGovernista, deputados, controller.getPartidos());
		if (codigo.substring(0,3).equals("PL ")) {
			PL projetoPL = (PL) controllerProjeto.getProjetos().get(codigo);
			if (projetoPL.isTramitacaoConclusiva() == false) {
				if (votosAprovados >= politicos.length / 2 + 1) {
					controller.getComissoes().get(controllerProjeto.getProjetos().get(codigo).getLocalAtual()).getProjetosVotados().add(codigo);
					controllerProjeto.getProjetos().get(codigo).setLocalAtual(proximoLocal);
					if ("plenario".equals(proximoLocal)) {
						controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("EM VOTACAO (Plenario - 1o turno)");
						return true;
					}
				
					controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("EM VOTACAO (" + proximoLocal + ")");
					return true;
				}
			
				if (proximoLocal.equals("plenario")) {
					controller.getComissoes().get(controllerProjeto.getProjetos().get(codigo).getLocalAtual()).getProjetosVotados().add(codigo);
					controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("EM VOTACAO (Plenario - 1o turno)");
					controllerProjeto.getProjetos().get(codigo).setLocalAtual(proximoLocal);
					return false;
				}
				controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("EM VOTACAO (" + proximoLocal + ")");
				controller.getComissoes().get(controllerProjeto.getProjetos().get(codigo).getLocalAtual()).getProjetosVotados().add(codigo);
				controllerProjeto.getProjetos().get(codigo).setLocalAtual(proximoLocal);
				return false;
			
		}
			else {
				if (votosAprovados >= politicos.length / 2 + 1) {
					controller.getComissoes().get(controllerProjeto.getProjetos().get(codigo).getLocalAtual()).getProjetosVotados().add(codigo);
					controllerProjeto.getProjetos().get(codigo).setLocalAtual(proximoLocal);
					if ("-".equals(proximoLocal)) {
						controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("APROVADO");
						controllerPessoa.getPessoas().get(controllerProjeto.getProjetos().get(codigo).getAutor()).getDeputado().aprovouUmaLei();
						return true;
					}
				
					controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("EM VOTACAO (" + proximoLocal + ")");
					return true;
				}
			
				if (proximoLocal.equals("-")) {
					controller.getComissoes().get(controllerProjeto.getProjetos().get(codigo).getLocalAtual()).getProjetosVotados().add(codigo);
					controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("ARQUIVADO");
					controllerProjeto.getProjetos().get(codigo).setLocalAtual(proximoLocal);
					return false;
				}
				controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("ARQUIVADO");
				controller.getComissoes().get(controllerProjeto.getProjetos().get(codigo).getLocalAtual()).getProjetosVotados().add(codigo);
				controllerProjeto.getProjetos().get(codigo).setLocalAtual(proximoLocal);
				return false;
			}
	}else {
		if (votosAprovados >= politicos.length / 2 + 1) {
			controller.getComissoes().get(controllerProjeto.getProjetos().get(codigo).getLocalAtual()).getProjetosVotados().add(codigo);
			controllerProjeto.getProjetos().get(codigo).setLocalAtual(proximoLocal);
			if ("plenario".equals(proximoLocal)) {
				controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("EM VOTACAO (Plenario - 1o turno)");
				return true;
			}
		
			controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("EM VOTACAO (" + proximoLocal + ")");
			return true;
		}
	
		if (proximoLocal.equals("plenario")) {
			controller.getComissoes().get(controllerProjeto.getProjetos().get(codigo).getLocalAtual()).getProjetosVotados().add(codigo);
			controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("EM VOTACAO (Plenario - 1o turno)");
			controllerProjeto.getProjetos().get(codigo).setLocalAtual(proximoLocal);
			return false;
		}
		controllerProjeto.getProjetos().get(codigo).setSituacaoAtual("EM VOTACAO (" + proximoLocal + ")");
		controller.getComissoes().get(controllerProjeto.getProjetos().get(codigo).getLocalAtual()).getProjetosVotados().add(codigo);
		controllerProjeto.getProjetos().get(codigo).setLocalAtual(proximoLocal);
		return false;
	}
	}
}

