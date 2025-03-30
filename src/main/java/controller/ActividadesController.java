package controller;

import model.*;
import utilidades.XMLManager;
import view.VistaConsola;
import view.VistaConsolaActividad;

import java.io.File;
import java.util.Scanner;

public class ActividadesController {

    private static final String ARCHIVO_XML = "actividades.xml";
    private ListaActividades listaActividades;
    private ListaIniciativas listaIniciativas;
    private ListaUsuarios listaUsuarios;

    public ActividadesController() {
        this.listaActividades = new ListaActividades();
        this.listaIniciativas = ListaIniciativas.cargarDesdeXML("iniciativas.xml");
        this.listaUsuarios = ListaUsuarios.cargarDesdeXML("voluntarios.xml");

        File file = new File(ARCHIVO_XML);
        if (file.exists() && file.length() > 0) {
            this.listaActividades = ListaActividades.cargarDesdeXML(ARCHIVO_XML);
        }
    }

    /**
     * Registra una nueva actividad en el sistema, validando que la iniciativa asociada exista.
     */
    public void registrarActividad() {
        Actividad actividad = VistaConsolaActividad.pideActividad();

        // Validar que la iniciativa existe
        if (!listaIniciativas.existeIniciativa(actividad.getIniciativaAsociada())) {
            VistaConsola.mostrarMensaje("Error: La iniciativa especificada no existe.");
            return;
        }

        // Verificar que la actividad no exista ya
        if (listaActividades.existeActividad(actividad.getNombre(), actividad.getIniciativaAsociada())) {
            VistaConsola.mostrarMensaje("Error: La actividad ya está registrada en esta iniciativa.");
            return;
        }

        try {
            listaActividades.agregarActividad(actividad);
            XMLManager.writeXML(listaActividades, ARCHIVO_XML);
            VistaConsola.mostrarMensaje("✅ Actividad registrada exitosamente.");
        } catch (IllegalArgumentException e) {
            VistaConsola.mostrarMensaje("Error al registrar la actividad: " + e.getMessage());
        }
    }

    /**
     * Asigna un voluntario como responsable de una actividad específica dentro de una iniciativa.
     *
     * @param usuarioCreador Usuario creador que gestiona la iniciativa y las actividades.
     */
    public void agregarVoluntarioActividad(UsuarioCreador usuarioCreador) {
        Scanner sc = new Scanner(System.in);

        ListaIniciativas iniciativasUsuario = new CreadorController().cargarIniciativasPorUsuario(usuarioCreador);

        VistaConsola.mostrarMensaje("Ingrese el nombre de usuario del voluntario:");
        String nombreVoluntario = sc.nextLine();
        VistaConsola.mostrarMensaje("Ingrese el nombre de la iniciativa:");
        String nombreIniciativa = sc.nextLine();
        VistaConsola.mostrarMensaje("Ingrese el nombre de la actividad:");
        String nombreActividad = sc.nextLine();

        // Verificar que el voluntario, la iniciativa y la actividad existen
        if (listaUsuarios.buscar(nombreVoluntario) != null &&
                listaActividades.buscar(nombreIniciativa) != null &&
                iniciativasUsuario.buscar(nombreActividad) != null) {

            Actividad actividad = listaActividades.buscar(nombreActividad);
            actividad.setResponsable(nombreVoluntario);
            VistaConsola.mostrarMensaje("✅ Usuario asignado exitosamente.");
        } else {
            VistaConsola.mostrarMensaje("Error: Datos ingresados no válidos.");
        }
    }
}
