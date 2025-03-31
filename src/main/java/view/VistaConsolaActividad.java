package view;

import model.Actividad;
import java.time.LocalDate;
import java.util.Scanner;

public class VistaConsolaActividad {
    static Scanner sc = new Scanner(System.in);

    public static Actividad pideActividad() {
        Actividad actividad = null;

        // Solicitar solo nombre, descripción e iniciativa
        System.out.println("Ingrese el nombre de la actividad:");
        String nombre = sc.nextLine();

        System.out.println("Ingrese la descripción:");
        String descripcion = sc.nextLine();

        System.out.println("Ingrese el nombre de la iniciativa a la que pertenece:");
        String iniciativa = sc.nextLine();

        // Crear la actividad solo con nombre, descripción e iniciativa
        actividad = new Actividad(nombre, descripcion, iniciativa);

        // Opcionalmente, puedes solicitar los valores restantes para configurarlos después:
        System.out.println("¿Desea asignar un responsable? (si/no):");
        if (sc.nextLine().equalsIgnoreCase("si")) {
            System.out.println("Ingrese el nombre del responsable:");
            actividad.setResponsable(sc.nextLine());
        }

        System.out.println("¿Desea asignar una fecha de inicio? (si/no):");
        if (sc.nextLine().equalsIgnoreCase("si")) {
            System.out.println("Ingrese la fecha de inicio (YYYY-MM-DD):");
            actividad.setFechaInicio(LocalDate.parse(sc.nextLine()));
        }

        System.out.println("¿Desea asignar una fecha de fin? (si/no):");
        if (sc.nextLine().equalsIgnoreCase("si")) {
            System.out.println("Ingrese la fecha de fin (YYYY-MM-DD):");
            actividad.setFechaFin(LocalDate.parse(sc.nextLine()));
        }

        return actividad;
    }
}