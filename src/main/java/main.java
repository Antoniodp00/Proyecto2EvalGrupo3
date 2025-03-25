import controller.UsuarioController;
import model.ListaUsuarios;
import model.Usuario;
import model.UsuarioVoluntario;
import utilidades.PersistenciaXML;
import utilidades.Utilidades;

public class Main {
    public static void main(String[] args) {

        UsuarioController.registrarUsuario();
        UsuarioController.iniciarSesion();


    }
}

