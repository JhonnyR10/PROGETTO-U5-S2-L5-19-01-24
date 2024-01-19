package giovannilongo.PROGETTOU5S2L5190124.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(long id) {
        super(id + " non trovato!");
    }
}
