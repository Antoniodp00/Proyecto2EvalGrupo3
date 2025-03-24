package utilidades;

import javax.xml.bind.*;
import java.io.File;

public class PersistenciaXML {

    public static <T> boolean guardar(T objeto, String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(objeto.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            marshaller.marshal(objeto, new File(fileName));
            return true;
        } catch (JAXBException e) {
            throw new RuntimeException("Error al escribir el archivo XML: " + fileName, e);
        }
    }

    public static <T> T cargar(String fileName, Class<T> clazz) {
        File file = new File(fileName);

        if (!file.exists() || file.length() == 0) {
            try {
                T nuevoObjeto = clazz.getDeclaredConstructor().newInstance();
                guardar(nuevoObjeto, fileName);
                return nuevoObjeto;
            } catch (Exception e) {
                throw new RuntimeException("Error al crear nueva instancia de " + clazz.getName(), e);
            }
        }

        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return clazz.cast(unmarshaller.unmarshal(file));
        } catch (JAXBException e) {
            throw new RuntimeException("Error al leer el archivo XML: " + fileName, e);
        }
    }
}

