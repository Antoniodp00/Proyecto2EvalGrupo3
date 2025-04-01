package view;

import model.Iniciativa;
import model.UsuarioCreador;

import java.util.Scanner;

public class VistaConsolaIniciativa {
    static Scanner sc = new Scanner(System.in);

    /**
     * Solicita al usuario los datos necesarios para crear una nueva iniciativa.
     *
     * @param creador El usuario que crea la iniciativa.
     * @return Un objeto de tipo Iniciativa con los datos ingresados.
     */
    public static Iniciativa pideIniciativa(UsuarioCreador creador) {
        // Definir códigos ANSI para colores
        final String RESET = "\u001B[0m";
        final String CYAN = "\u001B[36m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";

        System.out.println(CYAN + "\n=========================" + RESET);
        System.out.println(GREEN + "   CREACIÓN DE INICIATIVA" + RESET);
        System.out.println(CYAN + "=========================" + RESET);

        System.out.print(YELLOW + "Ingrese el nombre de la iniciativa: " + RESET);
        String nombreIniciativa = sc.nextLine().trim();

        System.out.print(YELLOW + "Ingrese la descripción de la iniciativa: " + RESET);
        String descripcion = sc.nextLine().trim();

        String nombre = creador.getNombreUsuario();
        System.out.println(GREEN + "Creador de la iniciativa: " + RESET + nombre);

        return new Iniciativa(nombreIniciativa, descripcion, nombre);
    }
}