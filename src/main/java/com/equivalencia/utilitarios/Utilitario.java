package com.equivalencia.utilitarios;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.modelo.CadeiaConjuntoFinito;
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
		// adiciona em uma lista, a ordem com que os rotulos sao executadas!
		for (InstrucaoMonolitica instrucao : instrucoes) {

			if (instrucao.getTipo() == TipoInstrucao.PARTIDA) {
				this.contadorRotuloMonolitico++;
			}

			if (instrucao.getTipo() == TipoInstrucao.TESTE) {
				listaOrdemExecucaoOperacoes.add(instrucao.getDestinoTesteVerdadeiro());
				listaOrdemExecucaoOperacoes.add(instrucao.getDestinoTesteFalso());
			}

			if (instrucao.getTipo() == TipoInstrucao.OPERACAO) {
				// caso de quando a primeira instrucao e uma operacao, ele nao executa a proxima! ele executa ele mesmo!
				if (posicaoAtual == 1) {
					listaOrdemExecucaoOperacoes.add(1);
				} else {
					// executa o destino
					listaOrdemExecucaoOperacoes.add(instrucao.getDestinoOperacao());
				}
			}

			posicaoAtual++;
		}

		// percorre a lista de ordem, identificando operacoes e numerando elas (rotulo) conforme sao executadas
		for (Integer indexTesteAtual : listaOrdemExecucaoOperacoes) {
			try {
				InstrucaoMonolitica instrucao = instrucoes.get(indexTesteAtual.intValue());
				if (instrucao.getTipo() == TipoInstrucao.OPERACAO && instrucao.getRotuloOperacao() == null) {
					instrucao.setRotuloOperacao(String.valueOf(this.contadorRotuloMonolitico));
					instrucoes.set(indexTesteAtual, instrucao);
					this.contadorRotuloMonolitico++;
				}
			} catch (Exception e) {
				// as vezes o destino e uma operacao vazia/ nao existe o rotulo, nao faz nada neste caso
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

	// na lista de instrucoes compostas!
	// 1 - ENCONTRAR ROTULO QUE CONTEM A ULTIMA PARADA!

	public static List<CadeiaConjuntoFinito> defineCadeiaConjuntoFinito(List<InstrucaoRotuladaComposta> instrucoesCompostas) {
		// usado para nao alterar a primeira lista/original, java trabalha com ponteiros
		List<InstrucaoRotuladaComposta> listaInstrucoesAuxiliares = new ArrayList<>(instrucoesCompostas);

		List<CadeiaConjuntoFinito> cadeias = new ArrayList<>();
		cadeias.add(new CadeiaConjuntoFinito());

		int indexTeste = 0;
		// procura ultima parada, começa testando por ela
		int contador = 0;
		for (InstrucaoRotuladaComposta instrucao : listaInstrucoesAuxiliares) {
			if (instrucao != null) {
				if (instrucao.getTipo1() == TipoInstrucao.PARADA || instrucao.getTipo2() == TipoInstrucao.PARADA) {
					indexTeste = contador;
				}
			}
			contador++;
		}

		boolean continuaAdicionando = true;

		InstrucaoRotuladaComposta instrucaoEmTeste = null;
		int indexLimitePrograma = indexTeste;
		// procura todos iguais ao rotulo q estou testando....
		while (continuaAdicionando) {
			instrucaoEmTeste = listaInstrucoesAuxiliares.get(indexTeste);
			StringBuilder conjunto = new StringBuilder(instrucaoEmTeste.getRotulo());
			listaInstrucoesAuxiliares.set(indexTeste, null);

			contador = 0;
			for (InstrucaoRotuladaComposta instrucao : listaInstrucoesAuxiliares) {
				if (instrucao != null && instrucaoEmTeste.toString().equals(instrucao.toString())) {
					// remove da lista de instrucoes compostas pois ja foi identificada para adicionar no conjunto finito
					listaInstrucoesAuxiliares.set(contador, null);
					// adiciona a lista do proximo conjunto finito
					conjunto.append("," + instrucao.getRotulo());
				}
				contador++;
			}
			// monta o proximo conjunto finito e adiciona a lista
			cadeias.add((new CadeiaConjuntoFinito(cadeias.size() - 1, cadeias.get(cadeias.size() - 1).getConjunto(), conjunto.toString())));

			// percorre a lista para saber se sobrou alguma instrucao para adicionar! ps: menor que o index inicial/da ultima parada
			contador = 0;
			//set false caso nao ache novo conjunto para adicionar
			continuaAdicionando = false;
			for (InstrucaoRotuladaComposta instrucao : listaInstrucoesAuxiliares) {
				if (instrucao != null && contador < indexLimitePrograma) {
					// achou novo index pra testar!
					indexTeste = contador;
					continuaAdicionando = true;
				}
				contador++;
			}

		}

		// por formalismo, o ultimo conjunto se repete com novo rotulo!
		cadeias.add((new CadeiaConjuntoFinito(cadeias.size() - 1, cadeias.get(cadeias.size() - 1).getConjunto(), null)));
		return cadeias;
	}

}
