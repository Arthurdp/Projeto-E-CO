package eco;
/**
 * representação de uma pessoa que exerce a função de deputado.
 *
 */
public class Deputado implements Funcao{
	/**
	 * data do inicio da vida publica do deputado
	 */
	private String data;
	/**
	 * numero de leis aprovadas pelo deputado.
	 */
	private int leisAprovadas;
	/**
	 * Constroi um novo deputado.
	 * @param data data do inicio da vida publica do novo deputado.
	 */
	public Deputado(String data) {
		this.data = data;
		this.leisAprovadas = 0;
	}
	
	public String getData() {
		return data;
	}

	public int getLeisAprovadas() {
		return leisAprovadas;
	}

	public void setLeisAprovadas(int leisAprovadas) {
		this.leisAprovadas = leisAprovadas;
	}
}
