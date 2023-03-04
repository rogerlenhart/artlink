package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.controller.request.EditarPerfilRequest;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.repository.UsuarioRepository;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditarPerfilService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void editar(EditarPerfilRequest request) {
        Usuario usuario = usuarioAutenticadoService.get();

        usuario.setApelido(request.getApelido());
        usuario.setImagemPerfil(request.getImagemPerfil());
        usuario.setNome(request.getNome());

        usuarioRepository.save(usuario);
    }
}
