package com.equivalencia.modelo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.equivalencia.modelo.InstrucaoMonolitica;
import com.equivalencia.modelo.tipo.TipoInstrucao;

public class InstrucaoMonoliticaTeste {

	@Test(expected = Exception.class)
	public void teste_criaInstrucaoAtravesEntradaUsuario_1() throws Exception {
		new InstrucaoMonolitica(null);
	}

	@Test(expected = Exception.class)
	public void teste_criaInstrucaoAtravesEntradaUsuario_2() throws Exception {
		new InstrucaoMonolitica("");
	}

	@Test(expected = Exception.class)
	public void teste_criaInstrucaoAtravesEntradaUsuario_3() throws Exception {
		new InstrucaoMonolitica(" ");
	}

	@Test(expected = Exception.class)
	public void teste_criaInstrucaoAtravesEntradaUsuario_4() throws Exception {
		new InstrucaoMonolitica("A");
	}

	@Test(expected = Exception.class)
	public void teste_criaInstrucaoAtravesEntradaUsuario_5() throws Exception {
		new InstrucaoMonolitica("SE T1 VA-PARA 2 SENAO 3");
	}

	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_6() throws Exception {
		InstrucaoMonolitica instrucao = new InstrucaoMonolitica("SE T1 VA-PARA 2 SENAO-va-para 3");

		assertEquals(TipoInstrucao.TESTE, instrucao.getTipo());
		assertEquals(2, instrucao.getDestinoTesteVerdadeiro());
		assertEquals(3, instrucao.getDestinoTesteFalso());
		assertEquals("T1", instrucao.getIdentificador());
	}

	@Test
	public void teste_criaInstrucaoAtravesEntradaUsuario_7() throws Exception {
		InstrucaoMonolitica instrucao = new InstrucaoMonolitica("FACA F VA-PARA 6");

		assertEquals(TipoInstrucao.OPERACAO, instrucao.getTipo());
		assertEquals(6, instrucao.getDestinoOperacao());
		assertEquals("F", instrucao.getIdentificador());
	}

	@Test(expected = Exception.class)
	public void teste_criaInstrucaoAtravesEntradaUsuario_8() throws Exception {
		new InstrucaoMonolitica("FAA F VA-PARA 6");
	}

	// TESTE QUE DA ERRO, POIS O IDENTIFICADOR DA INSTRUCAO DE TESTE NAO COMECA COM T
	@Test(expected = Exception.class)
	public void teste_criaInstrucaoAtravesEntradaUsuario_9() throws Exception {
		new InstrucaoMonolitica("SE A1 VA-PARA 2 SENAO-va-para 3");
	}

	// TESTE COM INSTRUCAO TESTE
	@Test
	public void teste_buscaIndexProximaInstrucaoExecutada_1() throws Exception {
		InstrucaoMonolitica instrucao = new InstrucaoMonolitica("SE T1 VA-PARA 2 SENAO-va-para 3");

		assertEquals(2, instrucao.buscaIndexProximaInstrucaoExecutada(true));
		assertEquals(3, instrucao.buscaIndexProximaInstrucaoExecutada(false));
	}

	// TESTE COM INSTRUCAO OPERACAO
	@Test
	public void teste_buscaIndexProximaInstrucaoExecutada_2() throws Exception {
		InstrucaoMonolitica instrucao = new InstrucaoMonolitica("FACA F VA-PARA 6");

		assertEquals(6, instrucao.buscaIndexProximaInstrucaoExecutada(true));
		assertEquals(6, instrucao.buscaIndexProximaInstrucaoExecutada(false));
	}

	// erro no rodulo 4 durante instanciacao do programa, "va para"
	@Test(expected = Exception.class)
	public void teste_criaListaInstrucoesMonoliticasParaPrograma_1() throws Exception {
		List<String> entradas1 = new ArrayList<>();
		entradas1.add("SE T1 VA-PARA 2 SENAO-va-para 3");
		entradas1.add("FACA F VA-PARA 6");
		entradas1.add("SE T2 VA-PARA 5 SENAO-VA-PARA 4");
		entradas1.add("FACA G VA PARA 7");
		entradas1.add("FACA F VA-PARA 0");
		entradas1.add("SE T3 VA-PARA 4 SENAO-VA-PARA 1");

		InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(entradas1);
	}

	// erro no rodulo 5 durante instanciacao do programa, "va para"
	@Test(expected = Exception.class)
	public void teste_criaListaInstrucoesMonoliticasParaPrograma_2() throws Exception {
		List<String> entradas1 = new ArrayList<>();
		entradas1.add("SE T1 VA-PARA 2 SENAO-va-para 3");
		entradas1.add("FACA F VA-PARA 6");
		entradas1.add("SE T2 VA-PARA 5 SENAO-VA-PARA 4");
		entradas1.add("FACA G VA-PARA 7");
		entradas1.add("FACA F VA PARA 0");
		entradas1.add("SE T3 VA-PARA 4 SENAO-VA-PARA 1");

		InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(entradas1);
	}

	// nenhum erro, intrucoes de entrada corretas
	@Test
	public void teste_criaListaInstrucoesMonoliticasParaPrograma_3() throws Exception {
		List<String> entradas1 = new ArrayList<>();
		entradas1.add("SE T1 VA-PARA 2 SENAO-va-para 3");
		entradas1.add("FACA F VA-PARA 6");
		entradas1.add("SE T2 VA-PARA 5 SENAO-VA-PARA 4");
		entradas1.add("FACA G VA-PARA 7");
		entradas1.add("FACA F VA-PARA 0");
		entradas1.add("SE T3 VA-PARA 4 SENAO-VA-PARA 1");

		InstrucaoMonolitica.criaListaInstrucoesMonoliticasParaPrograma(entradas1);
	}

}