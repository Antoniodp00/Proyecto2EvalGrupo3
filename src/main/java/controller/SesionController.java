package controller;

import exceptions.PuntosInsuficientesException;
import exceptions.UsuarioYaExisteException;
import model.*;
import utilidades.Utilidades;
import view.Menus;
import view.VistaConsola;
import java.util.Scanner;

public class SesionController {
    private UsuarioController controladorUsuario = new UsuarioController();

    /**
     * Método principal para iniciar el sistema, mostrando un mensaje de bienvenida
     * y ofreciendo las opciones de iniciar sesión, registrarse o salir.
     */
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            opcion = Menus.Sesion();

            switch (opcion) {
                case 1:
                     manejarSesion();
                    break;
                case 2:
                    try {
                        boolean registrado = controladorUsuario.registrarUsuario();
                        if (registrado) {
                            System.out.println("Usuario registrado exitosamente.");
                        }
                    } catch (UsuarioYaExisteException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    VistaConsola.mostrarMensaje("👋 ¡Hasta luego!");
                    break;
                default:
                    VistaConsola.mostrarMensaje("❌ Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 3);
    }

    /**
     * Maneja el inicio de sesión del usuario y lo redirige al menú correspondiente
     * según el tipo de usuario que haya iniciado sesión.
     */
    public void manejarSesion() {
        Usuario usuarioActivo = controladorUsuario.iniciarSesion();
        if (usuarioActivo instanceof UsuarioCreador) {
            mostrarMenuCreador((UsuarioCreador) usuarioActivo);
        } else if (usuarioActivo instanceof UsuarioVoluntario) {
            mostrarMenuVoluntario((UsuarioVoluntario) usuarioActivo);
        } else if (usuarioActivo instanceof UsuarioAdministrador) {
            mostrarMenuAdministrador((UsuarioAdministrador) usuarioActivo);
        }
    }

    /**
     * Muestra el menú principal para usuarios creadores.
     */
    private void mostrarMenuCreador(UsuarioCreador creador) {
        int opcion;
        do {
            opcion = Menus.MenuCreador();
            switch (opcion) {
                case 1:
                    mostrarMenuIniciativas(creador);
                    break;
                case 2:
                    mostrarMenuActividades(creador);
                    break;
                case 3:
                    cerrarSesion();
                    break;
                default:
                    VistaConsola.mostrarMensaje("❌ Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 3);
    }

    /**
     * Muestra el menú para gestionar instancias (iniciativas) del usuario creador.
     */
    private void mostrarMenuIniciativas(UsuarioCreador creador) {
        int opcion;
        do {
            opcion = Menus.MenuIniciativas();
            switch (opcion) {
                case 1:
                    new CreadorController().crearIniciativa(creador);
                    break;
                case 2:
                    new CreadorController().mostrarIniciativas(creador);
                    break;
                case 3:
                    new CreadorController().actualizarIniciativa(creador);
                    break;
                case 4:
                    new CreadorController().eliminarIniciativa(creador);
                    break;
                case 5:
                    return;
                default:
                    VistaConsola.mostrarMensaje("❌ Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 5);
    }

    /**
     * Muestra el menú para gestionar actividades del usuario creador.
     */
    private void mostrarMenuActividades(UsuarioCreador creador) {
        ActividadesController actividadesController = new ActividadesController();
        int opcion;
        do {
            opcion = Menus.MenuActividades();
            switch (opcion) {
                case 1:
                    actividadesController.registrarActividad();
                    break;
                case 2:
                    actividadesController.listarActividades(creador);
                    break;
                case 3:
                    actividadesController.actualizarActividad(creador);
                    break;
                case 4:
                    actividadesController.eliminarActividad(creador);
                    break;
                case 5:
                    actividadesController.asignarVoluntario(creador);
                    break;
                case 6:
                    return;
                default:
                    VistaConsola.mostrarMensaje("❌ Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 6);
    }

    /**
     * Muestra el menú principal para voluntarios.
     */
    private void mostrarMenuVoluntario(UsuarioVoluntario voluntario) {
        ActividadesController actividadesController = new ActividadesController();
        PremiosController premiosController = new PremiosController();
        int opcion;
        do {
            opcion = Menus.MenuVoluntario();
            switch (opcion) {
                case 1:
                    actividadesController.listarActividadesDisponibles();
                    break;
                case 2:
                    actividadesController.listarMisActividades(voluntario);
                    break;
                case 3:
                   actividadesController.asignarseActividad(voluntario);
                    break;
                case 4:
                    actividadesController.cambiarEstadoActividad(voluntario);
                    break;
                case 5:
                    VistaConsola.mostrarMensaje("Tienes "+voluntario.getPuntos()+" puntos");
                    break;
                case 6:
                    premiosController.listarPremios();
                    break;
                case 7:
                    String nombrePremio = Utilidades.leeString("Ingrese el nombre del premio a canjear: ");
                    try {
                        boolean exito = premiosController.canjearPremio(voluntario, nombrePremio);
                    } catch (PuntosInsuficientesException e) {
                        VistaConsola.mostrarMensaje(e.getMessage());
                    }
                    break;
                case 8:
                    cerrarSesion();
                    break;
                default:
                    VistaConsola.mostrarMensaje("❌ Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 8);
    }

    /**
     * Muestra el menú principal para administradores.
     */
    private void mostrarMenuAdministrador(UsuarioAdministrador administrador) {
        PremiosController premiosController = new PremiosController();
        int opcion;
        do {
            opcion = Menus.MenuAdministrador();
            switch (opcion) {
                case 1:
                    mostrarMenuUsuarios(administrador);
                    break;
                case 2:
                    premiosController.agregarPremio();
                    break;
                case 3:
                    premiosController.listarPremios();
                    break;
                case 4:
                    premiosController.eliminarPremio();
                    break;
                case 5:
                    cerrarSesion();
                    break;
                default:
                    VistaConsola.mostrarMensaje("❌ Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 4);
    }

    /**
     * Muestra el menú de usuarios para administradores.
     */
    private void mostrarMenuUsuarios(UsuarioAdministrador administrador) {
        UsuarioController usuarioController = new UsuarioController();
        int opcion;
        do {
            opcion = Menus.MenuUsuarios();
            switch (opcion) {
                case 1:
                    usuarioController.actualizarUsuario();
                    break;
                case 2:
                    usuarioController.eliminarUsuario();
                    break;
                case 3:
                    VistaConsola.mostrarMensaje("Volviendo al menú principal...");
                    break;
                default:
                    VistaConsola.mostrarMensaje("❌ Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 3);
    }


    /**
     * Cierra la sesión del usuario activo.
     */
    public void cerrarSesion() {
        Sesion.cerrarSesion();
        VistaConsola.mostrarMensaje("👋 Sesión cerrada con éxito.");
    }
}