package controller;

import model.Premio;
import model.ListaPremios;
import model.UsuarioVoluntario;
import view.VistaPremios;

import java.util.Set;

public class PremiosController {
    private static final String ARCHIVO_PREMIOS = "premios.xml";
    private ListaPremios listaPremios;

    // Constructor: carga la lista de premios desde el archivo XML al iniciar el controlador.
    public PremiosController() {
        this.listaPremios = ListaPremios.cargarDesdeXML(ARCHIVO_PREMIOS);
    }

    /**
     * Método para agregar un nuevo premio a la lista de premios.
     * Solo los administradores pueden agregar premios.
     */
    public void agregarPremio() {
        // Se solicita la información del premio desde la vista
        Premio nuevoPremio = VistaPremios.pidePremio();
        // Se agrega el premio a la lista
        listaPremios.agregar(nuevoPremio);
        // Se guarda la lista actualizada en el archivo XML
        guardarPremios();
        System.out.println("Premio agregado correctamente.");
    }


    /**
     * Método que permite a un usuario voluntario canjear un premio si tiene suficientes puntos.
     *
     * @param usuario      Usuario voluntario que quiere canjear un premio.
     * @param nombrePremio Nombre del premio a canjear.
     * @return true si el canje fue exitoso, false si no fue posible.
     */
    public boolean canjearPremio(UsuarioVoluntario usuario, String nombrePremio) {
        // Se busca el premio en la lista
        Premio premio = listaPremios.buscar(nombrePremio);

        // Si el premio no existe, se notifica al usuario y se retorna false
        if (premio == null) {
            System.out.println("Premio no encontrado.");
            return false;
        }

        // Si el usuario no tiene suficientes puntos, se notifica y se retorna false
        if (usuario.getPuntos() < premio.getCosto()) {
            System.out.println("No tienes suficientes puntos para canjear este premio.");
            return false;
        }


        // Se descuentan los puntos al usuario y se guardan los cambios
        usuario.restarPuntos(premio.getCosto());
        usuario.guardarEnXML();  // Guardar cambios del usuario
        System.out.println("Canje exitoso: " + premio.getNombre());
        return true;
    }

    /**
     * Método para listar todos los premios disponibles.
     * Muestra los premios junto con su costo en puntos.
     */
    public void listarPremios() {
        // Se obtiene la lista de premios
        Set<Premio> premios = listaPremios.getPremios();

        // Si no hay premios disponibles, se informa al usuario
        if (premios.isEmpty()) {
            System.out.println("No hay premios disponibles.");
            return;
        }

        // Se muestra la lista de premios disponibles
        System.out.println("Premios disponibles:");
        for (Premio premio : premios) {
            System.out.println("- " + premio.getNombre() + " (" + premio.getCosto() + " puntos)");
        }
    }

    /**
     * Método privado para guardar los cambios en la lista de premios en el archivo XML.
     */
    private void guardarPremios() {
        listaPremios.guardarXML(ARCHIVO_PREMIOS);
    }
}
