package com.equivalencia.modelo;

import java.util.List;

import com.equivalencia.modelo.tipo.TipoInstrucao;
import com.equivalencia.utilitarios.UtilitarioRotuloOperacoes;

public class Programa {

	List<InstrucaoMonolitica> instrucoesMonoliticasPrograma1;
	List<InstrucaoMonolitica> instrucoesMonoliticasPrograma2;

	// instancia do programa, a partir da entrada de instrucoes monolitcas do usuario em formato STRING/TEXTO
	public Programa(List<InstrucaoMonolitica> programa1, List<InstrucaoMonolitica> programa2) throws Exception {
		this.instrucoesMonoliticasPrograma1 = programa1;
		this.instrucoesMonoliticasPrograma2 = programa2;

		// o 1 é a partida do programa 1
		int comeco = 1;

		UtilitarioRotuloOperacoes utilitario = UtilitarioRotuloOperacoes.rotulaFuncoesDasInstrucoesMonoliticasPrograma(comeco, instrucoesMonoliticasPrograma1);
		// o utilitario guarda o numero da proxima funcao, que sera a funcao de partida da segunda lista de instrucoes
		UtilitarioRotuloOperacoes.rotulaFuncoesDasInstrucoesMonoliticasPrograma(utilitario.getContador(), instrucoesMonoliticasPrograma2);
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

				if (instrucaoEmTeste.getTipo() == TipoInstrucao.PARTIDA) {
					indexInstrucaoEmTeste = 1;
					continue;
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

				if (instrucaoEmTeste.getTipo() == TipoInstrucao.PARTIDA) {
					indexInstrucaoEmTeste = 1;
					continue;
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

	public List<InstrucaoMonolitica> getInstrucoesMonoliticasPrograma1() {
		return instrucoesMonoliticasPrograma1;
	}

	public List<InstrucaoMonolitica> getInstrucoesMonoliticasPrograma2() {
		return instrucoesMonoliticasPrograma2;
	}

}
