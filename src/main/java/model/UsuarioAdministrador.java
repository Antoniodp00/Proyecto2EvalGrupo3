package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usuarioAdministrador")
public class UsuarioAdministrador extends Usuario{
    public UsuarioAdministrador(String nombre, String usuario, String email, String password) {
        super(nombre, usuario, email, password);
    }
    public UsuarioAdministrador() {}


}
