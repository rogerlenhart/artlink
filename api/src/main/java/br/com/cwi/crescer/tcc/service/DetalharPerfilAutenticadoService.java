package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.controller.response.UsuarioDetalhadoResponse;
import br.com.cwi.crescer.tcc.mapper.UsuarioDetalhadoMapper;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalharPerfilAutenticadoService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public UsuarioDetalhadoResponse detalhar() {
        Usuario usuario = usuarioAutenticadoService.get();

        return UsuarioDetalhadoMapper.toResponse(usuario);
    }
}
