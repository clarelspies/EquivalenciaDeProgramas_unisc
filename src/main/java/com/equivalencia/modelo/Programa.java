package com.equivalencia.modelo;

import java.util.ArrayList;
import java.util.List;

public class Programa {

	List<InstrucaoRotuladaSimples> instrucoesRotuladasSimples;

	// instancia do programa, a partir da entrada do usuario e montada as instrucoes rotuladas simples do programa
	public Programa(List<String> entradas) throws Exception {
		this.instrucoesRotuladasSimples = new ArrayList<>();

		// coloca null no index 0 da lista, para começar as intruções no index 1
		instrucoesRotuladasSimples.add(null);

		for (String entrada : entradas) {
			InstrucaoRotuladaSimples instrucao = InstrucaoRotuladaSimples.criaInstrucaoAtravesEntradaUsuario(entrada);

			if (instrucao.isInstrucaoValida()) {
				instrucoesRotuladasSimples.add(instrucao);
			} else {
				throw new Exception("Instrução em formato incompatível, por favor verificar entrada: " + instrucoesRotuladasSimples.size());
			}

		}

	}

	// defini instrucoes rotuladas compostas a partir das intrucoes rotuladas simples, primeiro passo da equivalencia!
	public void definiInstrucoesRotuladas() {

	}

	public List<InstrucaoRotuladaSimples> getInstrucoesRotuladasSimples() {
		return instrucoesRotuladasSimples;
	}

}
