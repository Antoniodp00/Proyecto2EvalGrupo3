import controller.*;
import model.Iniciativa;
import model.Usuario;
import model.UsuarioCreador;
import model.UsuarioVoluntario;

public class Main {
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        ActividadesController actividadesController = new ActividadesController();
        CreadorController iniciativasController = new CreadorController();
        PremiosController premiosController = new PremiosController();

//usuarioController.registrarUsuario();
//usuarioController.iniciarSesion();

        // UsuarioCreador creador = new UsuarioCreador("Juan", "juanp", "juanp", "juanp", "juanp");
        UsuarioVoluntario usuarioVoluntario = new UsuarioVoluntario("Juan", "juanp", "juanp@gmail.com", "123456");
        //iniciativasController.crearIniciativa(creador);
        // iniciativasController.cargarIniciativasPorUsuario(creador);
        // iniciativasController.mostrarIniciativas(creador);


        //premiosController.agregarPremio();
        //premiosController.listarPremios();
        premiosController.canjearPremio(usuarioVoluntario, "Balon");

    }
}

