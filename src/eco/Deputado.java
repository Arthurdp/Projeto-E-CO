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
		validador(data);
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
	
	public void validador(String data1) {
		if(data1.length() != 8)
			throw new IllegalArgumentException("Erro ao cadastrar deputado: data invalida");
		
		String[] numeros = data1.split("");
		for(String digito : numeros) {
			if(!digito.equals("-")) 
				if(!digito.matches("[0-9]"))
					throw new IllegalArgumentException("Erro ao cadastrar deputado: data invalida");
		}
		
		if(data1.trim().equals(""))
			throw new NullPointerException("Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
		
		
		Date hoje = new Date();
		Date data2 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		try {
			data2 = sdf.parse(data1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(data2.after(hoje))
			throw new IllegalArgumentException("Erro ao cadastrar deputado: data futura");
	}
}
