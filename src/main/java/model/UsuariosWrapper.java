package model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "usuarios")
public class UsuariosWrapper {
    private Set<Usuario> usuarios = new HashSet<>();

    @XmlElement(name = "usuario")
    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}

