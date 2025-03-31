package controller;

import model.*;
import utilidades.Utilidades;
import view.Menus;
import java.util.Scanner;

public class SesionController {
    private UsuarioController controladorUsuario;

    // Método principal para manejar la sesión
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

    // Menú para el Creador
    private void mostrarMenuCreador(UsuarioCreador creador) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            opcion = Menus.MenuCreador();
            switch (opcion) {
                case 1:
                    mostrarMenuInstancias(creador);
                    break;
                case 2:
                    mostrarMenuActividades(creador);
                    break;
                case 3:
                    cerrarSesion();
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 3);
    }

    // Menú de opciones de instancias (crear, listar, actualizar, eliminar)
    private void mostrarMenuInstancias(UsuarioCreador creador) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean salir = false;
        do {
            opcion = Menus.MenuInstancias();  // Menú de opciones de instancias
            switch (opcion) {
                case 1:  // Opción para crear una nueva iniciativa
                    CreadorController.crearIniciativa(creador);
                    break;
                case 2:
                    CreadorController.mostrarIniciativas(creador);
                    break;
                case 3:
                    CreadorController.actualizarIniciativa(creador);  // Lógica para actualizar la iniciativa
                    break;
                case 4:
                    CreadorController.eliminarIniciativa(creador);  // Lógica para eliminar la iniciativa
                    break;
                case 5:
                    mostrarMenuCreador(creador);
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 5);
    }


    // Menú de opciones de actividades (registrar, leer, actualizar, eliminar, agregar voluntarios)
    private void mostrarMenuActividades(UsuarioCreador creador) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            opcion = Menus.MenuActividades();
            switch (opcion) {
                case 1:
                    ActividadesController.registrarActividad();
                    break;
                case 2:
                    ActividadesController.listarActividades(creador);  // Leer actividades
                    break;
                case 3:
                    ActividadesController.actualizarActividad(creador);  // Lógica para actualizar una actividad
                    break;
                case 4:
                    ActividadesController.eliminarActividad(creador);  // Lógica para eliminar una actividad
                    break;
                case 5:
                    ActividadesController.agregarVoluntarioActividad(creador);
                    break;
                case 6:
                    mostrarMenuCreador(creador);
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 6);
    }




    // Menú para el Voluntario
    private void mostrarMenuVoluntario(UsuarioVoluntario voluntario) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            opcion = Menus.MenuVoluntario();
            switch (opcion) {
                case 1:
                    ActividadesController.listarActividades();  // Listar actividades disponibles para el voluntario
                    break;
                case 2:
                    ActividadesController.listarMisActividades(voluntario);  // Listar las actividades del voluntario
                    break;
                case 3:
                    // Actualizar alguna actividad asignada (a implementar según sea necesario)
                    break;
                case 4:
                    // Eliminar alguna actividad asignada (a implementar según sea necesario)
                    break;
                case 5:
                    PremiosController.listarPremios();
                    break;
                case 6:
                    String premio = Utilidades.leeString("Introduce el nombre del premio que quieres canjear: ");
                    PremiosController.canjearPremio(voluntario, premio);
                    break;
                case 7:
                    cerrarSesion();
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 7);
    }

    // Menú para el Administrador
    private void mostrarMenuAdministrador(UsuarioAdministrador administrador) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            opcion = Menus.MenuAdministrador();
            switch (opcion) {
                case 1:
                    mostrarMenuUsuarios();
                    break;
                case 2:
                    PremiosController.agregarPremio();
                    break;
                case 3:
                    PremiosController.eliminarPremio();  // Eliminar premio
                    break;
                case 4:
                    cerrarSesion();
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 4);
    }

    // Menú para administrar usuarios (registrar, listar, actualizar, eliminar)
    private void mostrarMenuUsuarios() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            opcion = Menus.MenuAdministrador();
            switch (opcion) {
                case 1:
                    UsuarioController.registrarUsuario();  // Registrar un nuevo usuario
                    break;
                case 2:
                    UsuarioController.listarUsuarios();  // Listar todos los usuarios
                    break;
                case 3:
                    UsuarioController.actualizarUsuario();  // Lógica para actualizar un usuario
                    break;
                case 4:
                    UsuarioController.eliminarUsuario();  // Eliminar usuario
                    break;
                case 5:
                    cerrarSesion();  // Cerrar sesión
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 5);
    }




    // Método para cerrar sesión
    public static void cerrarSesion() {
        Sesion.cerrarSesion();  // Llamamos al método estático para cerrar la sesión
    }
}
