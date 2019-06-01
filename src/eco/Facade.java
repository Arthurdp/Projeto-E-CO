package eco;

public class Facade {
	
	private ControllerPessoa controllerPessoa;
	
	public Facade() {
		controllerPessoa = new ControllerPessoa();
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		
	}
	
	public void cadastrarDeputado(String DNI, String dataDeInicio) {
		
	}
	
	public void cadastrarPartido(String partido) {
		
	}
	
	public String exibirPessoa(String DNI) {
		return controllerPessoa.exibirPessoa(DNI);
	}
	
	public String exibirBase() {
		return "";
	}
	
	

}
