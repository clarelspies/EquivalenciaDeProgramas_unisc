package com.equivalencia.utilitarios;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.modelo.InstrucaoMonolitica;
import com.equivalencia.modelo.tipo.TipoInstrucao;

//classe utilizada para retornar a lista de instrucoes numeradas, e um contador que representa a contagem;
public class UtilitarioRotuloOperacoes {

	private int contador;
	private List<InstrucaoMonolitica> instrucoes;

	//se for o primeiro programa, começa em 2!
	public static UtilitarioRotuloOperacoes rotulaFuncoesDasInstrucoesMonoliticasPrograma(int comeco, List<InstrucaoMonolitica> instrucoes) {
		UtilitarioRotuloOperacoes retorno = new UtilitarioRotuloOperacoes();

		List<Integer> ordemTesteOperacao = new ArrayList<>();
		comeco++;
		int posicaoAtual = 0;
		for (InstrucaoMonolitica instrucao : instrucoes) {
			
			if (instrucao.getTipo() == TipoInstrucao.TESTE) {
				ordemTesteOperacao.add(instrucao.getDestinoTesteVerdadeiro());
				
			}
			
			// problema de começar com OPERACAO ao inves de TESTE
			if (posicaoAtual == 1 && instrucao.getTipo() == TipoInstrucao.OPERACAO) {
				ordemTesteOperacao.add(1);
			
			}

			posicaoAtual++;
		}

		for (Integer indexTesteAtual : ordemTesteOperacao) {

			try {
				InstrucaoMonolitica instrucao = instrucoes.get(indexTesteAtual.intValue());
				if (instrucao.getTipo() == TipoInstrucao.OPERACAO && instrucao.getRotuloOperacao() == null) {
					instrucao.setRotuloOperacao(String.valueOf(comeco));
					instrucoes.set(indexTesteAtual, instrucao);
					comeco++;
				}
			} catch (Exception e) {
				// as vezes o destino é uma operacao vazia/ nao existe o rotulo
			}

		}

		int indexTesteAtual = 0;
		for (InstrucaoMonolitica instrucao : instrucoes) {
			if (instrucao.getTipo() == TipoInstrucao.OPERACAO && instrucao.getRotuloOperacao() == null) {
				instrucao.setRotuloOperacao(String.valueOf(comeco));
				instrucoes.set(indexTesteAtual, instrucao);
				comeco++;
			}
			indexTesteAtual++;
		}

		retorno.setContador(comeco);
		retorno.setInstrucoes(instrucoes);

		return retorno;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public List<InstrucaoMonolitica> getInstrucoes() {
		return instrucoes;
	}

	public void setInstrucoes(List<InstrucaoMonolitica> instrucoes) {
		this.instrucoes = instrucoes;
	}

}
