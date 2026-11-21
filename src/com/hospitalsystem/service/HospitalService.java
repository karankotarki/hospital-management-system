package com.hospitalsystem.service;

import com.hospitalsystem.exception.DoctorNotFoundException;
import com.hospitalsystem.exception.PatientNotFoundException;
import com.hospitalsystem.model.Appointment;
import com.hospitalsystem.model.Doctor;
import com.hospitalsystem.model.Patient;
import com.hospitalsystem.util.DoctorSorter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Member 1 - Hospital Service
 * Core business operations with focus on patient registration and records.
 */
public class HospitalService {

    // Patient registry using HashMap for O(1) lookup
    private final Map<String, Patient> patientMap = new HashMap<>();
    private final Map<String, Doctor> doctorMap = new HashMap<>();
    private final List<Appointment> appointmentList = new ArrayList<>();

    private int appointmentCounter = 101;

    public HospitalService() {
        // Pre-populate sample records for testing
        doctorMap.put("D1", new Doctor("D1", "Rajesh Sharma", 45, "Cardiologist", 800.0, "Mon, Wed, Fri"));
        doctorMap.put("D2", new Doctor("D2", "Priya Patel", 38, "General Physician", 400.0, "Tue, Thu, Sat"));
        doctorMap.put("D3", new Doctor("D3", "Amit Verma", 50, "Orthopedic", 600.0, "Mon, Tue, Thu"));

        patientMap.put("P1", new Patient("P1", "Rohan Gupta", 30, "Fever & Cough"));
        patientMap.put("P2", new Patient("P2", "Sneha Rao", 25, "Joint Pain"));
    }

    // Patient registration with input validation
    public void registerPatient(String id, String name, int age, String disease) {
        if (patientMap.containsKey(id)) {
            throw new IllegalArgumentException("Patient ID " + id + " already exists in records.");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be positive.");
        }
        Patient p = new Patient(id, name, age, disease);
        patientMap.put(id, p);
    }

    public void addDoctor(String id, String name, int age, String specialization, double fee, String availableDays) {
        if (doctorMap.containsKey(id)) {
            throw new IllegalArgumentException("Doctor ID " + id + " already exists.");
        }
        if (fee < 0) {
            throw new IllegalArgumentException("Consultation fee cannot be negative.");
        }
        doctorMap.put(id, new Doctor(id, name, age, specialization, fee, availableDays));
    }

    public Appointment bookAppointment(String patientId, String doctorId, String date, boolean isEmergency)
            throws PatientNotFoundException, DoctorNotFoundException {

        Patient patient = patientMap.get(patientId);
        if (patient == null) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found.");
        }

        Doctor doctor = doctorMap.get(doctorId);
        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor with ID " + doctorId + " not found.");
        }

        String apptId = "APT" + (appointmentCounter++);
        Appointment appt = new Appointment(apptId, patient, doctor, date, isEmergency);

        // Emergency triage: emergency cases placed at the head of the queue
        if (isEmergency) {
            appointmentList.add(0, appt);
        } else {
            appointmentList.add(appt);
        }

        return appt;
    }

    public void payBill(String appointmentId) {
        for (Appointment a : appointmentList) {
            if (a.getId().equalsIgnoreCase(appointmentId)) {
                if (a.isPaid()) {
                    throw new IllegalArgumentException("Appointment " + appointmentId + " is already paid.");
                }
                a.markAsPaid();
                return;
            }
        }
        throw new IllegalArgumentException("Appointment ID " + appointmentId + " not found.");
    }

    public List<Patient> getPatients() {
        return new ArrayList<>(patientMap.values());
    }

    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctorMap.values());
    }

    public List<Doctor> getDoctorsSortedByFee() {
        List<Doctor> sorted = new ArrayList<>(doctorMap.values());
        DoctorSorter.sortByFeeAscending(sorted);
        return sorted;
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointmentList);
    }

    public double getTotalRevenue() {
        double total = 0.0;
        for (Appointment a : appointmentList) {
            if (a.isPaid()) {
                total += a.getAmount();
            }
        }
        return total;
    }
}
