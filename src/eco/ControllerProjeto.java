package eco;

import java.util.HashMap;
import java.util.Map;

public class ControllerProjeto {
	
	private Validador validador = new Validador();
	private Map<String, Projeto> projetos;
	private int contadorPL;
	private int contadorPLP;
	private int contadorPEC;
	
	public ControllerProjeto(){
		this.projetos = new HashMap<>();
		this.contadorPL = 1;
		this.contadorPLP = 1;
		this.contadorPEC = 1;
	}
	
	
	public void cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		validador.validaEntrada(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		validador.validaEntrada(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		validador.validaEntrada(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		validador.validaEntrada(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		if(!ControllerPessoa.contem(dni)) 
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		if(!ControllerPessoa.eDeputado(dni))
			throw new IllegalArgumentException("Erro ao cadastrar projeto: pessoa nao eh deputado");
		if(ano < 1988)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano anterior a 1988");
		if(ano > 2019)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano posterior ao ano atual");
		String key = "PL " + this.contadorPL + "/" + ano;
		Projeto novoProjeto = new PL(dni, ano, this.contadorPL, ementa, interesses, url, conclusivo);
		this.projetos.put(key,novoProjeto );
		this.contadorPL++;
	}
	
	public void cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		validador.validaEntrada(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		validador.validaEntrada(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		validador.validaEntrada(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		validador.validaEntrada(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		validador.validaEntrada(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		if(!ControllerPessoa.contem(dni)) 
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		if(!ControllerPessoa.eDeputado(dni))
			throw new IllegalArgumentException("Erro ao cadastrar projeto: pessoa nao eh deputado");
		if(ano < 1988)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano anterior a 1988");
		if(ano > 2019)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano posterior ao ano atual");
		String key = "PLP " + this.contadorPLP + "/" + ano;
		Projeto novoProjeto = new PLP(dni, ano, this.contadorPLP, ementa, interesses, url, artigos);
		this.projetos.put(key, novoProjeto);
		this.contadorPLP++;
	}
	
	public void cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		validador.validaEntrada(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		validador.validaEntrada(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		validador.validaEntrada(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		validador.validaEntrada(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		validador.validaEntrada(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		validador.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		if(!ControllerPessoa.contem(dni)) 
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		if(!ControllerPessoa.eDeputado(dni))
			throw new IllegalArgumentException("Erro ao cadastrar projeto: pessoa nao eh deputado");
		if(ano < 1988)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano anterior a 1988");
		if(ano > 2019)
			throw new IllegalArgumentException("Erro ao cadastrar projeto: ano posterior ao ano atual");
		String key = "PEC " + this.contadorPEC + "/" + ano;
		Projeto novoProjeto = new PEC(dni, ano, this.contadorPEC, ementa, interesses, url, artigos);
		this.projetos.put(key, novoProjeto);
		this.contadorPEC++;
	}
	
	public String exibirProjeto(String codigo) {
		return this.projetos.get(codigo).toString();
	}
	
	
	

}
