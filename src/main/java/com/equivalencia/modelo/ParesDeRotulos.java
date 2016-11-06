package com.equivalencia.modelo;

public class ParesDeRotulos {
	private String rotulo;
	private String conjunto;

	public ParesDeRotulos(InstrucaoRotuladaComposta instrucao1, InstrucaoRotuladaComposta instrucao2) {
		this.conjunto = "{(" + instrucao1.getRotulo() + "," + instrucao2.getRotulo() + ")}";
		this.rotulo = "B0";
	}

	public ParesDeRotulos(int rotulo, InstrucaoRotuladaComposta instrucao1, InstrucaoRotuladaComposta instrucao2) {
		String conjunto1 = "(" + instrucao1.getRotulo1() + "," + instrucao2.getRotulo1() + ")";
		String conjunto2 = "(" + instrucao1.getRotulo2() + "," + instrucao2.getRotulo2() + ")";
		this.conjunto = "{" + conjunto1 + "," + conjunto2 + "}";

		this.rotulo = "B" + rotulo;
	}

	public ParesDeRotulos(int rotulo) {
		this.rotulo = "B" + rotulo;
		this.conjunto = "Ø";
	}

	@Override
	public String toString() {
		return this.rotulo + ":" + this.conjunto;
	}

}
