package eco;
/**
 * Representação de uma pessoa.
 *
 */
public class Pessoa {
	/**
	 * inicia um novo validador;
	 */
	Validador validador = new Validador();
	/**
	 * nome da pessoa.
	 */
	private String nome;
	/**
	 * numero de identificação da da pessoa.
	 */
	private String dni;
	/**
	 * estado de origem da pessoa.
	 */
	private String estado;
	/**
	 * array de interesses da pessoa
	 */
	private String interesses;
	/**
	 * partido ao qual a pessoa é filiada.
	 */
	private String partido;
	/**
	 * função exercida pela pessoa.
	 */
	private Deputado deputado;
	
	/**
	 * Constroi uma nova pessoa que não esteja filiada a nenhum partido.
	 * @param nome nome da nova pessoa.
	 * @param dni numero de identificação da nova pessoa.
	 * @param estado estado de origem da nova pessoa.
	 * @param interresses interesses da nova pessoa.
	 */
	public Pessoa(String nome, String dni, String estado, String interesses) {
		validador.validaEntrada(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		validador.validaEntrada(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		validador.validaEntrada(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar pessoa: dni invalido");
		this.nome = nome;
		this.dni = dni;
		this.estado = estado;
		this.interesses = interesses;
	}
	
	/**
	 * Constroi uma nova pessoa que esteja filiada a nenhum partido.
	 * @param nome nome da nova pessoa.
	 * @param dni numero de identificação da nova pessoa.
	 * @param estado estado de origem da nova pessoa.
	 * @param interresses interesses da nova pessoa.
	 * @param partido partido ao qual a nova pessoa é filiada.
	 */
	public Pessoa(String nome, String dni, String estado, String interesses,String partido) {
		validador.validaEntrada(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		validador.validaEntrada(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		validador.validaEntrada(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar pessoa: dni invalido");
		this.nome = nome;
		this.dni = dni;
		this.estado = estado;
		this.interesses = interesses;
		this.partido = partido;
	}
	
	/**
	 * Transforma a pessoa em um deputado.
	 * @param data data de inicio na vida publica.
	 */
	public void virouDeputado(String data) {
		this.deputado = new Deputado(data);
	}
	
	/**
	 * exibe as informações de uma pessoa ou de um deputado.
	 * @param dni identificação da pessoa ou deputado.
	 * @return
	 */
	public String exibirPessoa() {
		if (deputado == null) {
			if(this.partido == null) {
				if(this.interesses.trim().equals("")) {
					return this.nome + " - " + this.dni + " (" + this.estado + ")";
				}
				else
					return this.nome + " - " + this.dni + " (" + this.estado + ")" + " - Interesses: " + this.interesses;
			}
			else
				if(this.interesses.trim().contentEquals(""))
					return this.nome + " - " + this.dni + " (" + this.estado + ")" + " - " + this.partido;
				
				else
					return this.nome + " - " + this.dni + " (" + this.estado + ")" + " - " + this.partido + " - Interesses: " + this.interesses;
		}
		else {
			if(this.interesses.trim().equals(""))
				return "POL: " + this.nome + " - " + this.dni + " (" + this.estado + ")" + " - " + this.partido + " - " + this.deputado.getData() + " - " + this.deputado.getLeisAprovadas() + " Leis";									
			else
				return "POL: " + this.nome + " - " + this.dni + " (" + this.estado + ")" + " - " + this.partido + " - Interesses: " + this.interesses + " - " + this.deputado.getData() + " - " + this.deputado.getLeisAprovadas() + " Leis";									
		}
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	
	public String getPartido() {
		return this.partido;
	}
}

