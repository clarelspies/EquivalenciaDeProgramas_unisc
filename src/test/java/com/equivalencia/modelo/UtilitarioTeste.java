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

		List<InstrucaoRotuladaComposta> instrucoesCompostas1 = utilitario.geraInstrucoesRotuladasCompostas(instrucoesMonoliticas1);
		List<InstrucaoRotuladaComposta> instrucoesCompostas2 = utilitario.geraInstrucoesRotuladasCompostas(instrucoesMonoliticas2);

		assertEquals("1:(F,2)(CICLO,ω)", instrucoesCompostas1.get(0).toStringComRotulo());
		assertEquals("2:(G,3)(G,3)", instrucoesCompostas1.get(1).toStringComRotulo());
		assertEquals("3:(F,4)(PARADA,Σ)", instrucoesCompostas1.get(2).toStringComRotulo());
		assertEquals("4:(F,2)(CICLO,ω)", instrucoesCompostas1.get(3).toStringComRotulo());
		assertEquals("ω:(CICLO,ω)(CICLO,ω)", instrucoesCompostas1.get(4).toStringComRotulo());

		assertEquals("5:(F,6)(CICLO,ω)", instrucoesCompostas2.get(0).toStringComRotulo());
		assertEquals("6:(G,7)(G,7)", instrucoesCompostas2.get(1).toStringComRotulo());
		assertEquals("7:(F,8)(PARADA,Σ)", instrucoesCompostas2.get(2).toStringComRotulo());
		assertEquals("8:(F,6)(CICLO,ω)", instrucoesCompostas2.get(3).toStringComRotulo());
		assertEquals("ω:(CICLO,ω)(CICLO,ω)", instrucoesCompostas1.get(4).toStringComRotulo());

		List<CadeiaConjuntoFinito> conjuntos1 = Utilitario.defineCadeiaConjuntoFinito(instrucoesCompostas1);
		List<CadeiaConjuntoFinito> conjuntos2 = Utilitario.defineCadeiaConjuntoFinito(instrucoesCompostas2);

		assertEquals("A0:{Σ}", conjuntos1.get(0).toString());
		assertEquals("A1:{Σ,3}", conjuntos1.get(1).toString());
		assertEquals("A2:{Σ,3,2}", conjuntos1.get(2).toString());
		assertEquals("A3:{Σ,3,2,1,4}", conjuntos1.get(3).toString());
		assertEquals("A4:{Σ,3,2,1,4}", conjuntos1.get(4).toString());

		assertEquals("A0:{Σ}", conjuntos2.get(0).toString());
		assertEquals("A1:{Σ,7}", conjuntos2.get(1).toString());
		assertEquals("A2:{Σ,7,6}", conjuntos2.get(2).toString());
		assertEquals("A3:{Σ,7,6,5,8}", conjuntos2.get(3).toString());
		assertEquals("A4:{Σ,7,6,5,8}", conjuntos2.get(4).toString());
	}

	@Test
	public void teste_geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida_4() throws Exception {
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

		List<CadeiaConjuntoFinito> conjuntos1 = Utilitario.defineCadeiaConjuntoFinito(instrucoesCompostas1);
		List<CadeiaConjuntoFinito> conjuntos2 = Utilitario.defineCadeiaConjuntoFinito(instrucoesCompostas2);

		assertEquals("A0:{Σ}", conjuntos1.get(0).toString());
		assertEquals("A1:{Σ,6}", conjuntos1.get(1).toString());
		assertEquals("A2:{Σ,6,5}", conjuntos1.get(2).toString());
		assertEquals("A3:{Σ,6,5,4,3}", conjuntos1.get(3).toString());
		assertEquals("A4:{Σ,6,5,4,3,2,1}", conjuntos1.get(4).toString());
		assertEquals("A5:{Σ,6,5,4,3,2,1}", conjuntos1.get(5).toString());

		assertEquals("A0:{Σ}", conjuntos2.get(0).toString());
		assertEquals("A1:{Σ,12}", conjuntos2.get(1).toString());
		assertEquals("A2:{Σ,12,11}", conjuntos2.get(2).toString());
		assertEquals("A3:{Σ,12,11,10}", conjuntos2.get(3).toString());
		assertEquals("A4:{Σ,12,11,10,9,8}", conjuntos2.get(4).toString());
		assertEquals("A5:{Σ,12,11,10,9,8}", conjuntos2.get(5).toString());

	}

}
