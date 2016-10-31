package com.equivalencia.modelo.tipo;

import com.equivalencia.modelo.InstrucaoMonolitica;

public enum TipoInstrucao {
	TESTE {
		@Override
		public String getIdentificador(InstrucaoMonolitica instrucao) {
			return null;
		}

		@Override
		public String getRotulo(InstrucaoMonolitica instrucao) {
			return "";
		}

	},
	OPERACAO {
		@Override
		public String getIdentificador(InstrucaoMonolitica instrucao) {
			return instrucao.getIdentificador();
		}

		@Override
		public String getRotulo(InstrucaoMonolitica instrucao) {
			return instrucao.getRotuloOperacao();
		}

	},
	PARADA {

		@Override
		public String getIdentificador(InstrucaoMonolitica instrucao) {
			return "PARADA";
		}

		// OMEGA
		@Override
		public String getRotulo(InstrucaoMonolitica instrucao) {
			return "Σ";
		}

	},
	PARTIDA {
		@Override
		public String getIdentificador(InstrucaoMonolitica instrucao) {
			return null;
		}

		@Override
		public String getRotulo(InstrucaoMonolitica instrucao) {
			return "";
		}

	},
	CICLO {

		@Override
		public String getIdentificador(InstrucaoMonolitica instrucao) {
			return "CICLO";
		}

		// SIGMA
		@Override
		public String getRotulo(InstrucaoMonolitica instrucao) {
			return "ω";
		}

	}

	;

	// para operacao identificador da instrucao monolitica.
	// parada/ciclo são diferentes
	// teste / partida nao possui
	public abstract String getIdentificador(InstrucaoMonolitica instrucao);

	// para operacao, index da funcao.. da contagem de funcoes!!
	// parada/ciclo são diferentes
	// teste/partida nao possui
	public abstract String getRotulo(InstrucaoMonolitica instrucao);
}
