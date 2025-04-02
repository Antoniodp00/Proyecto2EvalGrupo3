package controller;

import model.Iniciativa;
import model.ListaIniciativas;
import model.UsuarioCreador;
import utilidades.Utilidades;
import utilidades.XMLManager;
import view.VistaConsola;
import view.VistaConsolaIniciativa;

import java.io.File;

public class CreadorController {
    private static final String ARCHIVO_INICIATIVAS = "iniciativas.xml";
    private ListaIniciativas listaIniciativas;

    /**
     * Constructor: Carga las iniciativas desde el archivo XML al iniciar la clase.
     * Si el archivo no existe o está vacío, inicializa una nueva lista de iniciativas.
     */
    public CreadorController() {
        File archivo = new File(ARCHIVO_INICIATIVAS);
        if (archivo.exists() && archivo.length() > 0) {
            this.listaIniciativas = ListaIniciativas.cargarDesdeXML(ARCHIVO_INICIATIVAS);
        } else {
            this.listaIniciativas = new ListaIniciativas();
        }
    }

    /**
     * Método para que un usuario creador agregue una nueva iniciativa.
     * La iniciativa se almacena en la lista y se guarda en el archivo XML.
     *
     * @param creador Usuario que crea la iniciativa.
     */
    public void crearIniciativa(UsuarioCreador creador) {
        Iniciativa nuevaIniciativa = VistaConsolaIniciativa.pideIniciativa(creador);
        if (listaIniciativas.agregar(nuevaIniciativa)) {
            XMLManager.writeXML(listaIniciativas, ARCHIVO_INICIATIVAS);
            VistaConsola.mostrarMensaje("✅ Iniciativa creada y guardada con éxito.");
        } else {
            VistaConsola.mostrarMensaje("❌ La iniciativa ya existe.");
        }
    }

    /**
     * Muestra todas las iniciativas creadas por un usuario específico.
     * Si no tiene iniciativas registradas, se informa al usuario.
     *
     * @param usuario Usuario creador de las iniciativas.
     */
    public void mostrarIniciativas(UsuarioCreador usuario) {
        if (listaIniciativas.getIniciativas().isEmpty()) {
            VistaConsola.mostrarMensaje("❌ No tienes iniciativas registradas.");
        } else {
            VistaConsola.mostrarMensaje("📌 Tus iniciativas:");
            for (Iniciativa ini : listaIniciativas.getIniciativas()) {
                if (ini.getCreador().equals(usuario.getNombreUsuario())) {
                    VistaConsola.mostrarMensaje("- " + ini.getNombre() + ": " + ini.getDescripcion());
                }
            }
        }
    }

    /**
     * Permite actualizar el nombre y la descripción de una iniciativa existente.
     * La iniciativa debe existir en la lista para poder modificarla.
     *
     * @param creador Usuario creador que desea actualizar su iniciativa.
     */
    public void actualizarIniciativa(UsuarioCreador creador) {
        String nombreIniciativa = Utilidades.leeString("Introduce el nombre de la iniciativa a actualizar:");
        Iniciativa iniciativa = null;

        // Buscar la iniciativa en la lista
        for (Iniciativa ini : listaIniciativas.getIniciativas()) {
            if (ini.getNombre().equalsIgnoreCase(nombreIniciativa)&&ini.getCreador().equals(creador.getNombreUsuario())) {
                iniciativa = ini;
            }
        }

        if (iniciativa != null) {
            iniciativa.setNombre(Utilidades.leeString("Introduce el nuevo nombre de la iniciativa:"));
            iniciativa.setDescripcion(Utilidades.leeString("Introduce la nueva descripción:"));
            listaIniciativas.guardarXML(ARCHIVO_INICIATIVAS);
            VistaConsola.mostrarMensaje("✅ Iniciativa actualizada exitosamente.");
        } else {
            VistaConsola.mostrarMensaje("❌ Iniciativa no encontrada.");
        }
    }

    /**
     * Elimina una iniciativa de la lista si existe.
     * Se guarda la lista actualizada en el archivo XML.
     *
     * @param creador Usuario creador que desea eliminar una iniciativa.
     */
    public void eliminarIniciativa(UsuarioCreador creador) {
        String nombreIniciativa = Utilidades.leeString("Introduce el nombre de la iniciativa a eliminar:");
        Iniciativa iniciativa = null;

        // Buscar la iniciativa en la lista
        for (Iniciativa ini : listaIniciativas.getIniciativas()) {
            if (ini.getNombre().equalsIgnoreCase(nombreIniciativa)&&ini.getCreador().equals(creador.getNombreUsuario())) {
                iniciativa = ini;
                break;
            }
        }

        if (iniciativa != null) {
            listaIniciativas.getIniciativas().remove(iniciativa);
           listaIniciativas.guardarXML(ARCHIVO_INICIATIVAS);
            VistaConsola.mostrarMensaje("✅ Iniciativa eliminada exitosamente.");
        } else {
            VistaConsola.mostrarMensaje("❌ Iniciativa no encontrada.");
        }
    }
}
