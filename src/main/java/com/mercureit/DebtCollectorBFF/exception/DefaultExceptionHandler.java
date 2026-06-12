package com.mercureit.DebtCollectorBFF.exception;

/**
 * Préparer le message à logger et tracer l'exception.
 *
 *
 */

abstract class DefaultExceptionHandler implements ExceptionHandler {
	/**
	 *
	 */
	private static final long serialVersionUID = 2270868033669621899L;

	private static final String USER = "UNKNOWN-USER";

	@Override
	public void handle(Throwable th) {
		this.handle(th, null);
	}

	@Override
	public void handle(Throwable th, String uuid) {
		String message = th.getMessage();
		String user = getUser();

		log(message, user, th, uuid);
	}

	/**
	 * Get authenticated user.
	 * 
	 * @return
	 */
	private String getUser() {

		// For the moment return a common one.
		return USER;
	}

	protected abstract void log(String message, String user, Throwable th, String uuid);

}
