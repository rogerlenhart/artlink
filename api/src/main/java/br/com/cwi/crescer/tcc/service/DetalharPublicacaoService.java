package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.controller.response.PublicacaoDetalhadaResponse;
import br.com.cwi.crescer.tcc.domain.Publicacao;
import br.com.cwi.crescer.tcc.mapper.PublicacaoDetalhadaMapper;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.tcc.service.core.BuscarPublicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalharPublicacaoService {

    @Autowired
    private BuscarPublicacaoService buscarPublicacaoService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public PublicacaoDetalhadaResponse detalhar(Long id) {
        Publicacao publicacao = buscarPublicacaoService.porId(id);

        Usuario usuario = usuarioAutenticadoService.get();

        boolean foiCurtido = publicacao.foiCurtidoPorUsuario(usuario);

        return PublicacaoDetalhadaMapper.toResponse(publicacao, foiCurtido);
    }
}
