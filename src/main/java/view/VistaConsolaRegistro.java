package view;

import controller.UsuarioController;
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

        mostrarMensaje("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        mostrarMensaje("Ingrese su nombre de usuario: ");
        String nombreUsuario = sc.nextLine();
        String email = pideEmail();
        mostrarMensaje("Ingrese su contraseña");
        String pass = sc.nextLine();
        String passCifrada = Utilidades.cifrarSHA256(pass);
        mostrarMensaje("Ingrese su ROL");
        String rol = sc.nextLine();
        if (rol.equals("Creador")) {
            mostrarMensaje("Ingrese el nombre de la ONG: ");
            String nombreONG = sc.nextLine();
            usuario = new UsuarioCreador(nombre, nombreUsuario, email, passCifrada, nombreONG);
        } else if (rol.equals("Voluntario")) {
            usuario = new UsuarioVoluntario(nombre, nombreUsuario, email, passCifrada);
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
                    throw new EmailInvalidoException("Formato de correo incorrecto, prueba de nuevo.");
                }
                valida = true;
            } catch (EmailInvalidoException e) {
                System.out.println(e.getMessage());
            }

        } while (!valida);
        return email;
    }

    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }


}


