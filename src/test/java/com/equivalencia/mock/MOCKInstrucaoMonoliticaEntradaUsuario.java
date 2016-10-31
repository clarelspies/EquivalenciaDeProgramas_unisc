package com.equivalencia.mock;

import java.util.ArrayList;
import java.util.List;

public class MOCKInstrucaoMonoliticaEntradaUsuario {

	public static List<String> teste1_programa1() {
		List<String> entradas = new ArrayList<>();
		entradas.add("SE T1 VA-PARA 2 SENAO-va-para 3");
		entradas.add("FACA F VA-PARA 6");
		entradas.add("SE T2 VA-PARA 5 SENAO-VA-PARA 4");
		entradas.add("FACA G VA-PARA 7");
		entradas.add("FACA F VA-PARA 7");
		entradas.add("SE T3 VA-PARA 4 SENAO-VA-PARA 1");

		return entradas;
	}

	public static List<String> teste1_programa2() {
		List<String> entradas = new ArrayList<>();

		entradas.add("FACA F VA-PARA 2");
		entradas.add("SE T1 VA-PARA 3 SENAO-va-para 1");
		entradas.add("FACA g VA-PARA 4");
		entradas.add("SE T2 VA-PARA 1 SENAO-VA-PARA 5");
		entradas.add("FACA h VA-PARA 6");
		entradas.add("SE T3 VA-PARA 7 SENAO-VA-PARA 5");

		return entradas;
	}

	public static List<String> teste2_programa1() {
		List<String> entradas = new ArrayList<>();
		entradas.add("SE T1 VA-PARA 2 SENAO-va-para 5");
		entradas.add("FACA F VA-PARA 3");
		entradas.add("SE T2 VA-PARA 1 SENAO-VA-PARA 4");
		entradas.add("FACA G VA-PARA 1");

		return entradas;
	}

	public static List<String> teste2_programa2() {
		List<String> entradas = new ArrayList<>();

		entradas.add("SE T1 VA-PARA 2 SENAO-va-para 6");
		entradas.add("FACA F VA-PARA 3");
		entradas.add("SE T2 VA-PARA 4 SENAO-VA-PARA 5");
		entradas.add("FACA F VA-PARA 3");
		entradas.add("FACA G VA-PARA 1");

		return entradas;
	}

	public static List<String> teste3_programa1() {
		List<String> entradas = new ArrayList<>();
		entradas.add("Se T1 va-para 2 senao-va-para 1");
		entradas.add("Se T2 va-para 3 senao-va-para 8");
		entradas.add("FACA F VA-PARA 4");
		entradas.add("FACA G VA-PARA 5");
		entradas.add("Se T3 va-para 6 senao-va-para 2");
		entradas.add("FACA F VA-PARA 7");
		entradas.add("Se T4 va-para 2 senao-va-para 7");

		return entradas;
	}

	public static List<String> teste3_programa2() {
		List<String> entradas = new ArrayList<>();

		entradas.add("SE T1 VA-PARA 2 SENAO-va-para 1");
		entradas.add("FACA F VA-PARA 3");
		entradas.add("FACA G VA-PARA 4");
		entradas.add("SE T2 VA-PARA 5 SENAO-VA-PARA 6");
		entradas.add("FACA F VA-PARA 1");

		return entradas;
	}

	public static List<String> teste4_programa1() {
		List<String> entradas = new ArrayList<>();

		entradas.add("Se T1 va-para 2 senao-va-para 3");// 1
		entradas.add("FACA G VA-PARA 1");// 2
		entradas.add("FACA F VA-PARA 4");// 3
		entradas.add("Se T2 va-para 5 senao-va-para 6");// 4
		entradas.add("FACA F VA-PARA 4");// 5
		entradas.add("FACA G VA-PARA 7");// 6
		entradas.add("se t3 VA-PARA 8 senao-va-para 9"); // 7
		entradas.add("FACA F va-para 10"); // 8
		entradas.add("se t4 VA-PARA 13 senao-va-para 7");// 9
		entradas.add("se t5 VA-PARA 13 senao-va-para 11"); // 10
		entradas.add("FACA G va-para 11"); // 11

		return entradas;
	}

	public static List<String> teste4_programa2() {
		List<String> entradas = new ArrayList<>();
		entradas.add("Se T1 va-para 2 senao-va-para 3");// 1
		entradas.add("FACA G VA-PARA 1");// 2
		entradas.add("FACA F VA-PARA 4");// 3
		entradas.add("Se T2 va-para 3 senao-va-para 5");// 4
		entradas.add("FACA G VA-PARA 6");// 5
		entradas.add("Se T3 va-para 7 senao-va-para 8");// 6
		entradas.add("FACA F VA-PARA 9");// 7
		entradas.add("FACA F VA-PARA 8");// 8
		entradas.add("Se T4 va-para 10 senao-va-para 6");// 9

		return entradas;
	}

}
