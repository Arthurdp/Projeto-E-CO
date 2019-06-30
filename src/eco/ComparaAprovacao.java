package eco;

import java.util.Comparator;
/**
 * Classe que implementa a interface comparator para 
 * permitir a comparacao de dois projetos pela
 * quantidade de aprovacoes
 */
public class ComparaAprovacao implements Comparator<Projeto>{
	public int compare(Projeto projeto1, Projeto projeto2) {
		if(projeto1.getAprovacoes() - projeto2.getAprovacoes() != 0) 
			return (projeto1.getAprovacoes() - projeto2.getAprovacoes()) * -1;
		
		else {
			if(projeto1.getAno() - projeto2.getAno() != 0)
				return projeto1.getAno() - projeto2.getAno();
			
			else {
				String[] array = projeto1.getCodigo().split(" ");
				 String[] array2 = projeto2.getCodigo().split(" ");
				 char numero = array[1].charAt(0);
				 char numero2 = array2[1].charAt(0);
				 
				 if(numero < numero2)
					 return -1;
				 else if(numero > numero2)
					 return 1;
			}
				
		}
		return 0;
	}
}
