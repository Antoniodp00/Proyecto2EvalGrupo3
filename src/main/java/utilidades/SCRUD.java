package utilidades;

import java.util.List;
import java.util.Optional;

public interface SCRUD<T> {
    boolean agregar(T elemento);
    boolean eliminar(T elemento);
    T buscar(T elemento);
    List<T> listar();
}
