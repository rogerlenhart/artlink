package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.controller.response.ListarUsuarioResponse;
import br.com.cwi.crescer.tcc.mapper.ListarUsuarioMapper;
import br.com.cwi.crescer.tcc.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PesquisarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<ListarUsuarioResponse> pesquisar(Pageable pageable, String texto) {
        return usuarioRepository.findByEmailContainsOrNomeContainsAllIgnoreCase(texto, texto, pageable)
                .map(ListarUsuarioMapper::toResponse);
    }
}
