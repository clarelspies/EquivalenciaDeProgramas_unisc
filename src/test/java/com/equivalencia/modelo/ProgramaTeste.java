package com.equivalencia.modelo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ProgramaTeste {

	// erro no rodulo 4 durante instanciacao do programa, "va para"
	@Test
	public void teste_Programa1() {
		try {
			List<String> entradas1 = new ArrayList<>();
			entradas1.add("SE T1 VA-PARA 2 SENAO-va-para 3");
			entradas1.add("FACA F VA-PARA 6");
			entradas1.add("SE T2 VA-PARA 5 SENAO-VA-PARA 4");
			entradas1.add("FACA G VA PARA 7"); // ROTULO INEXISTENTE = VAZIO/PARADA
			entradas1.add("FACA F VA-PARA 0");// 0 = VAZIO/PARADA
			entradas1.add("SE T3 VA-PARA 4 SENAO-VA-PARA-1");

			new Programa(entradas1);
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: 4";
			assertEquals(message, e.getMessage());
		}

	}

	// erro no rodulo 5 durante instanciacao do programa, "va para"
	@Test
	public void teste_Programa2() {
		try {
			List<String> entradas1 = new ArrayList<>();
			entradas1.add("SE T1 VA-PARA 2 SENAO-va-para 3");
			entradas1.add("FACA F VA-PARA 6");
			entradas1.add("SE T2 VA-PARA 5 SENAO-VA-PARA 4");
			entradas1.add("FACA G VA-PARA 7"); // ROTULO INEXISTENTE = VAZIO/PARADA
			entradas1.add("FACA F VA PARA 0");// 0 = VAZIO/PARADA
			entradas1.add("SE T3 VA-PARA 4 SENAO-VA-PARA-1");

			new Programa(entradas1);
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: 5";
			assertEquals(message, e.getMessage());
		}

	}

	// nenhum erro, intrucoes de entrada corretas
	@Test
	public void teste_Programa3() {
		try {
			List<String> entradas1 = new ArrayList<>();
			entradas1.add("SE T1 VA-PARA 2 SENAO-va-para 3");
			entradas1.add("FACA F VA-PARA 6");
			entradas1.add("SE T2 VA-PARA 5 SENAO-VA-PARA 4");
			entradas1.add("FACA G VA-PARA 7"); // ROTULO INEXISTENTE = VAZIO/PARADA
			entradas1.add("FACA F VA-PARA 0");// 0 = VAZIO/PARADA
			entradas1.add("SE T3 VA-PARA 4 SENAO-VA-PARA 1");

			new Programa(entradas1);
		} catch (Exception e) {
			String message = "Instrução em formato incompatível, por favor verificar entrada: 5";
			assertEquals(message, e.getMessage());
		}

	}

}
