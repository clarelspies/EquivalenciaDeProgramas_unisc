package com.equivalencia.modelo;

import java.util.List;

import com.equivalencia.modelo.tipo.TipoInstrucao;
import com.equivalencia.utilitarios.Utilitario;

public class Programa {

	List<InstrucaoMonolitica> instrucoesMonoliticasPrograma1;
	List<InstrucaoMonolitica> instrucoesMonoliticasPrograma2;

	// instancia do programa, a partir da entrada de instrucoes monolitcas do usuario em formato STRING/TEXTO
	public Programa(List<InstrucaoMonolitica> programa1, List<InstrucaoMonolitica> programa2) throws Exception {
		this.instrucoesMonoliticasPrograma1 = programa1;
		this.instrucoesMonoliticasPrograma2 = programa2;

		Utilitario utilitario = new Utilitario();
		utilitario.rotulaOperacoesEmOrdemExecucao(instrucoesMonoliticasPrograma1);
		utilitario.rotulaOperacoesEmOrdemExecucao(instrucoesMonoliticasPrograma2);
	}

	// defini instrucoes rotuladas compostas a partir das intrucoesmonoliticas,
	// primeiro passo da equivalencia!
	public void definiInstrucoesRotuladas() {

		int posicaoAtual = 0;
		// for (InstrucaoMonolitica instrucaoMonolitica : this.instrucoesMonoliticas) {
		// vai procurar a partir do início e de cada operacao
		// if (instrucaoMonolitica.getTipo() == TipoInstrucao.PARADA || instrucaoMonolitica.getTipo() == TipoInstrucao.OPERACAO) {

		// }

		posicaoAtual++;
		// }

		// vai pegar a lista de instrucoes monoliticas, e percorrer ela.
		// para o primeiro rotulo, e todos os demais rotulos que tiverem
		// OPERACOES VAI:
		// procurar até qual rotulo essas operações levam.
		// ps: para coluna 1 e 2 em operações, usa o mesmo destino. para coluna
		// 1 usa verdadeiro e coluna 2 falso em TESTES.
		// se for rotulo 0, volta pro 1!!
		// se for rotulo inexistente, PARADA.
		// se voltar ao mesmo rotulo CICLO.

	}

	public List<InstrucaoMonolitica> getInstrucoesMonoliticasPrograma1() {
		return instrucoesMonoliticasPrograma1;
	}

	public List<InstrucaoMonolitica> getInstrucoesMonoliticasPrograma2() {
		return instrucoesMonoliticasPrograma2;
	}

}
