package br.com.zssn.apirest.vos;

public class SinalizaInfeccaoVO {
	
	private int id;
	
	private int idsobrevivente;
	
	private String nomesobrevivente;
	
	private int quantidade;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdsobrevivente() {
		return idsobrevivente;
	}

	public void setIdsobrevivente(int idsobrevivente) {
		this.idsobrevivente = idsobrevivente;
	}

	public String getNomesobrevivente() {
		return nomesobrevivente;
	}

	public void setNomesobrevivente(String nomesobrevivente) {
		this.nomesobrevivente = nomesobrevivente;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	

	
}
