package com.mercureit.DebtCollectorBFF.SystemReclamations.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
