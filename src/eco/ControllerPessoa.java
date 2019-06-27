package eco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
/**
 * classe responsavel por controlar metodos e armazenar pessoas no sistema.
 * controla o cadastro de pessoas e deputados.
 */
public class ControllerPessoa {

	/**
	 * mapa de pessoas cadastradas.
	 */
	private HashMap<String, Pessoa> pessoas;
	/**
	 *  inicia um novo validador;
	 */
	private Validador validador;

	public ControllerPessoa() {
		this.pessoas = new HashMap<>();
		this.validador = new Validador();
	}

	public HashMap<String, Pessoa> getPessoas() {
		return pessoas;
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
		if(pessoas.containsKey(dni))
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
		if(pessoas.containsKey(dni))
			throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni ja cadastrado");
		pessoas.put(dni, nova);
	}

	public Pessoa retornaPessoa(String dni) {
		return pessoas.get(dni);
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
		validador.validaEntrada(dni,"Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao exibir pessoa: dni invalido");
		if (!pessoas.containsKey(dni)) {
			throw new IllegalArgumentException("Erro ao exibir pessoa: pessoa nao encontrada");
		}
		return pessoas.get(dni).exibirPessoa();
	}

	public void configurarEstrategiaPropostaRelacionada(String dni, String estrategia) {
		pessoas.get(dni).configurarEstrategiaPropostaRelacionada(estrategia);
	}
	/**
	 * Salva os dados do sistema em um diretorio separado na pasta arquivos.
	 */
	public void salvarSistema() {
		try {
			FileOutputStream arq = new FileOutputStream("arquivos/pessoas.arq");
			ObjectOutputStream obj = new ObjectOutputStream(arq);
			obj.writeObject(this.pessoas);
			
			
			obj.close();
			
		} catch (Exception e) {
								
		}
	}
	/**
	 * Carrega os dados do sistema, que estao em um diretorio separado na pasta arquivos.
	 */
	public void carregarSistema() {
		try {
		
			FileInputStream arq = new FileInputStream("arquivos/pessoas.arq");
			ObjectInputStream obj = new ObjectInputStream(arq);
			this.pessoas = (HashMap<String, Pessoa>) obj.readObject();
			
			obj.close();
			
		} catch (Exception e) {
							
		}
	}
	/**
	 * Limpa os dados do sistema, que estao em um diretorio separado na pasta arquivos.
	 */
	public void limparSistema() {
		pessoas.clear();
		try {
			File arq = new File("arquivos/pessoas.arq");
			arq.delete();
			
		} catch (Exception e) {	
			
		}
	}

}
