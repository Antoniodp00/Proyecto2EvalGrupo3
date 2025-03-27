package model;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.Objects;

@XmlRootElement(name = "actividad")
@XmlAccessorType(XmlAccessType.FIELD)
public class Actividad {

    @XmlElement(name = "nombre")
    private String nombre;

    @XmlElement(name = "descripcion")
    private String descripcion;

    @XmlElement(name = "responsable")
    private String responsable;

    @XmlElement(name = "iniciativa")
    private String iniciativaAsociada;

    @XmlElement(name = "fechaInicio")
    private LocalDate fechaInicio;

    @XmlElement(name = "fechaFin")
    private LocalDate fechaFin;

    public Actividad() {} // Constructor vacío para JAXB

    public Actividad(String nombre, String descripcion, String responsable, String iniciativaAsociada, LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.responsable = responsable;
        this.iniciativaAsociada = iniciativaAsociada;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }

    public String getIniciativaAsociada() { return iniciativaAsociada; }
    public void setIniciativaAsociada(String iniciativaAsociada) { this.iniciativaAsociada = iniciativaAsociada; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio.isAfter(this.fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) {
        if (this.fechaInicio != null && fechaFin.isBefore(this.fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        this.fechaFin = fechaFin;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Actividad)) return false;
        Actividad otra = (Actividad) obj;
        return Objects.equals(nombre, otra.nombre) &&
                Objects.equals(iniciativaAsociada, otra.iniciativaAsociada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, iniciativaAsociada);
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", responsable='" + responsable + '\'' +
                ", iniciativaAsociada='" + iniciativaAsociada + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
