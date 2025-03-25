package view;

import model.Usuario;
import model.UsuarioAdministrador;
import model.UsuarioCreador;
import model.UsuarioVoluntario;
import utilidades.Utilidades;

import java.util.HashMap;
import java.util.Scanner;

public class VistaConsolaLogin {
    static Scanner sc = new Scanner(System.in);

    public static HashMap<String, String> solicitarDatosLogin() {
        HashMap<String, String> datosLogin = new HashMap<>();

        VistaConsola.mostrarMensaje("Ingrese su nombre de usuario: ");
        String usuario = sc.nextLine();
        VistaConsola.mostrarMensaje("Ingrese su password: ");
        String password = sc.nextLine();

        datosLogin.put("usuario", usuario);
        datosLogin.put("password", password);

        return datosLogin;
    }

    public static void mostrarMensajeBienvenida(Usuario usuario) {
        if (usuario instanceof UsuarioVoluntario) {
            VistaConsola.mostrarMensaje("👤 Bienvenido Voluntario: " + usuario.getNombreUsuario());
        } else if (usuario instanceof UsuarioCreador) {
            VistaConsola.mostrarMensaje("🏗️ Bienvenido Creador: " + usuario.getNombreUsuario());
        } else if (usuario instanceof UsuarioAdministrador) {
            VistaConsola.mostrarMensaje("🛠️ Bienvenido Administrador: " + usuario.getNombreUsuario());
        }
    }

}
