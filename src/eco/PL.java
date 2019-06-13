package eco;

public class PL extends Projeto{
	private boolean tramitacaoConclusiva;

	public PL(String autor, int ano, int numeracao, String ementa, String interessesRelacionados,
			String endereçoDoDocumento, boolean conclusivo) {
		super(autor, ano, "PL " + numeracao + "/" + ano, ementa, interessesRelacionados, endereçoDoDocumento);
		this.tramitacaoConclusiva = conclusivo;
	}

	@Override
	public String toString() {
		return (this.tramitacaoConclusiva == true) ? "Projeto de Lei - " + getCodigo() + " - " + getAutor()  + " - " + getEmenta() + " - Conclusiva - " + getSituaçãoAtual() : "Projeto de Lei - " + getCodigo() + " - " + getAutor()  + " - " + getEmenta() + " - " + getSituaçãoAtual();
	}
	
	
}
