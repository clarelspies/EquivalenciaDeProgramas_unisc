package com.equivalencia.modelo;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.modelo.tipo.TipoInstrucao;
import com.equivalencia.utilitarios.Utilitario;

public class Programa {

	List<InstrucaoMonolitica> instrucoesMonoliticasPrograma1;
	List<InstrucaoMonolitica> instrucoesMonoliticasPrograma2;

	List<InstrucaoRotuladaComposta> instrucoesCompostas1;
	List<InstrucaoRotuladaComposta> instrucoesCompostas2;

	// instancia do programa, a partir da entrada de instrucoes monolitcas do usuario em formato STRING/TEXTO
	public Programa(List<InstrucaoMonolitica> programa1, List<InstrucaoMonolitica> programa2) throws Exception {
		this.instrucoesMonoliticasPrograma1 = programa1;
		this.instrucoesMonoliticasPrograma2 = programa2;

		this.instrucoesCompostas2 = new ArrayList<>();

		Utilitario utilitario = new Utilitario();
		utilitario.rotulaOperacoesEmOrdemExecucao(this.instrucoesMonoliticasPrograma1);
		utilitario.rotulaOperacoesEmOrdemExecucao(this.instrucoesMonoliticasPrograma2);

		this.instrucoesCompostas1 = Programa.geraInstrucoesRotuladasCompostas(this.instrucoesMonoliticasPrograma1);
		this.instrucoesCompostas2 = Programa.geraInstrucoesRotuladasCompostas(this.instrucoesMonoliticasPrograma2);

		// TODO adicionar ciclos no final, com W.. quando tem ciclo
	}

	// defini instrucoes rotuladas compostas a partir das intrucoesmonoliticas.. SÃO GERADAS AS INSTRUCOES COMPOSTAS NA PARTIDA E EM CADA OPERACAO!
	public static List<InstrucaoRotuladaComposta> geraInstrucoesRotuladasCompostas(List<InstrucaoMonolitica> instrucoesMonoliticas) {
		List<InstrucaoRotuladaComposta> instrucoesCompostas = new ArrayList<>();

		int contadorPosicaoAtual = 0;
		for (InstrucaoMonolitica instrucaoMonolitica : instrucoesMonoliticas) {
			if (instrucaoMonolitica.getTipo() == TipoInstrucao.OPERACAO || instrucaoMonolitica.getTipo() == TipoInstrucao.PARTIDA) {
				InstrucaoRotuladaComposta instrucaoComposta = Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(contadorPosicaoAtual, instrucoesMonoliticas);
				instrucoesCompostas.add(instrucaoComposta);
			}
			contadorPosicaoAtual++;
		}

		return instrucoesCompostas;
	}

	public List<InstrucaoMonolitica> getInstrucoesMonoliticasPrograma1() {
		return instrucoesMonoliticasPrograma1;
	}

	public List<InstrucaoMonolitica> getInstrucoesMonoliticasPrograma2() {
		return instrucoesMonoliticasPrograma2;
	}

	public List<InstrucaoRotuladaComposta> getInstrucoesCompostas1() {
		return instrucoesCompostas1;
	}

	public List<InstrucaoRotuladaComposta> getInstrucoesCompostas2() {
		return instrucoesCompostas2;
	}

}
