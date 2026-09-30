package appointment.service;

import java.util.ArrayList;

import appointment.model.Doctor;
import appointment.exception.InvalidLoginException;
import appointment.model.Availability;
import appointment.service.FileService;
public class DoctorService {

    ArrayList<Doctor> doctors = new ArrayList<>();
    FileService fileService = new FileService();
    public void viewDoctors() {

        if (doctors.isEmpty()) {
            System.out.println("No doctors registered");
            return;
        }

        System.out.println();
        System.out.println("===== Available Doctors =====");

        for (Doctor doctor : doctors) {

            System.out.println("ID: " + doctor.getId());
            System.out.println("Name: " + doctor.getName());
            System.out.println("Specialization: " + doctor.getSpecialization());
            System.out.println("-----------------------------");
        }
    }

    public void registerDoctor(
            String name,
            String email,
            String password,
            String specialization) {

        for (Doctor doctor : doctors) {

            if (doctor.getEmail().equals(email)) {
                System.out.println("Email already registered");
                return;
            }
        }

        Doctor doctor = new Doctor(
                doctors.size() + 1,
                name,
                email,
                password,
                specialization
        );

        doctors.add(doctor);

        fileService.saveDoctor(doctor);

        System.out.println("Doctor registration successful");
    }

    public Doctor login(String email, String password)
            throws InvalidLoginException {

        for (Doctor doctor : doctors) {

            if (doctor.getEmail().equals(email) &&
                doctor.getPassword().equals(password)) {

                return doctor;
            }
        }

        throw new InvalidLoginException(
                "Invalid email or password"
        );
    }
    public void searchBySpecialization(String specialization) {

        boolean found = false;

        for (Doctor doctor : doctors) {

            if (doctor.getSpecialization().equalsIgnoreCase(specialization)) {

                System.out.println();
                System.out.println("ID: " + doctor.getId());
                System.out.println("Name: " + doctor.getName());
                System.out.println("Specialization: " + doctor.getSpecialization());
                System.out.println("-----------------------------");

                found = true;
            }
        }

        if (!found) {
            System.out.println("No doctors found");
        }
    }
    public void searchByName(String name) {

        boolean found = false;

        for (Doctor doctor : doctors) {

            if (doctor.getName().equalsIgnoreCase(name)) {

                System.out.println();
                System.out.println("ID: " + doctor.getId());
                System.out.println("Name: " + doctor.getName());
                System.out.println("Specialization: " + doctor.getSpecialization());
                System.out.println("-----------------------------");

                found = true;
            }
        }

        if (!found) {
            System.out.println("No doctors found");
        }
    }
    public void viewDoctorAvailability(int doctorId) {

        for (Doctor doctor : doctors) {

            if (doctor.getId() == doctorId) {

                System.out.println();
                System.out.println("===== Doctor Availability =====");

                if (doctor.getAvailabilities().isEmpty()) {

                    System.out.println("No availability found");

                    return;
                }

                for (Availability availability : doctor.getAvailabilities()) {

                    System.out.println(
                            "ID: " + availability.getAvailabilityId()
                            + " | Date: " + availability.getDate()
                            + " | Time: " + availability.getTime()
                    );
                }

                return;
            }
        }

        System.out.println("Doctor not found");
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    }