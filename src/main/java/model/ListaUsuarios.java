package model;

import utilidades.SCRUD;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

@XmlRootElement(name = "usuarios")
public class ListaUsuarios implements SCRUD<Usuario>, Serializable {
    private Set<Usuario> usuarios = new HashSet<>();

    @XmlElementWrapper(name = "listaUsuarios") //JAXB serializa correctamente el conjunto
    @XmlElement(name = "usuario")
    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean agregarUsuario(Usuario usuario) {
        return usuarios.add(usuario); //Retorna `false` si el usuario ya existe
    }

    public boolean estaVacio() {
        return usuarios.isEmpty();
    }

    @Override
    public boolean agregar(Usuario usuario) {
        return usuarios.add(usuario);
    }

    @Override
    public boolean eliminar(Usuario usuario) {
        return usuarios.remove(usuario);
    }

    @Override
    public Usuario buscar(Usuario usuario) {
        return usuarios.contains(usuario) ? usuario : null; //Más eficiente con `contains()`
    }

    @Override
    public List<Usuario> listar() {
        return new ArrayList<>(usuarios);
    }
}
