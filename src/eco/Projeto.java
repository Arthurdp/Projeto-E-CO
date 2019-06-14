package eco;

abstract class Projeto{
	private String autor;
	private int ano;
	private String codigo;
	private String ementa;
	private String interessesRelacionados;
	private String situacaoAtual;
	private String enderecoDoDocumento;
	
	public Projeto(String autor, int ano, String codigo, String ementa, String interessesRelacionados, String enderecoDoDocumento) {
		this.autor = autor;
		this.ano = ano;
		this.codigo = codigo;
		this.ementa = ementa;
		this.interessesRelacionados = interessesRelacionados;
		this.situacaoAtual = "EM VOTACAO (CCJC)";
		this.enderecoDoDocumento = enderecoDoDocumento;
	}

	public String getAutor() {
		return autor;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getEmenta() {
		return ementa;
	}

	public String getSituaçãoAtual() {
		return situacaoAtual;
	}
	
	
}
