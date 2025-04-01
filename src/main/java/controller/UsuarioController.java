package controller;

import exceptions.UsuarioYaExisteException;
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
    private ListaUsuarios listaUsuarios;

    public UsuarioController() {
        this.listaUsuarios = new ListaUsuarios();
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @return true si el registro fue exitoso, false si el usuario ya existe.
     * @throws UsuarioYaExisteException si el usuario ya está registrado.
     */
    public boolean registrarUsuario() {
        int tipo = Menus.menuSelectTipoUsuarioRegistro();
        Usuario usuario = VistaConsolaRegistro.solicitarDatosRegistro(tipo);
        String archivo = determinarArchivoXML(usuario);

        listaUsuarios = ListaUsuarios.cargarDesdeXML(archivo);

        if (listaUsuarios.buscar(usuario.getNombreUsuario()) != null) {
            throw new UsuarioYaExisteException("El usuario " + usuario.getNombreUsuario() + " ya existe en " + archivo);
        }

        listaUsuarios.agregar(usuario);
        listaUsuarios.guardarXML(archivo);
        VistaConsola.mostrarMensaje("Registro guardado en " + archivo);
        return true;
    }

    /**
     * Determina el archivo XML correspondiente según el tipo de usuario
     * @param usuario el usuario a registrar
     * @return el nombre del archivo XML donde se almacenará el usuario
     */
    private String determinarArchivoXML(Usuario usuario) {
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

    /**
     * Inicia sesión con un usuario existente
     * @return el usuario logueado si la autenticación es exitosa, null si falla
     */
    public Usuario iniciarSesion() {
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

    /**
     * Busca un usuario en el archivo correspondiente según su rol
     * @param datosLogin HashMap con el nombre de usuario y la contraseña
     * @param opcion opción del menú que indica el tipo de usuario
     * @return el usuario si se encuentra, null en caso contrario
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
                VistaConsola.mostrarMensaje("Opción incorrecta");
                break;
        }
        return usuario;
    }

    /**
     * Busca un usuario en un archivo específico
     * @param datosLogin HashMap con el nombre de usuario y la contraseña
     * @param archivo nombre del archivo XML donde buscar
     * @return el usuario encontrado o null si no existe o la contraseña es incorrecta
     */
    private Usuario buscarEnArchivo(HashMap<String, String> datosLogin, String archivo) {
        listaUsuarios = ListaUsuarios.cargarDesdeXML(archivo);
        Usuario usuario = listaUsuarios.buscar(datosLogin.get("usuario"));

        if (usuario != null && HashUtil.verificarPassword(datosLogin.get("password"), usuario.getPassword())) {
            return usuario;
        }

        return null;
    }

    /**
     * Elimina un usuario del sistema
     */
    public void eliminarUsuario() {
        String nombreUsuario = Utilidades.leeString("Introduce el nombre del usuario a eliminar:");
        Usuario usuario = listaUsuarios.buscar(nombreUsuario);

        if (usuario != null) {
            listaUsuarios.eliminar(usuario);
            listaUsuarios.guardarXML(determinarArchivoXML(usuario));
            System.out.println("Usuario eliminado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    /**
     * Actualiza los datos de un usuario existente
     */
    public void actualizarUsuario() {
        String nombreUsuario = Utilidades.leeString("Introduce el nombre del usuario a actualizar:");
        Usuario usuario = listaUsuarios.buscar(nombreUsuario);

        if (usuario != null) {
            String nuevoNombre = Utilidades.leeString("Introduce el nuevo nombre del usuario:");
            String nuevaContraseña = Utilidades.leeString("Introduce la nueva contraseña:");
            usuario.setNombre(nuevoNombre);
            usuario.setPassword(nuevaContraseña);
            listaUsuarios.guardarXML(determinarArchivoXML(usuario));
            System.out.println("Usuario actualizado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}
