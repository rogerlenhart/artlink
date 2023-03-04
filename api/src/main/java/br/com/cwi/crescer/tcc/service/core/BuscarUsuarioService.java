package br.com.cwi.crescer.tcc.service.core;

import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BuscarUsuarioService {

    private static final String NOT_FOUND_MESSAGE = "Usuário não encontrado.";

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario porId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, NOT_FOUND_MESSAGE));
    }
}
