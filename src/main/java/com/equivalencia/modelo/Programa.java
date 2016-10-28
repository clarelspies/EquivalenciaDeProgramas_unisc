package com.equivalencia.modelo;

import java.util.ArrayList;
import java.util.List;

public class Programa {

	List<InstrucaoMonolitica> instrucoesMonoliticas;

	// instancia do programa, a partir da entrada de instrucoes monolitcas do usuario em formato STRING/TEXTO
	public Programa(List<String> entradas) throws Exception {
		this.instrucoesMonoliticas = new ArrayList<>();
		// coloca null no index 0 da lista, para começar as intruções no index 1
		instrucoesMonoliticas.add(new InstrucaoMonolitica());

		// cria objeto da instrucao monolitica
		for (String entrada : entradas) {
			InstrucaoMonolitica instrucao = InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario(entrada);
			instrucoesMonoliticas.add(instrucao);
		}

	}

	// defini instrucoes rotuladas compostas a partir das intrucoesmonoliticas, primeiro passo da equivalencia!
	public void definiInstrucoesRotuladas() {

		for (InstrucaoMonolitica instrucaoMonolitica : this.instrucoesMonoliticas) {

		}

		// vai pegar a lista de instrucoes monoliticas, e percorrer ela.
		// para o primeiro rotulo, e todos os demais rotulos que tiverem OPERACOES VAI:
		// procurar até qual rotulo essas operações levam.
		// ps: para coluna 1 e 2 em operações, usa o mesmo destino. para coluna 1 usa verdadeiro e coluna 2 falso em TESTES.
		// se for rotulo 0, volta pro 1!!
		// se for rotulo inexistente, PARADA.
		// se voltar ao mesmo rotulo CICLO.

	}

}
