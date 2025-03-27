package controller;

import model.*;
import utilidades.XMLManager;
import view.VistaConsola;
import view.VistaConsolaActividad;

import java.io.File;
import java.time.LocalDate;
import java.util.Set;

public class ActividadesController {

    private static final String ARCHIVO_XML = "actividades.xml";


    public static void registrarActividad() {
        ListaActividades lista = new ListaActividades();
        ListaIniciativas listaIniciativas = ListaIniciativas.cargarDesdeXML("iniciativas.xml");
        File file = new File(ARCHIVO_XML);
        Actividad actividad = VistaConsolaActividad.pideActividad();
        if (file.exists() && file.length() > 0) {
            lista = ListaActividades.cargarDesdeXML("actividades.xml");
        }

        // Validar que la iniciativa existe (aquí deberíamos integrar un sistema de validación de iniciativas)
        if (!listaIniciativas.existeIniciativa(actividad.getIniciativaAsociada())) {
            System.out.println("Error: La iniciativa especificada no existe.");
            return;
        }

        // Verificar que la actividad no exista ya
        if (lista.existeActividad(actividad.getNombre(), actividad.getIniciativaAsociada())) {
            System.out.println("Error: La actividad ya está registrada en esta iniciativa.");
            return;
        }

        try {
            lista.agregarActividad(actividad);
            XMLManager.writeXML(lista, ARCHIVO_XML);
            System.out.println("✅ Actividad registrada exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al registrar la actividad: " + e.getMessage());
        }
    }
}
