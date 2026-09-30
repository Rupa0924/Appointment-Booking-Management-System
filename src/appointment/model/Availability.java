package appointment.model;

public class Availability {

    private int availabilityId;
    private String date;
    private String time;

    public Availability(int availabilityId, String date, String time) {
        this.availabilityId = availabilityId;
        this.date = date;
        this.time = time;
    }

    public int getAvailabilityId() {
        return availabilityId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}