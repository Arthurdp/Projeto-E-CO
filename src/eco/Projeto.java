package eco;

import java.util.List;
import java.util.Map;

abstract class Projeto{
	private String autor;
	private int ano;
	protected String codigo;
	private String ementa;
	private String interesses;
	protected String situacaoAtual;
	private String url;
	protected String turno;
	protected String tipo;
	private String localAtual;
	protected String PLsVotadas;
	protected String tramitacao;
	private int conclusoes;
	private int aprovacoes;
	private int prioridade;
	
	public Projeto(String autor, int ano, String codigo, String ementa, String interesses, String url, int prioridade) {
		this.autor = autor;
		this.ano = ano;
		this.codigo = codigo;
		this.ementa = ementa;
		this.interesses = interesses;
		this.situacaoAtual = "EM VOTACAO (CCJC)";
		this.url = url;
		this.localAtual = "CCJC";
		this.PLsVotadas = "";
		this.tramitacao = "";
		this.conclusoes = 0;
		this.aprovacoes = 0;
		this.prioridade = prioridade;
	}
	
	public String getAutor() {
		return autor;
	}

	public String getEmenta() {
		return ementa;
	}
	
	public int getAno() {
		return ano;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public String getSituacaoAtual() {
		return situacaoAtual;
	}
	
	public String getInteresses() {
		return interesses;
	}
	
	public int getConclusoes() {
		return conclusoes;
	}
	
	public int getAprovacoes() {
		return aprovacoes;
	}
	
	public int getPrioridade() {
		return this.prioridade;
	}

	/**
	 * @return the localAtual
	 */
	public String getLocalAtual() {
		return localAtual;
	}
	
	/**
	 * @param localAtual the localAtual to set
	 */
	public void setLocalAtual(String localAtual) {
		this.localAtual = localAtual;
	}

	/**
	 * @param situacaoAtual the situacaoAtual to set
	 */
	public void setSituacaoAtual(String situacaoAtual) {
		this.situacaoAtual = situacaoAtual;
	}
	
	public void setConclusoes(int n) {
		this.conclusoes += n;
	}
	
	public void setAprovacoes() {
		this.aprovacoes ++;
	}
	
	public String getTramitacao() {
		return this.tramitacao;
	}

	public boolean interessesComuns(String interesses) {
		String[] i1 = interesses.split(",");
		String[] i2 = this.interesses.split(",");
		
		for (String i : i1) {
			for(String j : i2) {
				if (i.equals(j))
					return true;
			}
		}
		return false;
	}
	/**
	 * Conta os votos que foram aprovados recebendo status e uma lista contendo os politicos cadastrados e uma lista de partidos.
	 * Retorna um inteiro que representa a quantidade de votos aprovados.
	 @param statusGovernista status GOVERNISTA, LIVRE OU OPOSICAO
	 * @param politicos lista de todos os politicos cadastrados
	 * @param partidos lista de todos os partidos cadastrados
	 * @return retorna um inteiro
	 */
	public int contaVotos(String statusGovernista, List<Pessoa> politicos, List<String> partidos) {
		int votosAprovar = 0;
		for (Pessoa politico : politicos){
			if(statusGovernista.equals("GOVERNISTA")) {
				if(partidos.contains(politico.getPartido()))
						votosAprovar += 1;
			}
			
			if(statusGovernista.equals("OPOSICAO")) {
				if(!partidos.contains(politico.getPartido()))
						votosAprovar += 1;
			}
			else if(statusGovernista.equals("LIVRE")){				
				if(interessesComuns(politico.getInteresses()))
					votosAprovar += 1;
			}
			
		
		}
		return votosAprovar;
	} 
	/**
	 * Realiza a votacao de um projeto de lei pelo plenario
	 @param statusGovernista status governista do projeto de lei
	 * @param politicos lista de politicos cadastrados
	 * @param politicosPresentes lista de politicos presentes no plenario
	 * @param partidos lista de partidos cadastrados
	 * @return retorna um booleano, true se aprovada e false se reprovada
	 */
	abstract boolean votarPlenario(String estatusGovernista, List<Pessoa> politicos, List<Pessoa> politicosPresentes,List<String> partidos);
	/**Realiza a votação de um projeto de lei em uma commisao, sendo obrigatoria a votacao do projeto primeiramente
	 * pela CCJC, podendo ou nao seguir para outras comissoes.
	 * @param estatusGovernista status governista do projeto de lei
	 * @param deputados lista de deputados que compoem a comissao
	 * @param partidos lista de partidos cadastrados
	 * @param comissoes mapa de commisoes cadastradas
	 * @param proximoLocal proximo local em que o projeto de lei sera votado
	 * @return retorna um booleano, true se aprovada e false se reprovada
	 */
	abstract boolean votarComissao(String estatusGovernista, List<Pessoa> deputados, List<String> partidos, Map<String, Comissao> comissoes, String proximoLocal);
}
