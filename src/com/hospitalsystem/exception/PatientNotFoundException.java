package com.hospitalsystem.exception;

/**
 * Member 1 - Custom Exception for Patient lookup failure.
 */
public class PatientNotFoundException extends Exception {
    public PatientNotFoundException(String message) {
        super(message);
    }
}
