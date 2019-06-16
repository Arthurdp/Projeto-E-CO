package eco;

public class Facade {
	
	private Controller controller;
	
	public Facade() {
		this.controller = new Controller();
	}
	
	public void limparSistema() {
		
	}
	
	public void salvarSistema() {
		
	}
	
	public void carregarSistema() {
		
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		controller.cadastrarPessoa(nome, dni, estado, interesses);
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		controller.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}
	
	public void cadastrarDeputado(String DNI, String dataDeInicio) {
		controller.cadastraDeputado(DNI, dataDeInicio);
	}
	
	public void cadastrarPartido(String partido) {
		this.controller.cadastrarPartido(partido);
	}
	
	public String exibirPessoa(String DNI) {
		return controller.exibirPessoa(DNI);
	}
	
	public String exibirBase() {
		return controller.exibirBase();
	}
	
	public void cadastrarComissao(String tema, String politicos) {
		this.controller.cadastrarComissao(tema, politicos);
	}
	
	public void cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		controller.cadastrarPL(dni, ano, ementa, interesses, url, conclusivo);
	}
	
	public void cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		this.controller.cadastrarPLP(dni, ano, ementa, interesses, url, artigos);
	}
	
	public void cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		this.controller.cadastrarPEC(dni, ano, ementa, interesses, url, artigos);
	}
	
	public String exibirProjeto(String codigo) {
		return this.controller.exibirProjeto(codigo);
	}
	
	public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
		return this.controller.votarComissao(codigo, statusGovernista, proximoLocal);
	}
	
	public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {	
		return this.controller.votarPlenario(codigo, statusGovernista, presentes);
	}
	public void salvarSistem() {
		
	}

}
