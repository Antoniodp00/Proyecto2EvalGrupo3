package model;


import utilidades.SCRUD;

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
    public Usuario buscar(Usuario elemento) {
        Usuario aux = null;
        if (usuarios.contains(elemento)) {
            aux = elemento;
        }
        return aux;
    }

}

