package eco;

import java.util.Comparator;

public class ComparaConstitucional implements Comparator<Projeto>{
	public int compare(Projeto projeto1, Projeto projeto2) {
		if(projeto1.getPrioridade() - projeto2.getPrioridade() != 0) {
			
			if(projeto1.getPrioridade() < projeto2.getPrioridade()) {
				return -1;
			}else if(projeto1.getPrioridade() > projeto2.getPrioridade()) {
				return 1;
			}
		}
			
		else {
			if(projeto1.getAno() - projeto2.getAno() != 0)
				return projeto1.getAno() - projeto2.getAno();
			
			else {
				String[] array = projeto1.getCodigo().split(" ");
				 String[] array2 = projeto2.getCodigo().split(" ");
				 char numero = array[1].charAt(0);
				 char numero2 = array2[1].charAt(0);
				 
				 return numero - numero2;
			}
				
		}
		return 0;
	}
}
