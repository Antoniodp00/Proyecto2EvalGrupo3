package controller;

import model.*;
import utilidades.Utilidades;
import utilidades.XMLManager;
import view.VistaConsola;
import view.VistaConsolaActividad;

import java.io.File;
import java.util.Scanner;

public class ActividadesController {

    private static final String ARCHIVO_XML = "actividades.xml";

    /**
     * Registra una nueva actividad si no existe ya en la lista y si la iniciativa asociada es válida.
     * Los datos de la actividad se solicitan a través de la consola.
     */
    public static void registrarActividad() {
        ListaActividades lista = new ListaActividades();
        ListaIniciativas listaIniciativas = ListaIniciativas.cargarDesdeXML("iniciativas.xml");
        File file = new File(ARCHIVO_XML);
        Actividad actividad = VistaConsolaActividad.pideActividad(); // Solicita los datos de la actividad

        if (file.exists() && file.length() > 0) {
            lista = ListaActividades.cargarDesdeXML("actividades.xml");
        }

        // Validar que la iniciativa asociada existe
        if (!listaIniciativas.existeIniciativa(actividad.getIniciativaAsociada())) {
            VistaConsola.mostrarMensaje("Error: La iniciativa especificada no existe.");
            return;
        }

        // Verificar que la actividad no esté duplicada
        if (lista.existeActividad(actividad.getNombre(), actividad.getIniciativaAsociada())) {
            VistaConsola.mostrarMensaje("Error: La actividad ya está registrada en esta iniciativa.");
            return;
        }

        try {
            lista.agregarActividad(actividad);
            XMLManager.writeXML(lista, ARCHIVO_XML);
            VistaConsola.mostrarMensaje("✅ Actividad registrada exitosamente.");
        } catch (IllegalArgumentException e) {
            VistaConsola.mostrarMensaje("Error al registrar la actividad: " + e.getMessage());
        }
    }

    /**
     * Agrega un voluntario a una actividad dentro de una iniciativa específica.
     *
     * @param usuarioCreador El usuario que está gestionando la asignación del voluntario.
     */
    public static void agregarVoluntarioActividad(UsuarioCreador usuarioCreador) {
        Scanner sc = new Scanner(System.in);

        ListaUsuarios listaUsuarios = ListaUsuarios.cargarDesdeXML("voluntarios.xml");
        ListaActividades listaActividades = ListaActividades.cargarDesdeXML("actividades.xml");
        ListaIniciativas listaIniciativas = CreadorController.cargarIniciativasPorUsuario(usuarioCreador.getNombreUsuario());

        VistaConsola.mostrarMensaje("Ingrese el nombre del usuario que desea agregar a la actividad.");
        String nombreVoluntario = sc.nextLine();
        VistaConsola.mostrarMensaje("Ingrese el nombre de la iniciativa");
        String nombreIniciativa = sc.nextLine();
        VistaConsola.mostrarMensaje("Ingrese el nombre de la actividad");
        String nombreActividad = sc.nextLine();

        // Verificar si el voluntario, la iniciativa y la actividad existen
        if (listaUsuarios.buscar(nombreVoluntario) != null &&
                listaActividades.buscar(nombreActividad) != null &&
                listaIniciativas.buscar(nombreIniciativa) != null) {

            Actividad actividad = listaActividades.buscar(nombreActividad);
            actividad.setResponsable(nombreVoluntario);
            VistaConsola.mostrarMensaje("✅ Usuario agregado correctamente a la actividad.");
        } else {
            VistaConsola.mostrarMensaje("Error: Datos incorrectos, por favor verifique los nombres ingresados.");
        }
    }

    /**
     * Comprueba si un usuario ya está asignado a alguna actividad.
     *
     * @param usuario El usuario a verificar.
     * @return true si el usuario ya está asignado a una actividad, false en caso contrario.
     */
    public static boolean compruebaActividadAsignada(Usuario usuario) {
        boolean asignado = false;
        ListaActividades listaActividades = ListaActividades.cargarDesdeXML("actividades.xml");

        for (Actividad actividad : listaActividades.getActividades()) {
            if (actividad.getResponsable() != null && actividad.getResponsable().equals(usuario.getNombreUsuario()) && !asignado) {
                asignado = true;
            }
        }

        return asignado;
    }

    // Método para actualizar una actividad
    public static void actualizarActividad(UsuarioCreador creador) {
        Scanner scanner = new Scanner(System.in);
        String nombreActividad = Utilidades.leeString("Introduce el nombre de la actividad a actualizar:");
        Actividad actividad = ActividadesController.buscarActividad(creador, nombreActividad);

        if (actividad != null) {
            String nuevoNombre = Utilidades.leeString("Introduce el nuevo nombre de la actividad:");
            String nuevaDescripcion = Utilidades.leeString("Introduce la nueva descripción de la actividad:");
            actividad.setNombre(nuevoNombre);
            actividad.setDescripcion(nuevaDescripcion);
            ActividadesController.guardarActividades(creador);
            System.out.println("Actividad actualizada exitosamente.");
        } else {
            System.out.println("Actividad no encontrada.");
        }
    }

    // Método para eliminar una actividad
    public static void eliminarActividad(UsuarioCreador creador) {
        Scanner scanner = new Scanner(System.in);
        String nombreActividad = Utilidades.leeString("Introduce el nombre de la actividad a eliminar:");
        Actividad actividad = ActividadesController.buscarActividad(creador, nombreActividad);

        if (actividad != null) {
            ActividadesController.eliminarActividad(creador, actividad);
            System.out.println("Actividad eliminada exitosamente.");
        } else {
            System.out.println("Actividad no encontrada.");
        }
    }
}
