package model;

import view.VistaConsola;

public class Sesion {
    private static Sesion instancia;
    private Usuario usuarioActivo;

    private Sesion(Usuario usuario) {
        this.usuarioActivo = usuario;
    }

    public static void iniciarSesion(Usuario usuario) {
        if (instancia == null) {
            instancia = new Sesion(usuario);
           VistaConsola.mostrarMensaje("Bienvenido: " + usuario.getNombreUsuario());
        } else {
            System.out.println("Ya hay una sesión activa.");
        }
    }

    public static void cerrarSesion() {
        if (instancia != null) {
            System.out.println("Adios: " + instancia.usuarioActivo.getNombreUsuario());
            instancia = null;
        } else {
            System.out.println("No hay ninguna sesión activa.");
        }
    }

    public static Usuario getUsuarioActivo() {
        return instancia != null ? instancia.usuarioActivo : null;
    }

    public static boolean haySesionActiva() {
        return instancia != null;
    }
}
