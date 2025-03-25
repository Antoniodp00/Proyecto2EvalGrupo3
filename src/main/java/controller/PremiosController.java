package controller;

import model.Premio;
import model.UsuarioVoluntario;
import view.VistaConsola;
import view.VistaPremios;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PremiosController {

    public static void agregarPremio() {
        Set <Premio> premios = new HashSet<>();
        Premio premio = VistaPremios.pidePremio();

        if (premio != null) {
            VistaConsola.mostrarMensaje("Premio agregado con éxito: " + premio);
        } else {
            VistaConsola.mostrarMensaje("Error: No se pudo agregar el premio.");
        }
    }

    public static boolean canjearPremio(UsuarioVoluntario voluntario, Premio premio) {
        boolean canjear ;
        if (voluntario.getPuntos() >= premio.getCosto()) {
            voluntario.setPuntos(voluntario.getPuntos() - premio.getCosto());
            VistaConsola.mostrarMensaje("¡Canje exitoso! " + voluntario.getNombre() + " ha canjeado el premio: " + premio.getNombre());
            canjear = true;
        } else {
            VistaConsola.mostrarMensaje("No tienes suficientes puntos para canjear este premio.");
            canjear= false;
        }
        return canjear;
    }

}
