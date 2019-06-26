package eco;

import java.util.List;

public interface Estrategia {
	
	public String prioridade(String interesses, List<Projeto> projetos);
}
