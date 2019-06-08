package SOA;

public class SessionExistsException extends Exception {
    public SessionExistsException() {
        super("Session exists");
    }
}
