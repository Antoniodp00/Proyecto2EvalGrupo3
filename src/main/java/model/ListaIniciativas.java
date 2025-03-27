package model;

import utilidades.SCRUD;
import utilidades.XMLManager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
    public Iniciativa buscar(String nombreIniciativa) {
        Iniciativa iniciativa = null;
        for (Iniciativa i : iniciativas) {
            if (i.getNombre().equals(nombreIniciativa)) {
                iniciativa = i;
            }
        }
        return iniciativa;
    }

    public boolean existeIniciativa(String nombreActividad) {
        boolean existe = false;
        for (Iniciativa i : iniciativas) {
            if (i.getNombre().equals(nombreActividad)) {
                existe = true;
            }
        }
        return existe;
    }

    public boolean guardarXML(String archivo) {
        return XMLManager.writeXML(this, archivo);
    }


    public static ListaIniciativas cargarDesdeXML(String archivo) {
        ListaIniciativas lista = XMLManager.readXML(new ListaIniciativas(), archivo);

        if (lista == null) {
            lista = new ListaIniciativas(); // Si no hay datos, devuelve una lista vacía
        }

        return lista;
    }

}