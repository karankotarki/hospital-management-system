package com.hospitalsystem.model;

public class Appointment {
    private final String id;
    private final Patient patient;
    private final Doctor doctor;
    private final String date;
    private final boolean isEmergency;
    private boolean paid;

    public Appointment(String id, Patient patient, Doctor doctor, String date, boolean isEmergency) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.isEmergency = isEmergency;
        this.paid = false;
    }

    public String getId() { return id; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public String getDate() { return date; }
    public boolean isEmergency() { return isEmergency; }
    public double getAmount() { return doctor.getFee(); }
    public boolean isPaid() { return paid; }
    public void markAsPaid() { this.paid = true; }

    public String getDetails() {
        String tag = isEmergency ? "[EMERGENCY]" : "[Regular]";
        return "Appt ID: " + id + " | " + tag + " | Patient: " + patient.getName() + " | Doctor: Dr. " + doctor.getName()
                + " (" + doctor.getSpecialization() + ") | Date: " + date + " | Fee: Rs." + getAmount() + " | Status: " + (paid ? "PAID" : "UNPAID");
    }
}
