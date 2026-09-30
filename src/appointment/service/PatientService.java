package appointment.service;

import java.util.ArrayList;

import appointment.model.Patient;
import appointment.exception.InvalidLoginException;
import appointment.service.FileService;
public class PatientService {

    ArrayList<Patient> patients = new ArrayList<>();
    FileService fileService = new FileService();
    public void registerPatient(String name, String email, String password) {

        for (Patient patient : patients) {

            if (patient.getEmail().equals(email)) {
                System.out.println("Email already registered");
                return;
            }
        }

        Patient patient = new Patient(
                patients.size() + 1,
                name,
                email,
                password
        );

        patients.add(patient);
        fileService.savePatient(patient);

        System.out.println("Registration successful");
    }
    public Patient login(String email, String password)
            throws InvalidLoginException {

        for (Patient patient : patients) {

            if (patient.getEmail().equals(email) &&
                patient.getPassword().equals(password)) {

                return patient;
            }
        }

        throw new InvalidLoginException(
                "Invalid email or password"
        );
    }
    public void viewPatients() {

        if (patients.isEmpty()) {
            System.out.println("No patients registered");
            return;
        }

        System.out.println();
        System.out.println("===== Registered Patients =====");

        for (Patient patient : patients) {

            System.out.println("ID: " + patient.getId());
            System.out.println("Name: " + patient.getName());
            System.out.println("Email: " + patient.getEmail());
            System.out.println("-----------------------------");
        }
    }
}