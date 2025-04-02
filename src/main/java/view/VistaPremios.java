package view;

import model.Premio;
import utilidades.Utilidades;

import java.util.Scanner;

public class VistaPremios {
    static Scanner sc = new Scanner(System.in);

    // Colores ANSI para mejorar la visualización en consola
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String BOLD = "\u001B[1m";


    /**
     * Método para solicitar al usuario los datos de un premio.
     * Se solicita el nombre, costo (en puntos) y descripción del premio.
     *
     * @return Un objeto de tipo Premio con los datos ingresados por el usuario.
     */
    public static Premio pidePremio() {
        System.out.println(BOLD + CYAN + "\n🎁 REGISTRO DE PREMIO 🎁" + RESET);
        System.out.println(YELLOW + "Por favor, ingrese los siguientes datos:" + RESET);

        System.out.print(BOLD + GREEN + "🏆 Nombre del premio: " + RESET);
        String nombre = sc.nextLine().trim();

        int costo;
        do {
            costo = Utilidades.leeEntero(BOLD + GREEN + "💰 Ingrese el costo del premio (en puntos): " + RESET);
            if (costo <= 0) {
                System.out.println(YELLOW + "⚠️ El costo debe ser mayor a 0. Inténtelo de nuevo." + RESET);
            }
        } while (costo <= 0);

        System.out.print(BOLD + GREEN + "📜 Descripción del premio: " + RESET);
        String descripcion = sc.nextLine().trim();

        System.out.println(GREEN + "\n✅ Premio registrado con éxito!" + RESET);

        return new Premio(nombre, costo, descripcion);
    }
}

