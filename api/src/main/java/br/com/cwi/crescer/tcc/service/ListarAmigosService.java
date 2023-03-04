package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.controller.response.ListarUsuarioResponse;
import br.com.cwi.crescer.tcc.mapper.ListarUsuarioMapper;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.repository.UsuarioRepository;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarAmigosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public Page<ListarUsuarioResponse> listar(Pageable pageable, String texto) {

        Usuario usuario = usuarioAutenticadoService.get();

        return usuarioRepository.findByAmigos(usuario, pageable, texto).map(ListarUsuarioMapper::toResponse);
    }
}
