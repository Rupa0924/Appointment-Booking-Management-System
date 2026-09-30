package appointment.service;

import appointment.model.Availability;
import appointment.model.Doctor;

public class AvailabilityService {
	FileService fileService = new FileService();
	public void addAvailability(
	        Doctor doctor,
	        String date,
	        String time) {

	    for (Availability availability : doctor.getAvailabilities()) {

	        if (availability.getDate().equals(date) &&
	            availability.getTime().equals(time)) {

	            System.out.println("This time slot already exists");
	            return;
	        }
	    }

	    int id = doctor.getAvailabilities().size() + 1;

	    Availability availability = new Availability(
	            id,
	            date,
	            time
	    );

	    doctor.getAvailabilities().add(availability);

	    fileService.saveAvailability(
	            doctor,
	            availability
	    );

	    System.out.println("Availability added successfully");
	}
    public void viewAvailability(Doctor doctor) {

        if (doctor.getAvailabilities().isEmpty()) {
            System.out.println("No availability found");
            return;
        }

        System.out.println();
        System.out.println("===== Doctor Availability =====");

        for (Availability availability : doctor.getAvailabilities()) {

            System.out.println("ID: " + availability.getAvailabilityId());
            System.out.println("Date: " + availability.getDate());
            System.out.println("Time: " + availability.getTime());
            System.out.println("-----------------------------");
        }
    }
}