package eco;
/**
 * Representação de uma pessoa.
 *
 */
public class Pessoa {
	/**
	 * nome da pessoa.
	 */
	protected String nome;
	/**
	 * numero de identificação da da pessoa.
	 */
	protected String dni;
	/**
	 * estado de origem da pessoa.
	 */
	protected String estado;
	/**
	 * array de interesses da pessoa
	 */
	protected String[] interesses;
	/**
	 * partido ao qual a pessoa é filiada.
	 */
	protected Partido partido;
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
	public Pessoa(String nome, String dni, String estado, String interresses) {
		validaEntrada(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		validaEntrada(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		validaEntrada(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		validaDni(dni, "Erro ao cadastrar pessoa: dni invalido");
		this.nome = nome;
		this.dni = dni;
		this.estado = estado;
		this.interesses = interresses.split(",");		
	}
	
	/**
	 * Constroi uma nova pessoa que esteja filiada a nenhum partido.
	 * @param nome nome da nova pessoa.
	 * @param dni numero de identificação da nova pessoa.
	 * @param estado estado de origem da nova pessoa.
	 * @param interresses interesses da nova pessoa.
	 * @param partido partido ao qual a nova pessoa é filiada.
	 */
	public Pessoa(String nome, String dni, String estado, String interresses, Partido partido) {
		validaEntrada(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		validaEntrada(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		validaEntrada(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		validaDni(dni, "Erro ao cadastrar pessoa: dni invalido");
		this.nome = nome;
		this.dni = dni;
		this.estado = estado;
		this.interesses = interresses.split(",");
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
	protected String exibirInteresses() {
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
	public String exibirPessoa(String dni) {
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
	/**
	 * verifica se a entrada é vazia ou nula.
	 * @param parametro parametro a ser avaliado.
	 * @param mensagem mensagem de erro.
	 */
	public static void validaEntrada(String parametro, String mensagem) {
		if (parametro == null)
			throw new NullPointerException(mensagem);
		
		if (parametro.trim().equals(""))
			throw new IllegalArgumentException(mensagem);
	}
	/**
	 * verifica se o dni passado na construção de uma nova pessoa é valido.
	 * @param dni dni a ser avaliado.
	 * @param msg mensagem de erro.
	 */
	static void validaDni(String dni, String msg) {
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
	
}

