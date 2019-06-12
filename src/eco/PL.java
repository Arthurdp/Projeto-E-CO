package eco;

public class PL extends ProjetoDeLei{
	private boolean tramitacaoConclusiava;

	public PL(String autor, int ano, int numeracao, String ementa, String interessesRelacionados,
			String endereçoDoDocumento, boolean conclusivo) {
		super(autor, ano, "PL " + numeracao +"/" + ano, ementa, interessesRelacionados, endereçoDoDocumento);
		this.tramitacaoConclusiava = conclusivo;
	}

	@Override
	public String toString() {
		return "Projeto de Lei - " + getCodigo() + " - " + getAutor()  + " - " + getEmenta() + " - " + this.tramitacaoConclusiava + " - " + getSituaçãoAtual();
	}
	
	
}
