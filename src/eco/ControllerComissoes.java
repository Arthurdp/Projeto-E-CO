package eco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}

