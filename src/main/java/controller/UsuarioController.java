package controller;

import model.*;
import utilidades.HashUtil;
import utilidades.Utilidades;
import view.Menus;
import view.VistaConsola;
import view.VistaConsolaLogin;
import view.VistaConsolaRegistro;

import java.util.HashMap;
import java.util.Scanner;

public class UsuarioController {

    public static boolean registrarUsuario() {
        int tipo = Menus.menuSelectTipoUsuarioRegistro();
        Usuario usuario = VistaConsolaRegistro.solicitarDatosRegistro(tipo);
        String archivo = determinarArchivoXML(usuario);

        ListaUsuarios listaUsuarios = ListaUsuarios.cargarDesdeXML(archivo);

        if (listaUsuarios.buscar(usuario.getNombreUsuario()) == null) {
            listaUsuarios.agregar(usuario);
            listaUsuarios.guardarXML(archivo);
            VistaConsola.mostrarMensaje("Registro guardado en " + archivo);
            return true;
        } else {
            VistaConsola.mostrarMensaje("El usuario ya existe en " + archivo);
            return false;
        }
    }

    private static String determinarArchivoXML(Usuario usuario) {
        String archivo = "";
        if (usuario instanceof UsuarioVoluntario) {
            archivo = "voluntarios.xml";
        }
        if (usuario instanceof UsuarioCreador) {
            archivo = "creadores.xml";
        }
        if (usuario instanceof UsuarioAdministrador) {
            archivo = "administradores.xml";
        }
        return archivo;
    }

    public static Usuario iniciarSesion() {
        if (Sesion.haySesionActiva()) {
            VistaConsola.mostrarMensaje("Ya hay una sesión activa. Cierra sesión primero.");
            return null;
        }

        int tipo = Menus.menuIniciarSesion();
        HashMap<String, String> datosLogin = VistaConsolaLogin.solicitarDatosLogin();
        Usuario usuarioLogueado = buscarUsuarioPorRol(datosLogin, tipo);

        if (usuarioLogueado != null) {
            Sesion.iniciarSesion(usuarioLogueado);
            VistaConsolaLogin.mostrarMensajeBienvenida(usuarioLogueado);
        } else {
            VistaConsola.mostrarMensaje("Usuario no encontrado.");
        }

        return usuarioLogueado;
    }

    private static Usuario buscarUsuarioPorRol(HashMap<String, String> datosLogin, int opcion) {
        Usuario usuario = null;

        switch (opcion) {
            case 1:
                usuario = buscarEnArchivo(datosLogin, "creadores.xml");
                break;
            case 2:
                usuario = buscarEnArchivo(datosLogin, "voluntarios.xml");
                break;
            case 3:
                usuario = buscarEnArchivo(datosLogin, "administradores.xml");
                break;
            default:
                VistaConsola.mostrarMensaje("Opcion incorrecta");
                break;
        }
        return usuario;
    }

    private static Usuario buscarEnArchivo(HashMap<String, String> datosLogin, String archivo) {
        ListaUsuarios listaUsuarios = ListaUsuarios.cargarDesdeXML(archivo);
        Usuario usuario = listaUsuarios.buscar(datosLogin.get("usuario"));

        if (usuario != null && HashUtil.verificarPassword(datosLogin.get("password"), usuario.getPassword())) {
            return usuario;
        }

        return null;
    }
    public static void eliminarUsuario() {
        Scanner scanner = new Scanner(System.in);
        String nombreUsuario = Utilidades.leeString("Introduce el nombre del usuario a eliminar:");
        Usuario usuario = UsuarioController.buscarEnArchivo(nombreUsuario);

        if (usuario != null) {
            UsuarioController.eliminarUsuario();
            System.out.println("Usuario eliminado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
    public static void actualizarUsuario() {
        Scanner scanner = new Scanner(System.in);
        String nombreUsuario = Utilidades.leeString("Introduce el nombre del usuario a actualizar:");
        Usuario usuario = UsuarioController.buscarEnArchivo(nombreUsuario);

        if (usuario != null) {
            String nuevoNombre = Utilidades.leeString("Introduce el nuevo nombre del usuario:");
            String nuevaContraseña = Utilidades.leeString("Introduce la nueva contraseña:");
            usuario.setNombre(nuevoNombre);
            usuario.setContraseña(nuevaContraseña);
            UsuarioController.guardarUsuarios();
            System.out.println("Usuario actualizado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}
