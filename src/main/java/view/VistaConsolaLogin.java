package view;

import model.Usuario;
import model.UsuarioAdministrador;
import model.UsuarioCreador;
import model.UsuarioVoluntario;
import java.util.HashMap;
import java.util.Scanner;

public class VistaConsolaLogin {
    static Scanner sc = new Scanner(System.in);

    // Códigos ANSI para colores
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";


    /**
     * Solicita los datos de inicio de sesión al usuario.
     * Se le pedirá que ingrese su nombre de usuario y su contraseña.
     *
     * @return Un HashMap con las credenciales ingresadas (usuario y contraseña).
     */
    public static HashMap<String, String> solicitarDatosLogin() {
        HashMap<String, String> datosLogin = new HashMap<>();

        System.out.println(CYAN + "\n🔐 Iniciar Sesión" + RESET);
        System.out.print(GREEN + "👤 Ingrese su nombre de usuario: " + RESET);
        String usuario = sc.nextLine();
        System.out.print(GREEN + "🔑 Ingrese su contraseña: " + RESET);
        String password = sc.nextLine();

        datosLogin.put("usuario", usuario);
        datosLogin.put("password", password);

        return datosLogin;
    }

    /**
     * Muestra un mensaje de bienvenida personalizado según el tipo de usuario que ha iniciado sesión.
     *
     * @param usuario Objeto de la clase Usuario que representa al usuario autenticado.
     */
    public static void mostrarMensajeBienvenida(Usuario usuario) {
        System.out.println();
        if (usuario instanceof UsuarioVoluntario) {
            System.out.println(YELLOW + "👤 Bienvenido Voluntario: " + usuario.getNombreUsuario() + RESET);
        } else if (usuario instanceof UsuarioCreador) {
            System.out.println(GREEN + "🏗️ Bienvenido Creador: " + usuario.getNombreUsuario() + RESET);
        } else if (usuario instanceof UsuarioAdministrador) {
            System.out.println(RED + "🛠️ Bienvenido Administrador: " + usuario.getNombreUsuario() + RESET);
        }
    }
}
