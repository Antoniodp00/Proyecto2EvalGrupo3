package controller;

import exceptions.PuntosInsuficientesException;
import model.*;
import utilidades.Utilidades;
import view.VistaConsola;
import view.VistaPremios;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PremiosController {
    private final String ARCHIVO_PREMIOS = "premios.xml";
    private ListaPremios listaPremios;

    // Constructor que carga los premios desde el archivo XML
    public PremiosController() {
        this.listaPremios = ListaPremios.cargarDesdeXML(ARCHIVO_PREMIOS);
    }

    /**
     * Método para que un administrador agregue un premio.
     */
    public void agregarPremio() {
        Premio nuevoPremio = VistaPremios.pidePremio();
        listaPremios.agregar(nuevoPremio);
        listaPremios.guardarXML(ARCHIVO_PREMIOS);
        VistaConsola.mostrarMensaje("✅ Premio agregado correctamente.");
    }

    /**
     * Método para que un usuario voluntario canjee un premio.
     *
     * @param usuario      Usuario voluntario que quiere canjear un premio.
     * @param nombrePremio Nombre del premio a canjear.
     * @return true si el canje fue exitoso, false en caso contrario.
     */
    public boolean canjearPremio(UsuarioVoluntario usuario, String nombrePremio) {
        ListaUsuarios listaUsuarios = ListaUsuarios.cargarDesdeXML("voluntarios.xml");
        Premio premio = listaPremios.buscar(nombrePremio);
        boolean canjeado = false;

        if (premio != null && usuario.getPuntos() >= premio.getCosto()) {
            usuario.restarPuntos(premio.getCosto());
            listaUsuarios.actualizar(usuario);
            listaUsuarios.guardarXML("voluntarios.xml");
            VistaConsola.mostrarMensaje("🎉 Canje exitoso: " + premio.getNombre());
            canjeado = true;
        } else if (premio == null) {
            VistaConsola.mostrarMensaje("❌ Premio no encontrado.");
        } else {
            throw new PuntosInsuficientesException("❌ No tienes suficientes puntos para canjear este premio.");
        }

        return canjeado;
    }

    /**
     * Método para listar todos los premios disponibles.
     */
    public void listarPremios() {
        Set<Premio> premios = listaPremios.getPremios();

        if (premios.isEmpty()) {
            VistaConsola.mostrarMensaje("❌ No hay premios disponibles.");
            return;
        }

        VistaConsola.mostrarMensaje("🎁 Premios disponibles:");
        for (Premio premio : premios) {
            VistaConsola.mostrarMensaje("- " + premio.getNombre() + " (" + premio.getCosto() + " puntos)");
        }
    }

    /**
     * Método para eliminar un premio por su nombre.
     */
    public void eliminarPremio() {
        listaPremios = ListaPremios.cargarDesdeXML(ARCHIVO_PREMIOS);
        String nombrePremio = Utilidades.leeString("Introduce el nombre del premio a eliminar:");
        Premio premio = listaPremios.buscar(nombrePremio);

        if (premio != null) {
            listaPremios.eliminar(premio);
            listaPremios.guardarXML(ARCHIVO_PREMIOS);
            VistaConsola.mostrarMensaje("✅ Premio eliminado exitosamente.");
        } else {
            VistaConsola.mostrarMensaje("❌ Premio no encontrado.");
        }
    }
}