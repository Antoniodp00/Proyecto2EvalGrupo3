package view;

import model.Premio;
import utilidades.Utilidades;

import java.util.Scanner;

public class VistaPremios {
    static Scanner sc = new Scanner(System.in);

    public static Premio pidePremio () {
        VistaConsola.mostrarMensaje("Nombre del Premio: ");
        String nombre=sc.nextLine();
       int costo = Utilidades.leeEntero("Ingrese el costo del Premio(Puntos): ");
        VistaConsola.mostrarMensaje("Ingrese una descripcion: ");
        String descripcion=sc.nextLine();

        Premio premio = new Premio(nombre, costo, descripcion);
        return premio;
    }
}
