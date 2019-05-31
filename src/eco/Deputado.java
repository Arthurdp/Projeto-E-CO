package eco;

public class Deputado extends Pessoa{
	
	private String data;
	private int leisAprovadas;

	public Deputado(String nome, String dni, String estado, String interresses, String data, int leisAprovadas, String partido) {
		super(nome, dni, estado, interresses, partido);
		this.data = data;
		this.leisAprovadas = leisAprovadas;
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
	
	public String exibirPessoa() {
		return this.nome + " - " + this.dni + "(" + this.estado + ")" + " - " partido + " - " interresses ;
	}
}
