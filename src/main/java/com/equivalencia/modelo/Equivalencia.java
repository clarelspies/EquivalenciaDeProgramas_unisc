package com.equivalencia.modelo;

import java.util.List;

import com.equivalencia.utilitarios.Utilitario;

public class Equivalencia {

	List<InstrucaoMonolitica> instrucoesMonoliticasPrograma1;
	List<InstrucaoMonolitica> instrucoesMonoliticasPrograma2;

	List<InstrucaoRotuladaComposta> instrucoesCompostas1;
	List<InstrucaoRotuladaComposta> instrucoesCompostas2;

	List<CadeiaConjuntoFinito> cadeiaConjuntosFinitos1;
	List<CadeiaConjuntoFinito> cadeiaConjuntosFinitos2;

	// armazena instrucoes prontas para a equivalencia
	List<InstrucaoRotuladaComposta> instrucoesCompostasSimplificadas1;
	List<InstrucaoRotuladaComposta> instrucoesCompostasSimplificadas2;

	// instancia do programa, a partir da entrada de instrucoes monolitcas do usuario em formato STRING/TEXTO
	public Equivalencia(List<InstrucaoMonolitica> programa1, List<InstrucaoMonolitica> programa2) throws Exception {
		Utilitario utilitario = new Utilitario();

		// informas as instrucoes monoliticas
		this.instrucoesMonoliticasPrograma1 = programa1;
		this.instrucoesMonoliticasPrograma2 = programa2;

		// rotula as operacoes monoliticas em ordem de execucao, para instrucoes compostas
		utilitario.rotulaOperacoesEmOrdemExecucao(this.instrucoesMonoliticasPrograma1);
		utilitario.rotulaOperacoesEmOrdemExecucao(this.instrucoesMonoliticasPrograma2);

		// 1 passo, definição das intruções rotuladas compostas
		this.instrucoesCompostas1 = utilitario.geraInstrucoesRotuladasCompostas(this.instrucoesMonoliticasPrograma1);
		this.instrucoesCompostas2 = utilitario.geraInstrucoesRotuladasCompostas(this.instrucoesMonoliticasPrograma2);

		// 2 passo, definição da cadeia de conjuntos
		this.cadeiaConjuntosFinitos1 = Utilitario.defineCadeiaConjuntoFinito(this.instrucoesCompostas1);
		this.cadeiaConjuntosFinitos2 = Utilitario.defineCadeiaConjuntoFinito(this.instrucoesCompostas2);

		List<InstrucaoRotuladaComposta> rotulosAlemLimitePrograma1 = Utilitario.verificaQuaisInstrucoesCompostasFicaramForaLimitePrograma(this.instrucoesCompostas1, this.cadeiaConjuntosFinitos1.get(this.cadeiaConjuntosFinitos1.size() - 1));
		List<InstrucaoRotuladaComposta> rotulosAlemLimitePrograma2 = Utilitario.verificaQuaisInstrucoesCompostasFicaramForaLimitePrograma(this.instrucoesCompostas2, this.cadeiaConjuntosFinitos2.get(this.cadeiaConjuntosFinitos2.size() - 1));

		// 3 passo, simplificação (nem sempre é necessário)
		// verifica para o programa 1 se teve rotulos fora do limite do programa e simplifica se necessário
		if (rotulosAlemLimitePrograma1.size() > 0) {
			this.instrucoesCompostasSimplificadas1 = Utilitario.executaSimplificacao(this.instrucoesCompostas1, rotulosAlemLimitePrograma1);
		} else {
			this.instrucoesCompostasSimplificadas1 = this.instrucoesCompostas1;
		}

		// verifica para o programa 2 se teve rotulos fora do limite do programa e simplifica se necessário
		if (rotulosAlemLimitePrograma2.size() > 0) {
			this.instrucoesCompostasSimplificadas2 = Utilitario.executaSimplificacao(this.instrucoesCompostas2, rotulosAlemLimitePrograma2);
		} else {
			this.instrucoesCompostasSimplificadas2 = this.instrucoesCompostas2;
		}

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

	public List<CadeiaConjuntoFinito> getCadeiaConjuntosFinitos1() {
		return cadeiaConjuntosFinitos1;
	}

	public List<CadeiaConjuntoFinito> getCadeiaConjuntosFinitos2() {
		return cadeiaConjuntosFinitos2;
	}

	public List<InstrucaoRotuladaComposta> getInstrucoesCompostasSimplificadas1() {
		return instrucoesCompostasSimplificadas1;
	}

	public List<InstrucaoRotuladaComposta> getInstrucoesCompostasSimplificadas2() {
		return instrucoesCompostasSimplificadas2;
	}

}
