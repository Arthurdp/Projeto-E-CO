package eco;
/**
 * Representação de um partido.
 */
public class Partido implements Comparable<Partido> {
	/**
	 * nome do partido.
	 */
	private String nome;
	/**
	 * constroi um novo partido.
	 * @param nome nome do novo partido.
	 */
	public Partido(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	/**
	 * mostra a representação de um partido no formato string.
	 */
	@Override
	public String toString() {
		return nome;
	}
	/**
	 * permite a comparação de dois partidos.
	 */
	@Override
	public int compareTo(Partido p) {
		return nome.compareTo(p.getNome());
	}
}
