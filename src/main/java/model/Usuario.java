package model;

import java.util.Objects;

public abstract class Usuario {
    private String nombre;
    private String usuario;
    private String email;
    private String password;

    public Usuario(String nombre, String usuario, String email, String password) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.email = email;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
        return Objects.equals(usuario, usuario1.usuario) && Objects.equals(email, usuario1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, email);
    }

 public abstract String cifrarPassword();

}
