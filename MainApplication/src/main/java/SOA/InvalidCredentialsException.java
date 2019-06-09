package SOA;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        super("Zły login lub hasło");
    }
}
