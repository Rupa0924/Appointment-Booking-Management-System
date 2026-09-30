package appointment.model;

import java.util.Scanner;

import appointment.service.PatientService;
import appointment.service.DoctorService;
import appointment.service.AdminService;
import appointment.service.AvailabilityService;
import appointment.service.AppointmentService;
import appointment.exception.AppointmentNotFoundException;
import appointment.exception.InvalidAppointmentStatusException;
import appointment.exception.InvalidLoginException;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PatientService service = new PatientService();
        DoctorService doctorService = new DoctorService();
        AdminService adminService = new AdminService();
        AvailabilityService availabilityService = new AvailabilityService();
        AppointmentService appointmentService = new AppointmentService();

        
       
        
        while (true) {

            System.out.println();
            System.out.println("===== Appointment Booking System =====");
            System.out.println("1. Patient Registration");
            System.out.println("2. Patient Login");
            System.out.println("3. Doctor Registration");
            System.out.println("4. Doctor Login");
            System.out.println("5. Admin Login");
            System.out.println("6. Exit");
            System.out.println("Enter your choice:");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            case 1:

                System.out.println("Enter your name:");
                String name = sc.nextLine();

                System.out.println("Enter your email:");
                String email = sc.nextLine();

                System.out.println("Enter your password:");
                String password = sc.nextLine();

                service.registerPatient(name, email, password);

                break;

            case 2:

                System.out.println("Enter your email:");
                String loginEmail = sc.nextLine();

                System.out.println("Enter your password:");
                String loginPassword = sc.nextLine();

                try {

                    Patient patient = service.login(
                            loginEmail,
                            loginPassword
                    );

                    System.out.println("Login successful");

                    patientMenu(
                            sc,
                            patient,
                            doctorService,
                            appointmentService
                    );

                } catch (InvalidLoginException e) {

                    System.out.println(e.getMessage());
                }

                break;
            case 3:

                System.out.println("Enter doctor's name:");
                String doctorName = sc.nextLine();

                System.out.println("Enter doctor's email:");
                String doctorEmail = sc.nextLine();

                System.out.println("Enter doctor's password:");
                String doctorPassword = sc.nextLine();

                System.out.println("Enter specialization:");
                String specialization = sc.nextLine();

                doctorService.registerDoctor(
                        doctorName,
                        doctorEmail,
                        doctorPassword,
                        specialization
                );

                break;

            case 4:

                System.out.println("Enter doctor's email:");
                String doctorLoginEmail = sc.nextLine();

                System.out.println("Enter doctor's password:");
                String doctorLoginPassword = sc.nextLine();

                try {

                    Doctor doctor = doctorService.login(
                            doctorLoginEmail,
                            doctorLoginPassword
                    );

                    System.out.println("Doctor login successful");

                    doctorMenu(
                            sc,
                            doctor,
                            availabilityService,
                            appointmentService
                    );

                } catch (InvalidLoginException e) {

                    System.out.println(e.getMessage());
                }
                break;

            case 5:

                System.out.println("Enter admin email:");
                String adminEmail = sc.nextLine();

                System.out.println("Enter admin password:");
                String adminPassword = sc.nextLine();

                try {

                    Admin admin = adminService.login(
                            adminEmail,
                            adminPassword
                    );

                    System.out.println("Admin login successful");

                    adminMenu(
                            sc,
                            admin,
                            service,
                            doctorService,
                            appointmentService
                    );

                } catch (InvalidLoginException e) {

                    System.out.println(e.getMessage());
                }
                break;

            case 6:

                System.out.println(
                        "Thank you for using Appointment Booking System"
                );

                sc.close();

                return;

            default:

                System.out.println("Invalid choice");
            }
        }
    }

    public static void patientMenu(
            Scanner sc,
            Patient patient,
            DoctorService doctorService,
            AppointmentService appointmentService) {

        while (true) {

            System.out.println();
            System.out.println("===== Patient Dashboard =====");
            System.out.println("1. View Profile");
            System.out.println("2. View Doctors");
            System.out.println("3. Search Doctor");
            System.out.println("4. View Doctor Availability");
            System.out.println("5. Book Appointment");
            System.out.println("6. My Appointments");
            System.out.println("7. Cancel Appointment");
            System.out.println("8. Logout");
            System.out.println("Enter your choice:");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            case 1:

                System.out.println();
                System.out.println("===== My Profile =====");
                System.out.println("ID: " + patient.getId());
                System.out.println("Name: " + patient.getName());
                System.out.println("Email: " + patient.getEmail());

                break;

            case 2:

                doctorService.viewDoctors();

                break;

          
            case 3:

                System.out.println("Enter doctor name:");
                String searchName = sc.nextLine().trim();

                doctorService.searchByName(searchName);

                break;
            case 4:

                System.out.println("Enter Doctor ID:");
                int availabilityDoctorId = sc.nextInt();
                sc.nextLine();

                doctorService.viewDoctorAvailability(
                        availabilityDoctorId
                );

                break;

            case 5:

                System.out.println("Enter Doctor ID:");
                int doctorId = sc.nextInt();
                sc.nextLine();

                System.out.println("Enter Date:");
                String date = sc.nextLine();

                System.out.println("Enter Time:");
                String time = sc.nextLine();

                Doctor selectedDoctor = null;

                for (Doctor doctor : doctorService.getDoctors()) {

                    if (doctor.getId() == doctorId) {

                        selectedDoctor = doctor;

                        break;
                    }
                }

                if (selectedDoctor == null) {

                    System.out.println("Doctor not found");

                    break;
                }

                appointmentService.bookAppointment(
                        patient,
                        selectedDoctor,
                        date,
                        time
                );

                break;

            case 6:

                appointmentService.viewMyAppointments(patient);

                break;

            case 7:

                System.out.println("Enter Appointment ID:");
                int appointmentId = sc.nextInt();
                sc.nextLine();

                try {

                    appointmentService.cancelAppointment(
                            appointmentId,
                            patient
                    );

                } catch (AppointmentNotFoundException e) {

                    System.out.println(e.getMessage());
                }

                break;
                

            case 8:

                System.out.println(
                        "Logged out successfully"
                );

               

                return;

            default:

                System.out.println("Invalid choice");
            }
        }
    }

    public static void doctorMenu(
            Scanner sc,
            Doctor doctor,
            AvailabilityService availabilityService,
            AppointmentService appointmentService) {

        while (true) {

            System.out.println();
            System.out.println("===== Doctor Dashboard =====");
            System.out.println("1. View Profile");
            System.out.println("2. Set Availability");
            System.out.println("3. View Availability");
            System.out.println("4. View Appointments");
            System.out.println("5. Update Appointment Status");
            System.out.println("6. Logout");
            System.out.println("Enter your choice:");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            case 1:

                System.out.println();
                System.out.println("===== Doctor Profile =====");
                System.out.println("ID: " + doctor.getId());
                System.out.println("Name: " + doctor.getName());
                System.out.println("Email: " + doctor.getEmail());
                System.out.println(
                        "Specialization: "
                        + doctor.getSpecialization()
                );

                break;

            case 2:

            	System.out.println("Enter Date:");
            	String date = sc.nextLine().trim();

            	System.out.println("Enter Time:");
            	String time = sc.nextLine().trim();

                availabilityService.addAvailability(
                        doctor,
                        date,
                        time
                );

                break;

            case 3:

                availabilityService.viewAvailability(doctor);

                break;

            case 4:

                appointmentService.viewDoctorAppointments(doctor);

                break;

              

            case 5:

                System.out.println("Enter Appointment ID:");
                int appointmentId = sc.nextInt();
                sc.nextLine();

                System.out.println("Enter status:");
                String status = sc.nextLine();

                try {

                    appointmentService.updateAppointmentStatus(
                            appointmentId,
                            doctor,
                            status
                    );

                } catch (InvalidAppointmentStatusException e) {

                    System.out.println(e.getMessage());
                }

                break;
            case 6:

                System.out.println(
                        "Logged out successfully"
                );

                return;
            default:

                System.out.println("Invalid choice");
            }
        }
    }

    public static void adminMenu(
            Scanner sc,
            Admin admin,
            PatientService service,
            DoctorService doctorService,
            AppointmentService appointmentService) {
        while (true) {

            System.out.println();
            System.out.println("===== Admin Dashboard =====");
            System.out.println("1. View Profile");
            System.out.println("2. View Patients");
            System.out.println("3. View Doctors");
            System.out.println("4. View Appointments");
            System.out.println("5. Logout");
            System.out.println("Enter your choice:");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            case 1:

                System.out.println();
                System.out.println("===== Admin Profile =====");
                System.out.println("ID: " + admin.getId());
                System.out.println("Name: " + admin.getName());
                System.out.println("Email: " + admin.getEmail());

                break;

            case 2:

            	service.viewPatients();

                break;

            case 3:

                doctorService.viewDoctors();

                break;

            case 4:

                appointmentService.viewAppointments();

                break;

           

            case 5:

                System.out.println(
                        "Logged out successfully"
                );

                return;

            default:

                System.out.println("Invalid choice");
            }
        }
    }
}