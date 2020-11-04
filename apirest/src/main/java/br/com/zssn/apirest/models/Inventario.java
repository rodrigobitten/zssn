package br.com.zssn.apirest.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventario")
public class Inventario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private Long iditemcomercial;
	
	@Column(nullable = false)
	private Long idsobrevivente;
	
	@Column(nullable = false)
	private int quantidade;
	

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

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
	
	
	
}
