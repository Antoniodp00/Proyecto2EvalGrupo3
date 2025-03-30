package model;

import controller.CreadorController;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "usuarioCreador")
public class UsuarioCreador extends Usuario{

    private String ONG;  // Propiedad adicional para el tipo UsuarioCreador
    private ListaIniciativas iniciativas;

    public UsuarioCreador() {
    }

    public UsuarioCreador(String nombre, String nombreUsuario, String email, String password, String ONG) {
        super(nombre, nombreUsuario, email, password);
        this.ONG = ONG;
        this.iniciativas = new ListaIniciativas();
    }

    @XmlElement(name = "ONG")
    public String getONG() {
        return ONG;
    }

    public void setONG(String ONG) {
        this.ONG = ONG;
    }


    public ListaIniciativas getIniciativas() {
        return iniciativas;
    }

    public void setIniciativas(ListaIniciativas iniciativa) {
        this.iniciativas = iniciativas;
    }

}


