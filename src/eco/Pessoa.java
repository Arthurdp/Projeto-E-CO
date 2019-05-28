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
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((interresses == null) ? 0 : interresses.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((partido == null) ? 0 : partido.hashCode());
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
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (interresses == null) {
			if (other.interresses != null)
				return false;
		} else if (!interresses.equals(other.interresses))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (partido == null) {
			if (other.partido != null)
				return false;
		} else if (!partido.equals(other.partido))
			return false;
		return true;
	}

	public static void validaEntrada(String parametro, String mensagem) {
		if (parametro == null || parametro.trim().equals(""))
			throw new NullPointerException(mensagem);
	}
	
}
