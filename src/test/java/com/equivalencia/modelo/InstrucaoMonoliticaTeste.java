package com.equivalencia.modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.equivalencia.modelo.InstrucaoMonolitica;
import com.equivalencia.modelo.tipo.TipoInstrucao;

public class InstrucaoMonoliticaTeste {

	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_1() {
		try {
			InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario(null);
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: [null]";
			assertEquals(message, e.getMessage());
		}

	}

	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_2() {
		try {
			InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario("");
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: []";
			assertEquals(message, e.getMessage());
		}

	}

	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_3() {
		try {
			InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario(" ");
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: [ ]";
			assertEquals(message, e.getMessage());
		}

	}

	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_4() {
		try {
			InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario("A");
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: [A]";
			assertEquals(message, e.getMessage());
		}

	}

	// TESTE ONDE A ENTRADA E INVALIDA, POIS ESPERA-SE SENAO-VA-PARA 3 AO INVES DE SENAO 3
	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_5() {
		try {
			InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario("SE T1 VA-PARA 2 SENAO 3");
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
			assertEquals(TipoInstrucao.TESTE, InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario(teste1).getTipo());
			assertEquals(2, InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario(teste1).getDestinoTesteVerdadeiro());
			assertEquals(3, InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario(teste1).getDestinoTesteFalso());
			assertEquals("T1", InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario(teste1).getIdentificador());
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
			assertEquals(TipoInstrucao.OPERACAO, InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario(teste2).getTipo());
			assertEquals(6, InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario(teste2).getDestinoOperacao());
			assertEquals("F", InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario(teste2).getIdentificador());
		} catch (Exception e) {
			String message = "";
			assertEquals(message, e.getMessage());
		}

	}

	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_8() {
		try {
			InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario("FAA F VA-PARA 6");
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: [FAA F VA-PARA 6]";
			assertEquals(message, e.getMessage());
		}

	}

	// TESTE QUE DA ERRO, POIS O IDENTIFICADOR DA INSTRUCAO DE TESTE NAO COMECA COM T
	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_9() {
		try {
			String teste1 = "SE A1 VA-PARA 2 SENAO-va-para 3";
			assertEquals(TipoInstrucao.TESTE, InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario(teste1).getTipo());
			assertEquals(2, InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario(teste1).getDestinoTesteVerdadeiro());
			assertEquals(3, InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario(teste1).getDestinoTesteFalso());
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: [SE A1 VA-PARA 2 SENAO-va-para 3]";
			assertEquals(message, e.getMessage());
		}

	}

	// TESTE COM INSTRUCAO TESTE
	@Test
	public void teste_buscaIndexProximaInstrucaoExecutada_1() {
		try {

			InstrucaoMonolitica instrucao = InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario("SE T1 VA-PARA 2 SENAO-va-para 3");

			assertEquals(2, instrucao.buscaIndexProximaInstrucaoExecutada(true));
			assertEquals(3, instrucao.buscaIndexProximaInstrucaoExecutada(false));

		} catch (Exception e) {
			String message = "";
			assertEquals(message, e.getMessage());
		}
	}

	// TESTE COM INSTRUCAO OPERACAO
	@Test
	public void teste_buscaIndexProximaInstrucaoExecutada_2() {
		try {
			InstrucaoMonolitica instrucao = InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario("FACA F VA-PARA 6");

			assertEquals(6, instrucao.buscaIndexProximaInstrucaoExecutada(true));
			assertEquals(6, instrucao.buscaIndexProximaInstrucaoExecutada(false));
		} catch (Exception e) {
			String message = "";
			assertEquals(message, e.getMessage());
		}

	}

}