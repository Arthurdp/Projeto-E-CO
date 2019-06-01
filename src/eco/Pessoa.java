package eco;

public class Pessoa {

	protected String nome;
	protected String dni;
	protected String estado;
	protected String[] interesses;
	protected String partido;
	private Funcao funcao;
	
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
	
	public Pessoa(String nome, String dni, String estado, String interresses, String partido) {
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
	
	public void virouBandido(String data) {
		this.funcao = new Deputado(data);
	}
	
	protected String exibirInteresses() {
		String interesses = "interesses: ";
		for (String e : this.interesses) {
			interesses += e + ", ";
		}
		return interesses.substring(0, interesses.length() - 2);
	}
	
	public String exibirPessoa(String dni) {
		if (funcao == null) {
			if(this.partido == null) {
				if(this.interesses[0].trim().equals("")) {
					return this.nome + " - " + this.dni + "(" + this.estado + ")";
				}
				else
					return this.nome + " - " + this.dni + "(" + this.estado + ")" + " - " + exibirInteresses();
			}
			else
				if(this.interesses[0].trim().contentEquals(""))
					return this.nome + " - " + this.dni + "(" + this.estado + ")" + " - " + this.partido;
				
				else
					return this.nome + " - " + this.dni + "(" + this.estado + ")" + " - " + this.partido + " - " + exibirInteresses();
		}
		else {
			if(this.interesses[0].trim().equals(""))
				return this.nome + " - " + this.dni + "(" + this.estado + ")" + " - " + this.partido + " - " + this.funcao.getData() + " - " + this.funcao.getLeisAprovadas();									
			else
				return this.nome + " - " + this.dni + "(" + this.estado + ")" + " - " + this.partido + " - " + exibirInteresses() + " - " + this.funcao.getData() + " - " + this.funcao.getLeisAprovadas();									
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

	public static void validaEntrada(String parametro, String mensagem) {
		if (parametro == null || parametro.trim().equals(""))
			throw new NullPointerException(mensagem);
	}
	
	static void validaDni(String dni, String msg) {
		if(dni.trim().length() > 11 || dni.trim().length() < 11 )
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
