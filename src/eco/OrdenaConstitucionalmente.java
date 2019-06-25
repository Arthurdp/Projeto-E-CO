package eco;

import java.util.Comparator;

public class OrdenaConstitucionalmente implements Comparator<Projeto>{
	
	public int compare(Projeto projeto1, Projeto projeto2){
		String[] array = projeto1.getCodigo().split(" ");
		String[] array2 = projeto2.getCodigo().split(" ");
		
		if(array[0].equals("PEC") && !array2[0].equals("PEC"))
			return -1;
		
		else if(!array[0].equals("PEC") && array2[0].equals("PEC"))
			return 1;
		
		else if(array[0].equals("PLP") && array2[0].equals("PL"))
			return -1;
		
		else if(array[0].equals("PL") && array2[0].equals("PLP"))
			return 1;
		
		else {
			if(projeto1.getAno() < projeto2.getAno()) {
				return -1;
			}
			
			else if(projeto1.getAno() > projeto2.getAno()) {
				return 1;
			}
			
			else {
				 char numero = array[1].charAt(0);
				 char numero2 = array2[1].charAt(0);
				 
				 if(numero < numero2) {
					 return -1;
				 }
				 
				 else if(numero > numero2) {
					 return 1;
				 }
				 
				 else
					 throw new IllegalArgumentException("Nao era para estar lancando esse erro, fale com o tecnico a respeito desse bug");
		
			}
		}
	}
}
