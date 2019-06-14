package eco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * classe responsavel por controlar partidos e comissoes, e as representacoes de partidos cadastrados.
 *
 */
public class Controller {
	/**
	 * inicia um novo validador;
	 */
	private Validador validador;
	/**
	 * Lista contendo todos os partidos cadastrados.
	 */
	private List<String> partidos;
	/**
	 * mapa contendo todas as comissoes cadastradas
	 */
	private HashMap<String, Comissao> comissoes = new HashMap<>();
	
	
	public Controller() {
		new ControllerPessoa();
		this.partidos = new ArrayList<>();
		this.validador = new Validador();
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
		
		validador.validaEntrada(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
		validador.validaEntrada(politicos, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
		if(comissoes.containsKey(tema))
			throw new IllegalArgumentException("Erro ao cadastrar comissao: tema existente");
		String[] politics = politicos.split(",");
		for(String dni : politics) {
			validador.validaDni(dni, "Erro ao cadastrar comissao: dni invalido");
			if(!ControllerPessoa.contem(dni)) 
				throw new NullPointerException("Erro ao cadastrar comissao: pessoa inexistente");
			if(!ControllerPessoa.eDeputado(dni))
				throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa nao eh deputado");
		}
		Comissao nova = new Comissao(tema, politicos);
		comissoes.put(tema, nova);
		
	}
	
	
}
