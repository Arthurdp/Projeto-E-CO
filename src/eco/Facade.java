package eco;

public class Facade {
	
	private ControllerPessoa controllerPessoa;
	private ControllerPartido controllerPartido;
	
	public Facade() {
		controllerPessoa = new ControllerPessoa();
		controllerPartido = new ControllerPartido();
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		controllerPessoa.cadastrarPessoa(nome, dni, estado, interesses);
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		controllerPessoa.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}
	
	public void cadastrarDeputado(String DNI, String dataDeInicio) {
		controllerPessoa.cadastraDeputado(DNI, dataDeInicio);
	}
	
	public void cadastrarPartido(String partido) {
		controllerPartido.cadastrarPartido(partido);
	}
	
	public String exibirPessoa(String DNI) {
		return controllerPessoa.exibirPessoa(DNI);
	}
	
	public String exibirBase() {
		return controllerPartido.exibirBase();
	}
	
	

}
