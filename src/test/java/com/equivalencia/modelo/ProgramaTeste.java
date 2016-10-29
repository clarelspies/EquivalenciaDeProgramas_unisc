package com.equivalencia.modelo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ProgramaTeste {

	// erro no rodulo 4 durante instanciacao do programa, "va para"
	@Test
	public void teste_Programa_1() {
		try {
			List<String> entradas1 = new ArrayList<>();
			entradas1.add("SE T1 VA-PARA 2 SENAO-va-para 3");
			entradas1.add("FACA F VA-PARA 6");
			entradas1.add("SE T2 VA-PARA 5 SENAO-VA-PARA 4");
			entradas1.add("FACA G VA PARA 7");
			entradas1.add("FACA F VA-PARA 0");
			entradas1.add("SE T3 VA-PARA 4 SENAO-VA-PARA-1");

			new Programa(entradas1);
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: [FACA G VA PARA 7]";
			assertEquals(message, e.getMessage());
		}

	}

	// erro no rodulo 5 durante instanciacao do programa, "va para"
	@Test
	public void teste_Programa_2() {
		try {
			List<String> entradas1 = new ArrayList<>();
			entradas1.add("SE T1 VA-PARA 2 SENAO-va-para 3");
			entradas1.add("FACA F VA-PARA 6");
			entradas1.add("SE T2 VA-PARA 5 SENAO-VA-PARA 4");
			entradas1.add("FACA G VA-PARA 7");
			entradas1.add("FACA F VA PARA 0");
			entradas1.add("SE T3 VA-PARA 4 SENAO-VA-PARA-1");

			new Programa(entradas1);
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: [FACA F VA PARA 0]";
			assertEquals(message, e.getMessage());
		}

	}

	// nenhum erro, intrucoes de entrada corretas
	@Test
	public void teste_Programa_3() {

		List<String> entradas1 = new ArrayList<>();
		entradas1.add("SE T1 VA-PARA 2 SENAO-va-para 3");
		entradas1.add("FACA F VA-PARA 6");
		entradas1.add("SE T2 VA-PARA 5 SENAO-VA-PARA 4");
		entradas1.add("FACA G VA-PARA 7");
		entradas1.add("FACA F VA-PARA 0");
		entradas1.add("SE T3 VA-PARA 4 SENAO-VA-PARA 1");
		try {
			new Programa(entradas1);
		} catch (Exception e) {
			String message = "";
			assertEquals(message, e.getMessage());
		}

	}

	@Test
	public void teste_geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida_1() {

		List<String> entradas1 = new ArrayList<>();
		entradas1.add("SE T1 VA-PARA 2 SENAO-va-para 3");
		entradas1.add("FACA F VA-PARA 6");
		entradas1.add("SE T2 VA-PARA 5 SENAO-VA-PARA 4");
		entradas1.add("FACA G VA-PARA 7");
		entradas1.add("FACA F VA-PARA 7");
		entradas1.add("SE T3 VA-PARA 4 SENAO-VA-PARA 1");

		try {
			List<InstrucaoMonolitica> instrucoesMonoliticas = new Programa(entradas1).getInstrucoesMonoliticas();

			assertEquals("(F,2)(G,4)", Programa.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(0, instrucoesMonoliticas).toString());
			assertEquals("(G,4)(G,4)", Programa.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(2, instrucoesMonoliticas).toString());
			assertEquals("(PARADA,&)(PARADA,&)", Programa.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(4, instrucoesMonoliticas).toString());
			assertEquals("(PARADA,&)(PARADA,&)", Programa.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(5, instrucoesMonoliticas).toString());
		} catch (Exception e) {
			String message = "";
			assertEquals(message, e.getMessage());
		}
	}

}
