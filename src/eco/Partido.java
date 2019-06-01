package eco;

public class Partido implements Comparable<Partido> {
	private String nome;
	
	public Partido(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public int compareTo(Partido p) {
		return nome.compareTo(p.getNome());
	}
}
