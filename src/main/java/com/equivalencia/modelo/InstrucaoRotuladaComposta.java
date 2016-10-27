package com.equivalencia.modelo;

import com.equivalencia.modelo.tipo.TipoInstrucao;

//CADA INSTRUCAO ROTULADA É COMPOSTA POR DUAS INTRUCOES
public class InstrucaoRotuladaComposta {

	private TipoInstrucao tipo1;
	private String nomeInstrucao1;

	private TipoInstrucao tipo2;
	private String nomeInstrucao2;

	// além de assumir valores numéricos, pode assumir simbolo infinito para ciclo ou E em paradas.
	private String rotulo1;
	private String rotulo2;

	public TipoInstrucao getTipo1() {
		return tipo1;
	}

	public void setTipo1(TipoInstrucao tipo1) {
		this.tipo1 = tipo1;
	}

	public String getNomeInstrucao1() {
		return nomeInstrucao1;
	}

	public void setNomeInstrucao1(String nomeInstrucao1) {
		this.nomeInstrucao1 = nomeInstrucao1;
	}

	public TipoInstrucao getTipo2() {
		return tipo2;
	}

	public void setTipo2(TipoInstrucao tipo2) {
		this.tipo2 = tipo2;
	}

	public String getNomeInstrucao2() {
		return nomeInstrucao2;
	}

	public void setNomeInstrucao2(String nomeInstrucao2) {
		this.nomeInstrucao2 = nomeInstrucao2;
	}

	public String getRotulo1() {
		return rotulo1;
	}

	public void setRotulo1(String rotulo1) {
		this.rotulo1 = rotulo1;
	}

	public String getRotulo2() {
		return rotulo2;
	}

	public void setRotulo2(String rotulo2) {
		this.rotulo2 = rotulo2;
	}

}
