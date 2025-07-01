package eldoce.com.ar.ABMexternos.service;

import eldoce.com.ar.ABMexternos.repository.UsuarioRepository;
import eldoce.com.ar.ABMexternos.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario nuevoUsuario) {
        // validaciones si es necesario
        return usuarioRepository.save(nuevoUsuario);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
}

