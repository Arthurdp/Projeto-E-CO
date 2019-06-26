package eco;

import java.util.Collections;
import java.util.List;

public class Constitucional implements Estrategia {
	
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
				 int numero = Integer.parseInt(array[1].split("")[0]);
				 int numero2 = Integer.parseInt(array2[1].split("")[0]);
			   		 
				 if(numero < numero2) {
					 i = j;
					 maisVelho = projetos.get(j);
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
			return Collections.min(projetos, new ComparaConstitucional()).getCodigo();
	}
	
	
}