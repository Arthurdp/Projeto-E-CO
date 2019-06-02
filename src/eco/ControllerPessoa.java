package eco;

import java.util.HashMap;
/**
 * Controla todas as açoes realizadas sobre uma pessoa.
 *
 */
public class ControllerPessoa {
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
			throw new IllegalArgumentException("Erro ao exibir pessoa: pessoa nao existe");
		}
		eco.Pessoa.validaDni(dni, "Erro ao exibir pessoa: dni invalido");
		
		return pessoas.get(dni).exibirPessoa();
	}
}
