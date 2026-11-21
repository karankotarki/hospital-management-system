package com.hospitalsystem.model;

public class Doctor extends Person {
    private final String specialization;
    private final double fee;
    private final String availableDays;

    public Doctor(String id, String name, int age, String specialization, double fee, String availableDays) {
        super(id, name, age);
        this.specialization = specialization;
        this.fee = fee;
        this.availableDays = availableDays;
    }

    public String getSpecialization() { return specialization; }
    public double getFee() { return fee; }
    public String getAvailableDays() { return availableDays; }

    @Override
    public String getRole() { return "Doctor"; }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Specialization: " + specialization + " | Fee: Rs." + fee + " | Days: " + availableDays;
    }
}
