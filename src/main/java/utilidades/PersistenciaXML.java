package utilidades;

import model.Usuario;
import model.UsuariosLista;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaXML {
    private static final String ARCHIVO_XML = "usuarios.xml";

    public static List<Usuario> cargarUsuarios() {
        try {
            File file = new File(ARCHIVO_XML);
            if (!file.exists()) return new ArrayList<>();

            JAXBContext context = JAXBContext.newInstance(UsuariosLista.class); //Usamos UsuariosLista
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UsuariosLista lista = (UsuariosLista) unmarshaller.unmarshal(file);

            return lista.getUsuarios() != null ? lista.getUsuarios() : new ArrayList<>(); //Retorna lista vacía si es null

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void guardarUsuarios(List<Usuario> usuarios) {
        try {
            JAXBContext context = JAXBContext.newInstance(UsuariosLista.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            UsuariosLista lista = new UsuariosLista();
            lista.setUsuarios(usuarios);

            marshaller.marshal(lista, new File(ARCHIVO_XML));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
