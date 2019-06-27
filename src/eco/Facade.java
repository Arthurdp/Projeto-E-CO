package eco;

public class Facade {
	
	private ControllerGeral controllerGeral;
	
	public Facade() {
		this.controllerGeral = new ControllerGeral();
	}
	
	public void limparSistema() {
		controllerGeral.limpaSistema();
	}
	
	public void salvarSistema() {
		controllerGeral.salvarSistema();
	}
	
	public void carregarSistema() {
		controllerGeral.carregaSistema();
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		controllerGeral.cadastrarPessoa(nome, dni, estado, interesses);
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		controllerGeral.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}
	
	public void cadastrarDeputado(String DNI, String dataDeInicio) {
		controllerGeral.cadastrarDeputado(DNI, dataDeInicio);
	}
	
	public void cadastrarPartido(String partido) {
		this.controllerGeral.cadastrarPartido(partido);
	}
	
	public String exibirPessoa(String DNI) {
		return controllerGeral.exibirPessoa(DNI);
	}
	
	public String exibirBase() {
		return controllerGeral.exibirBase();
	}
	
	public void cadastrarComissao(String tema, String politicos) {
		this.controllerGeral.cadastrarComissao(tema, politicos);
	}
	
	public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		return this.controllerGeral.cadastrarPL(dni, ano, ementa, interesses, url, conclusivo);
	}
	
	public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		return this.controllerGeral.cadastrarPLP(dni, ano, ementa, interesses, url, artigos);
	}
	
	public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		return this.controllerGeral.cadastrarPEC(dni, ano, ementa, interesses, url, artigos);
	}
	
	public String exibirProjeto(String codigo) {
		return this.controllerGeral.exibirProjeto(codigo);
	}
	public void limparSistem() {
		
	}
	
	public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
		return this.controllerGeral.votarComissao(codigo, statusGovernista, proximoLocal);
	}
	
	public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {	
		return this.controllerGeral.votarPlenario(codigo, statusGovernista, presentes);
	}
	public void salvarSistem() {
		
	}
	
	public String exibirTramitacao(String codigo) {
		return controllerGeral.exibirTramitacao(codigo);
	}
	
	public void configurarEstrategiaPropostaRelacionada(String dni, String estrategia) {
		controllerGeral.configurarEstrategiaPropostaRelacionada(dni, estrategia);
	}
	
	public String pegarPropostaRelacionada(String dni) {
		return controllerGeral.pegarPropostaRelacionada(dni);
	}
}
