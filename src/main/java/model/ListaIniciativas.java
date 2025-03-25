package model;

import utilidades.SCRUD;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListaIniciativas {public class ListaUsuarios implements SCRUD<Usuario> {
    private Set<Usuario> usuarios = new HashSet<>();


    public Set<Usuario> getIniciativas() {
        return usuarios;
    }

    public void setIniciativas(Set<Iniciativa> iniciativa ) {
        this.Iniciativa = iniciativa;
    }

    public boolean agregariniciativa(Iniciativa iniciativa) {
        return Iniciativa.add(iniciativa); //Retorna `false` si el usuario ya existe
    }

    public boolean estaVacio(Iniciativa iniciativa) {return iniciativa.isEmpty();
    }

    @Override
    public boolean agregar(Iniciativa iniciativa) {
        return iniciativa.add(iniciativa);
    }

    @Override
    public boolean eliminar(Iniciativa iniciativa) {
        return iniciativa.remove(iniciativa);
    }

    @Override
    public Usuario buscar(Iniciativa iniciativa) {
        return usuarios.contains(iniciativa) ? iniciativa : null; //Más eficiente con `contains()`
    }

    @Override
    public List<Iniciativa> listar() {
        return new ArrayList<>(Iniciativa);
    }
}
}
