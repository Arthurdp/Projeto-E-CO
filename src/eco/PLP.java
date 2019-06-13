package eco;

public class PLP extends ProjetosConstitucionais{

	public PLP(String autor, int ano, int numeracao, String ementa, String interessesRelacionados,
			String endereçoDoDocumento, String artigos) {
		super(autor, ano, "PLP " + numeracao + "/" + ano, ementa, interessesRelacionados, endereçoDoDocumento, artigos);
	}
	
	@Override
	public String toString() {
		return "Projeto de Lei Complementar - " + getCodigo() + " - " + getAutor()  + " - " + getEmenta() + " - " + getArtigos() + " - " + getSituaçãoAtual();
	}
}