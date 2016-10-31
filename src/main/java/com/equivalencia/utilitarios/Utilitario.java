package com.equivalencia.utilitarios;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.modelo.InstrucaoMonolitica;
import com.equivalencia.modelo.tipo.TipoInstrucao;

public class Utilitario {

	private int contador = 1;

	public List<InstrucaoMonolitica> rotulaOperacoesEmOrdemExecucao(List<InstrucaoMonolitica> instrucoes) {
		List<Integer> listaOrdemExecucaoOperacoes = new ArrayList<>();

		int posicaoAtual = 0;
		for (InstrucaoMonolitica instrucao : instrucoes) {

			if (instrucao.getTipo() == TipoInstrucao.PARTIDA) {
				this.contador++;
			}

			if (instrucao.getTipo() == TipoInstrucao.TESTE) {
				listaOrdemExecucaoOperacoes.add(instrucao.getDestinoTesteVerdadeiro());
			}

			if (instrucao.getTipo() == TipoInstrucao.OPERACAO) {
				if (posicaoAtual == 1) {
					listaOrdemExecucaoOperacoes.add(1);
				} else {
					listaOrdemExecucaoOperacoes.add(instrucao.getDestinoOperacao());
				}
			}

			posicaoAtual++;
		}

		for (Integer indexTesteAtual : listaOrdemExecucaoOperacoes) {

			try {
				InstrucaoMonolitica instrucao = instrucoes.get(indexTesteAtual.intValue());
				if (instrucao.getTipo() == TipoInstrucao.OPERACAO && instrucao.getRotuloOperacao() == null) {
					instrucao.setRotuloOperacao(String.valueOf(this.contador));
					instrucoes.set(indexTesteAtual, instrucao);
					this.contador++;
				}
			} catch (Exception e) {
				// as vezes o destino é uma operacao vazia/ nao existe o rotulo
			}

		}

		int indexTesteAtual = 0;
		for (InstrucaoMonolitica instrucao : instrucoes) {
			if (instrucao.getTipo() == TipoInstrucao.OPERACAO && instrucao.getRotuloOperacao() == null) {
				instrucao.setRotuloOperacao(String.valueOf(this.contador));
				instrucoes.set(indexTesteAtual, instrucao);
				this.contador++;
			}
			indexTesteAtual++;
		}

		return instrucoes;
	}

}
