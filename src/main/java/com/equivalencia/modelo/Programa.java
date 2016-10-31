package com.equivalencia.modelo;

import java.util.List;

import com.equivalencia.utilitarios.Utilitario;

public class Programa {

	List<InstrucaoMonolitica> instrucoesMonoliticasPrograma1;
	List<InstrucaoMonolitica> instrucoesMonoliticasPrograma2;

	List<InstrucaoRotuladaComposta> instrucoesCompostas1;
	List<InstrucaoRotuladaComposta> instrucoesCompostas2;

	// instancia do programa, a partir da entrada de instrucoes monolitcas do usuario em formato STRING/TEXTO
	public Programa(List<InstrucaoMonolitica> programa1, List<InstrucaoMonolitica> programa2) throws Exception {
		Utilitario utilitario = new Utilitario();

		// informas as instrucoes monoliticas
		this.instrucoesMonoliticasPrograma1 = programa1;
		this.instrucoesMonoliticasPrograma2 = programa2;

		// rotula as operacoes monoliticas em ordem de execucao, para instrucoes compostas
		utilitario.rotulaOperacoesEmOrdemExecucao(this.instrucoesMonoliticasPrograma1);
		utilitario.rotulaOperacoesEmOrdemExecucao(this.instrucoesMonoliticasPrograma2);

		//
		this.instrucoesCompostas1 = utilitario.geraInstrucoesRotuladasCompostas(this.instrucoesMonoliticasPrograma1);
		this.instrucoesCompostas2 = utilitario.geraInstrucoesRotuladasCompostas(this.instrucoesMonoliticasPrograma2);

		// TODO adicionar ciclos no final, com W.. quando tem ciclo
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
