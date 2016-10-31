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

	// espera-se que o index da operacao que testamos agora seja OPERACAO ou PARTIDA
	public static InstrucaoRotuladaComposta geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(int posicaoInicial, List<InstrucaoMonolitica> instrucoesMonoliticas) {
		InstrucaoRotuladaComposta instrucaoComposta = new InstrucaoRotuladaComposta();

		InstrucaoMonolitica instrucaoInicial = instrucoesMonoliticas.get(posicaoInicial);
		int indexInstrucaoEmTeste = instrucaoInicial.buscaIndexProximaInstrucaoExecutada(true);
		int executouEmLoop = 0;

		while (true) {
			// cada percorrida, busca proxima!
			InstrucaoMonolitica instrucaoEmTeste = null;
			try {
				instrucaoEmTeste = instrucoesMonoliticas.get(indexInstrucaoEmTeste);

				// primeiro caso de loop, fica executando ele mesmo
				// segundo caso de loop, fica executando testes encadeados
				if (indexInstrucaoEmTeste == instrucaoEmTeste.buscaIndexProximaInstrucaoExecutada(true) || executouEmLoop > 20) {
					instrucaoComposta.setTipo1(TipoInstrucao.CICLO);
					instrucaoComposta.setIdentificador1(TipoInstrucao.CICLO.getIdentificador(instrucaoEmTeste));
					instrucaoComposta.setRotulo1(TipoInstrucao.CICLO.getRotulo(instrucaoEmTeste));
					executouEmLoop = 0;
					break;
				}

				// se a instrucao q buscar for OPERACAO, ACHOU!
				if (instrucaoEmTeste.getTipo() == TipoInstrucao.OPERACAO) {
					instrucaoComposta.setTipo1(TipoInstrucao.OPERACAO);
					instrucaoComposta.setIdentificador1(TipoInstrucao.OPERACAO.getIdentificador(instrucaoEmTeste));
					instrucaoComposta.setRotulo1(TipoInstrucao.OPERACAO.getRotulo(instrucaoEmTeste));
					executouEmLoop = 0;
					break;
				}

				// SE NAO CAIR EM NENHUM CASO, ENTÃO É INSTRUCAO TESTE OU PARTIDA! CONTINUA PROCURANDO
				indexInstrucaoEmTeste = instrucaoEmTeste.buscaIndexProximaInstrucaoExecutada(true);
			} catch (Exception e) {
				// se a instrucao q buscar na lista der uma exception, quer dizer que estava alem das intrucoes existentes.. é uma parada
				instrucaoComposta.setTipo1(TipoInstrucao.PARADA);
				instrucaoComposta.setIdentificador1(TipoInstrucao.PARADA.getIdentificador(instrucaoEmTeste));
				instrucaoComposta.setRotulo1(TipoInstrucao.PARADA.getRotulo(instrucaoEmTeste));
				executouEmLoop = 0;
				break;
			}

			executouEmLoop++;
		}

		indexInstrucaoEmTeste = instrucaoInicial.buscaIndexProximaInstrucaoExecutada(false);
		executouEmLoop = 0;

		while (true) {
			// cada percorrida, busca proxima!
			InstrucaoMonolitica instrucaoEmTeste = null;
			try {
				instrucaoEmTeste = instrucoesMonoliticas.get(indexInstrucaoEmTeste);

				if (indexInstrucaoEmTeste == instrucaoEmTeste.buscaIndexProximaInstrucaoExecutada(false) || executouEmLoop > 20) {
					instrucaoComposta.setTipo2(TipoInstrucao.CICLO);
					instrucaoComposta.setIdentificador2(TipoInstrucao.CICLO.getIdentificador(instrucaoEmTeste));
					instrucaoComposta.setRotulo2(TipoInstrucao.CICLO.getRotulo(instrucaoEmTeste));
					executouEmLoop = 0;
					break;
				}

				// se a instrucao q buscar for OPERACAO, ACHOU!
				if (instrucaoEmTeste.getTipo() == TipoInstrucao.OPERACAO) {
					instrucaoComposta.setTipo2(TipoInstrucao.OPERACAO);
					instrucaoComposta.setIdentificador2(TipoInstrucao.OPERACAO.getIdentificador(instrucaoEmTeste));
					instrucaoComposta.setRotulo2(TipoInstrucao.OPERACAO.getRotulo(instrucaoEmTeste));
					executouEmLoop = 0;
					break;
				}

				// SE NAO CAIR EM NENHUM CASO, ENTÃO É INSTRUCAO TESTE OU PARTIDA! CONTINUA PROCURANDO
				indexInstrucaoEmTeste = instrucaoEmTeste.buscaIndexProximaInstrucaoExecutada(false);
			} catch (Exception e) {
				// se a instrucao q buscar na lista der uma exception, quer dizer que estava alem das intrucoes existentes.. é uma parada
				instrucaoComposta.setTipo2(TipoInstrucao.PARADA);
				instrucaoComposta.setIdentificador2(TipoInstrucao.PARADA.getIdentificador(instrucaoEmTeste));
				instrucaoComposta.setRotulo2(TipoInstrucao.PARADA.getRotulo(instrucaoEmTeste));
				executouEmLoop = 0;
				break;
			}
			executouEmLoop++;
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
