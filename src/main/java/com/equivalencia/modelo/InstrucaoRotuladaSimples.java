package com.equivalencia.modelo;

public class InstrucaoRotuladaSimples {

	private boolean instrucaoValida;
	private TipoInstrucao tipo;
	private String nomeInstrucao;

	private int destinoOperacao;

	private int destinoTesteVerdadeiro;
	private int destinoTesteFalso;

	public boolean isInstrucaoValida() {
		return instrucaoValida;
	}

	public void setInstrucaoValida(boolean instrucaoValida) {
		this.instrucaoValida = instrucaoValida;
	}

	public TipoInstrucao getTipo() {
		return tipo;
	}

	public void setTipo(TipoInstrucao tipo) {
		this.tipo = tipo;
	}

	public String getNomeInstrucao() {
		return nomeInstrucao;
	}

	public void setNomeInstrucao(String nomeInstrucao) {
		this.nomeInstrucao = nomeInstrucao;
	}

	public int getDestinoOperacao() {
		return destinoOperacao;
	}

	public void setDestinoOperacao(int destinoOperacao) {
		this.destinoOperacao = destinoOperacao;
	}

	public int getDestinoTesteVerdadeiro() {
		return destinoTesteVerdadeiro;
	}

	public void setDestinoTesteVerdadeiro(int destinoTesteVerdadeiro) {
		this.destinoTesteVerdadeiro = destinoTesteVerdadeiro;
	}

	public int getDestinoTesteFalso() {
		return destinoTesteFalso;
	}

	public void setDestinoTesteFalso(int destinoTesteFalso) {
		this.destinoTesteFalso = destinoTesteFalso;
	}

}
