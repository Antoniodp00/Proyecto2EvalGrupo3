package model;

import utilidades.XMLManager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usuarioVoluntario")
public class UsuarioVoluntario extends Usuario {

    private int puntos;

    public UsuarioVoluntario(String nombre, String usuario, String email, String password) {
        super(nombre, usuario, email, password);
        this.puntos = 112000;
    }

    public UsuarioVoluntario() {
    }
    @XmlElement(name = "puntos")
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void restarPuntos(int cantidad) {
        this.puntos -= cantidad;
    }

    public void guardarEnXML() {
        XMLManager.writeXML(this, "voluntarios.xml");
    }

}
