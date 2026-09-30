package appointment.exception;

public class InvalidAppointmentStatusException extends Exception {

    public InvalidAppointmentStatusException(String message) {
        super(message);
    }
}