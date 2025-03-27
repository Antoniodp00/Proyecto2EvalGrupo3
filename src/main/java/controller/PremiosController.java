package controller;

import model.Premio;
import model.ListaPremios;
import model.Usuario;
import model.UsuarioVoluntario;
import utilidades.XMLManager;
import view.VistaPremios;

import java.util.List;
import java.util.Set;

public class PremiosController {
    private static final String ARCHIVO_PREMIOS = "premios.xml";

    // Método para que un administrador agregue un premio
    public static void agregarPremio() {
        ListaPremios listaPremios = ListaPremios.cargarDesdeXML(ARCHIVO_PREMIOS);

        Premio nuevoPremio = VistaPremios.pidePremio();
        listaPremios.agregar(nuevoPremio);

        listaPremios.guardarXML(ARCHIVO_PREMIOS);
        System.out.println("✅ Premio agregado correctamente.");
    }

    // Método para que un usuario voluntario canjee un premio
    public static boolean canjearPremio(UsuarioVoluntario usuario, String nombrePremio) {
        ListaPremios listaPremios = ListaPremios.cargarDesdeXML(ARCHIVO_PREMIOS);
        Premio premio = listaPremios.buscar(nombrePremio);

        if (premio == null) {
            System.out.println("❌ Premio no encontrado.");
            return false;
        }

        if (usuario.getPuntos() < premio.getCosto()) {
            System.out.println("❌ No tienes suficientes puntos para canjear este premio.");
            return false;
        }

        // Restar los puntos al usuario y confirmar el canje
        usuario.restarPuntos(premio.getCosto());
        usuario.guardarEnXML();  // Guardar cambios del usuario
        System.out.println("🎉 Canje exitoso: " + premio.getNombre());

        return true;
    }

    // Método para listar premios disponibles
    public static void listarPremios() {
        ListaPremios listaPremios = ListaPremios.cargarDesdeXML(ARCHIVO_PREMIOS);
        Set<Premio> premios = listaPremios.getPremios();

        if (premios.isEmpty()) {
            System.out.println("❌ No hay premios disponibles.");
            return;
        }

        System.out.println("🎁 Premios disponibles:");
        for (Premio premio : premios) {
            System.out.println("- " + premio.getNombre() + " (" + premio.getCosto() + " puntos)");
        }
    }
}
