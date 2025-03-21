package model;

public class UsuarioVoluntario extends Usuario {
    private int puntos;

    public UsuarioVoluntario(String nombre, String usuario, String email, String password) {
        super(nombre, usuario, email, password);
        this.puntos = 0;
    }

    @Override
    public String cifrarPassword() {
        return "";
    }
}
