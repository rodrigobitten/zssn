package br.com.zssn.apirest.vos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatorioPontosPerdidosVO {

		@JsonProperty("Pontos Perdidos")
		private int pontosPerdidos;

		public int getPontosPerdidos() {
			return pontosPerdidos;
		}

		public void setPontosPerdidos(int pontosPerdidos) {
			this.pontosPerdidos = pontosPerdidos;
		}


}
