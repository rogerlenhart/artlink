package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.domain.Curtida;
import br.com.cwi.crescer.tcc.domain.Publicacao;
import br.com.cwi.crescer.tcc.repository.CurtidaRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.tcc.service.core.BuscarPublicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class CurtirPublicacaoService {

    @Autowired
    private BuscarPublicacaoService buscarPublicacaoService;

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public void curtir(Long id) {
        Usuario usuario = usuarioAutenticadoService.get();

        Publicacao publicacao = buscarPublicacaoService.porId(id);

        if(curtidaRepository.existsByPublicacaoAndUsuario(publicacao, usuario)) {
            throw new ResponseStatusException(BAD_REQUEST, "Você já curtiu esta publicação.");
        };

        Curtida curtida = new Curtida();
        curtida.setUsuario(usuario);
        curtida.setPublicacao(publicacao);

        curtidaRepository.save(curtida);
    }
}
