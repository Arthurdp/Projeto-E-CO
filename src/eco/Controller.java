package eco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * classe responsavel por controlar todo o sistema. controla o cadastro de pessoas,
 * deputados e partidos, e as representacoes de pessoas e partidos cadastrados.
 *
 */
public class Controller {
	
	private Validador validador;
	private List<String> partidos;
	private HashMap<String, Comissao> comissoes; 
	private ControllerPessoa cPessoa;
	private ControllerProjeto cProjeto;
	
	
	public Controller() {
		this.comissoes = new HashMap<>();
		this.partidos = new ArrayList<>();
		this.validador = new Validador();
		this.cPessoa = new ControllerPessoa();
		this.cProjeto = new ControllerProjeto();
	}
	
	/**
	 * cadastra um partido no sistema, adicionando uma string na lista de partidos.
	 * @param partido sigla do partido a ser cadastrado.
	 */
	public void cadastrarPartido(String partido) {
		validador.validaEntrada(partido,"Erro ao cadastrar partido: partido nao pode ser vazio ou nulo");
		this.partidos.add(partido);
	}
	
	/** 
	 * exibe todos os partidos cadastrados, em ordem lexicografica.
	 * @return as strings que representam os partidos cadastrados.
	 */
	public String exibirBase() {
		Collections.sort(partidos);
		String saida = "";
		for(String partido : partidos) {
			saida += partido + ",";
		}
		if (saida.length() > 0)
			return saida.substring(0, saida.length() - 1);
		else
			return saida;
	}
	/**
	 * Cadastra uma nova comissao inserindo-a no mapa de comissoes
	 * @param tema tema da comissao
	 * @param politicos string contendo os dni dos politicos participantes da comissao
	 */
	public void cadastrarComissao(String tema, String politicos){
		
		validador.validaEntrada(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
		validador.validaEntrada(politicos, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
		if(comissoes.containsKey(tema))
			throw new IllegalArgumentException("Erro ao cadastrar comissao: tema existente");
		String[] politics = politicos.split(",");
		for(String dni : politics) {
			validador.validaDni(dni, "Erro ao cadastrar comissao: dni invalido");
			if(!ControllerPessoa.contem(dni)) 
				throw new NullPointerException("Erro ao cadastrar comissao: pessoa inexistente");
			if(!ControllerPessoa.eDeputado(dni))
				throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa nao eh deputado");
		}
		Comissao nova = new Comissao(tema, politicos);
		comissoes.put(tema, nova);
		
	}

	public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
		switch (statusGovernista) {
		case ("GOVERNISTA"):
			
		}
		return false;
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
		for (String p : this.partidos) {
			if (cPessoa.getPessoa(dni).getPartido().equals(p))
				return true;
		}
		return false;
	}
	
	public int contaVotos(String codigo, String statusGovernista, String politicos) {
		int votosAprovar = 0;

		String politcs[]  = politicos.split(",");
		for (String dni : politcs){
			validador.validaDni(dni, "Erro ao votar proposta: dni invalido");
			if(!ControllerPessoa.contem(dni)) 
				throw new NullPointerException("Erro ao votar proposta: pessoa inexistente");
			if(!ControllerPessoa.eDeputado(dni))
				throw new IllegalArgumentException("Erro ao votar proposta: pessoa nao eh deputado");

			if(statusGovernista.equals("GOVERNISTA") && eDaBase(dni))
				votosAprovar += 1;
			if(statusGovernista.equals("OPOSICAO") && !eDaBase(dni))
				votosAprovar += 1;
			if(statusGovernista.equals("LIVRE")){
				if(interessesComuns(ControllerPessoa.getPessoa(dni).getInteresses(), ControllerProjeto.getProjeto(codigo).getInteresses()))
					votosAprovar += 1;
			}
		}
		return votosAprovar;
	}
	
	public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {	
		
		int votosAprovar = contaVotos(codigo, statusGovernista, presentes);
		String presents[]  = presentes.split(",");
		int deputados = 0;
		for(String i : cPessoa.getPessoas().keySet()) {
			if(ControllerPessoa.eDeputado(i)) {
				deputados += 1;
			}
		}
		
		if (codigo.substring(0,3).equals("PL ")) {
			if (votosAprovar >= Math.floor((presents.length / 2) + 1))
				return true;
			
		}else if(codigo.substring(0,3).equals("PLP")) {
			if(votosAprovar  >= (deputados/ 2) + 1)
				return true;
	
		}else if(codigo.substring(0,3).equals("PEC")) {
			if(votosAprovar  >= (deputados * 5 / 3) + 1)
				return true;
		}		
		return false;
	}
}

