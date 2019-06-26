package eco;

import java.util.ArrayList;
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
	
	
	
	public String prioridade(String interesses, List<Projeto> projetos) {
		List<Projeto> lista = new ArrayList<>();
		for(Projeto projeto : projetos) {
			if(projeto.interessesComuns(interesses))
				lista.add(projeto);
		}
		if(lista.size() == 0)
			return "";
		
		else if(lista.size() == 1)
			return lista.get(0).getCodigo();
		
		else {
			List<Projeto> lista2 = new ArrayList<>();
			for(Projeto projeto : lista) {
				if(projeto instanceof PEC)
					lista2.add(projeto);
				
			}
			if(lista2.size() == 1)
				return lista2.get(0).getCodigo();
			
			else if(lista2.size() > 1)
				return oMaisVelho(projetos).getCodigo();
			
			else {	
			
				for(Projeto projeto : lista) {
					if(projeto instanceof PLP)
						lista2.add(projeto);
					
				}
				if(lista2.size() == 1)
					return lista2.get(0).getCodigo();
			
				else if(lista2.size() > 1)
					return oMaisVelho(projetos).getCodigo();
			
				else {
					for(Projeto projeto : lista) {
						if(projeto instanceof PL)
							lista2.add(projeto);
				
					}
				
					if(lista2.size() == 1)
						return lista2.get(0).getCodigo();
			
					else if(lista2.size() > 1)
						return oMaisVelho(projetos).getCodigo();
					
					else
						return "";
			
				}
			}
		}
	}
}