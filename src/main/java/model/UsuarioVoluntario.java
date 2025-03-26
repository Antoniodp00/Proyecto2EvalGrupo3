package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usuarioVoluntario")
public class UsuarioVoluntario extends Usuario {
    @XmlElement(name = "puntos")
    private int puntos;

    public UsuarioVoluntario(String nombre, String usuario, String email, String password) {
        super(nombre, usuario, email, password);
        this.puntos = 0;
    }

    public UsuarioVoluntario() {
    }

}
