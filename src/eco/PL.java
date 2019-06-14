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
	 * @param autor dni do autor do PL
	 * @param ano ano de criacaoo do PL
	 * @param numeracao numero do PL criada no devido ano
	 * @param ementa  descricao do objetivo do PL
	 * @param interessesRelacionados ineresses relacionados ao PL criado
	 * @param endereçoDoDocumento endereco eletronico do PL
	 * @param conclusivo informa se a PL eh conclusiva ou nao conclusiva
	 */
	public PL(String autor, int ano, int numeracao, String ementa, String interessesRelacionados,
			String endereçoDoDocumento, boolean conclusivo) {
		super(autor, ano, "PL " + numeracao + "/" + ano, ementa, interessesRelacionados, endereçoDoDocumento);
		this.tramitacaoConclusiva = conclusivo;
	}

	@Override
	/**
	 * Representacao  de um PL no formato String.
	 */
	public String toString() {
		return (this.tramitacaoConclusiva == true) ? "Projeto de Lei - " + getCodigo() + " - " + getAutor()  + " - " + getEmenta() + " - Conclusiva - " + getSituaçãoAtual() : "Projeto de Lei - " + getCodigo() + " - " + getAutor()  + " - " + getEmenta() + " - " + getSituaçãoAtual();
	}
	
	
}
