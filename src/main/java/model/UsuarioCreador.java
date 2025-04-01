package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representa un usuario creador, que está asociado a una ONG.
 * Hereda de la clase Usuario.
 */
@XmlRootElement(name = "usuarioCreador")
public class UsuarioCreador extends Usuario {

    private String ONG; // Organización asociada al usuario creador

    /**
     * Constructor vacío requerido por JAXB para la serialización.
     */
    public UsuarioCreador() {
        // JAXB requiere un constructor vacío
    }

    /**
     * Constructor con parámetros para inicializar un usuario creador.
     *
     * @param nombre        Nombre del usuario.
     * @param nombreUsuario Nombre de usuario.
     * @param email         Correo electrónico.
     * @param password      Contraseña del usuario.
     * @param ONG           Nombre de la ONG a la que pertenece.
     */
    public UsuarioCreador(String nombre, String nombreUsuario, String email, String password, String ONG) {
        super(nombre, nombreUsuario, email, password);
        this.ONG = ONG;
    }

    @XmlElement(name = "ONG")
    public String getONG() {
        return ONG;
    }

    public void setONG(String ONG) {
        this.ONG = ONG;
    }
}



