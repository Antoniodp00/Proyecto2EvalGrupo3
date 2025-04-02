package model;

import utilidades.LocalDateAdapter;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase que representa una actividad dentro de una iniciativa.
 * Se utiliza para gestionar actividades en la aplicación.
 */
@XmlRootElement(name = "actividad") // Define el nombre del elemento raíz en XML
@XmlAccessorType(XmlAccessType.FIELD) // Permite la serialización directa de los campos
public class Actividad {

    @XmlElement(name = "nombre") // Nombre de la actividad
    private String nombre;

    @XmlElement(name = "descripcion") // Descripción de la actividad
    private String descripcion;

    @XmlElement(name = "responsable") // Responsable de la actividad
    private String responsable;

    @XmlElement(name = "iniciativa") // Nombre de la iniciativa a la que pertenece la actividad
    private String iniciativaAsociada;

    @XmlJavaTypeAdapter(LocalDateAdapter.class) // Adaptador para convertir LocalDate en XML
    private LocalDate fechaInicio; // Fecha de inicio de la actividad

    @XmlJavaTypeAdapter(LocalDateAdapter.class) // Adaptador para convertir LocalDate en XML
    private LocalDate fechaFin; // Fecha de finalización de la actividad

    @XmlElement(name = "estado") // Estado actual de la actividad
    private Estado estado;

    @XmlElement(name = "comentario") // Comentarios sobre la actividad
    private String comentario;

    /**
     * Constructor vacío requerido por JAXB.
     */
    public Actividad() {}

    /**
     * Constructor que inicializa una actividad con su nombre, descripción e iniciativa asociada.
     * Otros valores como responsable, fechas y estado se asignan con valores predeterminados.
     *
     * @param nombre            Nombre de la actividad.
     * @param descripcion       Descripción de la actividad.
     * @param iniciativaAsociada Iniciativa a la que pertenece la actividad.
     */
    public Actividad(String nombre, String descripcion, String iniciativaAsociada) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.iniciativaAsociada = iniciativaAsociada;

        // Valores por defecto
        this.responsable = ""; // Inicialmente sin responsable
        this.fechaInicio = LocalDate.now(); // Fecha de inicio predeterminada
        this.fechaFin = LocalDate.now().plusDays(1); // Fecha de fin predeterminada
        this.estado = Estado.NO_INICIADA; // Estado inicial
        this.comentario = ""; // Sin comentarios al inicio
    }

    // Métodos Getters y Setters para acceder y modificar los atributos

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }

    public String getIniciativaAsociada() { return iniciativaAsociada; }
    public void setIniciativaAsociada(String iniciativaAsociada) { this.iniciativaAsociada = iniciativaAsociada; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    /**
     * Método para comparar dos actividades según su nombre e iniciativa asociada.
     * Dos actividades son iguales si tienen el mismo nombre y pertenecen a la misma iniciativa.
     *
     * @param obj Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Son la misma referencia
        if (!(obj instanceof Actividad)) return false; // No es una instancia de Actividad
        Actividad otra = (Actividad) obj;
        return Objects.equals(nombre, otra.nombre) &&
                Objects.equals(iniciativaAsociada, otra.iniciativaAsociada);
    }

    /**
     * Método para generar un código hash basado en el nombre y la iniciativa asociada.
     *
     * @return Código hash único para la actividad.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, iniciativaAsociada);
    }

    /**
     * Método toString para representar la actividad en formato de texto.
     *
     * @return Cadena con los datos de la actividad.
     */
    @Override
    public String toString() {
        return "Actividad{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", responsable='" + responsable + '\'' +
                ", iniciativaAsociada='" + iniciativaAsociada + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estado=" + estado +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
