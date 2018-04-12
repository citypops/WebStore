package citypops.webstore.exception;

public class FieldNotValidException extends RuntimeException {

    private String message;

    public FieldNotValidException(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
