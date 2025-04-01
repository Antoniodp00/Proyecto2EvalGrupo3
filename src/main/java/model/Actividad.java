package model;

import utilidades.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaInicio;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaFin;

    @XmlElement(name = "estado")
    private Estado estado;  // Campo para el estado de la actividad
    @XmlElement(name = "comentario")
    private String comentario;  // Nuevo campo para el comentario


    public Actividad() {} // Constructor vacío para JAXB
    // Constructor que solo recibe nombre, descripcion e iniciativa
    public Actividad(String nombre, String descripcion, String iniciativaAsociada) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.iniciativaAsociada = iniciativaAsociada;

        // Valores predeterminados
        this.responsable = "";  // Puede ser asignado más tarde
        this.fechaInicio = LocalDate.now();  // Puede ser asignado más tarde
        this.fechaFin = LocalDate.now().plusDays(1); // Puede ser asignado más tarde
        this.estado = Estado.NO_INICIADA;  // Valor por defecto
        this.comentario = "";  // Inicialmente sin comentario
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getIniciativaAsociada() {
        return iniciativaAsociada;
    }

    public void setIniciativaAsociada(String iniciativaAsociada) {
        this.iniciativaAsociada = iniciativaAsociada;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
