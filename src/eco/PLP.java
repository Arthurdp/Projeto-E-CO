package eco;
/**
 * Representacao de um projeto de lei complementar(PLP).
 */
public class PLP extends ProjetosConstitucionais{
	/**
	 * Constroi um novo projeto de lei complementar, criando o objeto PLP.
	 * @param autor dni do autor do PLP
	 * @param ano ano de criacaoo do PLP
	 * @param numeracao numero do PLP criada no devido ano
	 * @param ementa  descricao do objetivo do PLP
	 * @param interessesRelacionados ineresses relacionados ao PLP criado
	 * @param endereçoDoDocumento endereco eletronico do PLP
	 * @param artigos Artigos da constituição sendo complementados
	 */
	public PLP(String autor, int ano, int numeracao, String ementa, String interessesRelacionados,
			String endereçoDoDocumento, String artigos) {
		super(autor, ano, "PLP " + numeracao + "/" + ano, ementa, interessesRelacionados, endereçoDoDocumento, artigos);
	}
	
	@Override
	/**
	 * Representacao  de um PLP no formato String.
	 */
	public String toString() {
		return "Projeto de Lei Complementar - " + getCodigo() + " - " + getAutor()  + " - " + getEmenta() + " - " + getArtigos() + " - " + getSituaçãoAtual();
	}
}
