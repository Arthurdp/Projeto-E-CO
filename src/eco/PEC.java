package eco;
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
	public PEC(String dni, int ano, int numeracao, String ementa, String interesses,
			String url, String artigos) {
		super(dni, ano, "PEC " + numeracao + "/" + ano, ementa, interesses, url, artigos);
		super.turno = "1o turno";
		super.tipo = "PEC";
	}

	@Override
	/**
	 * Representacao  de uma PEC no formato String.
	 */
	public String toString() {
		return "Projeto de Emenda Constitucional - " + getCodigo() + " - " + getAutor()  + " - " + getEmenta() + " - " + getArtigos().replace(",", ", ") + " - " + getSituacaoAtual();
	}
	
}
