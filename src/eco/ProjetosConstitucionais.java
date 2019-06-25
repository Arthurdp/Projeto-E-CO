package eco;

import java.util.List;
import java.util.Map;

abstract class ProjetosConstitucionais extends Projeto {
	private String artigos;
	public ProjetosConstitucionais(String dni, int ano, String codigo, String ementa, String interesses,
			String url, String artigos) {
		super(dni, ano, codigo, ementa, interesses, url);
		this.artigos = artigos;
	}
	
	public boolean votarComissao(String estatusGovernista, List<Pessoa> deputados, List<String> partidos, Map<String, Comissao> comissoes, String proximoLocal) {
		int votosAprovados = contaVotos(estatusGovernista, deputados, partidos);
		if (votosAprovados >= deputados.size() / 2 + 1) {
			this.PLsVotadas += "APROVADO (" + getLocalAtual() + "), ";
			this.tramitacao = this.PLsVotadas + this.situacaoAtual;
			comissoes.get(getLocalAtual()).getProjetosVotados().add(codigo);
			setLocalAtual(proximoLocal);
			if ("plenario".equals(proximoLocal)) {
				setSituacaoAtual("EM VOTACAO (Plenario - 1o turno)");
				return true;
			}
		
			setSituacaoAtual("EM VOTACAO (" + proximoLocal + ")");
			return true;
		}
		this.PLsVotadas += "REPROVADO (" + getLocalAtual() + "), ";
		this.tramitacao = this.PLsVotadas + this.situacaoAtual;
		if (proximoLocal.equals("plenario")) {
			comissoes.get(getLocalAtual()).getProjetosVotados().add(codigo);
			setSituacaoAtual("EM VOTACAO (Plenario - 1o turno)");
			setLocalAtual(proximoLocal);
			return false;
		}
		setSituacaoAtual("EM VOTACAO (" + proximoLocal + ")");
		comissoes.get(getLocalAtual()).getProjetosVotados().add(codigo);
		setLocalAtual(proximoLocal);
		return false;
	}
	
	public String getArtigos() {
		return this.artigos;
	}
	
}
