package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement
public class UsuarioCreador extends Usuario {
    private String ONG;


    public UsuarioCreador(String nombre, String usuario, String email, String password, String ONG) {
        super(nombre, usuario, email, password);
        this.ONG = ONG;
    }
public UsuarioCreador() {}

    public String getONG() {
        return ONG;
    }

    public void setONG(String ONG) {
        this.ONG = ONG;
    }
}
