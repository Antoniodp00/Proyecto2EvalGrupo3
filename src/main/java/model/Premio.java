package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que representa un premio dentro del sistema.
 * Se usa JAXB para la serialización/deserialización en XML.
 */
@XmlRootElement(name = "premio")  // Define el elemento raíz en XML
@XmlAccessorType(XmlAccessType.FIELD)  // Permite que JAXB acceda a los campos directamente
public class Premio {

    @XmlElement(name = "nombre")  // Mapea el campo al elemento XML <nombre>
    private String nombre;

    @XmlElement(name = "descripcion")  // Mapea el campo al elemento XML <descripcion>
    private String descripcion;

    @XmlElement(name = "costo")  // Mapea el campo al elemento XML <costo>
    private int costo;

    /**
     * Constructor parametrizado para crear un premio con valores específicos.
     * @param nombre Nombre del premio.
     * @param costo Costo del premio en puntos.
     * @param descripcion Breve descripción del premio.
     */
    public Premio(String nombre, int costo, String descripcion) {
        this.nombre = nombre;
        this.costo = costo;
        this.descripcion = descripcion;
    }

    /**
     * Constructor vacío requerido por JAXB para la deserialización.
     */
    public Premio() {
    }

    /**
     * Obtiene el nombre del premio.
     * @return Nombre del premio.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece un nuevo nombre para el premio.
     * @param nombre Nuevo nombre del premio.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el costo en puntos del premio.
     * @return Costo en puntos del premio.
     */
    public int getCosto() {
        return costo;
    }

    /**
     * Establece un nuevo costo para el premio.
     * @param costo Nuevo costo en puntos del premio.
     */
    public void setCosto(int costo) {
        this.costo = costo;
    }

    /**
     * Obtiene la descripción del premio.
     * @return Descripción del premio.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece una nueva descripción para el premio.
     * @param descripcion Nueva descripción del premio.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Representación en cadena del objeto Premio.
     * @return Cadena con la información del premio.
     */
    @Override
    public String toString() {
        return "Premio{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", costo=" + costo +
                '}';
    }
}
