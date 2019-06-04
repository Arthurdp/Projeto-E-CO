package eco;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * representação de uma pessoa que exerce a função de deputado.
 *
 */
public class Deputado implements Funcao{
	/**
	 * inicia um novo validador;
	 */
	Validador validador = new Validador();
	/**
	 * data do inicio da vida publica do deputado
	 */
	private Date data;
	/**
	 * numero de leis aprovadas pelo deputado.
	 */
	private int leisAprovadas;
	/**
	 * Constroi um novo deputado.
	 * @param data data do inicio da vida publica do novo deputado.
	 */

	public Deputado(String data) {
		validador.validaEntrada(data, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
		validador.validaData(data,"Erro ao cadastrar deputado: data invalida");
		validador.validaDataFutura(data, "Erro ao cadastrar deputado: data futura");
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		try {
			this.data = sdf.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.leisAprovadas = 0;
	}
	
	public String getData() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.data);
	}

	public int getLeisAprovadas() {
		return leisAprovadas;
	}

	public void setLeisAprovadas(int leisAprovadas) {
		this.leisAprovadas = leisAprovadas;
	}
}
