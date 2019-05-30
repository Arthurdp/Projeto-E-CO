package eco;

public class Pessoa {

	private String nome;
	private String dni;
	private String estado;
	private String interresses;
	private String partido;
	
	public Pessoa(String nome, String dni, String estado, String interresses, String partido) {
		validaEntrada(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		validaEntrada(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		validaEntrada(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		this.nome = nome;
		this.dni = dni;
		this.estado = estado;
		this.interresses = interresses;
		this.partido = partido;
	}

	public Pessoa(String nome, String dni, String estado, String interresses) {
		validaEntrada(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		validaEntrada(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		validaEntrada(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		this.nome = nome;
		this.dni = dni;
		this.estado = estado;
		this.interresses = interresses;
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

	public static void validaEntrada(String parametro, String mensagem) {
		if (parametro == null || parametro.trim().equals(""))
			throw new NullPointerException(mensagem);
	}
	
}
