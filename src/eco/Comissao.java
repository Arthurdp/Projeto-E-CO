package eco;

import java.util.HashSet;
import java.util.Set;

public class Comissao{
	
	private Validador validador = new Validador();
	
	/**
	 * string contendo os dni dos politicos participantes da comissao
	 */
	private String politicos;
	private Set<String> projetosVotados;
	/**
	 * Constroi uma nova comissao
	 * @param tema tema da comissao
	 * @param politicos string contendo os dni dos politicos participantes da comissao
	 */
	public Comissao(String tema, String politicos) {
		validador.validaEntrada(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
		validador.validaEntrada(politicos, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
		this.politicos = politicos;
		this.projetosVotados = new HashSet<>();
	}
	
	/**
	 * @return the politicos
	 */
	public String getPoliticos() {
		return politicos;
	}

	/**
	 * @return the projetosVotados
	 */
	public Set<String> getProjetosVotados() {
		return projetosVotados;
	}
}