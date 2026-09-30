package appointment.model;

import java.util.ArrayList;

public class Doctor extends User {

    private String specialization;

    private ArrayList<Availability> availabilities = new ArrayList<>();

    public Doctor(int id, String name, String email, String password, String specialization) {
        super(id, name, email, password);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public ArrayList<Availability> getAvailabilities() {
        return availabilities;
    }
}