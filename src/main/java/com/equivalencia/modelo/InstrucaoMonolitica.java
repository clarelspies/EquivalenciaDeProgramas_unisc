package com.equivalencia.modelo;

import java.util.ArrayList;
import java.util.List;

//modelo para sepresentar uma instrucao em formato monolitico
public class InstrucaoMonolitica {

	private TipoInstrucao tipo;
	private String identificador;

	// conta qual o numero da operação no fluxograma!
	private String rotuloOperacao;

	private int destinoOperacao;

	private int destinoTesteVerdadeiro;
	private int destinoTesteFalso;

	public InstrucaoMonolitica() {
		this.tipo = TipoInstrucao.PARTIDA;
		this.destinoOperacao = 1;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public TipoInstrucao getTipo() {
		return tipo;
	}

	public void setTipo(TipoInstrucao tipo) {
		this.tipo = tipo;
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

	public static List<InstrucaoMonolitica> criaListaInstrucoesMonoliticasParaPrograma(List<String> entradas) throws Exception {
		List<InstrucaoMonolitica> instrucoes = new ArrayList<>();
		instrucoes.add(new InstrucaoMonolitica());
		for (String entrada : entradas) {
			InstrucaoMonolitica instrucao = new InstrucaoMonolitica(entrada);
			instrucoes.add(instrucao);
		}
		return instrucoes;
	}

	public InstrucaoMonolitica(String entrada) throws Exception {
		boolean instrucaoValida = false;

		entrada = entrada.replaceAll("  ", " ").toUpperCase().trim();
		String partes[] = entrada.split(" ");

		this.identificador = partes[1];

		// para saber se e teste, instrucao deve comecar com SE e o rotulo deve começar com T: T1, TT1, TT2, T2
		if (partes[0].equals("SE") && partes[1].charAt(0) == 'T') {
			this.tipo = TipoInstrucao.TESTE;
			this.destinoTesteVerdadeiro = new Integer(partes[3]);
			this.destinoTesteFalso = new Integer(partes[5]);

			if (!entrada.contains("VA-PARA") || !entrada.contains("SENAO-VA-PARA")) {
				instrucaoValida = false;
			} else {
				instrucaoValida = true;
			}

		}
		// para saber se e operacao, intrucao deve comecar com FACA e o rodulo nao deve comecar com T: E1, F1, F, G, ET
		if (partes[0].equals("FACA") && partes[1].charAt(0) != 'T') {
			this.tipo = TipoInstrucao.OPERACAO;
			this.destinoOperacao = new Integer(partes[3]);
			instrucaoValida = true;
			
			if (!entrada.contains("VA-PARA") || entrada.contains("SENAO-VA-PARA")) {
				instrucaoValida = false;
			} else {
				instrucaoValida = true;
			}
		}

		// teste se a instrucao do usuario era valida
		if (!instrucaoValida) {
			throw new Exception();
		}
	}

	public int buscaIndexProximaInstrucaoExecutada(boolean primeiroConjunto) {
		// SE FOR OPERACAO, NAO IMPORTA SE É PRIMEIRO OU SEGUNDO CONJUNTO.. O RESULTADO É IGUAL.
		if (this.getTipo() == TipoInstrucao.OPERACAO || this.getTipo() == TipoInstrucao.PARTIDA) {
			return this.getDestinoOperacao();
		} else {
			// SE FOR TESTE, A PRIMEIRA COLUNA REPRESENTA VERDADEIRO E A SEGUNDA FALSO.
			if (primeiroConjunto) {
				return this.getDestinoTesteVerdadeiro();
			} else {
				return this.getDestinoTesteFalso();
			}
		}

	}

	public String getRotuloOperacao() {
		return rotuloOperacao;
	}

	public void setRotuloOperacao(String rotuloOperacao) {
		this.rotuloOperacao = rotuloOperacao;
	}

}
