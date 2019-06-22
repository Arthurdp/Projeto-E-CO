package eco;

import java.util.List;

public class PL extends Projeto{
	
	private boolean tramitacaoConclusiva;

	public PL(String autor, int ano, String codigo, String ementa, String interesses,
			String url, boolean conclusivo) {
		super(autor, ano, codigo, ementa, interesses, url);
		this.tramitacaoConclusiva = conclusivo;
		super.tipo = "PL";
	}
	@Override
	public boolean votarPlenario(String estatusGovernista, List<Pessoa> politicos, int qntDeputados, List<String> partidos) {
		
		if(politicos.size() < Math.floor((qntDeputados / 2)) + 1)
			throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
		
		if(getSituacaoAtual().equals("APROVADO") || getSituacaoAtual().equals("ARQUIVADO"))
			throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
		
		if(!getLocalAtual().equals("plenario"))
			throw new IllegalArgumentException("Erro ao votar proposta: tramitacao em comissao");
		
		int votosAprovar = contaVotos(estatusGovernista, politicos, partidos);
		
		if (votosAprovar >= Math.floor((qntDeputados / 2)) + 1) {
			setSituacaoAtual("APROVADO");
			return true;
		}else 
			setSituacaoAtual("ARQUIVADO");
		
		return false;
	}
	
	//public boolean votarComissao(String estatusGovernista, List )
	
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
