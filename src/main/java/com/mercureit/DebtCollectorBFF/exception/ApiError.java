package com.mercureit.DebtCollectorBFF.exception;

import java.io.Serializable;

public class ApiError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4130929122515363388L;

	private ErrorCode code;
	private String description;
	private String uuid;

	public ApiError(ErrorCode code, String description, String uuid) {
		this.code = code;
		this.description = description;
		this.uuid = uuid;
	}

	public ApiError(ErrorCode code, String description) {
		this.code = code;
		this.description = description;
	}

	public ErrorCode getCode() {
		return code;
	}

	public void setCode(ErrorCode code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public static void manageExceptions(String errors) throws APIErrorException {

		if (errors.contains("E200"))
			throw new APIErrorException(ErrorCode.A001);

	}
}
