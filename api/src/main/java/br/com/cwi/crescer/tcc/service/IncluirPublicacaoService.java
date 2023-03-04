package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.controller.request.IncluirPublicacaoRequest;
import br.com.cwi.crescer.tcc.domain.Publicacao;
import br.com.cwi.crescer.tcc.mapper.IncluirPublicacaoMapper;
import br.com.cwi.crescer.tcc.repository.PublicacaoRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.tcc.service.core.NowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncluirPublicacaoService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private NowService nowService;

    public void incluir(IncluirPublicacaoRequest request) {
        Usuario usuario = usuarioAutenticadoService.get();

        Publicacao publicacao = IncluirPublicacaoMapper.toEntity(request);

        publicacao.setAutor(usuario);
        publicacao.setDataCriacao(nowService.getDateTime());

        publicacaoRepository.save(publicacao);
    }
}
