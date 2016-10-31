package com.equivalencia.modelo;

import static org.junit.Assert.*;

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

	@Test
	public void teste_geraInstrucoesRotuladasCompostas_1() throws Exception {
		List<InstrucaoMonolitica> instrucoesMonoliticas1 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste4_programa1());
		List<InstrucaoMonolitica> instrucoesMonoliticas2 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste4_programa2());

		Utilitario utilitario = new Utilitario();
		utilitario.rotulaOperacoesEmOrdemExecucao(instrucoesMonoliticas1);
		utilitario.rotulaOperacoesEmOrdemExecucao(instrucoesMonoliticas2);

		List<InstrucaoRotuladaComposta> instrucoesCompostas1 = utilitario.geraInstrucoesRotuladasCompostas(instrucoesMonoliticas1);
		List<InstrucaoRotuladaComposta> instrucoesCompostas2 = utilitario.geraInstrucoesRotuladasCompostas(instrucoesMonoliticas2);

		assertEquals("1:(G,2)(F,3)", instrucoesCompostas1.get(0).toStringComRotulo());
		assertEquals("2:(G,2)(F,3)", instrucoesCompostas1.get(1).toStringComRotulo());
		assertEquals("3:(F,4)(G,5)", instrucoesCompostas1.get(2).toStringComRotulo());
		assertEquals("4:(F,4)(G,5)", instrucoesCompostas1.get(3).toStringComRotulo());
		assertEquals("5:(F,6)(CICLO,ω)", instrucoesCompostas1.get(4).toStringComRotulo());
		assertEquals("6:(PARADA,Σ)(G,7)", instrucoesCompostas1.get(5).toStringComRotulo());
		assertEquals("7:(G,7)(G,7)", instrucoesCompostas1.get(6).toStringComRotulo());
		assertEquals("ω:(CICLO,ω)(CICLO,ω)", instrucoesCompostas1.get(7).toStringComRotulo());

		assertEquals("8:(G,9)(F,10)", instrucoesCompostas2.get(0).toStringComRotulo());
		assertEquals("9:(G,9)(F,10)", instrucoesCompostas2.get(1).toStringComRotulo());
		assertEquals("10:(F,10)(G,11)", instrucoesCompostas2.get(2).toStringComRotulo());
		assertEquals("11:(F,12)(F,13)", instrucoesCompostas2.get(3).toStringComRotulo());
		assertEquals("12:(PARADA,Σ)(F,13)", instrucoesCompostas2.get(4).toStringComRotulo());
		assertEquals("13:(F,13)(F,13)", instrucoesCompostas2.get(5).toStringComRotulo());
	}

}
