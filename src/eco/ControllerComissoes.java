package eco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * classe responsavel por controlar todo o sistema. controla o cadastro de pessoas,
 * deputados e partidos, e as representacoes de pessoas e partidos cadastrados.
 *
 */
public class ControllerComissoes {

	/**
	 * inicia um novo validador;
	 */
	private Validador validador = new Validador();
	/**
	 * Lista contendo os partidos cadastrados que formam a base governista.
	 */
	private List<String> partidos;
	/**
	 * mapa contendo todas as comissoes cadastradas.
	 */
	private HashMap<String, Comissao> comissoes;


	public ControllerComissoes() {
		this.validador = new Validador();
		this.comissoes = new HashMap<>();
		this.partidos = new ArrayList<>();

	}

	public HashMap<String, Comissao> getComissoes() {
		return comissoes;
	}

	public List<String> getPartidos() {
		return partidos;
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
	/**
	 * Cadastra uma nova comissao inserindo-a no mapa de comissoes
	 * @param tema tema da comissao
	 * @param politicos string contendo os dni dos politicos participantes da comissao
	 */
	public void cadastrarComissao(String tema, String politicos){
		Comissao nova = new Comissao(tema, politicos);
		comissoes.put(tema, nova);

	}
	/**
	 * Salva os dados do sistema em um diretorio separado na pasta arquivos.
	 */
	public void salvarSistema() {
		try {			
			FileOutputStream arq = new FileOutputStream("arquivos/comissoes.arq");
			ObjectOutputStream obj = new ObjectOutputStream(arq);
			obj.writeObject(this.comissoes);
			
			FileOutputStream arq1 = new FileOutputStream("arquivos/partidos.arq");
			ObjectOutputStream obj1 = new ObjectOutputStream(arq1);
			obj1.writeObject(this.partidos);
			
			
			obj.close();
			obj1.close();
		} catch (Exception e) {
							
		}		
	}
	/**
	 * Carrega os dados do sistema, que estao em um diretorio separado na pasta arquivos.
	 */
	public void carregarSistema() {
		try {			
			FileInputStream arq = new FileInputStream("arquivos/comissoes.arq");
			ObjectInputStream obj = new ObjectInputStream(arq);
			this.comissoes = (HashMap<String, Comissao>) obj.readObject();
			FileInputStream arq1 = new FileInputStream("arquivos/partidos.arq");
			ObjectInputStream obj1 = new ObjectInputStream(arq1);
			this.partidos = (List<String>) obj1.readObject();
			
			obj.close();
			obj1.close();
		} catch (Exception e) {
								
		}			
	}
	/**
	 * Limpa os dados do sistema, que estao em um diretorio separado na pasta arquivos.
	 */
	public void limparSistema() {
		comissoes.clear();
		partidos.clear();
		try {
			File arq = new File("arquivos/comissoes.arq");
			arq.delete();
			File arq1 = new File("arquivos/partidos.arq");
			arq1.delete();
			
		} catch (Exception e) {	
			
		}
	}
}

