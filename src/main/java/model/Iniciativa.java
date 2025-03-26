package model;



import java.util.ArrayList;
import java.util.List;

public class Iniciativa {
    private String nombre;
    private String descripcion;
    private List<Actividad> actividades;
    private UsuarioCreador creador;

    public Iniciativa(String nombre, String descripcion, UsuarioCreador creador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
        this.actividades = new ArrayList<>();
    }


    /*public void agregarColaborador(UsuarioVoluntario voluntario) {
        System.out.println("El voluntario " + voluntario.getNombre() + " ha sido agregado a la iniciativa.");
    }*/


    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public UsuarioCreador getCreador() { return creador; }
    public List<Actividad> getActividades() { return actividades; }
}
