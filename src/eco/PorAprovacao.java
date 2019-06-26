package eco;

import java.util.Collections;
import java.util.List;

public class PorAprovacao implements Estrategia{
	public Projeto oMaisVelho(List<Projeto> projetos) {
	   	Projeto maisVelho = projetos.get(0);
		int i = 0;
	   	
		for(int j = i + 1; j < projetos.size(); j++) {
	   		if(i == projetos.size() - 1)
	   			break;

	   		if(projetos.get(i).getAno() > projetos.get(j).getAno()) {
	   			maisVelho = projetos.get(j);
			   	i = j;
			}
			   	 
	   		else if(projetos.get(i).getAno() == projetos.get(j).getAno()) {
				 String[] array = projetos.get(i).getCodigo().split(" ");
				 String[] array2 = projetos.get(j).getCodigo().split(" ");
				 char numero = array[1].charAt(0);
				 char numero2 = array2[1].charAt(0);
			   		 
				 if(numero > numero2) {
					 maisVelho = projetos.get(j);
					 i = j;
		   		 }
		   	 }
	   	}
		return maisVelho;
    }
	
	public String prioridade(List<Projeto> projetos) {
		
		if(projetos.size() == 0)
			return "";
		
		else if(projetos.size() == 1)
			return projetos.get(0).getCodigo();
		
		else 
			return Collections.min(projetos, new ComparaAprovacao()).getCodigo();
	}
}
