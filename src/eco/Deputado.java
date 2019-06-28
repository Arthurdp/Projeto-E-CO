package eco;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * classe que representa uma pessoa que tem o cargo de deputado.
 * responsavel por seus atributos e metodos.
 *
 */
public class Deputado{
	/**
	 * inicia um novo validador;
	 */
	Validador validador = new Validador();
	/**
	 * data do inicio da vida publica do deputado.
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
	
	/**
	 * metodo que exibe a data em que uma pessoa virou deputado.
	 * @return retorna a data em que o deputado foi cadastrado.
	 */
	public String getData() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.data);
	}

	/**
	 * metodo que informa a quantidade de leis aprovadas por um deputado.
	 * @return retorna um inteiro com a quantidade de leis aprovadas.
	 */
	public int getLeisAprovadas() {
		return leisAprovadas;
	}

	/**
	 * metodo que permite alterar a quantidade de leis aprovadas por um deputado.
	 * @param leisAprovadas nova quantidade de leis aprovadas.
	 */
	public void aprovouUmaLei() {
		this.leisAprovadas ++;
	}
}
