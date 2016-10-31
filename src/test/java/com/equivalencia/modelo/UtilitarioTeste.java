package com.equivalencia.modelo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.equivalencia.utilitarios.Utilitario;

public class UtilitarioTeste {

	@Test
	public void teste_geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida_1() {
		List<String> entradas1 = new ArrayList<>();
		entradas1.add("SE T1 VA-PARA 2 SENAO-va-para 3");
		entradas1.add("FACA F VA-PARA 6");
		entradas1.add("SE T2 VA-PARA 5 SENAO-VA-PARA 4");
		entradas1.add("FACA G VA-PARA 7");
		entradas1.add("FACA F VA-PARA 7");
		entradas1.add("SE T3 VA-PARA 4 SENAO-VA-PARA 1");

		List<String> entradas2 = new ArrayList<>();

		entradas2.add("FACA F VA-PARA 2");
		entradas2.add("SE T1 VA-PARA 3 SENAO-va-para 1");
		entradas2.add("FACA g VA-PARA 4");
		entradas2.add("SE T2 VA-PARA 1 SENAO-VA-PARA 5");
		entradas2.add("FACA h VA-PARA 6");
		entradas2.add("SE T3 VA-PARA 7 SENAO-VA-PARA 5");

		try {
			List<InstrucaoMonolitica> instrucoesMonoliticas1 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(entradas1);
			List<InstrucaoMonolitica> instrucoesMonoliticas2 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(entradas2);

			Utilitario utilitario = new Utilitario();
			utilitario.rotulaOperacoesEmOrdemExecucao(instrucoesMonoliticas1);
			utilitario.rotulaOperacoesEmOrdemExecucao(instrucoesMonoliticas2);

			assertEquals("(F,2)(G,4)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(0, instrucoesMonoliticas1).toString());
			assertEquals("(G,4)(G,4)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(2, instrucoesMonoliticas1).toString());
			assertEquals("(PARADA,Σ)(PARADA,Σ)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(4, instrucoesMonoliticas1).toString());
			assertEquals("(PARADA,Σ)(PARADA,Σ)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(5, instrucoesMonoliticas1).toString());

			assertEquals("(F,6)(F,6)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(0, instrucoesMonoliticas2).toString());
			assertEquals("(G,7)(F,6)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(2, instrucoesMonoliticas2).toString());
			assertEquals("(F,6)(H,8)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(4, instrucoesMonoliticas2).toString());
			assertEquals("(PARADA,Σ)(H,8)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(5, instrucoesMonoliticas2).toString());
		} catch (Exception e) {
			String message = "";
			assertEquals(message, e.getMessage());
		}
	}

	@Test
	public void teste_geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida_2() {
		List<String> entradas1 = new ArrayList<>();
		entradas1.add("SE T1 VA-PARA 2 SENAO-va-para 5");
		entradas1.add("FACA F VA-PARA 3");
		entradas1.add("SE T2 VA-PARA 1 SENAO-VA-PARA 4");
		entradas1.add("FACA G VA-PARA 1");

		List<String> entradas2 = new ArrayList<>();

		entradas2.add("SE T1 VA-PARA 2 SENAO-va-para 6");
		entradas2.add("FACA F VA-PARA 3");
		entradas2.add("SE T2 VA-PARA 4 SENAO-VA-PARA 5");
		entradas2.add("FACA F VA-PARA 3");
		entradas2.add("FACA G VA-PARA 1");

		try {
			List<InstrucaoMonolitica> instrucoesMonoliticas1 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(entradas1);
			List<InstrucaoMonolitica> instrucoesMonoliticas2 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(entradas2);

			Utilitario utilitario = new Utilitario();
			utilitario.rotulaOperacoesEmOrdemExecucao(instrucoesMonoliticas1);
			utilitario.rotulaOperacoesEmOrdemExecucao(instrucoesMonoliticas2);

			assertEquals("(F,2)(PARADA,Σ)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(0, instrucoesMonoliticas1).toString());
			assertEquals("(F,2)(G,3)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(2, instrucoesMonoliticas1).toString());
			assertEquals("(F,2)(PARADA,Σ)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(4, instrucoesMonoliticas1).toString());

			assertEquals("(F,5)(PARADA,Σ)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(0, instrucoesMonoliticas2).toString());
			assertEquals("(F,6)(G,7)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(2, instrucoesMonoliticas2).toString());
			assertEquals("(F,6)(G,7)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(4, instrucoesMonoliticas2).toString());
			assertEquals("(F,5)(PARADA,Σ)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(5, instrucoesMonoliticas2).toString());
		} catch (Exception e) {
			String message = "";
			assertEquals(message, e.getMessage());
		}
	}

	@Test
	public void teste_geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida_3() {
		List<String> entradas1 = new ArrayList<>();
		entradas1.add("Se T1 va-para 2 senao-va-para 6");
		entradas1.add("faca f va-para 3");
		entradas1.add("Se T2 va-para 1 senao-va-para 4");
		entradas1.add("Faca g va-para 1");

		List<String> entradas2 = new ArrayList<>();

		entradas2.add("SE T1 VA-PARA 2 SENAO-va-para 6");
		entradas2.add("FACA F VA-PARA 3");
		entradas2.add("SE T2 VA-PARA 4 SENAO-VA-PARA 5");
		entradas2.add("FACA F VA-PARA 3");
		entradas2.add("FACA G VA-PARA 1");

		try {
			List<InstrucaoMonolitica> instrucoesMonoliticas1 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(entradas1);
			List<InstrucaoMonolitica> instrucoesMonoliticas2 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(entradas2);

			Utilitario utilitario = new Utilitario();
			utilitario.rotulaOperacoesEmOrdemExecucao(instrucoesMonoliticas1);
			utilitario.rotulaOperacoesEmOrdemExecucao(instrucoesMonoliticas2);

			assertEquals("(F,2)(PARADA,Σ)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(0, instrucoesMonoliticas1).toString());
			assertEquals("(F,2)(G,3)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(2, instrucoesMonoliticas1).toString());
			assertEquals("(F,2)(PARADA,Σ)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(4, instrucoesMonoliticas1).toString());

			assertEquals("(F,5)(PARADA,Σ)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(0, instrucoesMonoliticas2).toString());
			assertEquals("(F,6)(G,7)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(2, instrucoesMonoliticas2).toString());
			assertEquals("(F,6)(G,7)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(4, instrucoesMonoliticas2).toString());
			assertEquals("(F,5)(PARADA,Σ)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(5, instrucoesMonoliticas2).toString());
		} catch (Exception e) {
			String message = "";
			assertEquals(message, e.getMessage());
		}
	}

	@Test
	public void teste_geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida_4() {

		List<String> entradas1 = new ArrayList<>();
		entradas1.add("Se T1 va-para 2 senao-va-para 1");
		entradas1.add("Se T2 va-para 3 senao-va-para 8");
		entradas1.add("FACA F VA-PARA 4");
		entradas1.add("FACA G VA-PARA 5");
		entradas1.add("Se T3 va-para 6 senao-va-para 2");
		entradas1.add("FACA F VA-PARA 7");
		entradas1.add("Se T4 va-para 2 senao-va-para 7");

		List<String> entradas2 = new ArrayList<>();

		entradas2.add("SE T1 VA-PARA 2 SENAO-va-para 1");
		entradas2.add("FACA F VA-PARA 3");
		entradas2.add("FACA G VA-PARA 4");
		entradas2.add("SE T2 VA-PARA 5 SENAO-VA-PARA 6");
		entradas2.add("FACA F VA-PARA 1");

		try {
			List<InstrucaoMonolitica> instrucoesMonoliticas1 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(entradas1);
			List<InstrucaoMonolitica> instrucoesMonoliticas2 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(entradas2);

			Utilitario utilitario = new Utilitario();
			utilitario.rotulaOperacoesEmOrdemExecucao(instrucoesMonoliticas1);
			utilitario.rotulaOperacoesEmOrdemExecucao(instrucoesMonoliticas2);

			assertEquals("(F,2)(CICLO,ω)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(0, instrucoesMonoliticas1).toString());
			assertEquals("(G,3)(G,3)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(3, instrucoesMonoliticas1).toString());
			assertEquals("(F,4)(PARADA,Σ)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(4, instrucoesMonoliticas1).toString());
			assertEquals("(F,2)(CICLO,ω)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(6, instrucoesMonoliticas1).toString());

			assertEquals("(F,6)(CICLO,ω)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(0, instrucoesMonoliticas2).toString());
			assertEquals("(G,7)(G,7)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(2, instrucoesMonoliticas2).toString());
			assertEquals("(F,8)(PARADA,Σ)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(4, instrucoesMonoliticas2).toString());
			assertEquals("(F,6)(CICLO,ω)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(0, instrucoesMonoliticas2).toString());
		} catch (Exception e) {
			String message = "";
			assertEquals(message, e.getMessage());
		}
	}

	// VIDEO ANDREA
	@Test
	public void teste_geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida_5() {

		List<String> entradas1 = new ArrayList<>();

		entradas1.add("Se T1 va-para 2 senao-va-para 3");// 1
		entradas1.add("FACA G VA-PARA 1");// 2
		entradas1.add("FACA F VA-PARA 4");// 3
		entradas1.add("Se T2 va-para 5 senao-va-para 6");// 4
		entradas1.add("FACA F VA-PARA 4");// 5
		entradas1.add("FACA G VA-PARA 7");// 6
		entradas1.add("se t3 VA-PARA 8 senao-va-para 9"); // 7
		entradas1.add("FACA F va-para 10"); // 8
		entradas1.add("se t4 VA-PARA 13 senao-va-para 7");// 9
		entradas1.add("se t5 VA-PARA 13 senao-va-para 11"); // 10
		entradas1.add("FACA G va-para 11"); // 11

		List<String> entradas2 = new ArrayList<>();
		entradas2.add("Se T1 va-para 2 senao-va-para 3");// 1
		entradas2.add("FACA G VA-PARA 1");// 2
		entradas2.add("FACA F VA-PARA 4");// 3
		entradas2.add("Se T2 va-para 3 senao-va-para 5");// 4
		entradas2.add("FACA G VA-PARA 6");// 5
		entradas2.add("Se T3 va-para 7 senao-va-para 8");// 6
		entradas2.add("FACA F VA-PARA 9");// 7
		entradas2.add("FACA F VA-PARA 8");// 8
		entradas2.add("Se T4 va-para 10 senao-va-para 6");// 9

		try {
			List<InstrucaoMonolitica> instrucoesMonoliticas1 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(entradas1);
			List<InstrucaoMonolitica> instrucoesMonoliticas2 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(entradas2);

			Utilitario utilitario = new Utilitario();
			utilitario.rotulaOperacoesEmOrdemExecucao(instrucoesMonoliticas1);
			utilitario.rotulaOperacoesEmOrdemExecucao(instrucoesMonoliticas2);

			assertEquals("(G,2)(F,3)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(0, instrucoesMonoliticas1).toString());
			assertEquals("(G,2)(F,3)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(2, instrucoesMonoliticas1).toString());
			assertEquals("(F,4)(G,5)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(3, instrucoesMonoliticas1).toString());
			assertEquals("(F,4)(G,5)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(5, instrucoesMonoliticas1).toString());
			assertEquals("(F,6)(CICLO,ω)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(6, instrucoesMonoliticas1).toString());
			assertEquals("(PARADA,Σ)(G,7)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(8, instrucoesMonoliticas1).toString());
			assertEquals("(G,7)(G,7)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(11, instrucoesMonoliticas1).toString());

			assertEquals("(G,9)(F,10)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(0, instrucoesMonoliticas2).toString());
			assertEquals("(G,9)(F,10)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(2, instrucoesMonoliticas2).toString());
			assertEquals("(F,10)(G,11)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(3, instrucoesMonoliticas2).toString());
			assertEquals("(F,12)(F,13)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(5, instrucoesMonoliticas2).toString());
			assertEquals("(PARADA,Σ)(F,13)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(7, instrucoesMonoliticas2).toString());
			assertEquals("(F,13)(F,13)", Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(8, instrucoesMonoliticas2).toString());

		} catch (Exception e) {
			String message = "";
			assertEquals(message, e.getMessage());
		}
	}

}
