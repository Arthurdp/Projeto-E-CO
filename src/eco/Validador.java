package eco;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
/**
 * Classe que realiza a verificacao e valida um determinado parametro passado ao mesmo
 * de acordo com o modo de verificacao desejado.
 *
 */
public class Validador{
	
	/**
	 * verifica se a entrada eh vazia ou nula.
	 * @param parametro parametro a ser avaliado.
	 * @param mensagem mensagem de erro.
	 */
	public void validaEntrada(String parametro, String mensagem) {
		if (parametro == null)
			throw new NullPointerException(mensagem);
		
		if (parametro.trim().equals(""))
			throw new IllegalArgumentException(mensagem);
	}
	
	/**
	 * verifica se o dni passado na construcao de uma nova pessoa eh valido.
	 * @param dni dni a ser avaliado.
	 * @param msg mensagem de erro.
	 */
	public void validaDni(String dni, String msg) {
		if(dni.trim().length() > 11 || dni.trim().length() < 11 )
			throw new IllegalArgumentException(msg);
		if(!dni.split("")[9].equals("-"))
			throw new IllegalArgumentException(msg);
		String[] dados = dni.split("");
		for(String digito : dados) {
			if(!digito.equals("-")) {
				if(!digito.matches("[0-9]"))
					throw new IllegalArgumentException(msg); 
			}
				
		}
		
	}
	/**
	 * verifica se a data eh valida.
	 * @param data1 data a ser verificada.
	 * @param msg mensagem de erro.
	 */
	public void validaData(String data1, String msg) {
		if(data1.length() != 8)
			throw new IllegalArgumentException(msg);
		
		if(!validaDataFormato(data1))
			throw new IllegalArgumentException(msg);
		
		String[] numeros = data1.split("");
		for(String digito : numeros) {
			if(!digito.equals("-")) 
				if(!digito.matches("[0-9]"))
					throw new IllegalArgumentException(msg);
		}
	}
	
	/**
	 * verifica se a data eh futura.
	 * @param data1 data a ser verificada.
	 * @param msg mensagem de erro.
	 */
	public void validaDataFutura(String data1, String msg) {
		Date hoje = new Date();
		Date data2 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		try {
			data2 = sdf.parse(data1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(data2.after(hoje))
			throw new IllegalArgumentException(msg);
	}
	
	/**
	 * verifica se o formato da data eh valido.
	 * @param data data a ser verificada.
	 * @return retorna true se for valido.
	 */
	public static boolean validaDataFormato(String data) {
	    String dateFormat = "ddMMuuuu";
	    DateTimeFormatter dtf = DateTimeFormatter
	    .ofPattern(dateFormat)
	    .withResolverStyle(ResolverStyle.STRICT);
	    try {
	        LocalDate date = LocalDate.parse(data, dtf);
	        return true;
	    } catch (DateTimeParseException e) {
	       return false;
	    } 
	}
}
