package controller;

import model.*;
import utilidades.XMLManager;
import view.VistaConsola;
import view.VistaConsolaIniciativa;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreadorController {

    public static Set<Iniciativa> CargarIniciativas() {
        Set<Iniciativa> iniciativasAux = new HashSet<>();
        File file = new File("iniciativas.xml");

        if (file.exists() && file.length() > 0) {
            ListaIniciativas iniciativas = XMLManager.readXML(new ListaIniciativas(), "iniciativas.xml");
            if (iniciativas != null) {
                iniciativasAux = iniciativas.getIniciativas();
            }
        }
        return iniciativasAux;
    }

    public static Set<Iniciativa> cargarIniciativasPorUsuario(String nombreUsuario) {
        Set<Iniciativa> todasLasIniciativas = CargarIniciativas();
        Set<Iniciativa> iniciativasUsuario = new HashSet<>();

        for (Iniciativa iniciativa : todasLasIniciativas) {
            if (iniciativa.getCreador().equals(nombreUsuario)) { // Filtrar por creador
                iniciativasUsuario.add(iniciativa);
            }
        }
        return iniciativasUsuario;
    }


    public static void crearIniciativa(UsuarioCreador creador) {
        ListaIniciativas iniciativas;

        File xmlFile = new File("iniciativas.xml");
        if (xmlFile.exists() && xmlFile.length() > 0) {
            try {
                iniciativas = XMLManager.readXML(new ListaIniciativas(), "iniciativas.xml");
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
        Set<Iniciativa> iniciativas = usuario.getIniciativas();

        if (iniciativas.isEmpty()) {
            VistaConsola.mostrarMensaje("No tienes iniciativas registradas.");
        } else {
            VistaConsola.mostrarMensaje("Tus iniciativas:");
            for (Iniciativa ini : iniciativas) {
                System.out.println("- " + ini.getNombre() + ": " + ini.getDescripcion());
            }
        }
    }


}
