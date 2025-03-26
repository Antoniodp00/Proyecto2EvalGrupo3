package model;

import java.util.List;
import java.time.*;
import java.time.format.*;

//todo: métodos crearActividad, agregarComentario, validarFechas, agregarActividades, borrarActividades;

public class Actividad {
    private String nombre;
    private String descripcion;
    private Estado estado;
    private List<String> comentarios;
    private int id = 0;
    private LocalDate fechaInicio = LocalDate.now();
    private LocalDate fechaFin = LocalDate.of(0,1,1);
    private Usuario voluntarioAsignado;

    public Actividad(String nombre, String descripcion, Estado estado, List<String> comentarios, int id, LocalDate fechaInicio, LocalDate fechaFin, Usuario voluntarioAsignado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.comentarios = comentarios;
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.voluntarioAsignado = null;
    }

    public Actividad(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, Usuario voluntario) {
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

    public static void crearActividad(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, Usuario voluntario){
        Actividad actividad = new Actividad(nombre, descripcion, fechaInicio, fechaFin, voluntario);

        System.out.println("Actividad creada: " +actividad.getNombre());
    }

    public void cambiarEstado(String nuevoEstado) {
        try{
            Estado estadoConvertido = Estado.valueOf(nuevoEstado);
            this.estado = estadoConvertido;
            System.out.println("Cambiar estado a: " +this.estado);
        }catch (IllegalArgumentException e){
            System.out.println("ERROR: El estado '" + nuevoEstado + "' no es válido.");
        }
    }
}
