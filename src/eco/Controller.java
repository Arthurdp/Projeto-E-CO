package eco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {
	private ControllerPessoa controllerPessoa;
	private List<String> partidos;
	
	public Controller() {
		this.controllerPessoa = new ControllerPessoa();
		this.partidos = new ArrayList<>();
	}
	
	public String exibirPessoa(String dni) {
		return controllerPessoa.exibirPessoa(dni);
	}
	
	/**
	 * adiciona um objeto do tipo pessoa em pessoas.(sem partido)
	 * @param nome é o nome da pessoa que sera adicionada
	 * @param dni é o dni da pessoa que sera adicionada
	 * @param estado é o estado da pessoa que sera adicionada
	 * @param interesses sao os interesses da pessoa que sera adicionada
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		controllerPessoa.cadastrarPessoa(nome, dni, estado, interesses);
	}
	
	/**
	 * adiciona um objeto do tipo pessoa em pessoas.(com partido)
	 * @param nome é o nome da pessoa que sera adicionada
	 * @param dni é o dni da pessoa que sera adicionada
	 * @param estado é o estado da pessoa que sera adicionada
	 * @param interesses sao os interesses da pessoa que sera adicionada
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		controllerPessoa.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}
	
	/**
	 * com base em uma pessoa pre existente, o objeto Deputado é criado e introduzido dentro do objeto pessoa.
	 * @param dni da pessoa a ser selecionada
	 * @param data é a data do momento em que a pessoa se tornou deputado.
	 */
	public void cadastraDeputado(String dni, String data) {
		controllerPessoa.cadastraDeputado(dni, data);
	}
	
	public void cadastrarPartido(String partido) {
		this.partidos.add(partido);
	}
	
	public String exibirBase() {
		Collections.sort(partidos);
		String saida = "";
		for(String partido : partidos) {
			saida += partido + ",";
		}
		if (saida.length() > 0)
			return saida.substring(0, saida.length() - 1);
		else
			return saida;
	}
}
