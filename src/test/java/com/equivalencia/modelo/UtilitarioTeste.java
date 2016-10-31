package com.equivalencia.modelo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.equivalencia.mock.MOCKInstrucaoMonoliticaEntradaUsuario;
import com.equivalencia.utilitarios.Utilitario;

public class UtilitarioTeste {

	@Test
	public void teste_geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida_1() throws Exception {
		List<InstrucaoMonolitica> instrucoesMonoliticas1 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste1_programa1());
		List<InstrucaoMonolitica> instrucoesMonoliticas2 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste1_programa2());

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
	}

	@Test
	public void teste_geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida_2() throws Exception {
		List<InstrucaoMonolitica> instrucoesMonoliticas1 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste2_programa1());
		List<InstrucaoMonolitica> instrucoesMonoliticas2 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste2_programa2());

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

	}

	@Test
	public void teste_geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida_3() throws Exception {
		List<InstrucaoMonolitica> instrucoesMonoliticas1 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste3_programa1());
		List<InstrucaoMonolitica> instrucoesMonoliticas2 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste3_programa2());

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

	}

	@Test
	public void teste_geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida_4() throws Exception {
		List<InstrucaoMonolitica> instrucoesMonoliticas1 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste4_programa1());
		List<InstrucaoMonolitica> instrucoesMonoliticas2 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste4_programa2());

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

	}

}
