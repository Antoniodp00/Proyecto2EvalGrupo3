package model;

import utilidades.SCRUD;
import utilidades.XMLManager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase que representa una lista de premios en el sistema.
 * Implementa la interfaz SCRUD para realizar operaciones básicas de gestión.
 * Utiliza JAXB para la serialización/deserialización en XML.
 */
@XmlRootElement(name = "Premios") // Define el nombre del nodo raíz en XML
@XmlAccessorType(XmlAccessType.FIELD) // Indica que los campos se serializan directamente
public class ListaPremios implements SCRUD<Premio> {

    @XmlElement(name = "premio") // Cada premio se guardará como un nodo <premio> dentro de <Premios>
    private Set<Premio> premios; // Conjunto de premios almacenados

    /**
     * Constructor que inicializa la lista de premios como un HashSet vacío.
     */
    public ListaPremios() {
        this.premios = new HashSet<>();
    }

    /**
     * Obtiene el conjunto de premios almacenados.
     * @return Conjunto de premios.
     */
    public Set<Premio> getPremios() {
        return premios;
    }

    /**
     * Establece un nuevo conjunto de premios.
     * @param premios Conjunto de premios a establecer.
     */
    public void setPremios(Set<Premio> premios) {
        this.premios = premios;
    }

    /**
     * Agrega un premio a la lista.
     * @param elemento Premio a agregar.
     * @return `true` si el premio se agregó correctamente, `false` si ya existía.
     */
    @Override
    public boolean agregar(Premio elemento) {
        return premios.add(elemento);
    }

    /**
     * Elimina un premio de la lista.
     * @param elemento Premio a eliminar.
     * @return `true` si el premio se eliminó correctamente, `false` si no existía.
     */
    @Override
    public boolean eliminar(Premio elemento) {
        return premios.remove(elemento);
    }

    /**
     * Busca un premio por su nombre.
     * @param nombrePremio Nombre del premio a buscar.
     * @return Premio encontrado o `null` si no existe.
     */
    @Override
    public Premio buscar(String nombrePremio) {
        for (Premio p : premios) {
            if (p.getNombre().equals(nombrePremio)) {
                return p; // Retorna el premio encontrado
            }
        }
        return null; // Si no se encuentra, devuelve null
    }

    /**
     * Devuelve todos los premios almacenados en la lista.
     * @return Un conjunto con los premios registrados.
     */
    @Override
    public Set<Premio> listar() {
        return new HashSet<>(premios); // Devuelve una copia del conjunto de premios
    }

    /**
     * Guarda la lista de premios en un archivo XML.
     * @param archivo Nombre del archivo donde se guardará.
     * @return `true` si la operación fue exitosa, `false` en caso contrario.
     */
    public boolean guardarXML(String archivo) {
        return XMLManager.writeXML(this, archivo);
    }

    /**
     * Carga la lista de premios desde un archivo XML.
     * @param archivo Nombre del archivo de donde se leerá la información.
     * @return Objeto `ListaPremios` con los datos cargados, o una lista vacía si no existe.
     */
    public static ListaPremios cargarDesdeXML(String archivo) {
        ListaPremios lista = XMLManager.readXML(new ListaPremios(), archivo);

        // Si no se pudo cargar, se devuelve una lista vacía
        if (lista == null) {
            lista = new ListaPremios();
        }

        return lista;
    }
}
