package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "usuarioCreador")
public class UsuarioCreador extends Usuario {

    private String ONG;  // Propiedad adicional para el tipo UsuarioCreador

    public UsuarioCreador() {}

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
