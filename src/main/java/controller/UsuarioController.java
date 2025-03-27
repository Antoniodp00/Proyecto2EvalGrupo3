package controller;

import model.*;
import utilidades.HashUtil;
import utilidades.XMLManager;
import view.Menus;
import view.VistaConsola;
import view.VistaConsolaLogin;
import view.VistaConsolaRegistro;

import java.util.HashMap;

public class UsuarioController {

    public static boolean registrarUsuario() {
        int tipo = Menus.menuSelectTipoUsuarioRegistro();
        Usuario usuario = VistaConsolaRegistro.solicitarDatosRegistro(tipo);
        String archivo = determinarArchivoXML(usuario);

        ListaUsuarios listaUsuarios = ListaUsuarios.cargarDesdeXML(archivo);

        // Verificar si ya existe el usuario
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
}
