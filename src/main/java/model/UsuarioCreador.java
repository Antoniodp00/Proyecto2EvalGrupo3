package model;

import controller.CreadorController;
import utilidades.SCRUD;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@XmlRootElement(name = "usuarioCreador")
public class UsuarioCreador extends Usuario{

    private String ONG;  // Propiedad adicional para el tipo UsuarioCreador
    private Set<Iniciativa> iniciativas = CreadorController.cargarIniciativasPorUsuario(nombreUsuario);

    public UsuarioCreador() {
    }

    public UsuarioCreador(String nombre, String nombreUsuario, String email, String password, String ONG) {
        super(nombre, nombreUsuario, email, password);
        this.ONG = ONG;
        this.iniciativas = iniciativas;
    }

    @XmlElement(name = "ONG")
    public String getONG() {
        return ONG;
    }

    public void setONG(String ONG) {
        this.ONG = ONG;
    }


    public Set<Iniciativa> getIniciativas() {
        return iniciativas;
    }

    public void setIniciativas(List<Iniciativa> iniciativa) {
        this.iniciativas = iniciativas;
    }

}


