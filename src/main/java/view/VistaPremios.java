package view;

import model.Premio;
import utilidades.Utilidades;

import java.util.Scanner;

public class VistaPremios {
    static Scanner sc = new Scanner(System.in);

    public static Premio pidePremio() {
        Premio premioAux = null;
        VistaConsola.mostrarMensaje("Ingrese nombre del premio: ");
        String nombre = sc.nextLine();
        int costo = Utilidades.leeEntero("Ingrese el costo del premio(Puntos):");
        VistaConsola.mostrarMensaje("Ingrese descripcion del premio: ");
        String descripcion = sc.nextLine();
        premioAux = new Premio(nombre, costo, descripcion);
        return premioAux;

    }
}
