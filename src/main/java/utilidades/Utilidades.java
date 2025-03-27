package utilidades;

import java.util.Scanner;

public class Utilidades {
    static final String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    public static boolean validarEmail(String email) {
        return email.matches(emailRegex);
    }
    public static int leeEntero(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print(mensaje);
                numero = Integer.parseInt(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número entero válido.");
            }
        }
        return numero;
    }
}
