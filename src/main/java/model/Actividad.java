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

    /**
     * Constructor vacío necesario para JAXB.
     */
    public Actividad() {}

    /**
     * Constructor con parámetros para inicializar una actividad.
     *
     * @param nombre            Nombre de la actividad.
     * @param descripcion       Descripción de la actividad.
     * @param responsable       Responsable de la actividad.
     * @param iniciativaAsociada Nombre de la iniciativa asociada.
     * @param fechaInicio       Fecha de inicio de la actividad.
     * @param fechaFin          Fecha de finalización de la actividad.
     * @throws IllegalArgumentException Si la fecha de inicio es posterior a la fecha de fin.
     */
    public Actividad(String nombre, String descripcion, String responsable, String iniciativaAsociada, LocalDate fechaInicio, LocalDate fechaFin) {
        validarFechas(fechaInicio, fechaFin);
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
        validarFechas(fechaInicio, this.fechaFin);
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) {
        validarFechas(this.fechaInicio, fechaFin);
        this.fechaFin = fechaFin;
    }

    /**
     * Método para validar que la fecha de inicio no sea posterior a la fecha de fin.
     *
     * @param inicio Fecha de inicio.
     * @param fin    Fecha de fin.
     * @throws IllegalArgumentException Si la fecha de inicio es posterior a la de fin.
     */
    private void validarFechas(LocalDate inicio, LocalDate fin) {
        if (inicio != null && fin != null && inicio.isAfter(fin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }
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
