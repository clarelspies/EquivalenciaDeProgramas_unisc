package com.equivalencia.modelo;

public class ResultadoEquivalencia {
	private boolean saoEquivalentes;
	private String mensagem;

	public boolean isSaoEquivalentes() {
		return saoEquivalentes;
	}

	public void setSaoEquivalentes(boolean saoEquivalentes) {
		this.saoEquivalentes = saoEquivalentes;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
