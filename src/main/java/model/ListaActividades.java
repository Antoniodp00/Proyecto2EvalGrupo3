package model;

import utilidades.SCRUD;
import utilidades.XMLManager;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "Actividades")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaActividades implements SCRUD<Actividad> {

    @XmlElement(name = "actividad")
    private Set<Actividad> actividades;

    /**
     * Constructor por defecto que inicializa la lista de actividades como un HashSet.
     */
    public ListaActividades() {
        this.actividades = new HashSet<>();
    }

    /**
     * Obtiene el conjunto de actividades almacenadas.
     * @return Conjunto de actividades.
     */
    public Set<Actividad> getActividades() {
        return actividades;
    }

    /**
     * Establece un nuevo conjunto de actividades.
     * @param actividades Conjunto de actividades a establecer.
     */
    public void setActividades(Set<Actividad> actividades) {
        this.actividades = actividades;
    }

    /**
     * Agrega una nueva actividad al conjunto.
     * @param actividad Actividad a agregar.
     */
    public void agregarActividad(Actividad actividad) {
        actividades.add(actividad);
    }

    /**
     * Devuelve una representación en cadena de la lista de actividades.
     * @return Cadena con la información de todas las actividades.
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
     * Agrega un elemento al conjunto de actividades.
     * @param elemento Actividad a agregar.
     * @return true si la actividad se agrega con éxito, false si ya existía.
     */
    @Override
    public boolean agregar(Actividad elemento) {
        return actividades.add(elemento);
    }

    /**
     * Elimina una actividad del conjunto.
     * @param elemento Actividad a eliminar.
     * @return true si la actividad se eliminó correctamente, false si no existía.
     */
    @Override
    public boolean eliminar(Actividad elemento) {
        return actividades.remove(elemento);
    }

    /**
     * Busca una actividad por su nombre dentro del conjunto.
     * @param nombreActividad Nombre de la actividad a buscar.
     * @return La actividad encontrada o null si no existe.
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
     * Verifica si existe una actividad con un nombre específico en la lista.
     * @param nombreActividad Nombre de la actividad a verificar.
     * @param iniciativa Nombre de la iniciativa asociada (no se usa en la lógica actual).
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
     * @param archivo Nombre del archivo donde se guardará la lista.
     * @return true si la operación fue exitosa, false en caso de error.
     */
    public boolean guardarXML(String archivo) {
        return XMLManager.writeXML(this, archivo);
    }

    /**
     * Carga una lista de actividades desde un archivo XML.
     * @param archivo Nombre del archivo XML a cargar.
     * @return Una instancia de ListaActividades con los datos cargados.
     */
    public static ListaActividades cargarDesdeXML(String archivo) {
        ListaActividades lista = XMLManager.readXML(new ListaActividades(), archivo);

        if (lista == null) {
            lista = new ListaActividades(); // Si no hay datos, devuelve una lista vacía
        }

        return lista;
    }
}
