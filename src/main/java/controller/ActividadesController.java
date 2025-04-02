package controller;

import model.*;
import utilidades.Utilidades;
import utilidades.XMLManager;
import view.VistaConsola;
import view.VistaConsolaActividad;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ActividadesController {
    private static final String ARCHIVO_XML = "actividades.xml";
    private ListaActividades listaActividades;

    /**
     * Constructor: Carga las actividades desde el archivo XML al iniciar la clase.
     * Si el archivo no existe o está vacío, inicializa una nueva lista de actividades.
     */
    public ActividadesController() {
        File file = new File(ARCHIVO_XML);
        if (file.exists() && file.length() > 0) {
            this.listaActividades = ListaActividades.cargarDesdeXML(ARCHIVO_XML);
        } else {
            this.listaActividades = new ListaActividades();
        }
    }

    /**
     * Registra una nueva actividad si no existe ya en la lista y si la iniciativa asociada es válida.
     * Los datos de la actividad se solicitan a través de la consola.
     */
    public void registrarActividad() {
        ListaIniciativas listaIniciativas = ListaIniciativas.cargarDesdeXML("iniciativas.xml");
        Actividad actividad = VistaConsolaActividad.pideActividad(); // Solicita los datos de la actividad
        // Validar que la iniciativa asociada existe
        if (!listaIniciativas.existeIniciativa(actividad.getIniciativaAsociada())) {
            VistaConsola.mostrarMensaje("❌ Error: La iniciativa especificada no existe.");
            return;
        }

        // Verificar que la actividad no esté duplicada
        if (listaActividades.existeActividad(actividad.getNombre(), actividad.getIniciativaAsociada())) {
            VistaConsola.mostrarMensaje("❌ Error: La actividad ya está registrada en esta iniciativa.");
            return;
        }

        try {
            listaActividades.agregarActividad(actividad);
            listaActividades.guardarXML(ARCHIVO_XML);
            VistaConsola.mostrarMensaje("✅ Actividad registrada exitosamente.");
        } catch (IllegalArgumentException e) {
            VistaConsola.mostrarMensaje("❌ Error al registrar la actividad: " + e.getMessage());
        }
    }

    /**
     * Actualiza una actividad existente cambiando su nombre y descripción.
     *
     * @param creador Usuario creador que desea actualizar la actividad.
     */
    public void actualizarActividad(UsuarioCreador creador) {
        String nombreActividad = Utilidades.leeString("Introduce el nombre de la actividad a actualizar:");
        Actividad actividad = listaActividades.buscar(nombreActividad);
        ;

        if (actividad != null) {
            actividad.setNombre(Utilidades.leeString("Introduce el nuevo nombre de la actividad:"));
            actividad.setDescripcion(Utilidades.leeString("Introduce la nueva descripción de la actividad:"));
            listaActividades.guardarXML(ARCHIVO_XML);
            VistaConsola.mostrarMensaje("✅ Actividad actualizada exitosamente.");
        } else {
            VistaConsola.mostrarMensaje("❌ Actividad no encontrada.");
        }
    }

    /**
     * Elimina una actividad de la lista si existe.
     *
     * @param creador Usuario creador que desea eliminar una actividad.
     */
    public void eliminarActividad(UsuarioCreador creador) {
        String nombreActividad = Utilidades.leeString("Introduce el nombre de la actividad a eliminar:");
        Actividad actividad = listaActividades.buscar(nombreActividad);

        if (actividad != null) {
            listaActividades.eliminar(actividad);
            listaActividades.guardarXML(ARCHIVO_XML);
            VistaConsola.mostrarMensaje("✅ Actividad eliminada exitosamente.");
        } else {
            VistaConsola.mostrarMensaje("❌ Actividad no encontrada.");
        }
    }

    /**
     * Asigna un voluntario a una actividad si no tiene responsable,
     * verificando que la actividad pertenezca a una iniciativa creada por el usuario creador.
     */
    public void asignarVoluntario(UsuarioCreador creador) {
        Scanner sc = new Scanner(System.in);
        ListaUsuarios listaUsuarios = ListaUsuarios.cargarDesdeXML("voluntarios.xml");
        ListaActividades listaActividades = ListaActividades.cargarDesdeXML(ARCHIVO_XML);
        ListaIniciativas listaIniciativas = ListaIniciativas.cargarDesdeXML("iniciativas.xml");

        VistaConsola.mostrarMensaje("Ingrese el nombre del voluntario:");
        String nombreVoluntario = sc.nextLine();
        VistaConsola.mostrarMensaje("Ingrese el nombre de la actividad:");
        String nombreActividad = sc.nextLine();

        Usuario voluntario = listaUsuarios.buscar(nombreVoluntario);
        Actividad actividad = listaActividades.buscar(nombreActividad);

        if (voluntario != null && actividad != null) {
            Iniciativa iniciativa = listaIniciativas.buscar(actividad.getIniciativaAsociada());
            if (iniciativa != null && iniciativa.getCreador().equals(creador.getNombreUsuario())) {
                if (actividad.getResponsable() == null || actividad.getResponsable().isEmpty()) {
                    actividad.setResponsable(voluntario.getNombreUsuario());
                    actividad.setEstado(Estado.EN_PROGRESO);
                    actividad.setFechaInicio(java.time.LocalDate.now());
                    listaActividades.actualizar(actividad);
                    listaActividades.guardarXML(ARCHIVO_XML);
                    VistaConsola.mostrarMensaje("Voluntario " + voluntario.getNombre() + " asignado a la actividad: " + actividad.getNombre());
                } else {
                    VistaConsola.mostrarMensaje("La actividad ya tiene un responsable asignado.");
                }
            } else {
                VistaConsola.mostrarMensaje("No tienes permiso para asignar voluntarios a esta actividad.");
            }
        } else {
            VistaConsola.mostrarMensaje("❌ Error: Voluntario o actividad no encontrados.");
        }
    }

    /**
     * Permite que un voluntario se asigne a una actividad sin responsable.
     */
    public void asignarseActividad(UsuarioVoluntario voluntario) {
        Scanner sc = new Scanner(System.in);
        ListaActividades listaActividades = ListaActividades.cargarDesdeXML("actividades.xml");

        VistaConsola.mostrarMensaje("Ingrese el nombre de la actividad a la que desea unirse:");
        String nombreActividad = sc.nextLine();

        Actividad actividad = listaActividades.buscar(nombreActividad);

        if (actividad == null) {
            VistaConsola.mostrarMensaje("❌ Error: Actividad no encontrada.");
        } else if (actividad.getResponsable() == null || actividad.getResponsable().isEmpty()) {
            actividad.setResponsable(voluntario.getNombreUsuario());
            actividad.setEstado(Estado.EN_PROGRESO);
            actividad.setFechaInicio(java.time.LocalDate.now());
            VistaConsola.mostrarMensaje("Te has asignado a la actividad: " + actividad.getNombre());
            listaActividades.agregar(actividad);
            listaActividades.guardarXML(ARCHIVO_XML);
        } else {
            VistaConsola.mostrarMensaje("La actividad ya tiene un responsable asignado.");
        }
    }

    /**
     * Cambia el estado de una actividad a COMPLETADA y permite añadir un comentario.
     */
    public void cambiarEstadoActividad(UsuarioVoluntario voluntario) {
        ListaUsuarios listaVoluntarios = ListaUsuarios.cargarDesdeXML("voluntarios.xml");
        Scanner sc = new Scanner(System.in);
        ListaActividades listaActividades = ListaActividades.cargarDesdeXML(ARCHIVO_XML);

        VistaConsola.mostrarMensaje("Ingrese el nombre de la actividad a completar:");
        String nombreActividad = sc.nextLine();

        Actividad actividad = listaActividades.buscar(nombreActividad);

        if (actividad == null) {
            VistaConsola.mostrarMensaje("❌ Error: Actividad no encontrada.");
        } else if (actividad.getEstado().equals(Estado.EN_PROGRESO)) {
            VistaConsola.mostrarMensaje("¿Quieres añadir un comentario sobre la actividad? (Deja en blanco para omitir)");
            String comentario = sc.nextLine();
            actividad.setComentario(comentario);
            actividad.setFechaFin(java.time.LocalDate.now());
            actividad.setEstado(Estado.COMPLETADA);
            VistaConsola.mostrarMensaje("Estado de la actividad actualizado a: COMPLETADA");
            listaActividades.guardarXML(ARCHIVO_XML);
            voluntario.sumarPuntos();
            listaVoluntarios.actualizar(voluntario);
            listaVoluntarios.guardarXML("voluntarios.xml");
        } else {
            VistaConsola.mostrarMensaje("❌ Error: Actividad completada.");
        }
    }

    /**
     * Lista todas las actividades que pertenecen a las iniciativas creadas por un usuarioCreador.
     */
    public void listarActividades(UsuarioCreador creador) {
        ListaIniciativas listaIniciativas = ListaIniciativas.cargarDesdeXML("iniciativas.xml");
        ListaActividades listaActividades = ListaActividades.cargarDesdeXML(ARCHIVO_XML);
        Set<Actividad> actividadesFiltradas = new HashSet<>();

        for (Actividad actividad : listaActividades.getActividades()) {
            Iniciativa iniciativa = listaIniciativas.buscar(actividad.getIniciativaAsociada());
            if (iniciativa != null && iniciativa.getCreador().equals(creador.getNombreUsuario())) {
                actividadesFiltradas.add(actividad);
            }
        }

        if (actividadesFiltradas.isEmpty()) {
            VistaConsola.mostrarMensaje("❌ No hay actividades disponibles en tus iniciativas.");
        } else {
            VistaConsola.mostrarMensaje("Actividades disponibles en tus iniciativas:");
            for (Actividad actividad : actividadesFiltradas) {
                VistaConsola.mostrarMensaje("- " + actividad.getNombre() + " (" + actividad.getIniciativaAsociada() + ")- " + actividad.getResponsable() + " - " + actividad.getEstado());
            }
        }
    }

    /**
     * Lista todas las actividades sin responsable asignado.
     */
    public void listarActividadesDisponibles() {
        VistaConsola.mostrarMensaje("Actividades disponibles:");
        for (Actividad actividad : listaActividades.getActividades()) {
            if (actividad.getResponsable().isEmpty()) {
                VistaConsola.mostrarMensaje("- " + actividad.getNombre() + " (" + actividad.getIniciativaAsociada() + ")");
            }
        }
    }

    /**
     * Lista las actividades en las que un voluntario está asignado.
     */
    public void listarMisActividades(UsuarioVoluntario voluntario) {
        VistaConsola.mostrarMensaje("Tus actividades:");
        listaActividades = ListaActividades.cargarDesdeXML("actividades.xml");
        for (Actividad actividad : listaActividades.getActividades()) {
            if (actividad.getResponsable().equals(voluntario.getNombreUsuario())) {
                VistaConsola.mostrarMensaje("- " + actividad.getNombre() + " (" + actividad.getIniciativaAsociada() + ") -" + actividad.getEstado());
            }
        }
    }
}

