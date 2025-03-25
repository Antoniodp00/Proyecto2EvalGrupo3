package view;

import controller.UsuarioController;
import exceptions.EmailInvalidoException;
import model.Usuario;
import model.UsuarioAdministrador;
import model.UsuarioCreador;
import model.UsuarioVoluntario;
import utilidades.HashUtil;
import utilidades.Utilidades;


import java.util.Scanner;

public class VistaConsolaRegistro {
    static Scanner sc = new Scanner(System.in);

    public static Usuario solicitarDatosRegistro(int tipo) {
        Usuario usuario = null;

        VistaConsola.mostrarMensaje("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        VistaConsola.mostrarMensaje("Ingrese su nombre de usuario: ");
        String nombreUsuario = sc.nextLine();
        String email = pideEmail();
        VistaConsola.mostrarMensaje("Ingrese su contraseña");
        String pass = sc.nextLine();
        String passCifrada = HashUtil.hashPassword(pass);

        switch (tipo) {
            case 1:
                VistaConsola.mostrarMensaje("Ingrese su ONG: ");
                String ONG = sc.nextLine();
                usuario = new UsuarioCreador(nombre, nombreUsuario, email, passCifrada, ONG);
                break;
            case 2:
                usuario = new UsuarioVoluntario(nombre, nombreUsuario, email, passCifrada);
                break;
            case 3:
                usuario = new UsuarioAdministrador(nombre, nombreUsuario, email, passCifrada);
                break;
            default:
                VistaConsola.mostrarMensaje("Ingrese una opcion valida: ");
                break;
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

}


