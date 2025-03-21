package model;

public class UsuarioAdministrador extends Usuario{
    public UsuarioAdministrador(String nombre, String usuario, String email, String password) {
        super(nombre, usuario, email, password);
    }

    @Override
    public String cifrarPassword() {
        return "";
    }
}
