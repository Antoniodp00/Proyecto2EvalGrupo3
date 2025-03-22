package controller;

import exceptions.UsuarioYaExisteException;
import model.Usuario;
import utilidades.PersistenciaXML;
import utilidades.Utilidades;
import view.VistaConsolaRegistro;

import java.util.List;

public class UsuarioController {

    public boolean registrarUsuario() {
        boolean registrado = false;
        boolean valido = false;
        List<Usuario> usuarios = PersistenciaXML.cargarUsuarios();
        Usuario s;

        do {
            s = VistaConsolaRegistro.solicitarDatosRegistro();
            try {
                if (Utilidades.existeUsuario(s)) {
                    throw new UsuarioYaExisteException("El usuario ya existe");
                }
                valido = true;
            } catch (UsuarioYaExisteException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        usuarios.add(s); // Se agrega el usuario a la lista

        // Guardar la lista actualizada en el XML
        PersistenciaXML.guardarUsuarios(usuarios);

       VistaConsolaRegistro.mostrarMensaje("Registro guardado");

        registrado = true; // Se marca como registrado exitosamente
        return registrado;
    }

}
