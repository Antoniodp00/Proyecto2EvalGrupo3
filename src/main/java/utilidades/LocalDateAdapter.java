package utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adaptador para la conversión de LocalDate a String y viceversa en JAXB.
 * Permite la correcta serialización y deserialización de fechas en formato ISO.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Convierte un String en formato ISO (YYYY-MM-DD) a LocalDate.
     *
     * @param v Cadena de texto con la fecha en formato ISO.
     * @return Un objeto LocalDate o null si la cadena está vacía o es nula.
     */
    @Override
    public LocalDate unmarshal(String v) {
        if (v == null || v.isEmpty()) {
            return null;
        }
        return LocalDate.parse(v, FORMATTER);
    }

    /**
     * Convierte un LocalDate a String en formato ISO (YYYY-MM-DD).
     *
     * @param v Objeto LocalDate a convertir.
     * @return Cadena de texto con la fecha en formato ISO o null si el objeto es nulo.
     */
    @Override
    public String marshal(LocalDate v) {
        if (v == null) {
            return null;
        }
        return v.format(FORMATTER);
    }
}
