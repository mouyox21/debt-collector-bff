package com.mercureit.DebtCollectorBFF.SystemReclamations.exceptions;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String message) {
        super(message);
    }
}
