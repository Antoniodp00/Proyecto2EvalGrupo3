package view;

import utilidades.Utilidades;

public class Menus {
    public static int menuIniciarSesion() {
        System.out.println("===== INICIO DE SESIÓN =====");
        System.out.println("1. Usuario Creador ");
        System.out.println("2. Usuario Voluntario ");
        System.out.println("3. Usuario Administrador ");
        int opcion = Utilidades.leeEntero("Elige Opcion: ");
        return opcion;

    }
    public static int menuSelectTipoUsuarioRegistro() {
        System.out.println("===== REGISTRO =====");
        System.out.println("Seleccione un tipo de usuario para registrar: ");
        System.out.println("1. Usuario Creador ");
        System.out.println("2. Usuario Voluntario ");
        System.out.println("3. Usuario Administrador ");
        int opcion = Utilidades.leeEntero("Elige Opcion: ");
        return opcion;
    }
    public static void MenuCreador() {
        System.out.println("===== MENÚ PRINCIPAL =====");
        System.out.println("1. Configuración de Instancias");
        System.out.println("2. Configuración de Actividades");
        System.out.println("4. Cerrar sesión");
        System.out.println("5. Salir");
    }
    public static void MenuVoluntario() {
        System.out.println("===== MENÚ PRINCIPAL =====");
        System.out.println("1. Listado de Actividades");
        System.out.println("2. Listado de Mis Actividades");
        System.out.println("4. Consultar Mis Puntos");
        System.out.println("5. Canjear Puntos");
        System.out.println("6. Cerrar sesión");
        System.out.println("7. Salir");
    }
    public static void MenuAdministrador() {
        System.out.println("===== MENÚ PRINCIPAL =====");
        System.out.println("1. Configuración de Usuarios");
        System.out.println("6. Cerrar sesión");
        System.out.println("7. Salir");
    }
    public static void MenuInstancias() {
        System.out.println("=====  MENÚ DE INSTANCIAS =====");
        System.out.println("1. Crear Instancia");
        System.out.println("2. Leer Instancias");
        System.out.println("3. Actualizar Instancia");
        System.out.println("4. Eliminar Instancia");
        System.out.println("5. Volver al menú principal");

    }
    public static void MenuActividades() {
        System.out.println("===== MENÚ DE ACTIVIDADES =====");
        System.out.println("1. Crear Actividad");
        System.out.println("2. Leer Actividades");
        System.out.println("3. Actualizar Actividad");
        System.out.println("4. Eliminar Actividad");
        System.out.println("5. Asignar voluntarío");
        System.out.println("6. Volver al menú principal");

    }
    public static void MenuUsuario() {
        System.out.println("===== MENÚ DE USUARIOS =====");
        System.out.println("1. Crear Usuario");
        System.out.println("2. Leer Usuarios");
        System.out.println("3. Actualizar Usuario");
        System.out.println("4. Eliminar Usuario");
        System.out.println("5. Volver al menú principal");

    }
}
