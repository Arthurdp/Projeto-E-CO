package eco;
/**
 * Representacao de um projeto de lei(PL).
 *
 */
public class PL extends Projeto{
	/**
	 * booleano que informa se a PL eh conclusiva ou nao conclusiva.
	 */
	private boolean tramitacaoConclusiva;
	/**
	 * Constroi um novo projeto de lei criando o objeto PL.
	 * @param dni dni do autor do PL
	 * @param ano ano de criacaoo do PL
	 * @param numeracao numero do PL criada no devido ano
	 * @param ementa  descricao do objetivo do PL
	 * @param interesses ineresses relacionados ao PL criado
	 * @param url endereco eletronico do PL
	 * @param conclusivo informa se a PL eh conclusiva ou nao conclusiva
	 */
	public PL(String dni, int ano, int numeracao, String ementa, String interesses,
			String url, boolean conclusivo) {
		super(dni, ano, "PL " + numeracao + "/" + ano, ementa, interesses, url);
		this.tramitacaoConclusiva = conclusivo;
	}

	@Override
	/**
	 * Representacao  de um PL no formato String.
	 */
	public String toString() {
		return (this.tramitacaoConclusiva == true) ? "Projeto de Lei - " + getCodigo() + " - " + getDni()  + " - " + getEmenta() + " - Conclusiva - " + getSituaçãoAtual() : "Projeto de Lei - " + getCodigo() + " - " + getDni()  + " - " + getEmenta() + " - " + getSituaçãoAtual();
	}
	
	
}
