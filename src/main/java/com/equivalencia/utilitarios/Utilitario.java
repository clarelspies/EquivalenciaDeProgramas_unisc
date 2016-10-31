package com.equivalencia.utilitarios;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.modelo.InstrucaoMonolitica;
import com.equivalencia.modelo.InstrucaoRotuladaComposta;
import com.equivalencia.modelo.tipo.TipoInstrucao;

public class Utilitario {

	private int contadorRotuloMonolitico = 1;
	private int contadorRotuloComposto = 1;

	// rotula as operacoes das intrucoes monoliticas, conforme serão executadas em instrucoes compostas
	public List<InstrucaoMonolitica> rotulaOperacoesEmOrdemExecucao(List<InstrucaoMonolitica> instrucoes) {
		List<Integer> listaOrdemExecucaoOperacoes = new ArrayList<>();

		int posicaoAtual = 0;
		for (InstrucaoMonolitica instrucao : instrucoes) {

			if (instrucao.getTipo() == TipoInstrucao.PARTIDA) {
				this.contadorRotuloMonolitico++;
			}

			if (instrucao.getTipo() == TipoInstrucao.TESTE) {
				listaOrdemExecucaoOperacoes.add(instrucao.getDestinoTesteVerdadeiro());
				listaOrdemExecucaoOperacoes.add(instrucao.getDestinoTesteFalso());
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
					instrucao.setRotuloOperacao(String.valueOf(this.contadorRotuloMonolitico));
					instrucoes.set(indexTesteAtual, instrucao);
					this.contadorRotuloMonolitico++;
				}
			} catch (Exception e) {
				// as vezes o destino é uma operacao vazia/ nao existe o rotulo
			}
		}

		return instrucoes;
	}

	// gera todas as intrucoes compostas a partir de uma lista de instrucoes monoliticas
	public List<InstrucaoRotuladaComposta> geraInstrucoesRotuladasCompostas(List<InstrucaoMonolitica> instrucoesMonoliticas) {
		List<InstrucaoRotuladaComposta> instrucoesCompostas = new ArrayList<>();

		boolean possuiCiclo = false;
		int contadorPosicaoAtual = 0;

		for (InstrucaoMonolitica instrucaoMonolitica : instrucoesMonoliticas) {
			if (instrucaoMonolitica.getTipo() == TipoInstrucao.OPERACAO || instrucaoMonolitica.getTipo() == TipoInstrucao.PARTIDA) {
				InstrucaoRotuladaComposta instrucaoComposta = Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(contadorPosicaoAtual, instrucoesMonoliticas);
				instrucaoComposta.setRotulo(String.valueOf(this.contadorRotuloComposto));
				instrucoesCompostas.add(instrucaoComposta);

				this.contadorRotuloComposto++;
				// teste para saber se houve algum ciclo no programa
				if (instrucaoComposta.getTipo1() == TipoInstrucao.CICLO || instrucaoComposta.getTipo2() == TipoInstrucao.CICLO) {
					possuiCiclo = true;
				}
			}
			contadorPosicaoAtual++;
		}

		// se teve algum ciclo no programa, no final adiciona:
		if (possuiCiclo) {
			// auxiliar usado só para gerar SIGMA do CICLO
			InstrucaoMonolitica instrucaoMonoliticaAuxiliar = new InstrucaoMonolitica();
			instrucaoMonoliticaAuxiliar.setTipo(TipoInstrucao.CICLO);

			InstrucaoRotuladaComposta instrucaoComposta = new InstrucaoRotuladaComposta();
			instrucaoComposta.setRotulo(TipoInstrucao.CICLO.getRotulo(instrucaoMonoliticaAuxiliar));
			// SET PRA PRIMEIRA COLUNA
			instrucaoComposta.setInstrucaoRotulada(TipoInstrucao.CICLO.getIdentificador(instrucaoMonoliticaAuxiliar), TipoInstrucao.CICLO.getRotulo(instrucaoMonoliticaAuxiliar), TipoInstrucao.CICLO);
			// SET PRA SEGUNDA COLUNA
			instrucaoComposta.setInstrucaoRotulada(TipoInstrucao.CICLO.getIdentificador(instrucaoMonoliticaAuxiliar), TipoInstrucao.CICLO.getRotulo(instrucaoMonoliticaAuxiliar), TipoInstrucao.CICLO);
			instrucoesCompostas.add(instrucaoComposta);
		}

		return instrucoesCompostas;
	}

	// defini a partir de uma instrucao monolitica, qual será a instrucao composta correspondente
	public static InstrucaoRotuladaComposta geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(int posicaoInicial, List<InstrucaoMonolitica> instrucoesMonoliticas) {
		InstrucaoRotuladaComposta instrucaoComposta = new InstrucaoRotuladaComposta();
		InstrucaoMonolitica instrucaoInicial = instrucoesMonoliticas.get(posicaoInicial);

		int executouEmLoop = 0;
		boolean primeiraColuna = true;
		boolean trocou = false;

		int indexInstrucaoEmTeste = instrucaoInicial.buscaIndexProximaInstrucaoExecutada(primeiraColuna);
		while (instrucaoComposta.getTipo1() == null || instrucaoComposta.getTipo2() == null) {

			// cada percorrida, busca proxima!
			InstrucaoMonolitica instrucaoEmTeste = null;
			try {
				instrucaoEmTeste = instrucoesMonoliticas.get(indexInstrucaoEmTeste);

				// primeiro caso de loop, fica executando TESTES sem chegar a uma OPERACAO
				if (executouEmLoop > 50) {
					instrucaoComposta.setInstrucaoRotulada(TipoInstrucao.CICLO.getIdentificador(instrucaoEmTeste), TipoInstrucao.CICLO.getRotulo(instrucaoEmTeste), TipoInstrucao.CICLO);
					executouEmLoop = 0;
					primeiraColuna = false;
					trocou = true;
				} else {
					// se a instrucao q buscar for OPERACAO, ACHOU!
					if (instrucaoEmTeste.getTipo() == TipoInstrucao.OPERACAO) {
						instrucaoComposta.setInstrucaoRotulada(TipoInstrucao.OPERACAO.getIdentificador(instrucaoEmTeste), TipoInstrucao.OPERACAO.getRotulo(instrucaoEmTeste), TipoInstrucao.OPERACAO);
						executouEmLoop = 0;
						primeiraColuna = false;
						trocou = true;
					}
				}

			} catch (Exception e) {
				// se a instrucao q buscar na lista der uma exception, quer dizer que estava alem das intrucoes existentes.. é uma parada
				instrucaoComposta.setInstrucaoRotulada(TipoInstrucao.PARADA.getIdentificador(instrucaoEmTeste), TipoInstrucao.PARADA.getRotulo(instrucaoEmTeste), TipoInstrucao.PARADA);
				executouEmLoop = 0;
				primeiraColuna = false;
				trocou = true;
			}

			// SE NAO CAIR EM NENHUM CASO, ENTÃO É INSTRUCAO TESTE OU PARTIDA! CONTINUA PROCURANDO
			if (trocou) {
				indexInstrucaoEmTeste = instrucaoInicial.buscaIndexProximaInstrucaoExecutada(primeiraColuna);
				trocou = false;
			} else {
				indexInstrucaoEmTeste = instrucaoEmTeste.buscaIndexProximaInstrucaoExecutada(primeiraColuna);
				executouEmLoop++;
			}

		}

		return instrucaoComposta;
	}

}
