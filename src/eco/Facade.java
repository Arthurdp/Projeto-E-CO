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
	
	public void salvarSistem() {
		
	}

}
