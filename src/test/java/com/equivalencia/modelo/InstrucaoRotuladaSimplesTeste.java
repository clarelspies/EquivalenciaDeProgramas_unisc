package com.equivalencia.modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.equivalencia.modelo.InstrucaoRotuladaSimples;
import com.equivalencia.modelo.tipo.TipoInstrucao;

public class InstrucaoRotuladaSimplesTeste {

	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_1() {
		try {
			InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(null);
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: [null]";
			assertEquals(message, e.getMessage());
		}

	}

	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_2() {
		try {
			InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario("");
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: []";
			assertEquals(message, e.getMessage());
		}

	}

	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_3() {
		try {
			InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(" ");
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: [ ]";
			assertEquals(message, e.getMessage());
		}

	}

	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_4() {
		try {
			InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario("A");
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: [A]";
			assertEquals(message, e.getMessage());
		}

	}

	// TESTE ONDE A ENTRADA E INVALIDA, POIS ESPERA-SE SENAO-VA-PARA 3 AO INVES DE SENAO 3
	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_5() {
		try {
			InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario("SE T1 VA-PARA 2 SENAO 3");
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: [SE T1 VA-PARA 2 SENAO 3]";
			assertEquals(message, e.getMessage());
		}

	}

	// TESTE ONDE NAO E ESPERADO ERRO 1
	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_6() {
		try {
			String teste1 = "SE T1 VA-PARA 2 SENAO-va-para 3";
			assertEquals(TipoInstrucao.TESTE, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste1).getTipo());
			assertEquals(2, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste1).getDestinoTesteVerdadeiro());
			assertEquals(3, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste1).getDestinoTesteFalso());
			assertEquals("T1", InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste1).getNomeInstrucao());
		} catch (Exception e) {
			String message = "";
			assertEquals(message, e.getMessage());
		}

	}

	// TESTE ONDE NAO E ESPERADO ERRO 2
	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_7() {
		try {
			String teste2 = "FACA F VA-PARA 6";
			assertEquals(TipoInstrucao.OPERACAO, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste2).getTipo());
			assertEquals(6, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste2).getDestinoOperacao());
			assertEquals("F", InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste2).getNomeInstrucao());
		} catch (Exception e) {
			String message = "";
			assertEquals(message, e.getMessage());
		}

	}

	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_8() {
		try {
			InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario("FAA F VA-PARA 6");
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: [FAA F VA-PARA 6]";
			assertEquals(message, e.getMessage());
		}

	}

}