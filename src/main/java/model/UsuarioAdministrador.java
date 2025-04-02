package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representa un usuario con rol de administrador.
 * Hereda de la clase Usuario.
 */
@XmlRootElement(name = "usuarioAdministrador")
public class UsuarioAdministrador extends Usuario {

    /**
     * Constructor con parámetros para inicializar un usuario administrador.
     *
     * @param nombre   Nombre del administrador.
     * @param usuario  Nombre de usuario.
     * @param email    Correo electrónico.
     * @param password Contraseña del administrador.
     */
    public UsuarioAdministrador(String nombre, String usuario, String email, String password) {
        super(nombre, usuario, email, password);
    }

    /**
     * Constructor vacío requerido por JAXB para la serialización.
     */
    public UsuarioAdministrador() {
        // JAXB requiere un constructor vacío
    }
}

