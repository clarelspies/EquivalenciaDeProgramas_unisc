package com.equivalencia.modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.equivalencia.modelo.InstrucaoRotuladaSimples;
import com.equivalencia.modelo.TipoInstrucao;

public class InstrucaoRotuladaSimplesTeste {

	// TESTES USADAS NA CRIACAO DA INSTRUCAO-ROTULADA-SIMPLES, TAMBEM VERIFICA
	// SE A ENTRADA ESTÁ CONFORME O USUARI OESPERA ATRAVÉS DE TESTES UNITÁRIOS!
	@Test
	public void teste_criaInstrucao() {
		// TESTES COM ENTRADAS PERDIDAS, SEM PARAMETROS SUFICIENTES
		assertEquals(false, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(null).isInstrucaoValida());
		assertEquals(false, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario("").isInstrucaoValida());
		assertEquals(false, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(" ").isInstrucaoValida());
		assertEquals(false, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario("A").isInstrucaoValida());

		// TESTE COM INSTRUCAO VALIDA, UMA ENTRADA PERFEITA DE TESTE.
		String teste1 = "SE T1 VA-PARA 2 SENAO-va-para 3";
		assertEquals(TipoInstrucao.TESTE, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste1).getTipo());
		assertEquals(2, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste1).getDestinoTesteVerdadeiro());
		assertEquals(3, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste1).getDestinoTesteFalso());
		assertEquals(true, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste1).isInstrucaoValida());
		assertEquals("T1", InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste1).getNomeInstrucao());

		// TESTE COM UMA INSTRUCAO VALIDA, UMA ENTRADA PERFEITA DE OPERACAO
		String teste2 = "FACA F VA-PARA 6";
		assertEquals(true, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste2).isInstrucaoValida());
		assertEquals(TipoInstrucao.OPERACAO, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste2).getTipo());
		assertEquals(6, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste2).getDestinoOperacao());
		assertEquals("F", InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste2).getNomeInstrucao());

		// TESTE ONDE A ENTRADA E INVALIDA, POIS ESPERA-SE SENAO-VA-PARA 3 AO INVES DE SENAO 3
		String teste3 = "SE T1 VA-PARA 2 SENAO 3";
		assertEquals(false, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste3).isInstrucaoValida());

		// TESTE ONDE A ENTRADA E INVALIDA, POS ESPERA-SE VA-PARA AO INVES DE VA-PAA
		String teste4 = "FACA F VA-PAA 6";
		assertEquals(false, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste4).isInstrucaoValida());

		// TESTE DE INSTRUCAO INCOMPLETA
		String teste5 = "FACA 6";
		assertEquals(false, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste5).isInstrucaoValida());

		// TESTE ONDE ESPERA-SE FACA AO INVES DE FAA NO INICIO DA INSTRUCAO, PARA REPRESENTAR UMA OPERACAO
		String teste6 = "FAA F VA-PARA 6";
		assertEquals(false, InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(teste6).isInstrucaoValida());
	}

}