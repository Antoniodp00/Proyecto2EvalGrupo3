package controller;

import exceptions.UsuarioNoExisteException;
import exceptions.UsuarioYaExisteException;
import model.Sesion;
import model.Usuario;
import utilidades.PersistenciaXML;
import utilidades.Utilidades;
import view.VistaConsola;
import view.VistaConsolaLogin;
import view.VistaConsolaRegistro;

import java.util.HashMap;
import java.util.List;

public class UsuarioController {

    public boolean registrarUsuario() {
        boolean registrado = false;
        boolean valido = false;
        List<Usuario> usuarios = PersistenciaXML.cargarUsuarios();
        Usuario s;

        do {
            s = VistaConsolaRegistro.solicitarDatosRegistro();
            try {
                if (Utilidades.existeUsuarioRegistro(s)) {
                    throw new UsuarioYaExisteException("El usuario ya existe");
                }
                valido = true;
            } catch (UsuarioYaExisteException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        usuarios.add(s); // Se agrega el usuario a la lista

        // Guardar la lista actualizada en el XML
        PersistenciaXML.guardarUsuarios(usuarios);

        VistaConsola.mostrarMensaje("Registro guardado");

        registrado = true; // Se marca como registrado exitosamente
        return registrado;
    }

    public static Usuario iniciarSesion() {
        Usuario usuarioLogueado = null;
        HashMap<String, String> datosLogin = VistaConsolaLogin.solicitarDatosLogin();

        try {
            usuarioLogueado = validarCredenciales(datosLogin);

            if (verificarSesionActiva()) {
                usuarioLogueado = null;
            } else {
                Sesion.iniciarSesion(usuarioLogueado);
                VistaConsola.mostrarMensaje("Sesión iniciada para " + usuarioLogueado.getNombreUsuario());
            }

        } catch (UsuarioNoExisteException e) {
            System.out.println(e.getMessage());
        }

        return usuarioLogueado;
    }

    private static Usuario validarCredenciales(HashMap<String, String> datosLogin) throws UsuarioNoExisteException {
        Usuario usuario = null;
        if (!Utilidades.existeUsuarioLogin(datosLogin)) {
            throw new UsuarioNoExisteException("El usuario no existe.");
        }

        List<Usuario> usuarios = PersistenciaXML.cargarUsuarios();
        String usuarioIngresado = datosLogin.get("usuario");
        String passwordIngresada = Utilidades.cifrarSHA256(datosLogin.get("password"));

        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(usuarioIngresado) && u.getPassword().equals(passwordIngresada)) {
                usuario = u;
            }
        }
        return usuario;
    }

    private static boolean verificarSesionActiva() {
        if (Sesion.haySesionActiva()) {
            VistaConsola.mostrarMensaje("Ya hay una sesion activa. Cierra sesión primero.");
            return true;
        }
        return false;
    }

}
