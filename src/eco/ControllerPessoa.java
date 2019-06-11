package eco;

import java.util.HashMap;
/**
 * Controla todas as açoes realizadas sobre uma pessoa.
 *
 */
public class ControllerPessoa {
	
	private Validador validador = new Validador();
	/**
	 * Mapa de pessoas existentes.
	 */
	private HashMap<String, Pessoa> pessoas;
	/**
	 * inicia o mapa de pessoas.
	 */
	public ControllerPessoa() {
		pessoas = new HashMap<String, Pessoa>();
	}
	/**
	 * Exibe os dados de uma pessoa determinada pelo seu dni.
	 * @param dni dni da pessoa a ser exibida.
	 * @return retorna uma string com os dados da pessoa.
	 */
	public String exibirPessoa(String dni) {
		if (!pessoas.containsKey(dni)) {
			throw new IllegalArgumentException("Erro ao exibir pessoa: pessoa nao encontrada");
		}
		return pessoas.get(dni).exibirPessoa();
	}
	
	/**
	 * adiciona um objeto do tipo pessoa em pessoas.(sem partido)
	 * @param nome é o nome da pessoa que sera adicionada
	 * @param dni é o dni da pessoa que sera adicionada
	 * @param estado é o estado da pessoa que sera adicionada
	 * @param interesses sao os interesses da pessoa que sera adicionada
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		Pessoa nova = new Pessoa(nome, dni, estado, interesses);
		if(this.pessoas.containsKey(dni))
			throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni ja cadastrado");
		pessoas.put(dni, nova);
		
	}
	
	/**
	 * adiciona um objeto do tipo pessoa em pessoas.(com partido)
	 * @param nome é o nome da pessoa que sera adicionada
	 * @param dni é o dni da pessoa que sera adicionada
	 * @param estado é o estado da pessoa que sera adicionada
	 * @param interesses sao os interesses da pessoa que sera adicionada
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		Pessoa nova = new Pessoa(nome, dni, estado, interesses, partido);
		if(this.pessoas.containsKey(dni))
			throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni ja cadastrado");
		pessoas.put(dni, nova);
	}
	
	/**
	 * com base em uma pessoa pre existente, o objeto Deputado é criado e introduzido dentro do objeto pessoa.
	 * @param dni da pessoa a ser selecionada
	 * @param data é a data do momento em que a pessoa se tornou deputado.
	 */
	public void cadastraDeputado(String dni, String data) {
		validador.validaEntrada(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar deputado: dni invalido");
		if(!pessoas.containsKey(dni))
			throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa nao encontrada");
		if(pessoas.get(dni).getPartido() == null)
			throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa sem partido");
		validador.validaEntrada(data, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
		validador.validaData(data, "Erro ao cadastrar deputado: data invalida");
		validador.validaDataFutura(data, "Erro ao cadastrar deputado: data futura");
		
		
		
		
		
		Pessoa pessoa = pessoas.get(dni);
		pessoa.virouDeputado(data);
	}
}
