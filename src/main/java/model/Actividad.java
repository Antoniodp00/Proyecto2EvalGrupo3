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

    @XmlElement(name = "estado")
    private Estado estado;  // Campo para el estado de la actividad

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

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getIniciativaAsociada() {
        return iniciativaAsociada;
    }

    public String getResponsable() {
        return responsable;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getComentario() {
        return comentario;
    }

    // Métodos setters para establecer los valores más tarde
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    // Método para cambiar el estado y agregar un comentario
    public void cambiarEstado(UsuarioVoluntario voluntario, Estado nuevoEstado, String comentario) {
        if (voluntario != null && voluntario.getNombre().equals(this.responsable)) {
            // Si el voluntario es el responsable de la actividad, se puede cambiar el estado
            this.estado = nuevoEstado;
            this.comentario = comentario;  // Guardamos el comentario asociado al cambio de estado
        } else {
            throw new IllegalArgumentException("El voluntario no está autorizado para cambiar el estado de esta actividad.");
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
