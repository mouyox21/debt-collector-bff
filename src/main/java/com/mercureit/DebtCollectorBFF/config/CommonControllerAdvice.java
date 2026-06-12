package com.mercureit.DebtCollectorBFF.config;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import com.mercureit.DebtCollectorBFF.exception.APIErrorException;
import com.mercureit.DebtCollectorBFF.exception.ApiError;
import com.mercureit.DebtCollectorBFF.exception.ApiKeyException;
import com.mercureit.DebtCollectorBFF.exception.ErrorCode;
import com.mercureit.DebtCollectorBFF.exception.NotFoundIdException;

@ControllerAdvice
public class CommonControllerAdvice {

	@Autowired
	private com.mercureit.DebtCollectorBFF.exception.ExceptionHandler exceptionHandler;

	@ExceptionHandler({ NotFoundIdException.class })
	public ResponseEntity<Object> handle(NotFoundIdException e) {
		exceptionHandler.handle(e);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getApiError());

	}

	@ExceptionHandler({ ApiKeyException.class })
	public ResponseEntity<Object> handle(ApiKeyException e) {
		exceptionHandler.handle(e);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getApiError());

	}

	@ExceptionHandler({ APIErrorException.class })
	public ResponseEntity<Object> handle(APIErrorException e) {
		exceptionHandler.handle(e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getApiError());

	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handle(MethodArgumentNotValidException e) {

		exceptionHandler.handle(e);

		StringBuilder str = new StringBuilder();

		str.append("Arguments non valides :");

		for (final FieldError error : e.getBindingResult().getFieldErrors()) {
			str.append(" [" + error.getField() + ": " + error.getDefaultMessage() + "]");
		}
		for (final ObjectError error : e.getBindingResult().getGlobalErrors()) {
			str.append(" [" + error.getObjectName() + ": " + error.getDefaultMessage() + "]");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(ErrorCode.A500, str.toString()));

	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handle(Exception e) {
		if (e instanceof HttpClientErrorException && ((HttpClientErrorException) e).getRawStatusCode() == 400) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiError(ErrorCode.A500, e.getMessage().split("\"")[7]));
		}
		String uuid = UUID.randomUUID().toString();
		exceptionHandler.handle(e, uuid);
		ErrorCode code = ErrorCode.A500;
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(code, code.toString(), uuid));

	}

	/*@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handle(AccessDeniedException e) {
		ErrorCode code = ErrorCode.Z001;
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiError(code, code.toString()));

	}*/

}
