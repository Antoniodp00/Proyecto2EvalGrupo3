package model;


import view.VistaConsola;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representa un usuario voluntario que puede acumular y gastar puntos en el sistema.
 * Hereda de la clase Usuario.
 */
@XmlRootElement(name = "usuarioVoluntario")
public class UsuarioVoluntario extends Usuario {

    private static final String ARCHIVO_XML = "voluntarios.xml"; // Nombre del archivo XML

    private int puntos;

    /**
     * Constructor con parámetros para inicializar un usuario voluntario.
     *
     * @param nombre   Nombre del usuario.
     * @param usuario  Nombre de usuario.
     * @param email    Correo electrónico.
     * @param password Contraseña.
     */
    public UsuarioVoluntario(String nombre, String usuario, String email, String password) {
        super(nombre, usuario, email, password);
    }

    /**
     * Constructor vacío requerido por JAXB para la serialización.
     */
    public UsuarioVoluntario() {
        // JAXB requiere un constructor vacío
    }

    @XmlElement(name = "puntos")
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * Suma una cantidad de puntos al usuario.
     *
     * @param cantidad Cantidad de puntos a sumar.
     */
    public void sumarPuntos(int cantidad) {
        if (cantidad >0) {
            this.puntos += cantidad;
            setPuntos(this.puntos);
            VistaConsola.mostrarMensaje("✅ Se han añadido " + cantidad + " puntos. Total: " + this.puntos);
        } else {
            VistaConsola.mostrarMensaje("⚠ La cantidad de puntos a sumar debe ser mayor que 0.");
        }
    }

    /**
     * Resta puntos al usuario, asegurando que no sean negativos.
     *
     * @param cantidad Cantidad de puntos a restar.
     */
    public void restarPuntos(int cantidad) {
        if (cantidad > 0 && this.puntos >= cantidad) {
            this.puntos -= cantidad;
            VistaConsola.mostrarMensaje("❌ Se han restado " + cantidad + " puntos. Total: " + this.puntos);
        } else {
            VistaConsola.mostrarMensaje("⚠ No tienes suficientes puntos para restar o la cantidad ingresada no es válida.");
        }
    }
}

