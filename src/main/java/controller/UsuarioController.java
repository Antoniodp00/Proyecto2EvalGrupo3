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
    private ListaUsuarios listaUsuarios;

    public UsuarioController() {
        this.listaUsuarios = new ListaUsuarios();
    }

    /**
     * Método para registrar un nuevo usuario.
     * Se solicita al usuario los datos de registro y se guarda en el archivo XML correspondiente.
     *
     * @return true si el registro fue exitoso, false si el usuario ya existía.
     */
    public boolean registrarUsuario() {
        // Se solicita el tipo de usuario a registrar
        int tipo = Menus.menuSelectTipoUsuarioRegistro();

        // Se obtienen los datos del usuario desde la vista
        Usuario usuario = VistaConsolaRegistro.solicitarDatosRegistro(tipo);

        // Se determina en qué archivo XML se guardará el usuario
        String archivo = determinarArchivoXML(usuario);

        // Se carga la lista de usuarios desde el archivo XML
        listaUsuarios = ListaUsuarios.cargarDesdeXML(archivo);

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

    /**
     * Método que determina en qué archivo XML debe guardarse un usuario según su tipo.
     *
     * @param usuario Usuario a evaluar.
     * @return Nombre del archivo XML correspondiente.
     */
    private String determinarArchivoXML(Usuario usuario) {
        String archivo = "";
        if (usuario instanceof UsuarioVoluntario) {
            archivo = "voluntarios.xml";
        }
        else if (usuario instanceof UsuarioCreador) {
            archivo = "creadores.xml";
        }
         else if (usuario instanceof UsuarioAdministrador) {
            archivo = "administradores.xml";
        }
        return archivo;
    }

    /**
     * Método para iniciar sesión con un usuario.
     * Verifica si ya hay una sesión activa y, si no, busca al usuario en el archivo correspondiente.
     *
     * @return El usuario autenticado si el inicio de sesión fue exitoso, o null en caso contrario.
     */
    public Usuario iniciarSesion() {
        // Si ya hay una sesión activa, no permite iniciar otra
        if (Sesion.haySesionActiva()) {
            VistaConsola.mostrarMensaje("Ya hay una sesión activa. Cierra sesión primero.");
            return null;
        }

        // Se solicita el tipo de usuario y los datos de login
        int tipo = Menus.menuIniciarSesion();
        HashMap<String, String> datosLogin = VistaConsolaLogin.solicitarDatosLogin();

        // Se busca el usuario en el archivo correspondiente
        Usuario usuarioLogueado = buscarUsuarioPorRol(datosLogin, tipo);

        // Si el usuario fue encontrado, se inicia la sesión
        if (usuarioLogueado != null) {
            Sesion.iniciarSesion(usuarioLogueado);
            VistaConsolaLogin.mostrarMensajeBienvenida(usuarioLogueado);
        } else {
            VistaConsola.mostrarMensaje("Usuario no encontrado.");
        }

        return usuarioLogueado;
    }

    /**
     * Método que busca un usuario en el archivo correspondiente según el tipo de usuario.
     *
     * @param datosLogin Datos ingresados por el usuario (usuario y contraseña).
     * @param opcion     Tipo de usuario seleccionado.
     * @return El usuario encontrado si las credenciales son correctas, o null si no se encuentra.
     */
    private Usuario buscarUsuarioPorRol(HashMap<String, String> datosLogin, int opcion) {
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
                VistaConsola.mostrarMensaje("Opción incorrecta.");
                break;
        }
        return usuario;
    }

    /**
     * Método que busca un usuario en un archivo XML y verifica si la contraseña es correcta.
     *
     * @param datosLogin Datos ingresados por el usuario (usuario y contraseña).
     * @param archivo    Archivo XML donde se buscará al usuario.
     * @return El usuario si las credenciales son correctas, o null si no se encuentra o la contraseña es incorrecta.
     */
    private Usuario buscarEnArchivo(HashMap<String, String> datosLogin, String archivo) {
        // Se carga la lista de usuarios desde el archivo XML
        listaUsuarios = ListaUsuarios.cargarDesdeXML(archivo);

        // Se busca el usuario por su nombre de usuario
        Usuario usuario = listaUsuarios.buscar(datosLogin.get("usuario"));

        // Si el usuario existe y la contraseña es correcta, se devuelve el usuario
        if (usuario != null && HashUtil.verificarPassword(datosLogin.get("password"), usuario.getPassword())) {
            return usuario;
        }

        // Si no se encuentra o la contraseña es incorrecta, se devuelve null
        return null;
    }
}
