package controller;

import model.*;
import utilidades.XMLManager;
import view.VistaConsola;
import view.VistaConsolaIniciativa;

import java.io.File;

public class CreadorController {
    private ListaIniciativas listaIniciativas;

    public CreadorController() {
        this.listaIniciativas = new ListaIniciativas();
    }

    /**
     * Carga las iniciativas creadas por un usuario específico desde el archivo XML.
     *
     * @param usuarioCreador Nombre del usuario creador de las iniciativas.
     * @return Lista de iniciativas que pertenecen al usuario.
     */
    public ListaIniciativas cargarIniciativasPorUsuario(UsuarioCreador usuarioCreador) {
        // Se cargan todas las iniciativas desde el archivo XML
        ListaIniciativas todasLasIniciativas = ListaIniciativas.cargarDesdeXML("iniciativas.xml");
        ListaIniciativas iniciativasUsuario = new ListaIniciativas();

        // Se filtran solo las iniciativas que pertenecen al usuario
        for (Iniciativa iniciativa : todasLasIniciativas.getIniciativas()) {
            if (iniciativa.getCreador().equals(usuarioCreador.getNombreUsuario())) {
                iniciativasUsuario.agregar(iniciativa);
                usuarioCreador.setIniciativas(iniciativasUsuario);
            }

        }
        return iniciativasUsuario;
    }

    /**
     * Crea una nueva iniciativa asociada a un usuario creador.
     * Guarda la iniciativa en el archivo XML.
     *
     * @param creador Usuario que crea la iniciativa.
     */
    public void crearIniciativa(UsuarioCreador creador) {
        // Se verifica si el archivo XML de iniciativas existe y tiene contenido
        File xmlFile = new File("iniciativas.xml");
        if (xmlFile.exists() && xmlFile.length() > 0) {
            try {
                listaIniciativas = ListaIniciativas.cargarDesdeXML("iniciativas.xml");
            } catch (Exception e) {
                VistaConsola.mostrarMensaje("Error al leer el archivo de iniciativas: " + e.getMessage());
                return;
            }
        } else {
            listaIniciativas = new ListaIniciativas();
        }

        // Se solicita la información de la nueva iniciativa
        Iniciativa nuevaIniciativa = VistaConsolaIniciativa.pideIniciativa(creador);

        // Se agrega la iniciativa si no existe ya en la lista
        if (listaIniciativas.agregar(nuevaIniciativa)) {
            XMLManager.writeXML(listaIniciativas, "iniciativas.xml");
            VistaConsola.mostrarMensaje("Iniciativa creada y guardada con éxito.");
        } else {
            VistaConsola.mostrarMensaje("La iniciativa ya existe.");
        }
    }

    /**
     * Muestra todas las iniciativas creadas por un usuario.
     *
     * @param usuario Usuario creador de las iniciativas.
     */
    public void mostrarIniciativas(UsuarioCreador usuario) {
        ListaIniciativas iniciativas = cargarIniciativasPorUsuario(usuario);

        // Si el usuario no tiene iniciativas, se informa en consola
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
