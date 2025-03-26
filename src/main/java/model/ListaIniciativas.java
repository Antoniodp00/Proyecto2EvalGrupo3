package model;

import utilidades.SCRUD;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListaIniciativas {public class ListaUsuarios implements SCRUD<Iniciativa> {
    private Set<Iniciativa> iniciativas = new HashSet<>();


    public Set<Iniciativa> getIniciativas() {
        return iniciativas;
    }

    public void setIniciativas(Set<Iniciativa> iniciativa ) {
        this.iniciativas = iniciativas;
    }

    public boolean agregariniciativa(Iniciativa iniciativa) {
        return iniciativas.add(iniciativa); //Retorna `false` si el usuario ya existe
    }

    public boolean estaVacio(Iniciativa iniciativa) {return iniciativas.isEmpty();
    }

    @Override
    public boolean agregar(Iniciativa iniciativa) {
        return iniciativas.add(iniciativa);
    }

    @Override
    public boolean eliminar(Iniciativa iniciativa) {
        return iniciativas.remove(iniciativa);
    }

    @Override
    public Iniciativa buscar(Iniciativa iniciativa) {
        Iniciativa iniciativaAux = null;
        if (iniciativas.contains(iniciativa)) {
            iniciativaAux = iniciativa;
        }
        return iniciativaAux;
    }

    @Override
    public Set<Iniciativa> listar() {
        return new HashSet<>(iniciativas);
    }
}
}
