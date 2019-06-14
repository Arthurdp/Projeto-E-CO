package eco;

abstract class ProjetosConstitucionais extends Projeto {
	private String artigos;

	public ProjetosConstitucionais(String dni, int ano, String codigo, String ementa, String interesses,
			String url, String artigos) {
		super(dni, ano, codigo, ementa, interesses, url);
		this.artigos = artigos;
	}

	public String getArtigos() {
		return this.artigos;
	}
	
}
