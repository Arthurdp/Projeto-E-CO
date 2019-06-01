package eco;

import java.util.HashMap;

public class ControllerPessoa {
	
	private HashMap<String, Pessoa> pessoas;
	
	public ControllerPessoa() {
		pessoas = new HashMap<String, Pessoa>();
	}
	
	public String exibirPessoa(String dni) {
		if (!pessoas.containsKey(dni)) {
			throw new IllegalArgumentException("Erro ao exibir pessoa: pessoa nao existe");
		}
		eco.Pessoa.validaDni(dni, "Erro ao exibir pessoa: dni invalido");
		
		return pessoas.get(dni).exibirPessoa(dni);
	}
}
