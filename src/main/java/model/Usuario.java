package model;

import javax.xml.bind.annotation.*;
import java.util.Objects;

/**
 * Clase base que representa un usuario.
 * Puede ser extendida por UsuarioVoluntario, UsuarioCreador y UsuarioAdministrador.
 */
@XmlSeeAlso({UsuarioVoluntario.class, UsuarioCreador.class, UsuarioAdministrador.class})
@XmlRootElement(name = "usuario")  // Define el elemento raíz en XML
public class Usuario {

    private String nombre;
    protected String nombreUsuario;
    private String email;
    private String password;

    /**
     * Constructor vacío requerido por JAXB para la serialización XML.
     */
    public Usuario() {}

    /**
     * Constructor para inicializar un usuario con sus datos.
     *
     * @param nombre       Nombre del usuario.
     * @param nombreUsuario Nombre de usuario.
     * @param email        Correo electrónico.
     * @param password     Contraseña.
     */
    public Usuario(String nombre, String nombreUsuario, String email, String password) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }

    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "nombreUsuario")
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Método para comparar dos objetos Usuario basándose en nombreUsuario y email.
     *
     * @param obj Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Usuario usuario = (Usuario) obj;
        return Objects.equals(nombreUsuario, usuario.nombreUsuario) &&
                Objects.equals(email, usuario.email);
    }

    /**
     * Método hashCode basado en nombreUsuario y email.
     *
     * @return Código hash del usuario.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombreUsuario, email);
    }
}
