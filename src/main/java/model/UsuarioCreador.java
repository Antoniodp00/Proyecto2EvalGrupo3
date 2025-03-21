package model;

public class UsuarioCreador extends Usuario {
    private String ONG;

    public UsuarioCreador(String nombre, String usuario, String email, String password, String ONG) {
        super(nombre, usuario, email, password);
        this.ONG = ONG;
    }

    @Override
    public String cifrarPassword() {
        return "";
    }
}
