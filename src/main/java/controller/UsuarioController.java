package controller;

import model.Sesion;
import model.Usuario;
import model.ListaUsuarios;
import utilidades.PersistenciaXML;
import utilidades.Utilidades;
import view.VistaConsola;
import view.VistaConsolaLogin;
import view.VistaConsolaRegistro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class UsuarioController {

    public static boolean registrarUsuario() {
        boolean registrado = false;

        ListaUsuarios listaUsuarios = PersistenciaXML.cargar("usuarios.xml", ListaUsuarios.class);
        if (listaUsuarios == null) {
            listaUsuarios = new ListaUsuarios(); // 🔹 Si está vacío, se inicializa
        }

        Usuario usuario = VistaConsolaRegistro.solicitarDatosRegistro();

        if (listaUsuarios.agregarUsuario(usuario)) {
            PersistenciaXML.guardar(listaUsuarios, "usuarios.xml");
            VistaConsola.mostrarMensaje("✅ Registro guardado con éxito.");
            registrado = true;
        } else {
            VistaConsola.mostrarMensaje("❌ El usuario ya existe.");
        }

        return registrado;
    }



    public static Usuario iniciarSesion() {
        Usuario usuarioLogueado = null;

        if (Sesion.haySesionActiva()) {
            VistaConsola.mostrarMensaje("❌ Ya hay una sesión activa. Cierra sesión primero.");
        } else {
            ListaUsuarios listaUsuarios = PersistenciaXML.cargar("usuarios.xml", ListaUsuarios.class);

            if (listaUsuarios != null && !listaUsuarios.getUsuarios().isEmpty()) {
                HashMap<String, String> datosLogin = VistaConsolaLogin.solicitarDatosLogin();
                usuarioLogueado = validarCredenciales(datosLogin, listaUsuarios.getUsuarios());

                if (usuarioLogueado != null) {
                    Sesion.iniciarSesion(usuarioLogueado);
                    VistaConsola.mostrarMensaje("✅ Sesión iniciada para " + usuarioLogueado.getNombreUsuario());
                } else {
                    VistaConsola.mostrarMensaje("❌ Usuario o contraseña incorrectos.");
                }
            }
        }

        return usuarioLogueado;
    }


    private static Usuario validarCredenciales(HashMap<String, String> datosLogin, Set<Usuario> usuarios) {
        String usuarioIngresado = datosLogin.get("usuario");
        String passwordIngresada = Utilidades.cifrarSHA256(datosLogin.get("password"));
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(usuarioIngresado) && u.getPassword().equals(passwordIngresada)) {
               usuario = u;
            }
        }
        return usuario;
    }
}
