package eco;

import java.util.Collections;
import java.util.List;
/**
 * Classe que encontra a proposta mais relacionada ao deputado dentre
 * todas as propostas cadastradas de acordo com 
 * a estrategia APROVACAO
 */
public class PorAprovacao implements Estrategia{
	
	public String prioridade(List<Projeto> projetos) {
		
		if(projetos.size() == 0)
			return "";
		
		else if(projetos.size() == 1)
			return projetos.get(0).getCodigo();
		
		else 
			return Collections.min(projetos, new ComparaAprovacao()).getCodigo();
	}
}
