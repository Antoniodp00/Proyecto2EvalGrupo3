package view;

import utilidades.Utilidades;

public class Menus {
    public static int Sesion() {
        System.out.println("👋 Bienvenido al sistema. Selecciona una opción:");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
        int opcion = Utilidades.leeEntero("Elige Opcion: ");
        return opcion;

    }
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
    public static int MenuCreador() {
        System.out.println("===== MENÚ PRINCIPAL =====");
        System.out.println("1. Configuración de Instancias");
        System.out.println("2. Configuración de Actividades");
        System.out.println("3. Cerrar sesión");
        int opcion = Utilidades.leeEntero("Elige Opcion: ");
        return opcion;
    }
    public static int MenuVoluntario() {
        System.out.println("===== MENÚ PRINCIPAL =====");
        System.out.println("1. Listado de Actividades");
        System.out.println("2. Listado de Mis Actividades");
        System.out.println("3. Apuntarse a una actividad");
        System.out.println("4. Finalizar una actividad");
        System.out.println("5. Consultar Mis Puntos");
        System.out.println("6. Listar Premios");
        System.out.println("7. Canjear Puntos");
        System.out.println("8. Cerrar sesión");
        int opcion = Utilidades.leeEntero("Elige Opcion: ");
        return opcion;
    }
    public static int MenuAdministrador() {
        System.out.println("===== MENÚ PRINCIPAL =====");
        System.out.println("1. Configuración de Usuarios");
        System.out.println("2. Agregar Premios");
        System.out.println("3. Eliminar Premios");
        System.out.println("4. Cerrar sesión");
        int opcion = Utilidades.leeEntero("Elige Opcion: ");
        return opcion;
    }
    public static int MenuInstancias() {
        System.out.println("=====  MENÚ DE INSTANCIAS =====");
        System.out.println("1. Crear Instancia");
        System.out.println("2. Mostrar todas las Instancias");
        System.out.println("3. Actualizar Instancia");
        System.out.println("4. Eliminar Instancia");
        System.out.println("5. Volver al menú principal");
        int opcion = Utilidades.leeEntero("Elige Opcion: ");
        return opcion;

    }
    public static int MenuActividades() {
        System.out.println("===== MENÚ DE ACTIVIDADES =====");
        System.out.println("1. Crear Actividad");
        System.out.println("2. Leer Actividades");
        System.out.println("3. Actualizar Actividad");
        System.out.println("4. Eliminar Actividad");
        System.out.println("5. Asignar voluntarío");
        System.out.println("6. Volver al menú principal");
        int opcion = Utilidades.leeEntero("Elige Opcion: ");
        return opcion;

    }
    public static int MenuUsuarios() {
        System.out.println("===== MENÚ DE ACTIVIDADES =====");
        System.out.println("1. Actualizar Usuario");
        System.out.println("2. Eliminar Usuario");
        System.out.println("3. Volver al menú principal");
        int opcion = Utilidades.leeEntero("Elige Opcion: ");
        return opcion;

    }

}
