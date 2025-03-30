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

    @XmlElement(name = "creador")
    private String creador;

    @XmlTransient
    private List<Actividad> actividades;

    /**
     * Constructor vacío necesario para JAXB.
     */
    public Iniciativa() {
        this.actividades = new ArrayList<>();
    }

    /**
     * Constructor con parámetros para inicializar una iniciativa.
     *
     * @param nombre      Nombre de la iniciativa.
     * @param descripcion Descripción de la iniciativa.
     * @param creador     Nombre del creador de la iniciativa.
     */
    public Iniciativa(String nombre, String descripcion, String creador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
        this.actividades = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getCreador() { return creador; }
    public void setCreador(String creador) { this.creador = creador; }

    public List<Actividad> getActividades() { return actividades; }
    public void setActividades(List<Actividad> actividades) { this.actividades = actividades; }

    /**
     * Agrega una actividad a la lista de actividades.
     *
     * @param actividad Actividad a agregar.
     */
    public void agregarActividad(Actividad actividad) {
        if (actividad != null) {
            this.actividades.add(actividad);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Iniciativa)) return false;
        Iniciativa otra = (Iniciativa) obj;
        return Objects.equals(nombre, otra.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "Iniciativa{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", creador='" + creador + '\'' +
                ", actividades=" + actividades.size() + " actividades" +
                '}';
    }
}
