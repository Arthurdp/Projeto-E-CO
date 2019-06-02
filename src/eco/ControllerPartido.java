package eco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
/**
 * Controla todas as ações realizadas sobre um partido.
 *
 */
public class ControllerPartido {
	/**
	 * Conjunto de partidos que compõem a base governista.
	 */
	private Set<Partido> baseGovernista;
	/**
	 * inicia o conjunto de partidos.
	 */
	public ControllerPartido() {
		this.baseGovernista = new LinkedHashSet<>();
	}
	/**
	 * cadastra um novo partido na base governista.
	 * @param partido partido a ser adicionado na base governista.
	 */
	public void cadastrarPartido(String partido) {
		this.baseGovernista.add(new Partido(partido));
	}
	/**
	 * Exibe todos os partidos que compõem a base governista.
	 * @return
	 */
	public String exibirBase() {
		String saida = "";
		List<Partido> lista = new ArrayList<>();
		lista.addAll(this.baseGovernista);
		Collections.sort(lista);
		for(Partido p : lista) {
			saida += p.toString() + ",";
		}
		if(saida.length() > 0)
			return saida.substring(0, saida.length() - 1);
		else
			return saida;
	}
}
