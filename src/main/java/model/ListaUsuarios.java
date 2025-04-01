package model;

import utilidades.SCRUD;
import utilidades.XMLManager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase que gestiona una lista de usuarios dentro del sistema.
 * Implementa la interfaz SCRUD para realizar operaciones básicas.
 * Utiliza JAXB para la serialización/deserialización en XML.
 */
@XmlRootElement(name = "usuarios") // Define el nombre del nodo raíz en XML
public class ListaUsuarios implements SCRUD<Usuario> {

    private Set<Usuario> usuarios = new HashSet<>(); // Conjunto para almacenar los usuarios

    /**
     * Obtiene el conjunto de usuarios.
     * @return Conjunto de usuarios almacenados.
     */
    @XmlElement(name = "usuario") // Cada usuario se guardará como un nodo <usuario> dentro de <usuarios>
    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Establece un nuevo conjunto de usuarios.
     * @param usuarios Conjunto de usuarios a establecer.
     */
    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * Agrega un usuario a la lista.
     * @param elemento Usuario a agregar.
     * @return `true` si el usuario se agregó correctamente, `false` si ya existía.
     */
    @Override
    public boolean agregar(Usuario elemento) {
        return usuarios.add(elemento);
    }

    /**
     * Elimina un usuario de la lista.
     * @param elemento Usuario a eliminar.
     * @return `true` si el usuario se eliminó, `false` si no existía.
     */
    @Override
    public boolean eliminar(Usuario elemento) {
        return usuarios.remove(elemento);
    }

    /**
     * Busca un usuario por su nombre de usuario.
     * @param nombreUsuario Nombre de usuario a buscar.
     * @return Usuario encontrado o `null` si no existe.
     */
    @Override
    public Usuario buscar(String nombreUsuario) {
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(nombreUsuario)) {
                usuario = u; // Se asigna el usuario si coincide el nombre
            }
        }
        return usuario;
    }

    /**
     * Devuelve todos los usuarios almacenados en la lista.
     * @return Un conjunto con los usuarios registrados.
     */
    @Override
    public Set<Usuario> listar() {
        return new HashSet<>(usuarios); // Devuelve una copia del conjunto de usuarios
    }

    /**
     * Guarda la lista de usuarios en un archivo XML.
     * @param archivo Nombre del archivo donde se guardará.
     * @return `true` si la operación fue exitosa, `false` en caso contrario.
     */
    public boolean guardarXML(String archivo) {
        return XMLManager.writeXML(this, archivo);
    }

    /**
     * Carga la lista de usuarios desde un archivo XML.
     * @param archivo Nombre del archivo de donde se leerá la información.
     * @return Objeto `ListaUsuarios` con los datos cargados, o una lista vacía si no existe.
     */
    public static ListaUsuarios cargarDesdeXML(String archivo) {
        ListaUsuarios lista = XMLManager.readXML(new ListaUsuarios(), archivo);

        // Si no se pudo cargar, se devuelve una lista vacía
        if (lista == null) {
            lista = new ListaUsuarios();
        }

        return lista;
    }
}


