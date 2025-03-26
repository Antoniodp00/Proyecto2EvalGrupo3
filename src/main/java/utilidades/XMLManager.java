package utilidades;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

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
    public static <T> T readXML(String fileName, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al leer el XML: " + e.getMessage(), e);
        }
    }
}

