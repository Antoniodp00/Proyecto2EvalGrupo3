package model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "premio")
@XmlAccessorType(XmlAccessType.FIELD)
public class Premio {
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElement(name = "descripcion")
    private String descripcion;
    @XmlElement(name = "costo")
    private int costo;

    public Premio(String nombre, int costo, String descripcion) {
        this.nombre = nombre;
        this.costo = costo;
        this.descripcion = descripcion;
    }

    public Premio() {
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
                ", descripcion='" + descripcion + '\'' +
                ", costo=" + costo +
                '}';
    }
}
