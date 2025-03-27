package view;

import model.Actividad;

import java.time.LocalDate;
import java.util.Scanner;

    public class VistaConsolaActividad {
        static Scanner sc = new Scanner(System.in);
        public static Actividad pideActividad() {
         Actividad actividad = null;

            System.out.println("Ingrese el nombre de la actividad:");
            String nombre = sc.nextLine();

            System.out.println("Ingrese la descripción:");
            String descripcion = sc.nextLine();

            System.out.println("Ingrese el responsable:");
            String responsable = sc.nextLine();

            System.out.println("Ingrese el nombre de la iniciativa a la que pertenece:");
            String iniciativa = sc.nextLine();

            System.out.println("Ingrese la fecha de inicio (YYYY-MM-DD):");
            LocalDate fechaInicio = LocalDate.parse(sc.nextLine());

            System.out.println("Ingrese la fecha de fin (YYYY-MM-DD):");
            LocalDate fechaFin = LocalDate.parse(sc.nextLine());

            actividad = new Actividad(nombre,descripcion,responsable,iniciativa,fechaInicio,fechaFin);

          return  actividad;
        }
    }


