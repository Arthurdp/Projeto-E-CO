package eco;

import java.util.List;
import java.util.Map;

abstract class Projeto{
	private Validador validador;
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
		this.validador = new Validador();
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
	
	public void setConclusoes() {
		this.conclusoes++;
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
	
	abstract boolean votarPlenario(String estatusGovernista, List<Pessoa> politicos, List<Pessoa> politicosPresentes,List<String> partidos);
	
	abstract boolean votarComissao(String estatusGovernista, List<Pessoa> deputados, List<String> partidos, Map<String, Comissao> comissoes, String proximoLocal);
}
