package br.com.zssn.apirest.vos;

public class InventarioVO {
	
	private long id;
	
	private Long iditemcomercial;

	private Long idsobrevivente;

	private int quantidade;

	private String descricao;

	private int pontuacao;
	
	private String nomeSobrevivente;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getIditemcomercial() {
		return iditemcomercial;
	}

	public void setIditemcomercial(Long iditemcomercial) {
		this.iditemcomercial = iditemcomercial;
	}

	public Long getIdsobrevivente() {
		return idsobrevivente;
	}

	public void setIdsobrevivente(Long idsobrevivente) {
		this.idsobrevivente = idsobrevivente;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public String getNomeSobrevivente() {
		return nomeSobrevivente;
	}

	public void setNomeSobrevivente(String nomeSobrevivente) {
		this.nomeSobrevivente = nomeSobrevivente;
	}
	

}
