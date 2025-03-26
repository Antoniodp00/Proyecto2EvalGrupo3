package model;

import utilidades.SCRUD;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "Iniciativas")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaIniciativas implements SCRUD<Iniciativa> {

    @XmlElement(name = "iniciativa",type = Iniciativa.class)
    private Set<Iniciativa> iniciativas = new HashSet<>();

    public ListaIniciativas() {}

    public Set<Iniciativa> getIniciativas() {
        return iniciativas;
    }
    public void setIniciativas(Set<Iniciativa> personas) {
        this.iniciativas = personas;
    }

    public boolean addPersona(Iniciativa persona) {
        return iniciativas.add(persona);
    }

    public String toString() {
        return iniciativas.toString();
    }

    @Override
    public boolean agregar(Iniciativa elemento) {
        return iniciativas.add(elemento);
    }

    @Override
    public boolean eliminar(Iniciativa elemento) {

        return iniciativas.remove(elemento);
    }

    @Override
    public Iniciativa buscar(Iniciativa elemento) {
        Iniciativa aux = null;
        if (iniciativas.contains(elemento)) {
            aux = elemento;
        }
        return aux;
    }

}