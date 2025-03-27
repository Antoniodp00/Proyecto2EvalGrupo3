package model;


import utilidades.SCRUD;
import utilidades.XMLManager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "Premios")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaPremios implements SCRUD<Premio> {

    @XmlElement(name = "premio")
    private Set<Premio> premios;

    public ListaPremios() {
        this.premios = new HashSet<>();
    }

    public Set<Premio> getPremios() {
        return premios;
    }

    public void setPremios(Set<Premio> premios) {
        this.premios = premios;
    }

    @Override
    public boolean agregar(Premio elemento) {
        return premios.add(elemento);
    }

    @Override
    public boolean eliminar(Premio elemento) {
        return premios.remove(elemento);
    }

    @Override
    public Premio buscar(String nombrePremio) {
        Premio premio = null;
        for (Premio p : premios) {
            if (p.getNombre().equals(nombrePremio)) {
                premio = p;
            }
        }
        return premio;
    }

    public boolean guardarXML(String archivo) {
        return XMLManager.writeXML(this, archivo);
    }


    public static ListaPremios cargarDesdeXML(String archivo) {
        ListaPremios lista = XMLManager.readXML(new ListaPremios(), archivo);

        if (lista == null) {
            lista = new ListaPremios(); // Si no hay datos, devuelve una lista vacía
        }

        return lista;
    }
}
