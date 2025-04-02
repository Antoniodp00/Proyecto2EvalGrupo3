package model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una iniciativa dentro del sistema.
 * Una iniciativa tiene un nombre, una descripción y un creador.
 */
@XmlRootElement(name = "iniciativa") // Define el elemento raíz en el XML cuando se serializa.
@XmlAccessorType(XmlAccessType.FIELD) // Indica que los atributos se mapearán directamente a XML.
public class Iniciativa {

    @XmlElement(name = "nombre") // Define el nombre en el XML.
    private String nombre;

    @XmlElement(name = "descripcion") // Define la descripción en el XML.
    private String descripcion;

    @XmlElement(name = "creador") // Define el creador de la iniciativa en el XML.
    private String creador;

    /**
     * Constructor vacío requerido por JAXB para la serialización y deserialización XML.
     */
    public Iniciativa() {}

    /**
     * Constructor con parámetros para crear una iniciativa con datos iniciales.
     * @param nombre Nombre de la iniciativa.
     * @param descripcion Descripción de la iniciativa.
     * @param creador Nombre del creador de la iniciativa.
     */
    public Iniciativa(String nombre, String descripcion, String creador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;

    }

    // Métodos Getters y Setters para acceder y modificar los atributos.

    /**
     * Obtiene el nombre de la iniciativa.
     * @return Nombre de la iniciativa.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece un nuevo nombre para la iniciativa.
     * @param nombre Nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción de la iniciativa.
     * @return Descripción de la iniciativa.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece una nueva descripción para la iniciativa.
     * @param descripcion Descripción a asignar.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el nombre del creador de la iniciativa.
     * @return Nombre del creador.
     */
    public String getCreador() {
        return creador;
    }

    /**
     * Establece un nuevo creador para la iniciativa.
     * @param creador Nombre del creador.
     */
    public void setCreador(String creador) {
        this.creador = creador;
    }

}
