package com.hospitalsystem;

import com.hospitalsystem.exception.DoctorNotFoundException;
import com.hospitalsystem.exception.PatientNotFoundException;
import com.hospitalsystem.model.Appointment;
import com.hospitalsystem.model.Doctor;
import com.hospitalsystem.model.Patient;
import com.hospitalsystem.service.HospitalService;

import java.util.List;
import java.util.Scanner;

/**
 * Member 1 - Hospital Management Console
 * Handles patient management workflow and system interaction.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final HospitalService hospitalService = new HospitalService();

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("     HOSPITAL MANAGEMENT SYSTEM - MEMBER 1        ");
        System.out.println("==================================================");

        boolean isRunning = true;
        while (isRunning) {
            displayMenu();
            int userChoice = readInteger("Enter choice (1-8): ");

            switch (userChoice) {
                case 1:
                    handlePatientRegistration();
                    break;
                case 2:
                    handleListPatients();
                    break;
                case 3:
                    handleAddDoctor();
                    break;
                case 4:
                    handleViewDoctors();
                    break;
                case 5:
                    handleBookAppointment();
                    break;
                case 6:
                    handleViewAppointments();
                    break;
                case 7:
                    handlePayBill();
                    break;
                case 8:
                    isRunning = false;
                    System.out.println("Exiting system. Have a good day!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 8.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n----------- MAIN MENU -----------");
        System.out.println("1. Register New Patient");
        System.out.println("2. View Registered Patients");
        System.out.println("3. Add Doctor");
        System.out.println("4. View Doctors (Sorted by Fee)");
        System.out.println("5. Book Appointment (Regular / Emergency)");
        System.out.println("6. View Appointment Schedule");
        System.out.println("7. Settle Patient Bill");
        System.out.println("8. Exit");
        System.out.println("---------------------------------");
    }

    private static void handlePatientRegistration() {
        System.out.println("\n--- Patient Registration ---");
        String id = readString("Enter Patient ID (e.g. P101): ");
        String name = readString("Enter Patient Name: ");
        int age = readInteger("Enter Patient Age: ");
        String disease = readString("Enter Disease / Symptoms: ");

        try {
            hospitalService.registerPatient(id, name, age, disease);
            System.out.println("Success: Patient " + name + " registered with ID " + id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleListPatients() {
        System.out.println("\n--- Registered Patient Records ---");
        List<Patient> patients = hospitalService.getPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients on record.");
            return;
        }
        for (Patient p : patients) {
            System.out.println(p.getDetails());
        }
    }

    private static void handleAddDoctor() {
        System.out.println("\n--- Add Doctor ---");
        String id = readString("Enter Doctor ID: ");
        String name = readString("Enter Doctor Name: ");
        int age = readInteger("Enter Age: ");
        String spec = readString("Enter Specialization: ");
        double fee = readDecimal("Enter Consultation Fee (Rs): ");
        String days = readString("Enter Available Days: ");

        try {
            hospitalService.addDoctor(id, name, age, spec, fee, days);
            System.out.println("Doctor " + name + " added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleViewDoctors() {
        System.out.println("\n--- Doctor Directory (Selection Sort by Fee) ---");
        List<Doctor> doctors = hospitalService.getDoctorsSortedByFee();
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }
        for (Doctor d : doctors) {
            System.out.println(d.getDetails());
        }
    }

    private static void handleBookAppointment() {
        System.out.println("\n--- Book Appointment ---");
        String patientId = readString("Enter Patient ID: ");
        String doctorId = readString("Enter Doctor ID: ");
        String date = readString("Enter Date (YYYY-MM-DD): ");
        String emergencyInput = readString("Is this an Emergency case? (yes/no): ");
        boolean isEmergency = emergencyInput.equalsIgnoreCase("yes") || emergencyInput.equalsIgnoreCase("y");

        try {
            Appointment appt = hospitalService.bookAppointment(patientId, doctorId, date, isEmergency);
            if (isEmergency) {
                System.out.println("PRIORITY ALERT: Emergency appointment booked at top of queue!");
            }
            System.out.println("Confirmed: " + appt.getId() + " on " + appt.getDate());
        } catch (PatientNotFoundException | DoctorNotFoundException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }

    private static void handleViewAppointments() {
        System.out.println("\n--- Appointment Queue ---");
        List<Appointment> list = hospitalService.getAppointments();
        if (list.isEmpty()) {
            System.out.println("No scheduled appointments.");
            return;
        }
        for (Appointment a : list) {
            System.out.println(a.getDetails());
        }
    }

    private static void handlePayBill() {
        System.out.println("\n--- Billing Desk ---");
        handleViewAppointments();
        String apptId = readString("Enter Appointment ID to pay: ");

        try {
            hospitalService.payBill(apptId);
            System.out.println("Payment completed for " + apptId);
            System.out.println("Total Hospital Revenue: Rs. " + hospitalService.getTotalRevenue());
        } catch (IllegalArgumentException e) {
            System.out.println("Payment Failed: " + e.getMessage());
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDecimal(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid numeric value.");
            }
        }
    }
}
