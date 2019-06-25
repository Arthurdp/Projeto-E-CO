package eco;

import java.util.Comparator;

public class ordenaPelaConclusao implements Comparator<Projeto>{
	public int compare(Projeto projeto1, Projeto projeto2) {
		if(projeto1.getSituacaoAtual().equals("EM VOTACAO (Plenario - 2o turno)") && !projeto2.getSituacaoAtual().equals("EM VOTACAO (Plenario - 2o turno)"))
			return -1;
		
		else if(!projeto1.getSituacaoAtual().equals("EM VOTACAO (Plenario - 2o turno)") && projeto2.getSituacaoAtual().equals("EM VOTACAO (Plenario - 2o turno)"))
			return 1;
		
		else if(projeto1.getLocalAtual().equals("plenario") && !projeto2.getLocalAtual().equals("plenario"))
			return -1;
		
		else if(!projeto1.getLocalAtual().equals("plenario") && projeto2.getLocalAtual().equals("plenario"))
			return 1;
		
		else
			return projeto1.getConclusoes() - projeto2.getConclusoes();
	}
}
