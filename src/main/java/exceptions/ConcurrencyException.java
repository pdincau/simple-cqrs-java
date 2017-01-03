package exceptions;

public class ConcurrencyException extends RuntimeException {

    public ConcurrencyException() {
        super("This aggregate has changed");
    }
}
