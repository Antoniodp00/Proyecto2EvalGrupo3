package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class UsuariosLista {
    private List<Usuario> usuarios;

    public UsuariosLista() {} //Constructor vacío requerido por JAXB

    @XmlElement(name = "usuario")  //Indica que cada elemento en XML será un <usuario>
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
