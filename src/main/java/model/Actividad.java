package model;

import java.time.LocalDate;
import java.util.List;

public class Actividad {
    private String nombre;
    private String descripcion;
    private Estado estado;
    private List<String> comentarios;
    private int id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Usuario voluntarioAsignado;

    public Actividad(String nombre, String descripcion, Estado estado, List<String> comentarios, int id, LocalDate fechaInicio, LocalDate fechaFin, Usuario voluntarioAsignado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.comentarios = comentarios;
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.voluntarioAsignado = voluntarioAsignado;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Usuario getVoluntarioAsignado() {
        return voluntarioAsignado;
    }

    public void setVoluntarioAsignado(Usuario voluntarioAsignado) {
        this.voluntarioAsignado = voluntarioAsignado;
    }

    public void cambiarEstado(String nuevoEstado) {
        try {
            this.estado = Estado.valueOf(nuevoEstado);
            System.out.println("Estado cambiado a: " + nuevoEstado);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: El estado '" + nuevoEstado + "' no es válido.");
        }
    }

    public void agregarComentario(String comentario) {
        this.comentarios.add(comentario);
        System.out.println("Comentario agregado: " + comentario);
    }

    public boolean validarFechas() {
        if (fechaInicio.getYear() < fechaFin.getYear()) {
            return true;
        } else if (fechaInicio.getYear() > fechaFin.getYear()) {
            return false;
        } else {
            if (fechaInicio.getMonthValue() < fechaFin.getMonthValue()) {
                return true;
            } else if (fechaInicio.getMonthValue() > fechaFin.getMonthValue()) {
                return false;
            } else {
                return fechaInicio.getDayOfMonth() <= fechaFin.getDayOfMonth();
            }
        }
    }

    @Override
    public String toString() {
        return "Actividad{id=" + id + ", nombre='" + nombre + "'}";
    }
}
