package controller;

import model.Actividad;
import model.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActividadesController {


    // Método para crear una actividad
    public static void crearActividad(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, Usuario voluntarioAsignado) {

    }

    // Método para agregar una actividad
    public static void agregarActividad(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, Usuario voluntarioAsignado) {

    }

    // Método para eliminar una actividad por ID
    public static void eliminarActividad(int id) {


    }

    // Método para mostrar todas las actividades
    public static void mostrarActividades() {

    }

    // Método para generar un ID único para cada actividad
    private static int generarId() {
        return actividades.size() + 1;
    }
}
