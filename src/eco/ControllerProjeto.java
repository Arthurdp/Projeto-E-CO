package eco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerProjeto {

	/**
	 * Mapa contendo todos os projetos cadastrados.
	 */
	private Map<String, Projeto> projetos;

	private Validador validador;
	private Map<Integer, Integer> registradosPL;
	private Map<Integer, Integer> registradosPLP;
	private Map<Integer, Integer> registradosPEC;


	public ControllerProjeto(){
		this.validador = new Validador();
		this.projetos = new HashMap<>();
		this.registradosPL = new HashMap<>();
		this.registradosPLP = new HashMap<>();
		this.registradosPEC = new HashMap<>();
	}

	public Map<String, Projeto> getProjetos() {
		return projetos;
	}

	public boolean votarPlenario(String codigo, String statusGovernista, List<Pessoa> politicos, List<Pessoa> politicosPresentes, List<String> partidos) {


		validador.validaEntrada(codigo, "Erro ao votar proposta: codigo nao pode ser vazio ou nulo");
		validador.validaEntrada(statusGovernista, "Erro ao votar proposta: status governista nao pode ser vazio ou nulo");

		if(politicos.isEmpty())
			throw new IllegalArgumentException("Erro ao votar proposta: Deputados presentes nao pode ser vazio ou nulo");


		if(!projetos.containsKey(codigo))
			throw new IllegalArgumentException("Erro ao votar proposta: projeto inexistente");

		return projetos.get(codigo).votarPlenario(statusGovernista, politicos, politicosPresentes, partidos);
	}

	public boolean votarComissao(String codigo, String estatusGovernista, List<Pessoa> deputados, List<String> partidos, Map<String, Comissao> comissoes, String proximoLocal) {

		if(!projetos.containsKey(codigo))
			throw new IllegalArgumentException("Erro ao votar proposta: projeto inexistente");

		if (projetos.get(codigo).getLocalAtual().equals("plenario")) 
			throw new IllegalArgumentException("Erro ao votar proposta: proposta encaminhada ao plenario");

		if (projetos.get(codigo).getLocalAtual().equals("-")) 
			throw new IllegalArgumentException("");



		if(comissoes.containsKey("CCJC"))
			throw new IllegalArgumentException("Erro ao votar proposta: CCJC nao cadastrada");

		if (comissoes.get(projetos.get(codigo).getLocalAtual()).getProjetosVotados().contains(codigo)) 
			throw new IllegalArgumentException("");

		return projetos.get(codigo).votarComissao(estatusGovernista, deputados, partidos, comissoes, proximoLocal);
	}


	/**
	 * Cadastra um novo projeto de lei criando o objeto PL e adicionando-o ao mapa de projetos.
	 * @param dni dni do autor do projeto
	 * @param ano ano de criacaoo do projeto
	 * @param ementa descricao do objetivo do projeto
	 * @param interesses ineresses relacionados ao projeto criado
	 * @param url endereco eletronico do projeto
	 * @param conclusivo informa se o projeto eh conclusivo ou nao
	 */
	public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		if (this.registradosPL.containsKey(ano))
			this.registradosPL.put(ano, this.registradosPL.get(ano) + 1);
		else
			this.registradosPL.put(ano, 1);
		String key = "PL " + this.registradosPL.get(ano) + "/" + ano;
		Projeto novoProjeto = new PL(dni, ano, key, ementa, interesses, url, conclusivo);
		this.projetos.put(key,novoProjeto );
		return key;
	}
	/**
	 * Cadastra um novo projeto de lei complementar, criando o objeto PLP e adicionando-o ao mapa de projetos.
	 * @param dni dni do autor do projeto
	 * @param ano ano de criacaoo do projeto
	 * @param ementa descricao do objetivo do projeto
	 * @param interesses ineresses relacionados ao projeto criado
	 * @param url endereco eletronico do projeto
	 * @param artigos Artigos da constituição sendo complementados.
	 */
	public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		if (this.registradosPLP.containsKey(ano))
			this.registradosPLP.put(ano, this.registradosPLP.get(ano) + 1);
		else
			this.registradosPLP.put(ano, 1);
		String key = "PLP " + this.registradosPLP.get(ano) + "/" + ano;
		Projeto novoProjeto = new PLP(dni, ano, key, ementa, interesses, url, artigos);
		this.projetos.put(key, novoProjeto);
		return key;
	}
	/**
	 * Cadastra um novo Projeto de Emenda Constitucional, criando o objeto PEC e adicionando-o ao mapa de projetos.
	 * @param dni dni do autor do projeto
	 * @param ano ano de criacaoo do projeto
	 * @param ementa descricao do objetivo do projeto
	 * @param interesses ineresses relacionados ao projeto criado
	 * @param url endereco eletronico do projeto
	 * @param artigos Artigos da constituição sendo emendados
	 */
	public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		if (!this.registradosPEC.containsKey(ano))
			this.registradosPEC.put(ano, 1);
		else
			this.registradosPEC.put(ano, this.registradosPEC.get(ano) + 1);

		String key = "PEC " + this.registradosPEC.get(ano) + "/" + ano;
		Projeto novoProjeto = new PEC(dni, ano,key , ementa, interesses, url, artigos);
		this.projetos.put(key, novoProjeto);
		return key;
	}
	/**
	 * Exibe a representacao de um projeto de lei com suas informacoes no formato string
	 * @param codigo codigo do projeto de lei a ser exibido
	 * @return String com as informacoes do projeto de lei
	 */
	public String exibirProjeto(String codigo) {
		return projetos.get(codigo).toString();
	}

	public String exibirTramitacao(String codigo) {
		validador.validaEntrada(codigo, "Erro ao exibir tramitacao: codigo nao pode ser vazio ou nulo");
		if(!projetos.containsKey(codigo))
			throw new IllegalArgumentException("Erro ao exibir tramitacao: projeto inexistente");
		return projetos.get(codigo).getTramitacao();
	}

	public List<Projeto> retornaProjetosRelacionados(String interessesDaPessoa){
		List<Projeto> lista = new ArrayList<>();
		for(Projeto projeto : projetos.values()) {
			if(projeto.interessesComuns(interessesDaPessoa))
				lista.add(projeto);
		}
		return lista;
	}

	public String retornaLocalAtual(String codigo) {
		return projetos.get(codigo).getLocalAtual();
	}
	/**
	 * Salva os dados do sistema em um diretorio separado na pasta arquivos.
	 */
	public void salvarSistema() {
		try {
			FileOutputStream arq = new FileOutputStream("arquivos/projetos.arq");
			ObjectOutputStream obj = new ObjectOutputStream(arq);
			obj.writeObject(this.projetos);
			
			FileOutputStream arq1 = new FileOutputStream("arquivos/registradosPL.arq");
			ObjectOutputStream obj1 = new ObjectOutputStream(arq1);
			obj1.writeObject(this.registradosPL);
			
			FileOutputStream arq2 = new FileOutputStream("arquivos/registradosPLP.arq");
			ObjectOutputStream obj2 = new ObjectOutputStream(arq2);
			obj2.writeObject(this.registradosPLP);
			
			FileOutputStream arq3 = new FileOutputStream("arquivos/registradosPEC.arq");
			ObjectOutputStream obj3 = new ObjectOutputStream(arq3);
			obj3.writeObject(this.registradosPEC);
			

			obj.close();
			obj1.close();
			obj2.close();
			obj3.close();
		} catch (Exception e) {
						
		}		
	}
	/**
	 * Carrega os dados do sistema, que estao em um diretorio separado na pasta arquivos.
	 */
	public void carregarSistema() {
		try {
			FileInputStream arq = new FileInputStream("arquivos/projetos.arq");
			ObjectInputStream obj = new ObjectInputStream(arq);
			this.projetos = (Map<String, Projeto>) obj.readObject();
			FileInputStream arq1 = new FileInputStream("arquivos/registradosPL.arq");
			ObjectInputStream obj1 = new ObjectInputStream(arq1);
			this.registradosPL = (Map<Integer, Integer>) obj1.readObject();
			FileInputStream arq2 = new FileInputStream("arquivos/registradosPLP.arq");
			ObjectInputStream obj2 = new ObjectInputStream(arq2);
			this.registradosPLP = (Map<Integer, Integer>) obj2.readObject();
			FileInputStream arq3 = new FileInputStream("arquivos/registradosPEC.arq");
			ObjectInputStream obj3 = new ObjectInputStream(arq3);
			this.registradosPEC = (Map<Integer, Integer>) obj3.readObject();

			obj.close();
			obj1.close();
			obj2.close();
			obj3.close();
		} catch (Exception e) {
								
		}		
	}
	/**
	 * Limpa os dados do sistema, que estao em um diretorio separado na pasta arquivos.
	 */
	public void limparSistema() {
		projetos.clear();			
		registradosPL.clear();		
		registradosPLP.clear();		
		registradosPEC.clear();	
		try {
			File arq = new File("arquivos/projetos.arq");
			arq.delete();
			File arq1 = new File("arquivos/registradosPL.arq");
			arq1.delete();
			File arq2 = new File("arquivos/registradosPLP.arq");
			arq2.delete();
			File arq3 = new File("arquivos/registradosPEC.arq");
			arq3.delete();
		} catch (Exception e) {	
			
		}
	}
}
