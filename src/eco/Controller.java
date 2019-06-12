package eco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * classe responsavel por controlar todo o sistema. controla o cadastro de pessoas,
 * deputados e partidos, e as representacoes de pessoas e partidos cadastrados.
 *
 */
public class Controller {
	private Validador validador;
	private ControllerPessoa controllerPessoa;
	private List<String> partidos;
	
	public Controller() {
		this.controllerPessoa = new ControllerPessoa();
		this.partidos = new ArrayList<>();
		this.validador = new Validador();
	}
	
	/**
	 * cadastra uma nova pessoa sem partido no sistema, adicionando um objeto do tipo pessoa no mapa de pessoas.
	 * @param nome nome da pessoa que sera adicionada.
	 * @param dni dni da pessoa que sera adicionada.
	 * @param estado estado da pessoa que sera adicionada.
	 * @param interesses interesses da pessoa que sera adicionada.
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		controllerPessoa.cadastrarPessoa(nome, dni, estado, interesses);
	}
	
	/**
	 * cadastra uma nova pessoa com partido no sistema, adicionando um objeto do tipo pessoa no mapa de pessoas.
	 * @param nome nome da pessoa que sera adicionada.
	 * @param dni dni da pessoa que sera adicionada.
	 * @param estado estado da pessoa que sera adicionada.
	 * @param interesses interesses da pessoa que sera adicionada.
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		controllerPessoa.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}
	
	/**
	 * inicia a vida publica de uma pessoa, tornando a mesma em um deputado.
	 * @param dni da pessoa que vai se tornar um deputado.
	 * @param data data do momento em que a pessoa se tornou deputado.
	 */
	public void cadastraDeputado(String dni, String data) {
		controllerPessoa.cadastraDeputado(dni, data);
	}
	
	/**
	 * cadastra um partido no sistema, adicionando uma string na lista de partidos.
	 * @param partido sigla do partido a ser cadastrado.
	 */
	public void cadastrarPartido(String partido) {
		validador.validaEntrada(partido,"Erro ao cadastrar partido: partido nao pode ser vazio ou nulo");
		this.partidos.add(partido);
	}
	
	/**
	 * exibe dados de uma pessoa cadastrada no sistema determinada pelo seu dni. 
	 * @param dni dni da pessoa a ser exibida;
	 * @return a representacao textual de uma pessoa cadastrada.
	 */
	public String exibirPessoa(String dni) {
		validador.validaEntrada(dni,"Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao exibir pessoa: dni invalido");
		return controllerPessoa.exibirPessoa(dni);
	}
	
	/** 
	 * exibe todos os partidos cadastrados, em ordem lexicografica.
	 * @return as strings que representam os partidos cadastrados.
	 */
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
