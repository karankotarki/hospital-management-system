package com.hospitalsystem.model;

/**
 * Member 1 - Patient Model.
 * Extends Person (INHERITANCE).
 */
public class Patient extends Person {

    private final String patientDisease;

    public Patient(String patientId, String patientName, int patientAge, String patientDisease) {
        super(patientId, patientName, patientAge);
        this.patientDisease = patientDisease;
    }

    public String getDisease() {
        return patientDisease;
    }

    @Override
    public String getRole() {
        return "Patient";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Disease: " + patientDisease;
    }
}
