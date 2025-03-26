package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.time.*;
import java.time.format.*;


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
    // Lista estática para almacenar las actividades
    private static List<Actividad> actividades = new ArrayList<>();
    private static int nextId = 1;  // Generador de IDs

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
            this.estado = Estado.valueOf(nuevoEstado);
            System.out.println("Cambiar estado a: ");
        }catch (IllegalArgumentException e){
            System.out.println("ERROR: El estado '" + nuevoEstado + "' no es válido.");
        }
    }

    public void agregarComentario(String comentario){
        this.comentarios.add(comentario); // Añadir el nuevo comentario a la lista
        System.out.println("Comentario agregado: " + comentario);
    }

    public boolean validarFechas() {
        // Compara año, mes y día de la fechaInicio y fechaFin
        if (fechaInicio.getYear() < fechaFin.getYear()) {
            return true;
        } else if (fechaInicio.getYear() > fechaFin.getYear()) {
            return false;
        } else { // Si los años son iguales, comparamos los meses
            if (fechaInicio.getMonthValue() < fechaFin.getMonthValue()) {
                return true;
            } else if (fechaInicio.getMonthValue() > fechaFin.getMonthValue()) {
                return false;
            } else { // Si el año y mes son iguales, comparamos los días
                return fechaInicio.getDayOfMonth() <= fechaFin.getDayOfMonth();
            }
        }
    }

    public static void agregarActividad(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, Usuario voluntarioAsignado) {
        Actividad nuevaActividad = new Actividad(nombre, descripcion, fechaInicio, fechaFin, voluntarioAsignado);
        actividades.add(nuevaActividad);
        System.out.println("Actividad agregada: " + nuevaActividad);
    }

    // Método para eliminar una actividad por ID
    public static void eliminarActividad(int id) {
        boolean encontrado = false;
        for (Actividad actividad : actividades) {
            if (actividad.getId() == id) {
                actividades.remove(actividad);
                System.out.println("Actividad eliminada: " + actividad);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró una actividad con el ID: " + id);
        }
    }

    public static void mostrarActividades() {
        if (actividades.isEmpty()) {
            System.out.println("No hay actividades disponibles.");
        } else {
            for (Actividad actividad : actividades) {
                System.out.println(actividad);
            }
        }
    }

    @Override
    public String toString() {
        return "Actividad{id=" + id + ", nombre='" + nombre + "'}";
    }
}