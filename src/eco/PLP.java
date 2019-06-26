package eco;

import java.util.List;

/**
 * Representacao de um projeto de lei complementar(PLP).
 */
public class PLP extends ProjetosConstitucionais{
	/**
	 * Constroi um novo projeto de lei complementar, criando o objeto PLP.
	 * @param dni dni do autor do PLP
	 * @param ano ano de criacaoo do PLP
	 * @param numeracao numero do PLP criada no devido ano
	 * @param ementa  descricao do objetivo do PLP
	 * @param interesses ineresses relacionados ao PLP criado
	 * @param url endereco eletronico do PLP
	 * @param artigos Artigos da constituicao sendo complementados
	 */
	public PLP(String dni, int ano, String codigo, String ementa, String interesses,
			String url, String artigos) {
		super(dni, ano, codigo, ementa, interesses, url, 10, artigos);
		super.turno = "1o turno";
		super.tipo = "PLP";
		this.tramitacao = this.situacaoAtual;
	}
	
	public boolean votarPlenario(String estatusGovernista, List<Pessoa> politicos, List<Pessoa> politicosPresentes, List<String> partidos) {
		
		if(politicosPresentes.size() < Math.floor((politicos.size() / 2)) + 1)
			throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
		
		if(getSituacaoAtual().equals("APROVADO") || getSituacaoAtual().equals("ARQUIVADO"))
			throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
		
		if(!getLocalAtual().equals("plenario"))
			throw new IllegalArgumentException("Erro ao votar proposta: tramitacao em comissao");
		
		int votosAprovar = contaVotos(estatusGovernista, politicosPresentes, partidos);
		
		if (votosAprovar >= Math.floor((politicosPresentes.size() / 2)) + 1) {
			if(getSituacaoAtual().equals("EM VOTACAO (Plenario - 1o turno)")) {
				setSituacaoAtual("EM VOTACAO (Plenario - 2o turno)");
				this.PLsVotadas += "APROVADO (Plenario - 1o turno), ";
				this.tramitacao = this.PLsVotadas + this.situacaoAtual;
				setConclusoes();
				setAprovacoes();
				return true;
			}
			else if(getSituacaoAtual().equals("EM VOTACAO (Plenario - 2o turno)")) {
				this.tramitacao = this.PLsVotadas + "APROVADO (Plenario - 2o turno)";
				setSituacaoAtual("APROVADO");
				setConclusoes();
				setAprovacoes();
				return true;
			}
			
		}
		else {
			this.PLsVotadas += "REJEITADO (Plenario - 1o turno)";
			this.tramitacao = this.PLsVotadas;
			
			setSituacaoAtual("ARQUIVADO");
			setConclusoes();
		}
		return false;
	}
	
	@Override
	/**
	 * Representacao  de um PLP no formato String.
	 */
	public String toString() {
		return "Projeto de Lei Complementar - " + this.codigo + " - " + getAutor()  + " - " + getEmenta() + " - " + getArtigos() + " - " + getSituacaoAtual();
	}
}
