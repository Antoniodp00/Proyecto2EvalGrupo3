package view;

import model.Iniciativa;
import model.UsuarioCreador;

import java.util.Scanner;

public class VistaConsolaIniciativa {
    static Scanner sc = new Scanner(System.in);
    public static Iniciativa pideIniciativa(UsuarioCreador creador){
        VistaConsola.mostrarMensaje("Ingrese el nombre de la iniciativa");
        String nombreIniciativa = sc.nextLine();
        VistaConsola.mostrarMensaje("Ingrese la descripcion de la iniciativa");
        String descripcion = sc.nextLine();
        String nombre = creador.getNombreUsuario();
        System.out.println("Creador de la iniciativa: " + nombre);
        Iniciativa iniciativa = new Iniciativa(nombreIniciativa,descripcion,nombre);

        return iniciativa;
    }
}
