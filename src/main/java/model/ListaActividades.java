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
 * Clase que gestiona una lista de actividades, proporcionando métodos para agregar, eliminar, buscar y listar actividades.
 * Implementa la interfaz SCRUD para definir operaciones básicas sobre las actividades.
 */
@XmlRootElement(name = "actividades") // Define el nombre del elemento raíz en el XML.
@XmlAccessorType(XmlAccessType.FIELD) // Indica que los campos serán mapeados automáticamente a XML.
public class ListaActividades implements SCRUD<Actividad> {

    @XmlElement(name = "actividad") // Define que cada actividad será un elemento XML dentro de la lista.
    private Set<Actividad> actividades; // Conjunto de actividades almacenadas.

    /**
     * Constructor por defecto que inicializa la lista de actividades.
     */
    public ListaActividades() {
        this.actividades = new HashSet<>();
    }

    /**
     * Obtiene el conjunto de actividades.
     * @return Conjunto de actividades.
     */
    public Set<Actividad> getActividades() {
        return actividades;
    }

    /**
     * Establece una nueva colección de actividades.
     * @param actividades Conjunto de actividades a asignar.
     */
    public void setActividades(Set<Actividad> actividades) {
        this.actividades = actividades;
    }

    /**
     * Agrega una actividad al conjunto de actividades.
     * @param actividad Actividad a agregar.
     */
    public void agregarActividad(Actividad actividad) {
        actividades.add(actividad);
    }

    /**
     * Devuelve una representación en cadena de la lista de actividades.
     * @return Cadena con la lista de actividades.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Lista de Actividades:\n");
        for (Actividad a : actividades) {
            sb.append(a).append("\n");
        }
        return sb.toString();
    }

    /**
     * Agrega una actividad a la lista.
     * @param elemento Actividad a agregar.
     * @return true si la actividad se agregó correctamente, false si ya existía.
     */
    @Override
    public boolean agregar(Actividad elemento) {
        return actividades.add(elemento);
    }

    /**
     * Elimina una actividad de la lista.
     * @param elemento Actividad a eliminar.
     * @return true si la actividad fue eliminada, false si no se encontraba en la lista.
     */
    @Override
    public boolean eliminar(Actividad elemento) {
        return actividades.remove(elemento);
    }

    /**
     * Busca una actividad por su nombre.
     * @param nombreActividad Nombre de la actividad a buscar.
     * @return La actividad encontrada, o null si no existe.
     */
    @Override
    public Actividad buscar(String nombreActividad) {
        for (Actividad a : actividades) {
            if (a.getNombre().equals(nombreActividad)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Devuelve una copia del conjunto de actividades.
     * @return Un nuevo conjunto con las actividades almacenadas.
     */
    @Override
    public Set<Actividad> listar() {
        return new HashSet<>(actividades); // Devuelve una copia para evitar modificaciones directas.
    }

    /**
     * Verifica si una actividad existe en la lista por su nombre y la iniciativa a la que pertenece.
     * @param nombreActividad Nombre de la actividad a verificar.
     * @param iniciativa Nombre de la iniciativa asociada (no se usa en la verificación).
     * @return true si la actividad existe, false en caso contrario.
     */
    public boolean existeActividad(String nombreActividad, String iniciativa) {
        for (Actividad a : actividades) {
            if (a.getNombre().equals(nombreActividad)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Guarda la lista de actividades en un archivo XML.
     * @param archivo Ruta del archivo donde se guardará la lista.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean guardarXML(String archivo) {
        return XMLManager.writeXML(this, archivo);
    }

    /**
     * Carga una lista de actividades desde un archivo XML.
     * @param archivo Ruta del archivo XML a leer.
     * @return Un objeto ListaActividades con los datos cargados, o una lista vacía si no se encontró información.
     */
    public static ListaActividades cargarDesdeXML(String archivo) {
        ListaActividades lista = XMLManager.readXML(new ListaActividades(), archivo);

        if (lista == null) {
            lista = new ListaActividades(); // Si no hay datos, devuelve una lista vacía.
        }

        return lista;
    }
}
