import controller.ActividadesController;
import controller.CreadorController;
import controller.PremiosController;
import controller.UsuarioController;
import model.Usuario;
import model.UsuarioCreador;
import model.UsuarioVoluntario;

public class Main {
    public static void main(String[] args) {



        UsuarioVoluntario voluntario = new UsuarioVoluntario("juan","juanp","juan@gmai.com","123");


        // Intentar canjear un premio
        PremiosController.canjearPremio(voluntario, "Pala");

        // Mostrar premios nuevamente
        PremiosController.listarPremios();
    }
}

