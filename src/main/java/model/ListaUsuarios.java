package model;

import utilidades.SCRUD;
import utilidades.XMLManager;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "usuarios")
public class ListaUsuarios implements SCRUD<Usuario> {
    private Set<Usuario> usuarios = new HashSet<>();

    @XmlElement(name = "usuario")
    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public boolean agregar(Usuario elemento) {
        return usuarios.add(elemento);
    }

    @Override
    public boolean eliminar(Usuario elemento) {
        return usuarios.remove(elemento);
    }

    @Override
    public Usuario buscar(String nombreUsuario) {
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(nombreUsuario)) {
                usuario = u;
            }
        }
        return usuario;
    }

    public boolean guardarXML(String archivo) {
        return XMLManager.writeXML(this, archivo);
    }


    public static ListaUsuarios cargarDesdeXML(String archivo) {
        ListaUsuarios lista = XMLManager.readXML(new ListaUsuarios(), archivo);

        if (lista == null) {
            lista = new ListaUsuarios(); // Si no hay datos, devuelve una lista vacía
        }

        return lista;
    }
}


