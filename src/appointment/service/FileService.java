package appointment.service;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import appointment.model.Patient;
import appointment.model.Doctor;
import appointment.model.Appointment;
import appointment.model.Availability;
public class FileService {
	public void createFile() {

	    File file = new File("patients.txt");

	    try {

	        if (file.exists()) {

	            System.out.println("File already exists");

	        } else {

	            file.createNewFile();

	            System.out.println("File created successfully");
	        }

	    } catch (Exception e) {

	        System.out.println("Error creating file");
	    }
	}
	public void writeData() {

	    try {

	        FileWriter writer = new FileWriter("patients.txt", true);

	       

	       

	        System.out.println("Data appended successfully");

	    } catch (Exception e) {

	        System.out.println("Error writing data");
	    }
	}
	public void readData() {

	    try {

	        FileReader reader = new FileReader("patients.txt");

	        BufferedReader br = new BufferedReader(reader);

	        String line;

	        while ((line = br.readLine()) != null) {

	            System.out.println(line);
	        }

	        br.close();

	    } catch (Exception e) {

	        System.out.println("Error reading file");
	    }
	}
	public void savePatient(Patient patient) {

	    try {

	        FileWriter writer = new FileWriter("patients.txt", true);

	        writer.write("Patient ID: " + patient.getId() + "\n");
	        writer.write("Name: " + patient.getName() + "\n");
	        writer.write("Email: " + patient.getEmail() + "\n");
	        writer.write("-----------------------------\n");

	        writer.close();

	        System.out.println("Patient data saved to file");

	    } catch (Exception e) {

	        System.out.println("Error saving patient data");
	    }
	}
	public void saveDoctor(Doctor doctor) {

	    try {

	        FileWriter writer = new FileWriter("doctors.txt", true);

	        writer.write("Doctor ID: " + doctor.getId() + "\n");
	        writer.write("Name: " + doctor.getName() + "\n");
	        writer.write("Email: " + doctor.getEmail() + "\n");
	        writer.write("Specialization: " + doctor.getSpecialization() + "\n");
	        writer.write("-----------------------------\n");

	        writer.close();

	        System.out.println("Doctor data saved to file");

	    } catch (Exception e) {

	        System.out.println("Error saving doctor data");
	    }
	}
	public void saveAppointment(Appointment appointment) {

	    try {

	        FileWriter writer =
	                new FileWriter("appointments.txt", true);

	        writer.write(appointmentToText(appointment));

	        writer.close();

	        System.out.println("Appointment data saved to file");

	    } catch (Exception e) {

	        System.out.println("Error saving appointment data");
	    }
	}
	public void readAppointments() {

	    try {

	        BufferedReader br =
	                new BufferedReader(
	                        new FileReader("appointments.txt")
	                );

	        String line;

	        while ((line = br.readLine()) != null) {

	            System.out.println(line);
	        }

	        br.close();

	    } catch (Exception e) {

	        System.out.println("Error reading appointments");
	    }
	}
	public void writeToFile(String fileName, String data) {

	    try {

	        FileWriter writer = new FileWriter(fileName);

	        writer.write(data);

	        writer.close();

	        System.out.println("File updated successfully");

	    } catch (Exception e) {

	        System.out.println("Error updating file");
	    }
	}
	public String appointmentToText(Appointment appointment) {

	    String data = "";

	    data += "Appointment ID: "
	            + appointment.getAppointmentId() + "\n";

	    data += "Patient: "
	            + appointment.getPatient().getName() + "\n";

	    data += "Doctor: "
	            + appointment.getDoctor().getName() + "\n";

	    data += "Date: "
	            + appointment.getDate() + "\n";

	    data += "Time: "
	            + appointment.getTime() + "\n";

	    data += "Status: "
	            + appointment.getStatus() + "\n";

	    data += "-----------------------------\n";

	    return data;
	}
	public String allAppointmentsToText(
	        ArrayList<Appointment> appointments) {

	    String data = "";

	    for (Appointment appointment : appointments) {

	        data += appointmentToText(appointment);
	    }

	    return data;
	}
	public void saveAvailability(
	        Doctor doctor,
	        Availability availability) {

	    try {

	        FileWriter writer =
	                new FileWriter("availability.txt", true);

	        writer.write("Doctor ID: " + doctor.getId() + "\n");
	        writer.write("Doctor: " + doctor.getName() + "\n");
	        writer.write("Availability ID: "
	                + availability.getAvailabilityId() + "\n");
	        writer.write("Date: " + availability.getDate() + "\n");
	        writer.write("Time: " + availability.getTime() + "\n");
	        writer.write("-----------------------------\n");

	        writer.close();

	        System.out.println("Availability data saved to file");

	    } catch (Exception e) {

	        System.out.println("Error saving availability data");
	    }
	}
}