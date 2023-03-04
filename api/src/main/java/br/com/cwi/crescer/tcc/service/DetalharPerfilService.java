package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.controller.response.UsuarioDetalhadoResponse;
import br.com.cwi.crescer.tcc.mapper.UsuarioDetalhadoMapper;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.service.core.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalharPerfilService {

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    public UsuarioDetalhadoResponse detalhar(Long id) {
        Usuario usuario = buscarUsuarioService.porId(id);

        return UsuarioDetalhadoMapper.toResponse(usuario);
    }
}
