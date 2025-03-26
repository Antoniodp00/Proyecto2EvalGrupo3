package model;

import javax.xml.bind.annotation.*;
import java.util.Objects;


@XmlSeeAlso({UsuarioVoluntario.class, UsuarioCreador.class, UsuarioAdministrador.class})
@XmlRootElement(name = "usuario")  // Define el elemento raíz en XML
public class Usuario {

    private String email;
    private String nombre;
    private String nombreUsuario;
    private String password;

    // Constructor vacío requerido por JAXB
    public Usuario() {}

    public Usuario(String email, String nombre, String nombreUsuario, String password) {
        this.email = email;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    @XmlElement(name = "email")  // Anotación para JAXB
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @XmlElement(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(nombreUsuario, usuario1.nombreUsuario) && Objects.equals(email, usuario1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreUsuario, email);
    }

}
