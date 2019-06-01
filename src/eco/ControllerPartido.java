package eco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ControllerPartido {
	private Set<Partido> baseGovernista;
	
	public ControllerPartido() {
		this.baseGovernista = new LinkedHashSet<>();
	}
	
	public void cadastrarPartido(String partido) {
		this.baseGovernista.add(new Partido(partido));
	}
	
	public String exibirBase() {
		String saida = "";
		List<Partido> lista = new ArrayList<>(this.baseGovernista);
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
