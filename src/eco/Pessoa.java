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
	private String[] interesses;
	/**
	 * partido ao qual a pessoa é filiada.
	 */
	private String partido;
	/**
	 * função exercida pela pessoa.
	 */
	private Funcao funcao;
	
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
		this.interesses = interesses.split(",");		
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
		this.interesses = interesses.split(",");
		this.partido = partido;
	}
	
	/**
	 * Transforma a pessoa em um deputado.
	 * @param data data de inicio na vida publica.
	 */
	public void virouDeputado(String data) {
		this.funcao = new Deputado(data);
	}
	/**
	 * Retorna uma string com todos os interesses da pessoa.
	 * @return
	 */
	public String exibirInteresses() {
		String interesses = "interesses: ";
		for (String e : this.interesses) {
			interesses += e + ", ";
		}
		return interesses.substring(0, interesses.length() - 2);
	}
	
	/**
	 * exibe as informações de uma pessoa ou de um deputado.
	 * @param dni identificação da pessoa ou deputado.
	 * @return
	 */
	public String exibirPessoa() {
		if (funcao == null) {
			if(this.partido == null) {
				if(this.interesses[0].trim().equals("")) {
					return this.nome + " - " + this.dni + " (" + this.estado + ")";
				}
				else
					return this.nome + " - " + this.dni + " (" + this.estado + ")" + " - " + exibirInteresses();
			}
			else
				if(this.interesses[0].trim().contentEquals(""))
					return this.nome + " - " + this.dni + " (" + this.estado + ")" + " - " + this.partido;
				
				else
					return this.nome + " - " + this.dni + " (" + this.estado + ")" + " - " + this.partido + " - " + exibirInteresses();
		}
		else {
			if(this.interesses[0].trim().equals(""))
				return "POL: " + this.nome + " - " + this.dni + " (" + this.estado + ")" + " - " + this.partido + " - " + this.funcao.getData() + " - " + this.funcao.getLeisAprovadas();									
			else
				return "POL: " + this.nome + " - " + this.dni + " (" + this.estado + ")" + " - " + this.partido + " - " + exibirInteresses() + " - " + this.funcao.getData() + " - " + this.funcao.getLeisAprovadas();									
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

	public String getNome() {
		return nome;
	}

	public String getDni() {
		return dni;
	}

	public String getEstado() {
		return estado;
	}

	public String[] getInteresses() {
		return interesses;
	}

	public String getPartido() {
		return partido;
	}

	public Funcao getFuncao() {
		return funcao;
	}
	
	
}

