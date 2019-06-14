package eco;

public class Comissao {
	
	private Validador validador = new Validador();
	/**
	 * tema da comissao
	 */
	private String tema;
	/**
	 * string contendo os dni dos politicos participantes da comissao
	 */
	private String politicos;
	
	/**
	 * Constroi uma nova comissao
	 * @param tema tema da comissao
	 * @param politicos string contendo os dni dos politicos participantes da comissao
	 */
	public Comissao(String tema, String politicos) {
		validador.validaEntrada(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
		validador.validaEntrada(politicos, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
		this.tema = tema;
		this.politicos = politicos;
	}
	
	
	
	
		
	}
	
