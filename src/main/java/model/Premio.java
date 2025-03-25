package model;

public class Premio {
    private String nombre;
    private String descripcion;
    private int costo;

    public Premio(String nombre, int costo, String descripcion) {
        this.nombre = nombre;
        this.costo = costo;
        this.descripcion = descripcion;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Premio{" +
                "nombre='" + nombre + '\'' +
                ", descripcioon='" + descripcion + '\'' +
                ", costo=" + costo +
                '}';
    }
}
