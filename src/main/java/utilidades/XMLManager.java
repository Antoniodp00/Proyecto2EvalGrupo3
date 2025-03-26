package utilidades;

import model.UsuariosWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

public class XMLManager {

    // Método para escribir cualquier objeto en XML
    public static <T> boolean writeXML(T objeto, String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(objeto.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(objeto, new File(fileName));
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método genérico para leer cualquier XML
    public static <T> T readXML(String archivo, Class<T> clazz) {
        File file = new File(archivo);

        // Si el archivo no existe o está vacío, lo creamos o devolvemos un objeto vacío
        if (!file.exists() || file.length() == 0) {
            try {
                file.createNewFile();  // Crea el archivo vacío
                System.out.println("Archivo " + archivo + " creado.");

                // Crear un objeto vacío dependiendo de la clase
                T obj = clazz.getDeclaredConstructor().newInstance();
                return obj; // Retorna un objeto vacío de la clase proporcionada
            } catch (IOException | ReflectiveOperationException e) {
                throw new RuntimeException("Error al crear el archivo o el objeto vacío: " + archivo, e);
            }
        }

        // Si el archivo tiene contenido, podemos leerlo
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException("Error al leer el XML: " + archivo, e);
        }
    }
}

