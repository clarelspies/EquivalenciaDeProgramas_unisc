package com.equivalencia.utilitarios;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.modelo.CadeiaConjuntoFinito;
import com.equivalencia.modelo.ParesDeRotulos;
import com.equivalencia.modelo.ResultadoEquivalencia;
import com.equivalencia.modelo.InstrucaoMonolitica;
import com.equivalencia.modelo.InstrucaoRotuladaComposta;
import com.equivalencia.modelo.TipoInstrucao;

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
		int contadorPosicaoAtual = 0;

		for (InstrucaoMonolitica instrucaoMonolitica : instrucoesMonoliticas) {
			if (instrucaoMonolitica.getTipo() == TipoInstrucao.OPERACAO || instrucaoMonolitica.getTipo() == TipoInstrucao.PARTIDA) {
				InstrucaoRotuladaComposta instrucaoComposta = Utilitario.geraInstrucaoRotuladaCompostaAtravesOperacaoOuPartida(contadorPosicaoAtual, instrucoesMonoliticas);
				instrucaoComposta.setRotulo(String.valueOf(this.contadorRotuloComposto));
				instrucoesCompostas.add(instrucaoComposta);

				this.contadorRotuloComposto++;
			}
			contadorPosicaoAtual++;
		}

		// se teve algum ciclo no programa, no final adiciona:
		instrucoesCompostas = adicionaCiclosAoFinalSeNecessario(instrucoesCompostas);

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
		// procura todos iguais ao rotulo q estou testando....mesmo após parada!
		while (continuaAdicionando) {
			instrucaoEmTeste = listaInstrucoesAuxiliares.get(indexTeste);
			StringBuilder conjunto = new StringBuilder(instrucaoEmTeste.getRotulo());
			listaInstrucoesAuxiliares.set(indexTeste, null);

			contador = 0;
			// verifica se tem rotulo igual ao rotulo em teste, para adicionar ao conjunto em andamento!
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

			// percorre a lista para saber se sobrou alguma instrucao para adicionar! ps: que nao tenha ultrapassado limite do programa!
			contador = 0;
			// set false caso nao ache novo conjunto para adicionar
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

	// verifica se ha algum rótulo além do limite do programa!
	// recebe a lista de instrucoes compostas, e a cadeia de conjunto finito FINAL gerada!
	// vai verificar se algum rótulo diferente de "ω" não foi incluído na cadeia de conjuntos finitos!
	public static List<InstrucaoRotuladaComposta> verificaQuaisInstrucoesCompostasFicaramForaLimitePrograma(List<InstrucaoRotuladaComposta> instrucoesRotuladasCompostas, CadeiaConjuntoFinito cadeiaConjuntoFinito) {
		List<InstrucaoRotuladaComposta> instrucoesCompostasForaLimitePrograma = new ArrayList<>();

		for (InstrucaoRotuladaComposta instrucao : instrucoesRotuladasCompostas) {
			if (instrucao != null && instrucao.getRotulo() != "ω" && !cadeiaConjuntoFinito.toString().contains(instrucao.getRotulo())) {
				instrucoesCompostasForaLimitePrograma.add(instrucao);
			}
		}

		return instrucoesCompostasForaLimitePrograma;
	}

	// eliminar os rotulos fora do limite do programa, e substituir as referencias a este rotulo!
	public static List<InstrucaoRotuladaComposta> executaSimplificacao(List<InstrucaoRotuladaComposta> instrucoesRotuladasCompostas, List<InstrucaoRotuladaComposta> instrucoeRotuladasCompostasForaLimitePrograma) {
		List<InstrucaoRotuladaComposta> instrucoeRotuladasCompostasSimplificadas = new ArrayList<>();

		// adiciona na lista nova todas que nao estao na lista de instrucoes fora do limite!
		for (InstrucaoRotuladaComposta instrucao : instrucoesRotuladasCompostas) {

			boolean foraLimite = false;
			// se a instrucao em teste nao está na lista de instrucoes fora do limite, adiciona ela!
			for (InstrucaoRotuladaComposta intrucaoForaLimite : instrucoeRotuladasCompostasForaLimitePrograma) {

				// se cair dentro, é porque a instrucao em teste esta fora do limite e nao pode ser adicionada!
				if (instrucao.getRotulo().equals(intrucaoForaLimite.getRotulo())) {
					foraLimite = true;
					break;
				}
			}

			// adiciona
			if (foraLimite == false) {
				// preciso dar um new, ao inves de passar a referencia.
				// java trabalha com ponteiros, a lista de simplificadas alteraria a lista original!
				instrucoeRotuladasCompostasSimplificadas.add(new InstrucaoRotuladaComposta(instrucao));
			}

		}

		int contador = 0;
		// para cada instrucao, usa metodo que verifica se precisa trocar referencia ou nao e executa
		for (InstrucaoRotuladaComposta instrucao : instrucoeRotuladasCompostasSimplificadas) {
			InstrucaoRotuladaComposta instrucaoTeste = new InstrucaoRotuladaComposta();
			instrucaoTeste = instrucao;

			InstrucaoRotuladaComposta instrucaoAposVerificacaoReferencia = executaTrocaReferenciaSeNecessario(instrucaoTeste, instrucoeRotuladasCompostasForaLimitePrograma);
			instrucoeRotuladasCompostasSimplificadas.set(contador, instrucaoAposVerificacaoReferencia);
			contador++;
		}
		// caso seja necessario adicionar ciclos no final, necessidade pode aparecer apos simplificaçao!
		instrucoeRotuladasCompostasSimplificadas = adicionaCiclosAoFinalSeNecessario(instrucoeRotuladasCompostasSimplificadas);
		return instrucoeRotuladasCompostasSimplificadas;
	}

	// TODO REFATORAR, ESTÁ MUITO FEIO ESTE MÉTODO!
	// procura as referencias ao conjunto 1 ou 2 de cada uma das intrucoes fora do limite, e substitui por ciclo!
	public static InstrucaoRotuladaComposta executaTrocaReferenciaSeNecessario(InstrucaoRotuladaComposta instrucaoEmTeste, List<InstrucaoRotuladaComposta> instrucoeRotuladasCompostasForaLimitePrograma) {

		for (InstrucaoRotuladaComposta instrucaoForaLimite : instrucoeRotuladasCompostasForaLimitePrograma) {
			// testa primeira coluna da instrucao em teste com primeira coluna da instrucao fora limite
			if (instrucaoEmTeste.getIdentificador1().equals(instrucaoForaLimite.getIdentificador1()) && instrucaoEmTeste.getRotulo1().equals(instrucaoForaLimite.getRotulo1())) {
				instrucaoEmTeste.setIdentificador1(TipoInstrucao.CICLO.getIdentificador(null));
				instrucaoEmTeste.setRotulo1(TipoInstrucao.CICLO.getRotulo(null));
				instrucaoEmTeste.setTipo1(TipoInstrucao.CICLO);
			}
			// testa primeira coluna da instrucao em teste com segunda coluna da instrucao fora limite
			if (instrucaoEmTeste.getIdentificador1().equals(instrucaoForaLimite.getIdentificador2()) && instrucaoEmTeste.getRotulo1().equals(instrucaoForaLimite.getRotulo2())) {
				instrucaoEmTeste.setIdentificador1(TipoInstrucao.CICLO.getIdentificador(null));
				instrucaoEmTeste.setRotulo1(TipoInstrucao.CICLO.getRotulo(null));
				instrucaoEmTeste.setTipo1(TipoInstrucao.CICLO);
			}

			// testa segunda coluna da instrucao em teste com primeira coluna da instrucao fora limite
			if (instrucaoEmTeste.getIdentificador2().equals(instrucaoForaLimite.getIdentificador1()) && instrucaoEmTeste.getRotulo2().equals(instrucaoForaLimite.getRotulo1())) {
				instrucaoEmTeste.setIdentificador2(TipoInstrucao.CICLO.getIdentificador(null));
				instrucaoEmTeste.setRotulo2(TipoInstrucao.CICLO.getRotulo(null));
				instrucaoEmTeste.setTipo2(TipoInstrucao.CICLO);
			}
			// testa segunda coluna da instrucao em teste com segunda coluna da instrucao fora limite
			if (instrucaoEmTeste.getIdentificador2().equals(instrucaoForaLimite.getIdentificador2()) && instrucaoEmTeste.getRotulo2().equals(instrucaoForaLimite.getRotulo2())) {
				instrucaoEmTeste.setIdentificador2(TipoInstrucao.CICLO.getIdentificador(null));
				instrucaoEmTeste.setRotulo2(TipoInstrucao.CICLO.getRotulo(null));
				instrucaoEmTeste.setTipo2(TipoInstrucao.CICLO);
			}

		}

		return instrucaoEmTeste;
	}

	// adiciona sigma + ciclos no final da lista de instrucoes rotuladas composta, usada no passo 1 e no passo 3!
	public static List<InstrucaoRotuladaComposta> adicionaCiclosAoFinalSeNecessario(List<InstrucaoRotuladaComposta> instrucoes) {

		// variavel que vai me dar o resultado se achou algum ciclo no programa
		boolean possuiAlgumCiclo = false;
		// variavel que vai me dar o resultado se ainda precisa adicionar o ciclo ocm rotulo sigma ao final (se ja tem, nao precisa)
		boolean precisaAdicionaCicloAoFinal = false;
		// ambas precisam ser true para adicionar!

		for (InstrucaoRotuladaComposta instrucao : instrucoes) {
			if (instrucao.getTipo1() == TipoInstrucao.CICLO || instrucao.getTipo2() == TipoInstrucao.CICLO) {
				possuiAlgumCiclo = true;
			}
		}

		// verifica se a ultima instrucao é um ciclo de rotulo sigma
		if (!instrucoes.get(instrucoes.size() - 1).getRotulo().equals(TipoInstrucao.CICLO.getRotulo(null))) {
			// cai aqui dentro de não é!
			precisaAdicionaCicloAoFinal = true;
		}

		if (precisaAdicionaCicloAoFinal && possuiAlgumCiclo) {
			// auxiliar usado só para gerar SIGMA do CICLO
			InstrucaoMonolitica instrucaoMonoliticaAuxiliar = new InstrucaoMonolitica();
			instrucaoMonoliticaAuxiliar.setTipo(TipoInstrucao.CICLO);

			InstrucaoRotuladaComposta instrucaoComposta = new InstrucaoRotuladaComposta();
			instrucaoComposta.setRotulo(TipoInstrucao.CICLO.getRotulo(instrucaoMonoliticaAuxiliar));
			// SET PRA PRIMEIRA COLUNA
			instrucaoComposta.setInstrucaoRotulada(TipoInstrucao.CICLO.getIdentificador(instrucaoMonoliticaAuxiliar), TipoInstrucao.CICLO.getRotulo(instrucaoMonoliticaAuxiliar), TipoInstrucao.CICLO);
			// SET PRA SEGUNDA COLUNA
			instrucaoComposta.setInstrucaoRotulada(TipoInstrucao.CICLO.getIdentificador(instrucaoMonoliticaAuxiliar), TipoInstrucao.CICLO.getRotulo(instrucaoMonoliticaAuxiliar), TipoInstrucao.CICLO);
			instrucoes.add(instrucaoComposta);
		}

		return instrucoes;

	}

	// 4 passo, verifica a equivalencia dos programas
	// união passada por ponteiro/referencia
	public static ResultadoEquivalencia verificaEquivalenciaProgramas(List<ParesDeRotulos> paresDeRotulo, List<InstrucaoRotuladaComposta> instrucoesSimplificadas1, List<InstrucaoRotuladaComposta> instrucoesSimplificadas2) {
		ResultadoEquivalencia resultado = new ResultadoEquivalencia();
		resultado.setSaoEquivalentes(true);

		// java trabalha com ponteiros/referencia
		List<InstrucaoRotuladaComposta> simplificadas1 = new ArrayList<>(instrucoesSimplificadas1);
		List<InstrucaoRotuladaComposta> verificadas1 = new ArrayList<>();

		List<InstrucaoRotuladaComposta> simplificadas2 = new ArrayList<>(instrucoesSimplificadas2);
		List<InstrucaoRotuladaComposta> verificadas2 = new ArrayList<>();

		// ADICIONA OS PRIMEIROS ROTULOS DE CADA PROGRAMA!
		paresDeRotulo.add(new ParesDeRotulos(simplificadas1.get(0), simplificadas2.get(0)));

		// percorre a lista comparando!

		// 1 - compara as instrucoes 1 de cada instrucaocomposta
		int maximo1 = instrucoesSimplificadas1.size();
		int maximo2 = instrucoesSimplificadas2.size();

		int contadorPrograma1 = 0;
		int contadorPrograma2 = 0;

		// pego os que serão testados.
		while (contadorPrograma1 < maximo1 && contadorPrograma2 < maximo2) {
			// verifica qual e o proximo rotulo valido para ser comparador
			InstrucaoRotuladaComposta instrucao1 = proximoRotuloSeraComparado(simplificadas1, verificadas1);
			InstrucaoRotuladaComposta instrucao2 = proximoRotuloSeraComparado(simplificadas2, verificadas2);

			// se as duas sao nulas, acabou as intrucoes para testar!
			if (instrucao1 == null && instrucao2 == null) {
				break;
			}

			// se uma e null e a outra nao, chega aqui e significa que programas nao sao equivalentes!
			if (instrucao1 == null || instrucao2 == null) {
				if (instrucao1 != null) {
					resultado.setMensagem("Não há instrução para testar com " + instrucao1.toStringComRotulo());
				} else {
					resultado.setMensagem("Não há instrução para testar com " + instrucao2.toStringComRotulo());
				}

				resultado.setSaoEquivalentes(false);
				break;
			}

			// testa se rotulos são equivalentes
			if (instrucao1.getTipo1() == instrucao2.getTipo1() && instrucao1.getTipo2() == instrucao2.getTipo2()) {
				// adiciona os pares de rotulos
				paresDeRotulo.add(new ParesDeRotulos(paresDeRotulo.size(), instrucao1, instrucao2));
			} else {
				// se os tipos nao sao iguais, nao sao equivalentes
				
				resultado.setMensagem("Os programas não são equivalentes em:\n" + instrucao1.toStringComRotulo() + "\n" + instrucao2.toStringComRotulo());
				resultado.setSaoEquivalentes(false);
				break;
			}

			contadorPrograma1++;
			contadorPrograma2++;
		}

		// adiciona o rotulo vazio ao final se forem equivalentes
		if (resultado.isSaoEquivalentes()) {
			paresDeRotulo.add(new ParesDeRotulos(paresDeRotulo.size()));
			resultado.setMensagem("Os programas são fortemente equivalentes!");
		}

		return resultado;
	}

	// recebe a lista de rotulos atuais, e a lista de rotulos q ja foram comparados.. e define qual será o proximo a ser comparado
	public static InstrucaoRotuladaComposta proximoRotuloSeraComparado(List<InstrucaoRotuladaComposta> simplificadas, List<InstrucaoRotuladaComposta> verificadas) {

		int contador = 0;
		for (InstrucaoRotuladaComposta instrucao : simplificadas) {
			// se a instrucao for diferente de null, é a proxima a ser testada.
			// nao testa ciclos com rotulo SIGMA
			if (instrucao != null && !instrucao.getRotulo().equals(TipoInstrucao.CICLO.getRotulo(null))) {

				boolean jaFoiVerificada = false;
				// testa se a instrucao ja foi verificada
				for (InstrucaoRotuladaComposta instrucaoVerificada : verificadas) {
					// se elas forem iguais, sem contar rotulo da lista.. entao ja foi verificada.
					if (instrucao.toString().equals(instrucaoVerificada.toString())) {
						jaFoiVerificada = true;
					}
				}

				// coloca na lista de ja testadas
				verificadas.add(instrucao);
				// tiro da lista de instrucoes que faltam ser testadas
				simplificadas.set(contador, null);

				if (!jaFoiVerificada) {
					return instrucao;
				}

			}

			contador++;
		}

		return null;

	}

}
