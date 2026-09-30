package appointment.service;

import java.util.ArrayList;

import appointment.model.Appointment;
import appointment.model.Availability;
import appointment.model.Patient;
import appointment.model.Doctor;
import appointment.exception.AppointmentNotFoundException;
import appointment.exception.InvalidAppointmentStatusException;
import appointment.service.FileService;
public class AppointmentService {

    ArrayList<Appointment> appointments = new ArrayList<>();
    FileService fileService = new FileService();
    public boolean isSlotAvailable(
            Doctor doctor,
            String date,
            String time) {

        for (Availability availability : doctor.getAvailabilities()) {

            if (availability.getDate().equals(date)
                    && availability.getTime().equals(time)) {

                return true;
            }
        }

        return false;
    }
    public void bookAppointment(
            Patient patient,
            Doctor doctor,
            String date,
            String time) {

        if (!isSlotAvailable(doctor, date, time)) {

            System.out.println(
                    "This time slot is not available"
            );

            return;
        }

        if (isAlreadyBooked(doctor, date, time)) {

            System.out.println(
                    "This time slot is already booked"
            );

            return;
        }

        int id = appointments.size() + 1;

        Appointment appointment = new Appointment(
                id,
                patient,
                doctor,
                date,
                time,
                "PENDING"
        );

        appointments.add(appointment);

        fileService.saveAppointment(appointment);

        System.out.println("Appointment booked successfully");
    }
    public void viewAppointments() {

        if (appointments.isEmpty()) {
            System.out.println("No appointments found");
            return;
        }

        System.out.println();
        System.out.println("===== Appointments =====");

        for (Appointment appointment : appointments) {

            System.out.println("Appointment ID: "
                    + appointment.getAppointmentId());

            System.out.println("Patient: "
                    + appointment.getPatient().getName());

            System.out.println("Doctor: "
                    + appointment.getDoctor().getName());

            System.out.println("Date: "
                    + appointment.getDate());

            System.out.println("Time: "
                    + appointment.getTime());

            System.out.println("Status: "
                    + appointment.getStatus());

            System.out.println("-----------------------------");
        }
    }
    public void viewMyAppointments(Patient patient) {

        boolean found = false;

        for (Appointment appointment : appointments) {

            if (appointment.getPatient().getId() == patient.getId()) {

                System.out.println();
                System.out.println("===== My Appointment =====");

                System.out.println(
                        "Appointment ID: "
                        + appointment.getAppointmentId()
                );

                System.out.println(
                        "Doctor: "
                        + appointment.getDoctor().getName()
                );

                System.out.println(
                        "Specialization: "
                        + appointment.getDoctor().getSpecialization()
                );

                System.out.println(
                        "Date: "
                        + appointment.getDate()
                );

                System.out.println(
                        "Time: "
                        + appointment.getTime()
                );

                System.out.println(
                        "Status: "
                        + appointment.getStatus()
                );

                System.out.println("-----------------------------");

                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointments found");
        }
    }
    public boolean isAlreadyBooked(
            Doctor doctor,
            String date,
            String time) {

        for (Appointment appointment : appointments) {

            if (appointment.getDoctor().getId() == doctor.getId()
                    && appointment.getDate().equals(date)
                    && appointment.getTime().equals(time)
                    && !appointment.getStatus().equals("CANCELLED")) {

                return true;
            }
        }

        return false;
    }
    public void viewDoctorAppointments(Doctor doctor) {

        boolean found = false;

        for (Appointment appointment : appointments) {

            if (appointment.getDoctor().getId() == doctor.getId()) {

                System.out.println();
                System.out.println("===== Doctor Appointments =====");

                System.out.println(
                        "Appointment ID: "
                        + appointment.getAppointmentId()
                );

                System.out.println(
                        "Patient: "
                        + appointment.getPatient().getName()
                );

                System.out.println(
                        "Date: "
                        + appointment.getDate()
                );

                System.out.println(
                        "Time: "
                        + appointment.getTime()
                );

                System.out.println(
                        "Status: "
                        + appointment.getStatus()
                );

                System.out.println("-----------------------------");

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No appointments found"
            );
        }
    }
    public void updateAppointmentStatus(
            int appointmentId,
            Doctor doctor,
            String status)
            throws InvalidAppointmentStatusException {
        if (!status.equals("PENDING")
                && !status.equals("ACCEPTED")
                && !status.equals("REJECTED")
                && !status.equals("COMPLETED"))
                {

        	throw new InvalidAppointmentStatusException(
        	        "Invalid appointment status"
        	);
        }

        for (Appointment appointment : appointments) {

            if (appointment.getAppointmentId() == appointmentId
                    && appointment.getDoctor().getId() == doctor.getId()) {

                if (status.equals("COMPLETED")
                        && !appointment.getStatus().equals("ACCEPTED")) {

                    System.out.println(
                            "Only accepted appointments can be completed"
                    );

                    return;
                }

                appointment.setStatus(status);

                String data =
                        fileService.allAppointmentsToText(appointments);

                fileService.writeToFile(
                        "appointments.txt",
                        data
                );

                System.out.println(
                        "Appointment status updated successfully"
                );

                return;
            }
        }

        System.out.println("Appointment not found");
    }
    public void cancelAppointment(
            int appointmentId,
            Patient patient) throws AppointmentNotFoundException { 

        for (Appointment appointment : appointments) {

            if (appointment.getAppointmentId() == appointmentId
                    && appointment.getPatient().getId() == patient.getId()) {

                if (appointment.getStatus().equals("CANCELLED")) {

                    System.out.println(
                            "Appointment is already cancelled"
                    );

                    return;
                }

                if (appointment.getStatus().equals("COMPLETED")) {

                    System.out.println(
                            "Completed appointment cannot be cancelled"
                    );

                    return;
                }

                appointment.setStatus("CANCELLED");

                System.out.println(
                        "Appointment cancelled successfully"
                );

                return;
            }
        }

        throw new AppointmentNotFoundException(
                "Appointment not found"
        );
    }
}