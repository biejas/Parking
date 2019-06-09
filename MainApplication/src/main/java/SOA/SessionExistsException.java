package SOA;

public class SessionExistsException extends Exception {
    public SessionExistsException() {
        super("Sesja już istnieje");
    }
}
