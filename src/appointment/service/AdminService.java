package appointment.service;
import appointment.exception.InvalidLoginException;
import appointment.model.Admin;

public class AdminService {

    private Admin admin;

    public AdminService() {

        admin = new Admin(
                1,
                "Admin",
                "admin@gmail.com",
                "admin123"
        );
    }

    public Admin login(String email, String password)
            throws InvalidLoginException {

        if (admin.getEmail().equals(email) &&
            admin.getPassword().equals(password)) {

            return admin;
        }

        throw new InvalidLoginException(
                "Invalid admin email or password"
        );
    }
}