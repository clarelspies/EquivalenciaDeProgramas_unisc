package com.equivalencia.modelo;

public class CadeiaConjuntoFinito {

	private String rotulo;
	private String conjunto;

	public CadeiaConjuntoFinito() {
		this.conjunto = "{Σ}";
		this.rotulo = "A0";
	}

	public CadeiaConjuntoFinito(int numeroUltimoRotulo, String ultimoConjunto, String novosConjuntos) {
		numeroUltimoRotulo++;
		this.rotulo = "A" + numeroUltimoRotulo;

		if (novosConjuntos != null) {
			this.conjunto = "{" + ultimoConjunto.replace("{", "").replaceAll("}", "") + "," + novosConjuntos + "}";
		} else {
			// usado para adicionar o ultimo novamente
			this.conjunto = ultimoConjunto;
		}

	}

	@Override
	public String toString() {
		return this.rotulo + ":" + this.conjunto;
	}

	public String getConjunto() {
		return conjunto;
	}

}
