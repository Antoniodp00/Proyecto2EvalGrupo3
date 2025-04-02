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

    // Códigos ANSI para colores
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String BOLD = "\u001B[1m";


    /**
     * Método para solicitar los datos de registro de un usuario.
     * Dependiendo del tipo ingresado, crea un UsuarioCreador, UsuarioVoluntario o UsuarioAdministrador.
     *
     * @param tipo El tipo de usuario a registrar (1 = Creador, 2 = Voluntario, 3 = Administrador).
     * @return Un objeto de tipo Usuario con los datos ingresados por el usuario.
     */
    public static Usuario solicitarDatosRegistro(int tipo) {
        Usuario usuario = null;

        System.out.println(BOLD + CYAN + "\n⚡ REGISTRO DE USUARIO ⚡" + RESET);
        System.out.println(YELLOW + "Por favor, ingrese los siguientes datos:" + RESET);

        System.out.print(BOLD + GREEN + "📝 Nombre: " + RESET);
        String nombre = sc.nextLine();

        System.out.print(BOLD + GREEN + "👤 Nombre de usuario: " + RESET);
        String nombreUsuario = sc.nextLine();

        String email = pideEmail();

        System.out.print(BOLD + GREEN + "🔒 Contraseña: " + RESET);
        String pass = sc.nextLine();
        String passCifrada = HashUtil.hashPassword(pass);

        switch (tipo) {
            case 1:
                System.out.print(BOLD + GREEN + "🏢 Nombre de la ONG: " + RESET);
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
                System.out.println(RED + "⚠️ Opción no válida. Inténtelo de nuevo." + RESET);
                break;
        }
        return usuario;
    }



    /**
     * Método para solicitar y validar un correo electrónico ingresado por el usuario.
     * Si el formato no es válido, se solicita nuevamente hasta que sea correcto.
     *
     * @return Un string con el email validado.
     */
    public static String pideEmail() {
        String email = "";
        boolean valida = false;

        do {
            System.out.print(BOLD + BLUE + "📧 Introduce el email: " + RESET);
            email = sc.nextLine();
            try {
                if (!Utilidades.validarEmail(email)) {
                    throw new EmailInvalidoException(RED + "❌ Formato de correo incorrecto. Inténtelo de nuevo." + RESET);
                }
                valida = true;
            } catch (EmailInvalidoException e) {
                System.out.println(e.getMessage());
            }
        } while (!valida);

        return email;
    }
}





