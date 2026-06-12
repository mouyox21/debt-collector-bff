package com.mercureit.DebtCollectorBFF.SystemReclamations.exceptions;

public class SystemException extends RuntimeException {
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
