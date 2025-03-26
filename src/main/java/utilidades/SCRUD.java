package utilidades;


import java.util.Set;

public interface SCRUD<T> {
    boolean agregar(T elemento);
    boolean eliminar(T elemento);
    T buscar(T elemento);
    Set<T> listar();
}
