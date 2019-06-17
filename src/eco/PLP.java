package eco;
/**
 * Representacao de um projeto de lei complementar(PLP).
 */
public class PLP extends ProjetosConstitucionais{
	/**
	 * Constroi um novo projeto de lei complementar, criando o objeto PLP.
	 * @param dni dni do autor do PLP
	 * @param ano ano de criacaoo do PLP
	 * @param numeracao numero do PLP criada no devido ano
	 * @param ementa  descricao do objetivo do PLP
	 * @param interesses ineresses relacionados ao PLP criado
	 * @param url endereco eletronico do PLP
	 * @param artigos Artigos da constituicao sendo complementados
	 */
	public PLP(String dni, int ano, String codigo, String ementa, String interesses,
			String url, String artigos) {
		super(dni, ano, codigo, ementa, interesses, url, artigos);
		super.turno = "1o turno";
		super.tipo = "PLP";
	}
	
	@Override
	/**
	 * Representacao  de um PLP no formato String.
	 */
	public String toString() {
		return "Projeto de Lei Complementar - " + this.codigo + " - " + getAutor()  + " - " + getEmenta() + " - " + getArtigos() + " - " + getSituacaoAtual();
	}
}
