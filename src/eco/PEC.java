package eco;
/**
 * Representacao de um projeto de Emenda Constitucional(PEC).
 */
public class PEC extends ProjetosConstitucionais{
	/**
	 * constroi um novo Projeto de Emenda Constitucional, criando o objeto PEC.
	 * @param autor dni do autor da PEC
	 * @param ano ano de criacaoo da PEC
	 * @param numeracao numero da PEC criada no devido ano
	 * @param ementa descricao do objetivo da PEC
	 * @param interesses ineresses relacionados a PEC criada
	 * @param url endereco eletronico da PEC
	 * @param artigos Artigos da constituição sendo emendados
	 */
	public PEC(String autor, int ano, int numeracao, String ementa, String interessesRelacionados,
			String endereçoDoDocumento, String artigos) {
		super(autor, ano, "PEC " + numeracao + "/" + ano, ementa, interessesRelacionados, endereçoDoDocumento, artigos);
	}

	@Override
	/**
	 * Representacao  de uma PEC no formato String.
	 */
	public String toString() {
		return "Projeto de Emenda Constitucional - " + getCodigo() + " - " + getAutor()  + " - " + getEmenta() + " - " + getArtigos().replace(",", ", ") + " - " + getSituaçãoAtual();
	}
	
}
