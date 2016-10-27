package com.equivalencia.validador;

import com.equivalencia.modelo.InstrucaoRotuladaSimples;
import com.equivalencia.modelo.TipoInstrucao;

//USADA PARA CRIAR UMA INSTRUCAO ROTULADA SIMPLES A PARTIR DA ENTRADA DO USUARIO
public class InstrucaoRotuladaSimplesUtilitario {

	public static InstrucaoRotuladaSimples criaInstrucao(String entrada) {
		InstrucaoRotuladaSimples instrucao = new InstrucaoRotuladaSimples();
		instrucao.setInstrucaoValida(false);
		try {
			entrada = entrada.replaceAll("  ", " ").toUpperCase().trim();
			String partes[] = entrada.split(" ");

			instrucao.setNomeInstrucao(partes[1]);

			if (partes[0].equals("SE")) {
				instrucao.setTipo(TipoInstrucao.TESTE);
				instrucao.setDestinoTesteVerdadeiro(new Integer(partes[3]));
				instrucao.setDestinoTesteFalso(new Integer(partes[5]));
				if (entrada.contains("VA-PARA") && entrada.contains("SENAO-VA-PARA")) {
					instrucao.setInstrucaoValida(true);
				}
			} else if (partes[0].equals("FACA")) {
				instrucao.setTipo(TipoInstrucao.OPERACAO);
				instrucao.setDestinoOperacao(new Integer(partes[3]));
				if (entrada.contains("VA-PARA") && !entrada.contains("SENAO-VA-PARA")) {
					instrucao.setInstrucaoValida(true);
				}
			}

			return instrucao;
		} catch (Exception e) {
			return instrucao;
		}

	}

}
