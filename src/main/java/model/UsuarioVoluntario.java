package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "usuarioVoluntario")
public class UsuarioVoluntario extends Usuario {
    @XmlElement(name = "puntos")
    private int puntos;
    private Set<Actividad> actividades;

    public UsuarioVoluntario(String nombre, String usuario, String email, String password) {
        super(nombre, usuario, email, password);
        this.puntos = 0;
        this.actividades = new HashSet<>();
    }

    public UsuarioVoluntario() {
        this.actividades = new HashSet<>();
    }

    public boolean estaEnActividad(Actividad actividad) {
        return actividades.contains(actividad);
    }

    public void agregarActividad(Actividad actividad) {
        actividades.add(actividad);
    }

    public void removerActividad(Actividad actividad) {
        actividades.remove(actividad);
    }
}