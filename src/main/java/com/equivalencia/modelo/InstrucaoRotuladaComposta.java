package com.equivalencia.modelo;

import com.equivalencia.modelo.tipo.TipoInstrucao;

public class InstrucaoRotuladaComposta {
	private String rotulo;

	private TipoInstrucao tipo1;
	private String identificador1;
	private String rotulo1;

	private TipoInstrucao tipo2;
	private String identificador2;
	private String rotulo2;

	public void setInstrucaoRotulada(String identificador, String rotulo, TipoInstrucao tipo) {
		if (this.tipo1 == null) {
			this.tipo1 = tipo;
			this.identificador1 = identificador;
			this.rotulo1 = rotulo;
		} else {
			this.tipo2 = tipo;
			this.identificador2 = identificador;
			this.rotulo2 = rotulo;
		}
	}

	@Override
	public String toString() {
		StringBuffer primeiroConjunto = new StringBuffer("");
		primeiroConjunto.append("(" + identificador1 + "," + rotulo1 + ")");

		StringBuffer segundoConjunto = new StringBuffer("");
		segundoConjunto.append("(" + identificador2 + "," + rotulo2 + ")");

		return primeiroConjunto.toString() + segundoConjunto.toString();
	}

	public String toStringComRotulo() {
		StringBuffer primeiroConjunto = new StringBuffer(this.rotulo + ":");
		primeiroConjunto.append("(" + identificador1 + "," + rotulo1 + ")");

		StringBuffer segundoConjunto = new StringBuffer("");
		segundoConjunto.append("(" + identificador2 + "," + rotulo2 + ")");

		return primeiroConjunto.toString() + segundoConjunto.toString();
	}

	public TipoInstrucao getTipo1() {
		return tipo1;
	}

	public TipoInstrucao getTipo2() {
		return tipo2;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public String getRotulo() {
		return rotulo;
	}

}
