package utilidades;

import java.util.Set;

/**
 * Interfaz genérica para operaciones CRUD (Create, Read, Update, Delete, Search).
 *
 * @param <T> Tipo de elementos que gestionará la implementación.
 */
public interface SCRUD<T> {

    /**
     * Agrega un nuevo elemento.
     *
     * @param elemento Elemento a agregar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    boolean agregar(T elemento);

    /**
     * Elimina un elemento existente.
     *
     * @param elemento Elemento a eliminar.
     * @return true si la eliminación fue exitosa, false si el elemento no existe.
     */
    boolean eliminar(T elemento);

    /**
     * Busca un elemento por su nombre o identificador.
     *
     * @param nombreElemento Nombre o identificador del elemento a buscar.
     * @return El elemento encontrado o null si no existe.
     */
    T buscar(String nombreElemento);

    /**
     * Obtiene todos los elementos almacenados.
     *
     * @return Un conjunto con todos los elementos.
     */
    Set<T> listar();
}

