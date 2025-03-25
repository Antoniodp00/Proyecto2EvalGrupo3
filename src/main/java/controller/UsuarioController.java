package controller;

import model.*;
import utilidades.HashUtil;
import utilidades.XMLManager;
import view.Menus;
import view.VistaConsola;
import view.VistaConsolaLogin;
import view.VistaConsolaRegistro;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class UsuarioController {

    public static boolean registrarUsuario() {
        boolean registrado = false;
        int tipo = Menus.menuSelectTipoUsuarioRegistro();
        Usuario usuario = VistaConsolaRegistro.solicitarDatosRegistro(tipo);
        String archivo = determinarArchivoXML(usuario);

        Set<Usuario> usuarios = cargarUsuarios(archivo);

        if (usuarios.add(usuario)) {
            XMLManager.writeXML(usuario, archivo);
            VistaConsola.mostrarMensaje("Registro guardado con éxito en " + archivo);
            registrado = true;
        } else {
            VistaConsola.mostrarMensaje("El usuario ya existe en " + archivo);
        }

        return registrado;
    }

    private static Set<Usuario> cargarUsuarios(String archivo) {
        Set<Usuario> usuarios = new HashSet<>();
        File file = new File(archivo);

        if (file.exists() && file.length() > 0) {
            usuarios = XMLManager.readXML(usuarios,archivo);
        }

        return usuarios;
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
        Usuario usuarioLogueado = null;

        if (Sesion.haySesionActiva()) {
            VistaConsola.mostrarMensaje("Ya hay una sesión activa. Cierra sesión primero.");
        } else {
            int tipo = Menus.menuIniciarSesion();
            HashMap<String, String> datosLogin = VistaConsolaLogin.solicitarDatosLogin();
            usuarioLogueado = buscarUsuarioPorRol(datosLogin, tipo);

            if (usuarioLogueado != null) {
                Sesion.iniciarSesion(usuarioLogueado);
                VistaConsolaLogin.mostrarMensajeBienvenida(usuarioLogueado);
            } else {
                VistaConsola.mostrarMensaje("Usuario o contraseña incorrectos.");
            }
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
        Usuario usuarioEncontrado = null;
        Set<Usuario> usuarios = cargarUsuarios(archivo);

        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(datosLogin.get("usuario")) &&
                    HashUtil.verificarPassword(usuario.getPassword(), datosLogin.get("password"))) {
                usuarioEncontrado = usuario;
            }
        }

        return usuarioEncontrado;
    }


}
