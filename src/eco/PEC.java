package eco;

public class PEC extends ProjetosConstitucionais{

	public PEC(String autor, int ano, int numeracao, String ementa, String interessesRelacionados,
			String endereçoDoDocumento, String artigos) {
		super(autor, ano, "PEC " + numeracao + "/" + ano, ementa, interessesRelacionados, endereçoDoDocumento, artigos);
	}

	@Override
	public String toString() {
		return "Projeto de Emenda Constitucional - " + getCodigo() + " - " + getAutor()  + " - " + getEmenta() + " - " + getArtigos().replace(",", ", ") + " - " + getSituaçãoAtual();
	}
	
}
