package eco;

import java.util.List;
import java.util.Map;
/**
 * Classe abstrata que representas os projetos de lei considerados constitucionais, sendo eles PEC e PLP.
 *
 */
abstract class ProjetosConstitucionais extends Projeto {
	/**
	 * Artigos da constituicao relacionados ao projeto de lei constitucional
	 */
	private String artigos;
	
	public ProjetosConstitucionais(String dni, int ano, String codigo, String ementa, String interesses,
			String url, int prioridade, String artigos) {
		super(dni, ano, codigo, ementa, interesses, url, prioridade);
		this.artigos = artigos;
	}
	
	public boolean votarComissao(String estatusGovernista, List<Pessoa> deputados, List<String> partidos, Map<String, Comissao> comissoes, String proximoLocal) {
		int votosAprovados = contaVotos(estatusGovernista, deputados, partidos);
		if (votosAprovados >= Math.floor((deputados.size() / 2)) + 1) {
			this.PLsVotadas += "APROVADO (" + getLocalAtual() + "), ";
			
			comissoes.get(getLocalAtual()).getProjetosVotados().add(codigo);
			setLocalAtual(proximoLocal);
			if ("plenario".equals(proximoLocal)) {
				setSituacaoAtual("EM VOTACAO (Plenario - 1o turno)");
				this.tramitacao = this.PLsVotadas + this.situacaoAtual;
				setConclusoes(500);
				setAprovacoes();
				return true;
			}
			setConclusoes(1);
			setAprovacoes();
			setSituacaoAtual("EM VOTACAO (" + proximoLocal + ")");
			this.tramitacao = this.PLsVotadas + this.situacaoAtual;
			return true;
		}
		this.PLsVotadas += "REPROVADO (" + getLocalAtual() + "), ";
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
	
	public String getArtigos() {
		return this.artigos;
	}
	
}
