package model;

import view.VistaConsola;

/**
 * Clase que gestiona la sesión activa de un usuario en el sistema.
 * Implementa el patrón Singleton para asegurar que solo haya una sesión activa a la vez.
 */
public class Sesion {
    private static Sesion instancia; // Única instancia de la sesión
    private Usuario usuarioActivo;   // Usuario actualmente autenticado

    /**
     * Constructor privado para evitar instancias múltiples.
     * @param usuario Usuario que inicia sesión.
     */
    private Sesion(Usuario usuario) {
        this.usuarioActivo = usuario;
    }

    /**
     * Inicia sesión con un usuario si no hay una sesión activa.
     * @param usuario Usuario que desea iniciar sesión.
     */
    public static void iniciarSesion(Usuario usuario) {
        if (instancia == null) { // Verifica si no hay una sesión activa
            instancia = new Sesion(usuario); // Crea una nueva sesión
            VistaConsola.mostrarMensaje("✅ Bienvenido: " + usuario.getNombreUsuario());
        } else {
            VistaConsola.mostrarMensaje("⚠ Ya hay una sesión activa.");
        }
    }

    /**
     * Cierra la sesión actual si hay una activa.
     */
    public static void cerrarSesion() {
        if (instancia != null) { // Verifica si hay una sesión activa
            VistaConsola.mostrarMensaje("👋 Adiós: " + instancia.usuarioActivo.getNombreUsuario());
            instancia = null; // Elimina la instancia, cerrando la sesión
        } else {
            VistaConsola.mostrarMensaje("⚠ No hay ninguna sesión activa.");
        }
    }

    /**
     * Obtiene el usuario actualmente autenticado.
     * @return Usuario activo o null si no hay sesión activa.
     */
    public static Usuario getUsuarioActivo() {
        if (instancia != null) {
            return instancia.usuarioActivo;
        } else {
            return null;
        }
    }

    /**
     * Verifica si hay una sesión activa en el sistema.
     * @return true si hay una sesión activa, false en caso contrario.
     */
    public static boolean haySesionActiva() {
        if (instancia != null) {
            return true;
        } else {
            return false;
        }
    }
}
