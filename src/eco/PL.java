package eco;

public class PL extends Projeto{
	private boolean tramitacaoConclusiva;

	public PL(String autor, int ano, int numeracao, String ementa, String interesses,
			String url, boolean conclusivo) {
		super(autor, ano, "PL " + numeracao + "/" + ano, ementa, interesses, url);
		this.tramitacaoConclusiva = conclusivo;
		super.tipo = "PL";
	}

	@Override
	public String toString() {
		return (this.tramitacaoConclusiva == true) ? "Projeto de Lei - " + getCodigo() + " - " + getAutor()  + " - " + getEmenta() + " - Conclusiva - " + 
				getSituacaoAtual() : "Projeto de Lei - " + getCodigo() + " - " + getAutor()  + " - " + getEmenta() + " - " + getSituacaoAtual();
	}
	
	
}
