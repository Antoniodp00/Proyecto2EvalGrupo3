package view;

import exceptions.EmailInvalidoException;
import model.Usuario;
import model.UsuarioCreador;
import model.UsuarioVoluntario;
import utilidades.Utilidades;


import java.util.Scanner;

public class VistaConsolaRegistro {
    static Scanner sc = new Scanner(System.in);

    public static Usuario solicitarDatosRegistro() {
        Usuario usuario = null;

        System.out.println("Ingrese su nombre: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese el nombre de usuario que desea registrar: ");
        String NombreUsuario = sc.nextLine();

        String email = pideEmail();

        String pass = sc.nextLine();

        //Pedir ONG si el usuario es creador si dice no queda como cadena vacia
        System.out.println("¿Es un usuario creador? (S/N): ");
        String respuesta = sc.nextLine().trim().toUpperCase();

        String ong = "";
        if (respuesta.equals("S")) {
            System.out.println("Ingrese el nombre de la ONG: ");
            ong = sc.nextLine();
            usuario = new UsuarioCreador(nombre, NombreUsuario, email, pass, ong);
        } else if (respuesta.equals("N")) {
            usuario = new UsuarioVoluntario(nombre, NombreUsuario, email, pass);
        }
        return usuario;
    }

    public static String pideEmail() {
        String email = "";
        boolean valida = false;

        do {
            System.out.print("Introduce el email: ");
            email = sc.nextLine();
            try {
                if (!Utilidades.validarEmail(email)) {
                    throw new EmailInvalidoException("Formato de mail incorrecto, prueba de nuevo.");
                }
                valida = true;  // Si no hay excepción, la matrícula es válida
            } catch (EmailInvalidoException e) {
                System.out.println(e.getMessage());
            }

        } while (!valida);
        return email;
    }

}
