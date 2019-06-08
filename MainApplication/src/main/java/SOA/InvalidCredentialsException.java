package SOA;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        super("Wrong username or password");
    }
}
