package exceptions;

public class UsuarioNoExisteException extends RuntimeException {
    public UsuarioNoExisteException(String message) {
        super(message);
    }
}
