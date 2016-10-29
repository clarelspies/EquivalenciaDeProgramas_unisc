package com.equivalencia.modelo;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.modelo.tipo.TipoInstrucao;

public class Programa {

	List<InstrucaoMonolitica> instrucoesMonoliticas;

	// instancia do programa, a partir da entrada de instrucoes monolitcas do usuario em formato STRING/TEXTO
	public Programa(List<String> entradas) throws Exception {
		this.instrucoesMonoliticas = new ArrayList<>();
		// add instrucao partida!
		instrucoesMonoliticas.add(new InstrucaoMonolitica());
		for (String entrada : entradas) {
			InstrucaoMonolitica instrucao = InstrucaoMonolitica.criaObjetoInstrucaoAtravesEntradaUsuario(entrada);
			instrucoesMonoliticas.add(instrucao);
		}
		this.rotulaFuncoesDasInstrucoesMonoliticas();
	}

	private void rotulaFuncoesDasInstrucoesMonoliticas() {
		List<Integer> ordemTesteOperacao = new ArrayList<>();

		for (InstrucaoMonolitica instrucao : this.instrucoesMonoliticas) {
			if (instrucao.getTipo() == TipoInstrucao.TESTE) {
				ordemTesteOperacao.add(instrucao.getDestinoTesteVerdadeiro());
			}
		}

		// começa em 2 pois a partida é o rótulo 1
		int contadorAtual = 2;
		for (Integer indexTesteAtual : ordemTesteOperacao) {
			InstrucaoMonolitica instrucao = this.instrucoesMonoliticas.get(indexTesteAtual.intValue());
			if (instrucao.getTipo() == TipoInstrucao.OPERACAO) {
				instrucao.setRotuloOperacao(String.valueOf(contadorAtual));
				this.instrucoesMonoliticas.set(indexTesteAtual, instrucao);
				contadorAtual++;
			}
		}
	}

	// defini instrucoes rotuladas compostas a partir das intrucoesmonoliticas,
	// primeiro passo da equivalencia!
	public void definiInstrucoesRotuladas() {

		int posicaoAtual = 0;
		for (InstrucaoMonolitica instrucaoMonolitica : this.instrucoesMonoliticas) {
			// vai procurar a partir do início e de cada operacao
			if (instrucaoMonolitica.getTipo() == TipoInstrucao.PARADA || instrucaoMonolitica.getTipo() == TipoInstrucao.OPERACAO) {

			}

			posicaoAtual++;
		}

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

	// espera-se que o index da operacao que testamos agora seja OPERACAO ou PARTIDA
	public static InstrucaoRotuladaComposta geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(int posicaoInicial, List<InstrucaoMonolitica> instrucoesMonoliticas) {
		InstrucaoRotuladaComposta instrucaoComposta = new InstrucaoRotuladaComposta();

		InstrucaoMonolitica instrucaoInicial = instrucoesMonoliticas.get(posicaoInicial);
		int indexInstrucaoEmTeste = instrucaoInicial.buscaIndexProximaInstrucaoExecutada(true);

		while (true) {
			// cada percorrida, busca proxima!
			InstrucaoMonolitica instrucaoEmTeste = null;
			try {
				instrucaoEmTeste = instrucoesMonoliticas.get(indexInstrucaoEmTeste);
				// se a isntrucao q buscar na lista for igual instrucaoAtual.. então hovue um loop! é um CICLO
				if (posicaoInicial == indexInstrucaoEmTeste) {
					instrucaoComposta.setTipo1(TipoInstrucao.CICLO);
					instrucaoComposta.setIdentificador1(TipoInstrucao.CICLO.getIdentificador(instrucaoEmTeste));
					instrucaoComposta.setRotulo1(TipoInstrucao.CICLO.getRotulo(instrucaoEmTeste));
					break;
				}

				// se a instrucao q buscar for OPERACAO, ACHOU!
				if (instrucaoEmTeste.getTipo() == TipoInstrucao.OPERACAO) {
					instrucaoComposta.setTipo1(TipoInstrucao.OPERACAO);
					instrucaoComposta.setIdentificador1(TipoInstrucao.OPERACAO.getIdentificador(instrucaoEmTeste));
					instrucaoComposta.setRotulo1(TipoInstrucao.OPERACAO.getRotulo(instrucaoEmTeste));
					break;
				}

				// SE NAO CAIR EM NENHUM CASO, ENTÃO É INSTRUCAO TESTE OU PARTIDA! CONTINUA PROCURANDO
				indexInstrucaoEmTeste = instrucaoEmTeste.buscaIndexProximaInstrucaoExecutada(true);
			} catch (Exception e) {
				// se a instrucao q buscar na lista der uma exception, quer dizer que estava alem das intrucoes existentes.. é uma parada
				instrucaoComposta.setTipo1(TipoInstrucao.PARADA);
				instrucaoComposta.setIdentificador1(TipoInstrucao.PARADA.getIdentificador(instrucaoEmTeste));
				instrucaoComposta.setRotulo1(TipoInstrucao.PARADA.getRotulo(instrucaoEmTeste));
				break;
			}
		}

		indexInstrucaoEmTeste = instrucaoInicial.buscaIndexProximaInstrucaoExecutada(false);

		while (true) {
			// cada percorrida, busca proxima!
			InstrucaoMonolitica instrucaoEmTeste = null;
			try {
				instrucaoEmTeste = instrucoesMonoliticas.get(indexInstrucaoEmTeste);
				// se a isntrucao q buscar na lista for igual instrucaoAtual.. então hovue um loop! é um CICLO
				if (posicaoInicial == indexInstrucaoEmTeste) {
					instrucaoComposta.setTipo2(TipoInstrucao.CICLO);
					instrucaoComposta.setIdentificador2(TipoInstrucao.CICLO.getIdentificador(instrucaoEmTeste));
					instrucaoComposta.setRotulo2(TipoInstrucao.CICLO.getRotulo(instrucaoEmTeste));
					break;
				}

				// se a instrucao q buscar for OPERACAO, ACHOU!
				if (instrucaoEmTeste.getTipo() == TipoInstrucao.OPERACAO) {
					instrucaoComposta.setTipo2(TipoInstrucao.OPERACAO);
					instrucaoComposta.setIdentificador2(TipoInstrucao.OPERACAO.getIdentificador(instrucaoEmTeste));
					instrucaoComposta.setRotulo2(TipoInstrucao.OPERACAO.getRotulo(instrucaoEmTeste));
					break;
				}

				// SE NAO CAIR EM NENHUM CASO, ENTÃO É INSTRUCAO TESTE OU PARTIDA! CONTINUA PROCURANDO
				indexInstrucaoEmTeste = instrucaoEmTeste.buscaIndexProximaInstrucaoExecutada(false);
			} catch (Exception e) {
				// se a instrucao q buscar na lista der uma exception, quer dizer que estava alem das intrucoes existentes.. é uma parada
				instrucaoComposta.setTipo2(TipoInstrucao.PARADA);
				instrucaoComposta.setIdentificador2(TipoInstrucao.PARADA.getIdentificador(instrucaoEmTeste));
				instrucaoComposta.setRotulo2(TipoInstrucao.PARADA.getRotulo(instrucaoEmTeste));
				break;
			}
		}

		return instrucaoComposta;
	}

	public List<InstrucaoMonolitica> getInstrucoesMonoliticas() {
		return instrucoesMonoliticas;
	}

}
