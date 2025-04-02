package view;

import utilidades.Utilidades;

public class Menus {
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String BOLD = "\u001B[1m";

    /**
     * Muestra el menú principal de inicio del sistema de voluntariado.
     *
     * @return La opción seleccionada por el usuario.
     */
    public static int Sesion() {
        System.out.println(BLUE + "\n══════════════════════════════════════" + RESET);
        System.out.println(GREEN + " 🌍 ¡Bienvenido al Sistema de Voluntariado! " + RESET);
        System.out.println(BLUE + "══════════════════════════════════════\n" + RESET);
        System.out.println("1. 🤝 Iniciar sesión");
        System.out.println("2. 📝 Registrarse");
        System.out.println("3. ❌ Salir");
        return Utilidades.leeEntero(YELLOW + "Elige una opción: " + RESET);
    }

    /**
     * Muestra el menú para elegir el tipo de usuario al iniciar sesión.
     *
     * @return La opción seleccionada por el usuario.
     */
    public static int menuIniciarSesion() {
        System.out.println(BLUE + "\n📌 ===== INICIO DE SESIÓN =====" + RESET);
        System.out.println("1. 👨‍🎨 Usuario Creador ");
        System.out.println("2. 🏅 Usuario Voluntario ");
        System.out.println("3. 🔧 Usuario Administrador ");
        return Utilidades.leeEntero(YELLOW + "Elige una opción: " + RESET);
    }

    /**
     * Muestra el menú para seleccionar el tipo de usuario durante el registro.
     *
     * @return La opción seleccionada por el usuario.
     */
    public static int menuSelectTipoUsuarioRegistro() {
        System.out.println(BLUE + "\n📝 ===== REGISTRO =====" + RESET);
        System.out.println("1. 👨‍🎨 Usuario Creador ");
        System.out.println("2. 🏅 Usuario Voluntario ");
        System.out.println("3. 🔧 Usuario Administrador ");
        return Utilidades.leeEntero(YELLOW + "Elige una opción: " + RESET);
    }

    /**
     * Muestra el menú de opciones para el usuario creador.
     *
     * @return La opción seleccionada por el usuario.
     */
    public static int MenuCreador() {
        System.out.println(BLUE + "\n🔧 ===== MENÚ PRINCIPAL =====" + RESET);
        System.out.println("1. ⚙️ Configuración de Instancias");
        System.out.println("2. 📌 Configuración de Actividades");
        System.out.println("3. 🚪 Cerrar sesión");
        return Utilidades.leeEntero(YELLOW + "Elige una opción: " + RESET);
    }

    /**
     * Muestra el menú de opciones para el usuario voluntario.
     *
     * @return La opción seleccionada por el usuario.
     */
    public static int MenuVoluntario() {
        System.out.println(CYAN + "\n💙 ===== MENÚ DEL VOLUNTARIO =====" + RESET);
        System.out.println("1. 📜 Listado de Actividades Disponibles");
        System.out.println("1. 📜 Listado de mis Actividades");
        System.out.println("2. ✍️ Apuntarse a una actividad");
        System.out.println("3. ✅ Finalizar una actividad");
        System.out.println("4. 🎖️ Consultar Mis Puntos");
        System.out.println("5. 🎁 Listar Premios");
        System.out.println("6. 🔄 Canjear Puntos");
        System.out.println("7. 🚪 Cerrar sesión");
        return Utilidades.leeEntero(YELLOW + "Elige una opción: " + RESET);
    }

    /**
     * Muestra el menú de opciones para el usuario administrador.
     *
     * @return La opción seleccionada por el usuario.
     */
    public static int MenuAdministrador() {
        System.out.println(RED + "\n🔧 ===== MENÚ DEL ADMINISTRADOR =====" + RESET);
        System.out.println("1. 👤 Configuración de Usuarios");
        System.out.println("2. 🎁 Agregar Premios");
        System.out.println("3. 🗑️ Eliminar Premios");
        System.out.println("4. 🚪 Cerrar sesión");
        return Utilidades.leeEntero(YELLOW + "Elige una opción: " + RESET);
    }

    /**
     * Muestra el menú de opciones para la gestión de instancias.
     *
     * @return La opción seleccionada por el usuario.
     */
    public static int MenuInstancias() {
        System.out.println(BLUE + "\n🏗️ ===== MENÚ DE INICIATIVAS =====" + RESET);
        System.out.println("1. 🏗️ Crear Iniciativa");
        System.out.println("2. 📜 Mostrar todas las Iniciativas");
        System.out.println("3. 🔄 Actualizar Iniciativa");
        System.out.println("4. ❌ Eliminar Iniciativa");
        System.out.println("5. 🔙 Volver al menú principal");
        return Utilidades.leeEntero(YELLOW + "Elige una opción: " + RESET);
    }

    /**
     * Muestra el menú de opciones para la gestión de actividades.
     *
     * @return La opción seleccionada por el usuario.
     */
    public static int MenuActividades() {
        System.out.println(BLUE + "\n📅 ===== MENÚ DE ACTIVIDADES =====" + RESET);
        System.out.println("1. 🆕 Crear Actividad");
        System.out.println("2. 📜 Leer Actividades");
        System.out.println("3. 🔄 Actualizar Actividad");
        System.out.println("4. ❌ Eliminar Actividad");
        System.out.println("5. 🤝 Asignar voluntario");
        System.out.println("6. 🔙 Volver al menú principal");
        return Utilidades.leeEntero(YELLOW + "Elige una opción: " + RESET);
    }

    /**
     * Muestra el menú de opciones para la gestión de usuarios.
     *
     * @return La opción seleccionada por el usuario.
     */
    public static int MenuUsuarios() {
        System.out.println(RED + "\n👥 ===== MENÚ DE USUARIOS =====" + RESET);
        System.out.println("1. 🔄 Actualizar Usuario");
        System.out.println("2. 🗑️ Eliminar Usuario");
        System.out.println("3. 🔙 Volver al menú principal");
        return Utilidades.leeEntero(YELLOW + "Elige una opción: " + RESET);
    }

}