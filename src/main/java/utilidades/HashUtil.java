package utilidades;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {
    // 🔹 Generar hash con BCrypt
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12)); // 12 es el costo (seguridad vs rendimiento)
    }

    // 🔹 Verificar contraseña ingresada contra el hash guardado
    public static boolean verificarPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
