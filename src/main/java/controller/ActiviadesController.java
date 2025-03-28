package controller;

import model.Actividad;
import model.UsuarioCreador;
import model.UsuarioVoluntario;

public class ActiviadesController {
    public static boolean asignarVoluntario(UsuarioCreador creador, UsuarioVoluntario voluntario, Actividad actividad) {
        if (voluntario.estaEnActividad(actividad)) {
            System.out.println("El voluntario ya está asignado a esta actividad.");
            return false;
        }
        voluntario.agregarActividad(actividad);
        creador.asignarVoluntario(voluntario, actividad);
        return true;
    }
}