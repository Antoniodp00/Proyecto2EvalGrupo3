package exceptions;

/**
 * Excepción personalizada para indicar que un usuario no tiene suficientes puntos.
 *
 * Esta excepción se lanza cuando un usuario intenta realizar una acción que requiere
 * una cantidad de puntos mayor a la que posee.
 */
public class PuntosInsuficientesException extends RuntimeException {

    /**
     * Constructor de la excepción que permite especificar un mensaje de error.
     *
     * @param message Mensaje descriptivo del error.
     */
    public PuntosInsuficientesException(String message) {
        super(message);
    }
}
