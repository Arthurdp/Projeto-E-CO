package eco;

public class Deputado implements Funcao{
	private String data;
	private int leisAprovadas;
	
	public Deputado(String data) {
		this.data = data;
		this.leisAprovadas = 0;
	}

	public String getData() {
		return data;
	}

	public int getLeisAprovadas() {
		return leisAprovadas;
	}

	public void setLeisAprovadas(int leisAprovadas) {
		this.leisAprovadas = leisAprovadas;
	}
	
	 
}
