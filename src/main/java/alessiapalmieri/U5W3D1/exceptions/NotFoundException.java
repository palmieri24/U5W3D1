package alessiapalmieri.U5W3D1.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(long id) {
        super(id + " not found!");
    }
}
