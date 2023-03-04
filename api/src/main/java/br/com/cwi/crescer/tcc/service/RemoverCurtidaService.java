package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.domain.Curtida;
import br.com.cwi.crescer.tcc.domain.Publicacao;
import br.com.cwi.crescer.tcc.repository.CurtidaRepository;
import br.com.cwi.crescer.tcc.repository.PublicacaoRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.tcc.service.core.BuscarPublicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class RemoverCurtidaService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private BuscarPublicacaoService buscarPublicacaoService;

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public void remover(Long id) {
        Usuario usuario = usuarioAutenticadoService.get();

        Publicacao publicacao = buscarPublicacaoService.porId(id);

        Curtida curtida = curtidaRepository.findByPublicacaoAndUsuario(publicacao, usuario)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Você não possui uma curtida neste post para remover."));

        curtidaRepository.delete(curtida);
    }
}
