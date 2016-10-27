package com.equivalencia.modelo;

import com.equivalencia.modelo.tipo.TipoInstrucao;

public class InstrucaoRotuladaComposta {

	private TipoInstrucao tipo1;
	private String identificador1;

	private TipoInstrucao tipo2;
	private String identificador2;

	// além de assumir valores numéricos, pode assumir simbolo infinito para ciclo ou E em paradas.
	private String rotulo1;
	private String rotulo2;

}
