package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.controller.response.ListarPublicacaoResponse;
import br.com.cwi.crescer.tcc.mapper.ListarPublicacaoMapper;
import br.com.cwi.crescer.tcc.repository.PublicacaoRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarPublicacaoService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    public Page<ListarPublicacaoResponse> listar(Pageable pageable) {
        Usuario usuario = usuarioAutenticadoService.get();

        return publicacaoRepository.findByPaginaInicial(usuario, pageable)
                .map(ListarPublicacaoMapper::toResponse);
    }
}
