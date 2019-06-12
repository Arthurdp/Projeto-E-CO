package eco;

import java.util.HashMap;
/**
 * classe responsavel por controlar metodos e armazenar pessoas no sistema.
 * controla o cadastro de pessoas e deputados.
 */
public class ControllerPessoa {
	
	private Validador validador = new Validador();
	/**
	 * mapa de pessoas cadastradas.
	 */
	private HashMap<String, Pessoa> pessoas;

	/**
	 * inicia o mapa de pessoas.
	 */
	public ControllerPessoa() {
		pessoas = new HashMap<String, Pessoa>();
	}
	
	/**
	 * cadastra uma nova pessoa sem partido no sistema, adicionando um objeto do tipo pessoa no mapa de pessoas.
	 * @param nome nome da pessoa que sera adicionada.
	 * @param dni dni da pessoa que sera adicionada.
	 * @param estado estado da pessoa que sera adicionada.
	 * @param interesses interesses da pessoa que sera adicionada.
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		Pessoa nova = new Pessoa(nome, dni, estado, interesses);
		if(this.pessoas.containsKey(dni))
			throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni ja cadastrado");
		pessoas.put(dni, nova);
		
	}
	
	/**
	 * cadastra uma nova pessoa com partido no sistema, adicionando um objeto do tipo pessoa no mapa de pessoas.
	 * @param nome nome da pessoa que sera adicionada.
	 * @param dni dni da pessoa que sera adicionada.
	 * @param estado estado da pessoa que sera adicionada.
	 * @param interesses interesses da pessoa que sera adicionada.
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		Pessoa nova = new Pessoa(nome, dni, estado, interesses, partido);
		if(this.pessoas.containsKey(dni))
			throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni ja cadastrado");
		pessoas.put(dni, nova);
	}
	
	/**
	 * inicia a vida publica de uma pessoa, tornando a mesma em um deputado.
	 * @param dni da pessoa que vai se tornar um deputado.
	 * @param data data do momento em que a pessoa se tornou deputado.
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
	
	/**
	 * exibe dados de uma pessoa cadastrada no sistema determinada pelo seu dni. 
	 * @param dni dni da pessoa a ser exibida;
	 * @return a representacao textual de uma pessoa cadastrada.
	 */
	public String exibirPessoa(String dni) {
		if (!pessoas.containsKey(dni)) {
			throw new IllegalArgumentException("Erro ao exibir pessoa: pessoa nao encontrada");
		}
		return pessoas.get(dni).exibirPessoa();
	}
	/**
	 * Informa se uma pessoa existe no mapa de pessoas
	 * @param dni dni da pessoa
	 * @return true se existe, false se não existe
	 */
	public boolean contem(String dni) {
		if(this.pessoas.containsKey(dni)) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Informa se uma pessoa eh um deputado
	 * @param dni dni da pessoa
	 * @return true se for deputado, false se não.
	 */
	public boolean eDeputado(String dni){
		if(this.pessoas.get(dni).getDeputado() == null) {
			return false;
		}else {
			return true;
		}
		
	}
			
}
