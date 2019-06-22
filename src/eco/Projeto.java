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
	private String situacaoAtual;
	private String url;
	protected String turno;
	protected String tipo;
	private String localAtual;
	
	public Projeto(String autor, int ano, String codigo, String ementa, String interesses, String url) {
		this.validador = new Validador();
		this.autor = autor;
		this.ano = ano;
		this.codigo = codigo;
		this.ementa = ementa;
		this.interesses = interesses;
		this.situacaoAtual = "EM VOTACAO (CCJC)";
		this.url = url;
		this.localAtual = "CCJC";
	}
	
	public String getAutor() {
		return autor;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getEmenta() {
		return ementa;
	}

	public String getSituacaoAtual() {
		return situacaoAtual;
	}
	
	public String getInteresses() {
		return interesses;
	}
	
	public String getTurno() {
		return this.turno;
	}
	
	public void setTurno(String novoTurno) {
		this.turno = novoTurno;
	}
	
	public String getTipo() {
		return this.tipo;
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
	
	public int contaVotos(String statusGovernista, List<Pessoa> politicos, List<String> partidos) {
		int votosAprovar = 0;
		for (Pessoa politico : politicos){
			if(statusGovernista.equals("GOVERNISTA")) {
				for(String partido : partidos) {
					if(partido.equals(politico.getPartido()))
						votosAprovar += 1;
				}
			}
			
			if(statusGovernista.equals("LIVRE")){				
				if(interessesComuns(politico.getInteresses(), interesses))
					votosAprovar += 1;
			}
		}
		return votosAprovar;
	} 
	
	abstract boolean votarPlenario(String estatusGovernista, List<Pessoa> politicos, int qntDeputados, List<String> partidos);
	
}
