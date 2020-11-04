package br.com.zssn.apirest.vos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatorioMediaItemSobreviventeVO {

	@JsonProperty("Item")
	private String descricao;

	@JsonProperty("Media")
	private BigDecimal media;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getMedia() {
		return media;
	}

	public void setMedia(BigDecimal media) {
		this.media = media;
	}
	
}
