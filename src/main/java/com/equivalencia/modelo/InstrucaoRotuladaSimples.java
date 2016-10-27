package com.equivalencia.modelo;

import com.equivalencia.modelo.tipo.TipoInstrucao;

public class InstrucaoRotuladaSimples {

	private TipoInstrucao tipo;
	private String nomeInstrucao;

	private int destinoOperacao;

	private int destinoTesteVerdadeiro;
	private int destinoTesteFalso;

	public TipoInstrucao getTipo() {
		return tipo;
	}

	public void setTipo(TipoInstrucao tipo) {
		this.tipo = tipo;
	}

	public String getNomeInstrucao() {
		return nomeInstrucao;
	}

	public void setNomeInstrucao(String nomeInstrucao) {
		this.nomeInstrucao = nomeInstrucao;
	}

	public int getDestinoOperacao() {
		return destinoOperacao;
	}

	public void setDestinoOperacao(int destinoOperacao) {
		this.destinoOperacao = destinoOperacao;
	}

	public int getDestinoTesteVerdadeiro() {
		return destinoTesteVerdadeiro;
	}

	public void setDestinoTesteVerdadeiro(int destinoTesteVerdadeiro) {
		this.destinoTesteVerdadeiro = destinoTesteVerdadeiro;
	}

	public int getDestinoTesteFalso() {
		return destinoTesteFalso;
	}

	public void setDestinoTesteFalso(int destinoTesteFalso) {
		this.destinoTesteFalso = destinoTesteFalso;
	}

	// TODO
	@Override
	public String toString() {
		return "";
	}

	public static InstrucaoRotuladaSimples criaInstrucaoAtravesEntradaUsuario(String entrada) throws Exception {
		String entradaOriginal = entrada;
		InstrucaoRotuladaSimples instrucao = new InstrucaoRotuladaSimples();
		try {
			entrada = entrada.replaceAll("  ", " ").toUpperCase().trim();
			String partes[] = entrada.split(" ");

			instrucao.setNomeInstrucao(partes[1]);

			if (partes[0].equals("SE")) {
				instrucao.setTipo(TipoInstrucao.TESTE);
				instrucao.setDestinoTesteVerdadeiro(new Integer(partes[3]));
				instrucao.setDestinoTesteFalso(new Integer(partes[5]));
				if (entrada.contains("VA-PARA") && entrada.contains("SENAO-VA-PARA")) {
					return instrucao;
				}
			} else if (partes[0].equals("FACA")) {
				instrucao.setTipo(TipoInstrucao.OPERACAO);
				instrucao.setDestinoOperacao(new Integer(partes[3]));
				if (entrada.contains("VA-PARA") && !entrada.contains("SENAO-VA-PARA")) {
					return instrucao;
				}
			}
			// se chegar aqui, nao reconheceu instrucao
			throw new Exception();
		} catch (Exception e) {
			// erro generico, qualquer erro ocorrodio da esta mensagem/exception
			throw new Exception("Instrução em formato incompatível, por favor verificar entrada: [" + entradaOriginal + "]");
		}

	}

}
