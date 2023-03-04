package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.controller.request.AdicionarComentarioRequest;
import br.com.cwi.crescer.tcc.domain.Comentario;
import br.com.cwi.crescer.tcc.domain.Publicacao;
import br.com.cwi.crescer.tcc.mapper.ComentarioMapper;
import br.com.cwi.crescer.tcc.repository.ComentarioRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.tcc.service.core.BuscarPublicacaoService;
import br.com.cwi.crescer.tcc.service.core.NowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarComentarioService {

    @Autowired
    private BuscarPublicacaoService buscarPublicacaoService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private NowService nowService;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public void adicionar(Long id, AdicionarComentarioRequest request) {
        Usuario usuario = usuarioAutenticadoService.get();

        Publicacao publicacao = buscarPublicacaoService.porId(id);

        Comentario comentario = ComentarioMapper.toEntity(request);

        comentario.setAutor(usuario);
        comentario.setPublicacao(publicacao);
        comentario.setDataCriacao(nowService.getDateTime());

        comentarioRepository.save(comentario);
    }
}
