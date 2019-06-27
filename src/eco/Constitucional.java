package eco;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Constitucional implements Estrategia, Serializable {
	
public String prioridade(List<Projeto> projetos) {
		
		if(projetos.size() == 0)
			return "";
		
		else if(projetos.size() == 1)
			return projetos.get(0).getCodigo();
		
		else 
			return Collections.min(projetos, new ComparaConstitucional()).getCodigo();
	}	
}