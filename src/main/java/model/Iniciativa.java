package model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "iniciativa")
@XmlAccessorType(XmlAccessType.FIELD)
public class Iniciativa {

    @XmlElement(name = "nombre")
    private String nombre;

    @XmlElement(name = "descripcion")
    private String descripcion;

    @XmlElement(name = "creador") // Aquí anotamos el creador
    private String creador;

    @XmlTransient
    private List<Actividad> misactividades;

    public Iniciativa() {} // JAXB necesita un constructor vacío

    public Iniciativa(String nombre, String descripcion, String creador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
        this.misactividades = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getCreador() { return creador; }
    public void setCreador(String creador) { this.creador = creador; }

    public List<Actividad> getActividades() {
        return misactividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.misactividades = actividades;
    }
}
