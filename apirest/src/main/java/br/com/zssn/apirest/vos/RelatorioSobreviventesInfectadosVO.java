package br.com.zssn.apirest.vos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatorioSobreviventesInfectadosVO {
	@JsonProperty("Sobreviventes Infectados")
	private BigDecimal sim;

	@JsonProperty("Sobreviventes Nao Infectados")
	private BigDecimal nao;

	public BigDecimal getSim() {
		return sim;
	}

	public void setSim(BigDecimal sim) {
		this.sim = sim;
	}

	public BigDecimal getNao() {
		return nao;
	}

	public void setNao(BigDecimal nao) {
		this.nao = nao;
	}


}
