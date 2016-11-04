package com.equivalencia.modelo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.equivalencia.mock.MOCKInstrucaoMonoliticaEntradaUsuario;

public class EquivalenciaTeste {

	@Test
	public void teste_equivalencia_1() throws Exception {
		List<InstrucaoMonolitica> instrucoesMonoliticas1 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste1_programa1());
		List<InstrucaoMonolitica> instrucoesMonoliticas2 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste1_programa2());

		Equivalencia programa = new Equivalencia(instrucoesMonoliticas1, instrucoesMonoliticas2);

		assertEquals("1:(F,2)(G,4)", programa.getInstrucoesCompostas1().get(0).toStringComRotulo());
		assertEquals("2:(G,4)(G,4)", programa.getInstrucoesCompostas1().get(1).toStringComRotulo());
		assertEquals("3:(PARADA,Σ)(PARADA,Σ)", programa.getInstrucoesCompostas1().get(2).toStringComRotulo());
		assertEquals("4:(PARADA,Σ)(PARADA,Σ)", programa.getInstrucoesCompostas1().get(3).toStringComRotulo());

		assertEquals("5:(F,6)(F,6)", programa.getInstrucoesCompostas2().get(0).toStringComRotulo());
		assertEquals("6:(G,7)(F,6)", programa.getInstrucoesCompostas2().get(1).toStringComRotulo());
		assertEquals("7:(F,6)(H,8)", programa.getInstrucoesCompostas2().get(2).toStringComRotulo());
		assertEquals("8:(PARADA,Σ)(H,8)", programa.getInstrucoesCompostas2().get(3).toStringComRotulo());

		assertEquals("A0:{Σ}", programa.getCadeiaConjuntosFinitos1().get(0).toString());
		assertEquals("A1:{Σ,4,3}", programa.getCadeiaConjuntosFinitos1().get(1).toString());
		assertEquals("A2:{Σ,4,3,2}", programa.getCadeiaConjuntosFinitos1().get(2).toString());
		assertEquals("A3:{Σ,4,3,2,1}", programa.getCadeiaConjuntosFinitos1().get(3).toString());
		assertEquals("A4:{Σ,4,3,2,1}", programa.getCadeiaConjuntosFinitos1().get(4).toString());

		assertEquals("A0:{Σ}", programa.getCadeiaConjuntosFinitos2().get(0).toString());
		assertEquals("A1:{Σ,8}", programa.getCadeiaConjuntosFinitos2().get(1).toString());
		assertEquals("A2:{Σ,8,7}", programa.getCadeiaConjuntosFinitos2().get(2).toString());
		assertEquals("A3:{Σ,8,7,6}", programa.getCadeiaConjuntosFinitos2().get(3).toString());
		assertEquals("A4:{Σ,8,7,6,5}", programa.getCadeiaConjuntosFinitos2().get(4).toString());
		assertEquals("A5:{Σ,8,7,6,5}", programa.getCadeiaConjuntosFinitos2().get(5).toString());

		assertEquals("1:(F,2)(G,4)", programa.getInstrucoesCompostasSimplificadas1().get(0).toStringComRotulo());
		assertEquals("2:(G,4)(G,4)", programa.getInstrucoesCompostasSimplificadas1().get(1).toStringComRotulo());
		assertEquals("3:(PARADA,Σ)(PARADA,Σ)", programa.getInstrucoesCompostasSimplificadas1().get(2).toStringComRotulo());
		assertEquals("4:(PARADA,Σ)(PARADA,Σ)", programa.getInstrucoesCompostasSimplificadas1().get(3).toStringComRotulo());

		assertEquals("5:(F,6)(F,6)", programa.getInstrucoesCompostasSimplificadas2().get(0).toStringComRotulo());
		assertEquals("6:(G,7)(F,6)", programa.getInstrucoesCompostasSimplificadas2().get(1).toStringComRotulo());
		assertEquals("7:(F,6)(H,8)", programa.getInstrucoesCompostasSimplificadas2().get(2).toStringComRotulo());
		assertEquals("8:(PARADA,Σ)(H,8)", programa.getInstrucoesCompostasSimplificadas2().get(3).toStringComRotulo());
	}

	@Test
	public void teste_equivalencia_2() throws Exception {
		List<InstrucaoMonolitica> instrucoesMonoliticas1 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste2_programa1());
		List<InstrucaoMonolitica> instrucoesMonoliticas2 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste2_programa2());

		Equivalencia programa = new Equivalencia(instrucoesMonoliticas1, instrucoesMonoliticas2);

		assertEquals("1:(F,2)(PARADA,Σ)", programa.getInstrucoesCompostas1().get(0).toStringComRotulo());
		assertEquals("2:(F,2)(G,3)", programa.getInstrucoesCompostas1().get(1).toStringComRotulo());
		assertEquals("3:(F,2)(PARADA,Σ)", programa.getInstrucoesCompostas1().get(2).toStringComRotulo());

		assertEquals("4:(F,5)(PARADA,Σ)", programa.getInstrucoesCompostas2().get(0).toStringComRotulo());
		assertEquals("5:(F,6)(G,7)", programa.getInstrucoesCompostas2().get(1).toStringComRotulo());
		assertEquals("6:(F,6)(G,7)", programa.getInstrucoesCompostas2().get(2).toStringComRotulo());
		assertEquals("7:(F,5)(PARADA,Σ)", programa.getInstrucoesCompostas2().get(3).toStringComRotulo());

		assertEquals("A0:{Σ}", programa.getCadeiaConjuntosFinitos1().get(0).toString());
		assertEquals("A1:{Σ,3,1}", programa.getCadeiaConjuntosFinitos1().get(1).toString());
		assertEquals("A2:{Σ,3,1,2}", programa.getCadeiaConjuntosFinitos1().get(2).toString());
		assertEquals("A3:{Σ,3,1,2}", programa.getCadeiaConjuntosFinitos1().get(3).toString());

		assertEquals("A0:{Σ}", programa.getCadeiaConjuntosFinitos2().get(0).toString());
		assertEquals("A1:{Σ,7,4}", programa.getCadeiaConjuntosFinitos2().get(1).toString());
		assertEquals("A2:{Σ,7,4,6,5}", programa.getCadeiaConjuntosFinitos2().get(2).toString());
		assertEquals("A3:{Σ,7,4,6,5}", programa.getCadeiaConjuntosFinitos2().get(3).toString());

		assertEquals("1:(F,2)(PARADA,Σ)", programa.getInstrucoesCompostasSimplificadas1().get(0).toStringComRotulo());
		assertEquals("2:(F,2)(G,3)", programa.getInstrucoesCompostasSimplificadas1().get(1).toStringComRotulo());
		assertEquals("3:(F,2)(PARADA,Σ)", programa.getInstrucoesCompostasSimplificadas1().get(2).toStringComRotulo());

		assertEquals("4:(F,5)(PARADA,Σ)", programa.getInstrucoesCompostasSimplificadas2().get(0).toStringComRotulo());
		assertEquals("5:(F,6)(G,7)", programa.getInstrucoesCompostasSimplificadas2().get(1).toStringComRotulo());
		assertEquals("6:(F,6)(G,7)", programa.getInstrucoesCompostasSimplificadas2().get(2).toStringComRotulo());
		assertEquals("7:(F,5)(PARADA,Σ)", programa.getInstrucoesCompostasSimplificadas2().get(3).toStringComRotulo());

	}

	@Test
	public void teste_equivalencia_3() throws Exception {
		List<InstrucaoMonolitica> instrucoesMonoliticas1 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste3_programa1());
		List<InstrucaoMonolitica> instrucoesMonoliticas2 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste3_programa2());

		Equivalencia programa = new Equivalencia(instrucoesMonoliticas1, instrucoesMonoliticas2);

		assertEquals("1:(F,2)(CICLO,ω)", programa.getInstrucoesCompostas1().get(0).toStringComRotulo());
		assertEquals("2:(G,3)(G,3)", programa.getInstrucoesCompostas1().get(1).toStringComRotulo());
		assertEquals("3:(F,4)(PARADA,Σ)", programa.getInstrucoesCompostas1().get(2).toStringComRotulo());
		assertEquals("4:(F,2)(CICLO,ω)", programa.getInstrucoesCompostas1().get(3).toStringComRotulo());
		assertEquals("ω:(CICLO,ω)(CICLO,ω)", programa.getInstrucoesCompostas1().get(4).toStringComRotulo());

		assertEquals("5:(F,6)(CICLO,ω)", programa.getInstrucoesCompostas2().get(0).toStringComRotulo());
		assertEquals("6:(G,7)(G,7)", programa.getInstrucoesCompostas2().get(1).toStringComRotulo());
		assertEquals("7:(F,8)(PARADA,Σ)", programa.getInstrucoesCompostas2().get(2).toStringComRotulo());
		assertEquals("8:(F,6)(CICLO,ω)", programa.getInstrucoesCompostas2().get(3).toStringComRotulo());
		assertEquals("ω:(CICLO,ω)(CICLO,ω)", programa.getInstrucoesCompostas2().get(4).toStringComRotulo());

		assertEquals("A0:{Σ}", programa.getCadeiaConjuntosFinitos1().get(0).toString());
		assertEquals("A1:{Σ,3}", programa.getCadeiaConjuntosFinitos1().get(1).toString());
		assertEquals("A2:{Σ,3,2}", programa.getCadeiaConjuntosFinitos1().get(2).toString());
		assertEquals("A3:{Σ,3,2,1,4}", programa.getCadeiaConjuntosFinitos1().get(3).toString());
		assertEquals("A4:{Σ,3,2,1,4}", programa.getCadeiaConjuntosFinitos1().get(4).toString());

		assertEquals("A0:{Σ}", programa.getCadeiaConjuntosFinitos2().get(0).toString());
		assertEquals("A1:{Σ,7}", programa.getCadeiaConjuntosFinitos2().get(1).toString());
		assertEquals("A2:{Σ,7,6}", programa.getCadeiaConjuntosFinitos2().get(2).toString());
		assertEquals("A3:{Σ,7,6,5,8}", programa.getCadeiaConjuntosFinitos2().get(3).toString());
		assertEquals("A4:{Σ,7,6,5,8}", programa.getCadeiaConjuntosFinitos2().get(4).toString());

		assertEquals("1:(F,2)(CICLO,ω)", programa.getInstrucoesCompostasSimplificadas1().get(0).toStringComRotulo());
		assertEquals("2:(G,3)(G,3)", programa.getInstrucoesCompostasSimplificadas1().get(1).toStringComRotulo());
		assertEquals("3:(F,4)(PARADA,Σ)", programa.getInstrucoesCompostasSimplificadas1().get(2).toStringComRotulo());
		assertEquals("4:(F,2)(CICLO,ω)", programa.getInstrucoesCompostasSimplificadas1().get(3).toStringComRotulo());
		assertEquals("ω:(CICLO,ω)(CICLO,ω)", programa.getInstrucoesCompostasSimplificadas1().get(4).toStringComRotulo());

		assertEquals("5:(F,6)(CICLO,ω)", programa.getInstrucoesCompostasSimplificadas2().get(0).toStringComRotulo());
		assertEquals("6:(G,7)(G,7)", programa.getInstrucoesCompostasSimplificadas2().get(1).toStringComRotulo());
		assertEquals("7:(F,8)(PARADA,Σ)", programa.getInstrucoesCompostasSimplificadas2().get(2).toStringComRotulo());
		assertEquals("8:(F,6)(CICLO,ω)", programa.getInstrucoesCompostasSimplificadas2().get(3).toStringComRotulo());
		assertEquals("ω:(CICLO,ω)(CICLO,ω)", programa.getInstrucoesCompostasSimplificadas2().get(4).toStringComRotulo());
	}

	@Test
	public void teste_equivalencia_4() throws Exception {
		List<InstrucaoMonolitica> instrucoesMonoliticas1 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste4_programa1());
		List<InstrucaoMonolitica> instrucoesMonoliticas2 = InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(MOCKInstrucaoMonoliticaEntradaUsuario.teste4_programa2());

		Equivalencia programa = new Equivalencia(instrucoesMonoliticas1, instrucoesMonoliticas2);

		assertEquals("1:(G,2)(F,3)", programa.getInstrucoesCompostas1().get(0).toStringComRotulo());
		assertEquals("2:(G,2)(F,3)", programa.getInstrucoesCompostas1().get(1).toStringComRotulo());
		assertEquals("3:(F,4)(G,5)", programa.getInstrucoesCompostas1().get(2).toStringComRotulo());
		assertEquals("4:(F,4)(G,5)", programa.getInstrucoesCompostas1().get(3).toStringComRotulo());
		assertEquals("5:(F,6)(CICLO,ω)", programa.getInstrucoesCompostas1().get(4).toStringComRotulo());
		assertEquals("6:(PARADA,Σ)(G,7)", programa.getInstrucoesCompostas1().get(5).toStringComRotulo());
		assertEquals("7:(G,7)(G,7)", programa.getInstrucoesCompostas1().get(6).toStringComRotulo());
		assertEquals("ω:(CICLO,ω)(CICLO,ω)", programa.getInstrucoesCompostas1().get(7).toStringComRotulo());

		assertEquals("8:(G,9)(F,10)", programa.getInstrucoesCompostas2().get(0).toStringComRotulo());
		assertEquals("9:(G,9)(F,10)", programa.getInstrucoesCompostas2().get(1).toStringComRotulo());
		assertEquals("10:(F,10)(G,11)", programa.getInstrucoesCompostas2().get(2).toStringComRotulo());
		assertEquals("11:(F,12)(F,13)", programa.getInstrucoesCompostas2().get(3).toStringComRotulo());
		assertEquals("12:(PARADA,Σ)(F,13)", programa.getInstrucoesCompostas2().get(4).toStringComRotulo());
		assertEquals("13:(F,13)(F,13)", programa.getInstrucoesCompostas2().get(5).toStringComRotulo());

		assertEquals("A0:{Σ}", programa.getCadeiaConjuntosFinitos1().get(0).toString());
		assertEquals("A1:{Σ,6}", programa.getCadeiaConjuntosFinitos1().get(1).toString());
		assertEquals("A2:{Σ,6,5}", programa.getCadeiaConjuntosFinitos1().get(2).toString());
		assertEquals("A3:{Σ,6,5,4,3}", programa.getCadeiaConjuntosFinitos1().get(3).toString());
		assertEquals("A4:{Σ,6,5,4,3,2,1}", programa.getCadeiaConjuntosFinitos1().get(4).toString());
		assertEquals("A5:{Σ,6,5,4,3,2,1}", programa.getCadeiaConjuntosFinitos1().get(5).toString());

		assertEquals("A0:{Σ}", programa.getCadeiaConjuntosFinitos2().get(0).toString());
		assertEquals("A1:{Σ,12}", programa.getCadeiaConjuntosFinitos2().get(1).toString());
		assertEquals("A2:{Σ,12,11}", programa.getCadeiaConjuntosFinitos2().get(2).toString());
		assertEquals("A3:{Σ,12,11,10}", programa.getCadeiaConjuntosFinitos2().get(3).toString());
		assertEquals("A4:{Σ,12,11,10,9,8}", programa.getCadeiaConjuntosFinitos2().get(4).toString());
		assertEquals("A5:{Σ,12,11,10,9,8}", programa.getCadeiaConjuntosFinitos2().get(5).toString());

		assertEquals("1:(G,2)(F,3)", programa.getInstrucoesCompostasSimplificadas1().get(0).toStringComRotulo());
		assertEquals("2:(G,2)(F,3)", programa.getInstrucoesCompostasSimplificadas1().get(1).toStringComRotulo());
		assertEquals("3:(F,4)(G,5)", programa.getInstrucoesCompostasSimplificadas1().get(2).toStringComRotulo());
		assertEquals("4:(F,4)(G,5)", programa.getInstrucoesCompostasSimplificadas1().get(3).toStringComRotulo());
		assertEquals("5:(F,6)(CICLO,ω)", programa.getInstrucoesCompostasSimplificadas1().get(4).toStringComRotulo());
		assertEquals("6:(PARADA,Σ)(CICLO,ω)", programa.getInstrucoesCompostasSimplificadas1().get(5).toStringComRotulo());
		assertEquals("ω:(CICLO,ω)(CICLO,ω)", programa.getInstrucoesCompostasSimplificadas1().get(6).toStringComRotulo());

		assertEquals("8:(G,9)(F,10)", programa.getInstrucoesCompostasSimplificadas2().get(0).toStringComRotulo());
		assertEquals("9:(G,9)(F,10)", programa.getInstrucoesCompostasSimplificadas2().get(1).toStringComRotulo());
		assertEquals("10:(F,10)(G,11)", programa.getInstrucoesCompostasSimplificadas2().get(2).toStringComRotulo());
		assertEquals("11:(F,12)(CICLO,ω)", programa.getInstrucoesCompostasSimplificadas2().get(3).toStringComRotulo());
		assertEquals("12:(PARADA,Σ)(CICLO,ω)", programa.getInstrucoesCompostasSimplificadas2().get(4).toStringComRotulo());
		assertEquals("ω:(CICLO,ω)(CICLO,ω)", programa.getInstrucoesCompostasSimplificadas2().get(5).toStringComRotulo());

	}

}
