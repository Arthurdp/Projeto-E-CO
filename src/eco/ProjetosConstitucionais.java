package eco;

abstract class ProjetosConstitucionais extends Projeto {
	private String artigos;

	public ProjetosConstitucionais(String autor, int ano, String codigo, String ementa, String interessesRelacionados,
			String endereçoDoDocumento, String artigos) {
		super(autor, ano, codigo, ementa, interessesRelacionados, endereçoDoDocumento);
		this.artigos = artigos;
	}

	public String getArtigos() {
		return this.artigos;
	}
	
}
