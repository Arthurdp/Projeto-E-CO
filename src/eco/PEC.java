package eco;

import java.util.List;

/**
 * Representacao de um projeto de Emenda Constitucional(PEC).
 */
public class PEC extends ProjetosConstitucionais{
	/**
	 * constroi um novo Projeto de Emenda Constitucional, criando o objeto PEC.
	 * @param dni dni do autor da PEC
	 * @param ano ano de criacaoo da PEC
	 * @param numeracao numero da PEC criada no devido ano
	 * @param ementa descricao do objetivo da PEC
	 * @param interesses ineresses relacionados a PEC criada
	 * @param url endereco eletronico da PEC
	 * @param artigos Artigos da constituicao sendo emendados
	 */
	public PEC(String dni, int ano, String codigo, String ementa, String interesses,
			String url, String artigos) {
		super(dni, ano, codigo, ementa, interesses, url, artigos);
		super.turno = "1o turno";
		super.tipo = "PEC";
	}

public boolean votarPlenario(String estatusGovernista, List<Pessoa> politicos, List<Pessoa> politicosPresentes, List<String> partidos) {
		
		if(politicosPresentes.size() < Math.floor(((3 * politicos.size()) / 5)) + 1) 
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
		}else {
			this.PLsVotadas += "REJEITADO (Plenario - 1o turno)";
			this.tramitacao = this.PLsVotadas;
			setSituacaoAtual("ARQUIVADO");
			setConclusoes();
			
		}
		return false;
	}
	
	@Override
	/**
	 * Representacao  de uma PEC no formato String.
	 */
	public String toString() {
		return "Projeto de Emenda Constitucional - " + this.codigo + " - " + getAutor()  + " - " + getEmenta() + " - " + getArtigos().replace(",", ", ") + " - " + getSituacaoAtual();
	}
	
}
