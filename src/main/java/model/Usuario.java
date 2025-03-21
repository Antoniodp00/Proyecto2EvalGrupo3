package model;

import java.util.Objects;


public abstract class Usuario {
    private String nombre;
    private String nombreUsuario;
    private String email;
    private String password;


    public Usuario() {}

    public Usuario(String nombre, String usuario, String email, String password) {
        this.nombre = nombre;
        this.nombreUsuario = usuario;
        this.email = email;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

 public abstract String cifrarPassword();

}
