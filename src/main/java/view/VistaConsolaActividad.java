package view;

import model.Actividad;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class VistaConsolaActividad {
    static Scanner sc = new Scanner(System.in);

    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";


    /**
     * Solicita al usuario los datos necesarios para crear una nueva actividad.
     *
     * @return Un objeto de tipo Actividad con los datos ingresados.
     */
    public static Actividad pideActividad() {
        Actividad actividad;

        System.out.println(BLUE + "\n══════════════════════════════════" + RESET);
        System.out.println(GREEN + "     ✨ CREACIÓN DE ACTIVIDAD ✨" + RESET);
        System.out.println(BLUE + "══════════════════════════════════\n" + RESET);

        System.out.print(YELLOW + "Ingrese el nombre de la actividad: " + RESET);
        String nombre = sc.nextLine();

        System.out.print(YELLOW + "Ingrese la descripción: " + RESET);
        String descripcion = sc.nextLine();

        System.out.print(YELLOW + "Ingrese el nombre de la iniciativa a la que pertenece: " + RESET);
        String iniciativa = sc.nextLine();

        actividad = new Actividad(nombre, descripcion, iniciativa);

        if (preguntar("¿Desea asignar un responsable?")) {
            System.out.print(YELLOW + "Ingrese el nombre del responsable: " + RESET);
            actividad.setResponsable(sc.nextLine());
        }

        if (preguntar("¿Desea asignar una fecha de inicio?")) {
            actividad.setFechaInicio(pedirFecha("Ingrese la fecha de inicio (YYYY-MM-DD): "));
        }

        if (preguntar("¿Desea asignar una fecha de fin?")) {
            actividad.setFechaFin(pedirFecha("Ingrese la fecha de fin (YYYY-MM-DD): "));
        }

        return actividad;
    }

    /**
     * Pregunta al usuario una confirmación de tipo sí/no.
     *
     * @param mensaje Mensaje que se mostrará al usuario.
     * @return true si la respuesta es "si" o "sí", false en caso contrario.
     */
    private static boolean preguntar(String mensaje) {
        System.out.print(YELLOW + mensaje + " (si/no): " + RESET);
        String respuesta = sc.nextLine().trim().toLowerCase();
        return respuesta.equals("si") || respuesta.equals("sí");
    }

    /**
     * Solicita al usuario una fecha en formato "YYYY-MM-DD" y la convierte en un objeto LocalDate.
     * Si el formato ingresado es incorrecto, solicita nuevamente la fecha hasta que sea válida.
     *
     * @param mensaje Mensaje que se mostrará al usuario para solicitar la fecha.
     * @return La fecha ingresada como un objeto LocalDate.
     */
    private static LocalDate pedirFecha(String mensaje) {
        while (true) {
            try {
                System.out.print(YELLOW + mensaje + RESET);
                return LocalDate.parse(sc.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("\u001B[31m❌ Formato incorrecto. Intente nuevamente.\u001B[0m");
            }
        }
    }
}
