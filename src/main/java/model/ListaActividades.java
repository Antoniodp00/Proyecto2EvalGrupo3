package model;

import utilidades.SCRUD;
import utilidades.XMLManager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

@XmlRootElement(name = "actividades")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaActividades implements SCRUD<Actividad> {

    @XmlElement(name = "actividad")
    private Set<Actividad> actividades;

    public ListaActividades() {
        this.actividades = new HashSet<>();
    }

    public Set<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(Set<Actividad> actividades) {
        this.actividades = actividades;
    }

    public void agregarActividad(Actividad actividad) {
        actividades.add(actividad);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Lista de Actividades:\n");
        for (Actividad a : actividades) {
            sb.append(a).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean agregar(Actividad elemento) {
        return actividades.add(elemento);
    }

    @Override
    public boolean eliminar(Actividad elemento) {
       return actividades.remove(elemento);
    }
    @Override
    public Actividad buscar(String nombreActividad) {
        Actividad actividad = null;
        for (Actividad a : actividades) {
            if (a.getNombre().equals(nombreActividad)) {
                actividad = a;
            }
        }
        return actividad;
    }


    public boolean existeActividad(String nombreActividad, String iniciativa) {
       boolean existe = false;
        for (Actividad a : actividades) {
            if (a.getNombre().equals(nombreActividad)) {
               existe = true;
            }
        }
        return existe;
    }


    public List<Actividad> obtenerActividadesPorVoluntario(UsuarioVoluntario voluntario,ListaActividades actividades) {
        List<Actividad> actividadesVoluntario = new ArrayList<>();

        for (Actividad actividad : actividades.getActividades()) {
            if (actividad.getResponsable().contains(voluntario.getNombreUsuario())) {
                actividadesVoluntario.add(actividad);
            }
        }

        return actividadesVoluntario;
    }


    public boolean guardarXML(String archivo) {
        return XMLManager.writeXML(this, archivo);
    }


    public static ListaActividades cargarDesdeXML(String archivo) {
        ListaActividades lista = XMLManager.readXML(new ListaActividades(), archivo);

        if (lista == null) {
            lista = new ListaActividades(); // Si no hay datos, devuelve una lista vacía
        }

        return lista;
    }
}
