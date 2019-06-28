package eco;

import java.util.List;
import java.util.Map;

public class PL extends Projeto{
	
	private boolean tramitacaoConclusiva;

	public PL(String autor, int ano, String codigo, String ementa, String interesses,
			String url, boolean conclusivo) {
		super(autor, ano, codigo, ementa, interesses, url, 15);
		this.tramitacaoConclusiva = conclusivo;
		super.tipo = "PL";
		this.tramitacao = this.situacaoAtual;
	}
	@Override
	public boolean votarPlenario(String estatusGovernista, List<Pessoa> politicos,List<Pessoa> politicosPresentes, List<String> partidos) {
		
		if( politicosPresentes.size() < Math.floor((politicos.size() / 2)) + 1)
			throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
		
		if(getSituacaoAtual().equals("APROVADO") || getSituacaoAtual().equals("ARQUIVADO"))
			throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
		
		if(!getLocalAtual().equals("plenario"))
			throw new IllegalArgumentException("Erro ao votar proposta: tramitacao em comissao");
		
		int votosAprovar = contaVotos(estatusGovernista, politicosPresentes, partidos);
		
		if (votosAprovar >= Math.floor((politicosPresentes.size() / 2)) + 1) {
			this.PLsVotadas += "APROVADO (Plenario), ";
			this.tramitacao = this.PLsVotadas + this.situacaoAtual;
			setSituacaoAtual("APROVADO");
			setAprovacoes();
			return true;
		}else { 
			this.PLsVotadas += "REJEITADO (Plenario)";
			this.tramitacao = this.PLsVotadas;
			setSituacaoAtual("ARQUIVADO");
		}
		return false;
	}
	
	public boolean votarComissao(String estatusGovernista, List<Pessoa> deputados, List<String> partidos, Map<String, Comissao> comissoes, String proximoLocal) {
		int votosAprovados = contaVotos(estatusGovernista, deputados, partidos);
		if(!tramitacaoConclusiva) {
			
			if (votosAprovados >= Math.floor((deputados.size() / 2)) + 1) {
				this.PLsVotadas += "APROVADO (" + getLocalAtual() + "), ";
				this.tramitacao = this.PLsVotadas + this.situacaoAtual;
					
				
				comissoes.get(getLocalAtual()).getProjetosVotados().add(codigo);
				setLocalAtual(proximoLocal);
				
				if ("plenario".equals(proximoLocal)) {
					setSituacaoAtual("EM VOTACAO (Plenario - 1o turno)");
					this.tramitacao = this.PLsVotadas + "EM VOTACAO (Plenario)";
					setConclusoes(1000);
					return true;
				}
			
				setSituacaoAtual("EM VOTACAO (" + proximoLocal + ")");
				setConclusoes(1);
				setAprovacoes();
				return true;
			}
			this.PLsVotadas += "REJEITADO (" + getLocalAtual() + "), ";
			this.tramitacao = this.PLsVotadas + this.situacaoAtual;
			if (proximoLocal.equals("plenario")) { 
				comissoes.get(getLocalAtual()).getProjetosVotados().add(codigo);
				setSituacaoAtual("EM VOTACAO (Plenario - 1o turno)");
				setLocalAtual(proximoLocal);
				setConclusoes(500);
				return false;
			}
			setSituacaoAtual("EM VOTACAO (" + proximoLocal + ")");
			comissoes.get(getLocalAtual()).getProjetosVotados().add(codigo);
			setLocalAtual(proximoLocal);
			setConclusoes(1);
			return false;
		}
		
		else {
			if (votosAprovados >= deputados.size() / 2 + 1) {
				this.PLsVotadas += "APROVADO (" + getLocalAtual() + "), ";
				comissoes.get(getLocalAtual()).getProjetosVotados().add(codigo);
				setLocalAtual(proximoLocal);
				this.tramitacao = this.PLsVotadas.substring(0, PLsVotadas.length()-2);
				
				
				if ("-".equals(proximoLocal)) {
					setSituacaoAtual("APROVADO");
					for(Pessoa deputado : deputados) {
						if(deputado.getDni().equals(getAutor()))
							deputado.getDeputado().aprovouUmaLei();
					}
					return true;
				}
				setConclusoes(1);
				setAprovacoes();
				setSituacaoAtual("EM VOTACAO (" + proximoLocal + ")");
				return true;
			}
			this.PLsVotadas += "REJEITADO (" + getLocalAtual() + ")";
			this.tramitacao = this.PLsVotadas;
			if (proximoLocal.equals("-")) {
				comissoes.get(getLocalAtual()).getProjetosVotados().add(codigo);
				setSituacaoAtual("ARQUIVADO");
				setLocalAtual(proximoLocal);
				return false;
			}
			setSituacaoAtual("ARQUIVADO");
			comissoes.get(getLocalAtual()).getProjetosVotados().add(codigo);
			setLocalAtual(proximoLocal);
			return false;
		}
		
	}
	
	@Override
	public String toString() {
		return (this.tramitacaoConclusiva == true) ? "Projeto de Lei - " + this.codigo + " - " + getAutor()  + " - " + getEmenta() + " - Conclusiva - " + 
				getSituacaoAtual() : "Projeto de Lei - " + this.codigo + " - " + getAutor()  + " - " + getEmenta() + " - " + getSituacaoAtual();
	}

	/**
	 * @return the tramitacaoConclusiva
	 */
	public boolean isTramitacaoConclusiva() {
		return tramitacaoConclusiva;
	}
	
	
}
