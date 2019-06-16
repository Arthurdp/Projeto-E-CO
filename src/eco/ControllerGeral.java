package eco;

public class ControllerGeral {
	
	Controller controller;
	ControllerPessoa controllerPessoa;
	ControllerProjeto controllerProjeto;
	Validador validador;
	
	public ControllerGeral() {
		this.controller = new Controller();
		this.controllerPessoa = new ControllerPessoa();
		this.controllerProjeto = new ControllerProjeto();
		this.validador = new Validador();
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		controllerPessoa.cadastrarPessoa(nome, dni, estado, interesses);
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		controllerPessoa.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}
	
	public void cadastrarDeputado(String DNI, String dataDeInicio) {
		controllerPessoa.cadastraDeputado(DNI, dataDeInicio);
	}
	
	public void cadastrarPartido(String partido) {
		this.controller.cadastrarPartido(partido);
	}
	
	public String exibirPessoa(String DNI) {
		return controllerPessoa.exibirPessoa(DNI);
	}
	
	public String exibirBase() {
		return controller.exibirBase();
	}
	
	public void cadastrarComissao(String tema, String politicos) {
		this.controller.cadastrarComissao(tema, politicos);
	}
	
	public void cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		controllerProjeto.cadastrarPL(dni, ano, ementa, interesses, url, conclusivo);
	}
	
	public void cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		this.controllerProjeto.cadastrarPLP(dni, ano, ementa, interesses, url, artigos);
	}
	
	public void cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		this.controllerProjeto.cadastrarPEC(dni, ano, ementa, interesses, url, artigos);
	}
	
	public String exibirProjeto(String codigo) {
		return this.controllerProjeto.exibirProjeto(codigo);
	}
	
	public boolean interessesComuns(String interesses1, String interesses2) {
		String[] i1 = interesses1.split(",");
		String[] i2 = interesses2.split(",");
		
		for (String i : i1) {
			for(String j : i2) {
				if (i.equals(j))
					return true;
			}
		}
		return false;
	}
	
	public boolean eDaBase(String dni) {
		for (String p : controller.getPartidos) {
			if (controllerPessoa.getPessoas().get(dni).getPartido().equals(p))
				return true;
		}
		return false;
	}
	
	public int contaVotos(String codigo, String statusGovernista, String politicos) {
		int votosAprovar = 0;

		String politcs[]  = politicos.split(",");
		for (String dni : politcs){
			validador.validaDni(dni, "Erro ao votar proposta: dni invalido");
			if(!controllerPessoa.getPessoas().containsKey(dni)) 
				throw new NullPointerException("Erro ao votar proposta: pessoa inexistente");
			if(controllerPessoa.getPessoas().get(dni).getDeputado() == null)
				throw new IllegalArgumentException("Erro ao votar proposta: pessoa nao eh deputado");

			if(statusGovernista.equals("GOVERNISTA") && eDaBase(dni))
				votosAprovar += 1;
			if(statusGovernista.equals("OPOSICAO") && !eDaBase(dni))
				votosAprovar += 1;
			if(statusGovernista.equals("LIVRE")){
				if(interessesComuns(controllerPessoa.getPessoas().get(dni).getInteresses(), controllerProjeto.getProjetos().get(codigo).getInteresses()))
					votosAprovar += 1;
			}
		}
		return votosAprovar;
	}
	
	public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {	
		int votosAprovar = contaVotos(codigo, statusGovernista, presentes);
		String presents[]  = presentes.split(",");
		int deputados = 0;
		for(String i : controllerPessoa.getPessoas().keySet()) {
			if(!(controllerPessoa.getPessoas().get(i).getDeputado() == null)) {
				deputados += 1;
			}
		}
		
		if (codigo.substring(0,3).equals("PL ")) {
			if (votosAprovar >= Math.floor((presents.length / 2) + 1))
				return true;
			
		}else if (!controllerProjeto.getProjetos().get(codigo).getTurno().equals("finalizado")) {
				if (codigo.substring(0,3).equals("PLP")) {
					if(controllerProjeto.getProjetos().get(codigo).getTurno().equals("1o turno"))
						controllerProjeto.getProjetos().get(codigo).setTurno("2o turno");
					else if(controllerProjeto.getProjetos().get(codigo).getTurno().equals("2o turno"))
						controllerProjeto.getProjetos().get(codigo).setTurno("finalizado");
					if(votosAprovar  >= (deputados/ 2) + 1)
						return true;
		
			}else if(codigo.substring(0,3).equals("PEC")) {
				if(controllerProjeto.getProjetos().get(codigo).getTurno().equals("1o turno"))
					controllerProjeto.getProjetos().get(codigo).setTurno("2o turno");
				else if(controllerProjeto.getProjetos().get(codigo).getTurno().equals("2o turno"))
					controllerProjeto.getProjetos().get(codigo).setTurno("finalizado");
				if(votosAprovar  >= (deputados * 5 / 3) + 1)
					return true;
		}
		}		
		return false;
	}
	
	public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
		if (controller.getComissoes().get(controllerProjeto.getProjetos().get(codigo).getLocalAtual()).getProjetosVotados().contains(codigo)) {
			int votosAprovados = contaVotos(codigo, statusGovernista, controller.getComissoes().get("CCJC").getPoliticos());
			String politicosCCJC[]  = controller.getComissoes().get("CCJC").getPoliticos().split(",");
			if (votosAprovados >= politicosCCJC.length / 2 + 1) {
				controller.getComissoes().get(controllerProjeto.getProjetos().get(codigo).getLocalAtual()).getProjetosVotados().add(codigo);
				controllerProjeto.getProjetos().get(codigo).setLocalAtual(proximoLocal);
				controllerPessoa.getPessoas().get(controllerProjeto.getProjetos().get(codigo).getAutor()).getDeputado().setLeisAprovadas((controllerPessoa.getPessoas().get(controllerProjeto.getProjetos().get(codigo).getAutor()).getDeputado().getLeisAprovadas()) + 1);
				return true;				
		} 
		}
		return false;
	}

}
