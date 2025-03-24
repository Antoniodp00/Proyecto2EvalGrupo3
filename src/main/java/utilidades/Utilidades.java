package utilidades;

import model.Usuario;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

public class Utilidades {
    static final String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    public static boolean validarEmail(String email) {
        return email.matches(emailRegex);
    }

    public static String cifrarSHA256(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al cifrar la contraseña", e);
        }
    }

    public static boolean existeUsuarioRegistro(Usuario usuario) {
        boolean existe = false;
        List<Usuario> usuarios = PersistenciaXML.cargarUsuarios();
        for (Usuario usuarioAux : usuarios) {
            if (usuarioAux.getNombreUsuario().equals(usuario.getNombreUsuario()) || usuarioAux.getEmail().equals(usuario.getEmail())) {
                existe = true;
            }
        }
        return existe;
    }

    public static boolean existeUsuarioLogin(HashMap<String, String> usuario) {
        boolean existe = false;
        List<Usuario> usuarios = PersistenciaXML.cargarUsuarios();
        String usuarioIngresado = usuario.get("usuario");
        String passwordIngresado = Utilidades.cifrarSHA256(usuario.get("password"));

        for (Usuario usuarioAux : usuarios) {
            if (usuarioAux.getNombreUsuario().equals(usuarioIngresado) && usuarioAux.getPassword().equals(passwordIngresado)) {
                existe = true;
            }
        }
        return existe;
    }

}
