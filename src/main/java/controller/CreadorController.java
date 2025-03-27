package controller;

import model.*;
import utilidades.XMLManager;
import view.VistaConsola;
import view.VistaConsolaIniciativa;

import java.io.File;

public class CreadorController {


    public static ListaIniciativas cargarIniciativasPorUsuario(String nombreUsuario) {
        ListaIniciativas todasLasIniciativas = ListaIniciativas.cargarDesdeXML("iniciativas.xml");
        ListaIniciativas iniciativasUsuario = new ListaIniciativas();

        for (Iniciativa iniciativa : todasLasIniciativas.getIniciativas()) {
            if (iniciativa.getCreador().equals(nombreUsuario)) { // Filtrar por creador
                iniciativasUsuario.agregar(iniciativa);
            }
        }
        return iniciativasUsuario;
    }


    public static void crearIniciativa(UsuarioCreador creador) {
        ListaIniciativas iniciativas;

        File xmlFile = new File("iniciativas.xml");
        if (xmlFile.exists() && xmlFile.length() > 0) {
            try {
                iniciativas = ListaIniciativas.cargarDesdeXML("iniciativas.xml");
            } catch (Exception e) {
                VistaConsola.mostrarMensaje("Error al leer el archivo de iniciativas: " + e.getMessage());
                return;
            }
        } else {
            iniciativas = new ListaIniciativas();
        }

        Iniciativa nuevaIniciativa = VistaConsolaIniciativa.pideIniciativa(creador);
        if (iniciativas.agregar(nuevaIniciativa)) {
            XMLManager.writeXML(iniciativas, "iniciativas.xml");
            VistaConsola.mostrarMensaje("Iniciativa creada y guardada con éxito.");
        } else {
            VistaConsola.mostrarMensaje("La iniciativa ya existe.");
        }
    }

    public static void mostrarIniciativas(UsuarioCreador usuario) {
       ListaIniciativas iniciativas = usuario.getIniciativas();

        if (iniciativas.getIniciativas().isEmpty()) {
            VistaConsola.mostrarMensaje("No tienes iniciativas registradas.");
        } else {
            VistaConsola.mostrarMensaje("Tus iniciativas:");
            for (Iniciativa ini : iniciativas.getIniciativas()) {
                System.out.println("- " + ini.getNombre() + ": " + ini.getDescripcion());
            }
        }
    }


}
