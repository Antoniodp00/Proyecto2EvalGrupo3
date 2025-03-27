package utilidades;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

public class XMLManager {
    public static <T> boolean writeXML(T objeto, String fileName) {
        boolean result = false;
        try {
            //Paso 1: Crear el contexto de JaxB para la clase que queremos serializar
            JAXBContext context = JAXBContext.newInstance(objeto.getClass());

            //Paso 2: proceso Marshalling: convertir objeto en XML
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(objeto,new File(fileName));
            result = true;

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static <T> T readXML(T objeto, String fileName) {
        File file = new File(fileName);

        // Si el archivo no existe, lo crea con un objeto vacío
        if (!file.exists()) {
            try {
                file.createNewFile(); // Crea un archivo vacío
                writeXML(objeto, fileName); // Escribe el objeto vacío
            } catch (IOException e) {
                throw new RuntimeException("Error al crear el archivo XML: " + e.getMessage(), e);
            }
        }

        T result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(objeto.getClass());
            Unmarshaller unmarshaller = context.createUnmarshaller();
            result = (T) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException("Error al leer el archivo XML: " + e.getMessage(), e);
        }

        return result;
    }
}
