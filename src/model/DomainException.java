package model;

public class DomainException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DomainException() {
        super();
    }

    public DomainException(String message, Throwable exception) {
        super(message, exception);
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(Throwable exception) {
        super(exception);
    }

}
