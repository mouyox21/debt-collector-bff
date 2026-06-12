package com.mercureit.DebtCollectorBFF.exception;

public enum ErrorCode {

	A001(""), A500("Une erreur système s'est produite"), Z001("Vous n'avez pas le droit d'accèder à cette ressource");

	private String code = "";

	ErrorCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
