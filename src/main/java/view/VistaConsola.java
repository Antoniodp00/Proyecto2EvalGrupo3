package view;



public class VistaConsola {
    /**
     * Muestra un mensaje en la consola con formato destacado.
     *
     * @param mensaje El mensaje que se imprimirá en la consola.
     */
    public static void mostrarMensaje(String mensaje) {
        final String BLUE = "\u001B[34m";
        final String RESET = "\u001B[0m";

        System.out.println(BLUE + "ℹ️  " + mensaje + RESET);
    }
}
