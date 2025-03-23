package view;

import model.Usuario;
import utilidades.Utilidades;

import java.util.HashMap;
import java.util.Scanner;

public class VistaConsolaLogin {
    static Scanner sc = new Scanner(System.in);

    public static HashMap<String, String> solicitarDatosLogin() {
        HashMap<String, String> datosLogin = new HashMap<>();

        VistaConsola.mostrarMensaje("Ingrese su nombre de usuario: ");
        String usuario = sc.nextLine();
        VistaConsola.mostrarMensaje("Ingrese su password: ");
        String password = sc.nextLine();

        datosLogin.put("usuario", usuario);
        datosLogin.put("password", password);

        return datosLogin;
    }



}
