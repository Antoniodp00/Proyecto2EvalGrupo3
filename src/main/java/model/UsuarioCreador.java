package model;

import utilidades.SCRUD;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;


@XmlRootElement(name = "usuarioCreador")
public class UsuarioCreador extends Usuario implements SCRUD<Iniciativa> {

    private String ONG;  // Propiedad adicional para el tipo UsuarioCreador
    private Set<Iniciativa> iniciativas;

    public UsuarioCreador() {
    }

    public UsuarioCreador(String nombre, String nombreUsuario, String email, String password, String ONG) {
        super(nombre, nombreUsuario, email, password);
        this.ONG = ONG;
    }

    @XmlElement(name = "ONG")
    public String getONG() {
        return ONG;
    }

    public void setONG(String ONG) {
        this.ONG = ONG;
    }


    public Set<Iniciativa> getIniciativas() {
        return iniciativas;
    }

    public void setIniciativas(Set<Iniciativa> iniciativa) {
        this.iniciativas = iniciativas;
    }

    public boolean estaVacio(Iniciativa iniciativa) {
        return iniciativas.isEmpty();
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


