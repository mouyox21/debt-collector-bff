package com.mercureit.DebtCollectorBFF.exception;

import java.io.Serializable;

public interface ExceptionHandler extends Serializable {

	/**
	 * 
	 * Methode handle : Handle the exception.
	 *
	 * @param th a throwable to handle.
	 */
	void handle(Throwable th);

	void handle(Throwable th, String uuid);

}
