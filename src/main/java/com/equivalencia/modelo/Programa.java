package com.equivalencia.modelo;

import java.util.ArrayList;
import java.util.List;

public class Programa {

	List<InstrucaoMonolitica> instrucoesRotuladasSimples;

	// instancia do programa, a partir da entrada de instrucoes monolitcas do usuario em formato STRING/TEXTO
	public Programa(List<String> entradas) throws Exception {
		this.instrucoesRotuladasSimples = new ArrayList<>();
		// coloca null no index 0 da lista, para começar as intruções no index 1
		instrucoesRotuladasSimples.add(null);

		// cria objeto da instrucao monolitica
		for (String entrada : entradas) {
			InstrucaoMonolitica instrucao = InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario(entrada);
			instrucoesRotuladasSimples.add(instrucao);
		}

	}

	// defini instrucoes rotuladas compostas a partir das intrucoesmonoliticas, primeiro passo da equivalencia!
	public void definiInstrucoesRotuladas() {

	}

}
