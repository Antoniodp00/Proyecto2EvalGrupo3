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
 * Clase que representa una lista de iniciativas.
 * Implementa la interfaz SCRUD para realizar operaciones CRUD.
 * Utiliza JAXB para la serialización y deserialización en XML.
 */
@XmlRootElement(name = "Iniciativas")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaIniciativas implements SCRUD<Iniciativa> {

    @XmlElement(name = "iniciativa", type = Iniciativa.class)
    private Set<Iniciativa> iniciativas = new HashSet<>();

    /**
     * Constructor por defecto que inicializa la lista de iniciativas como un HashSet vacío.
     */
    public ListaIniciativas() {}

    /**
     * Obtiene el conjunto de iniciativas almacenadas.
     * @return Conjunto de iniciativas.
     */
    public Set<Iniciativa> getIniciativas() {
        return iniciativas;
    }

    /**
     * Establece un nuevo conjunto de iniciativas.
     * @param iniciativas Conjunto de iniciativas a establecer.
     */
    public void setIniciativas(Set<Iniciativa> iniciativas) {
        this.iniciativas = iniciativas;
    }

    /**
     * Agrega una iniciativa a la lista.
     * @param elemento Iniciativa a agregar.
     * @return `true` si la iniciativa se agregó correctamente, `false` si ya existía.
     */
    @Override
    public boolean agregar(Iniciativa elemento) {
        return iniciativas.add(elemento);
    }

    /**
     * Elimina una iniciativa de la lista.
     * @param elemento Iniciativa a eliminar.
     * @return `true` si la iniciativa se eliminó correctamente, `false` si no existía.
     */
    @Override
    public boolean eliminar(Iniciativa elemento) {
        return iniciativas.remove(elemento);
    }

    /**
     * Busca una iniciativa por su nombre.
     * @param nombreIniciativa Nombre de la iniciativa a buscar.
     * @return Iniciativa encontrada o `null` si no existe.
     */
    @Override
    public Iniciativa buscar(String nombreIniciativa) {
        for (Iniciativa i : iniciativas) {
            if (i.getNombre().equals(nombreIniciativa)) {
                return i;
            }
        }
        return null;
    }


    /**
     * Devuelve todas las iniciativas almacenadas en la lista.
     * @return Un conjunto con las iniciativas registradas.
     */
    @Override
    public Set<Iniciativa> listar() {
        return new HashSet<>(iniciativas); // Devuelve una copia del conjunto
    }

    /**
     * Verifica si existe una iniciativa con el nombre dado.
     * @param nombreActividad Nombre de la iniciativa a verificar.
     * @return `true` si la iniciativa existe, `false` en caso contrario.
     */
    public boolean existeIniciativa(String nombreActividad) {
        for (Iniciativa i : iniciativas) {
            if (i.getNombre().equals(nombreActividad)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Guarda la lista de iniciativas en un archivo XML.
     * @param archivo Nombre del archivo donde se guardará.
     * @return `true` si la operación fue exitosa, `false` en caso contrario.
     */
    public boolean guardarXML(String archivo) {
        return XMLManager.writeXML(this, archivo);
    }

    /**
     * Carga la lista de iniciativas desde un archivo XML.
     * @param archivo Nombre del archivo de donde se leerá la información.
     * @return Objeto `ListaIniciativas` con los datos cargados, o una lista vacía si no existe.
     */
    public static ListaIniciativas cargarDesdeXML(String archivo) {
        ListaIniciativas lista = XMLManager.readXML(new ListaIniciativas(), archivo);

        if (lista == null) {
            lista = new ListaIniciativas(); // Si no hay datos, devuelve una lista vacía
        }

        return lista;
    }
}